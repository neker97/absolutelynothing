package com.nothing_really;

import android.app.Notification;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import com.nothing_really.absolutelynothing.R;
import com.google.android.gms.ads.AdRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import com.google.android.gms.ads.AdView;

import static com.nothing_really.AppNotifications.CHANNEL_1_ID;




public class Settings extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    private AdView mAdView;
    private static Map<String, Timer> tagId = new HashMap<>();
    sentence sen = new sentence();
    public static final int NOTIFICATION_ID = 1;
    private static NotificationManagerCompat notificationManager;

    private TextView tvf;
    private TextView tvs;
    private TextView tvt;
    private String tagCalled = null;

    private Switch language;

    private static SharedPreferences switchPreferences;
    private SharedPreferences.Editor mEditor;

    private long delay;
    private long intervalPeriod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mAdView = findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);

        notificationManager = NotificationManagerCompat.from(this);
        switchPreferences = getSharedPreferences("switchPreferences", 0);
        mEditor = switchPreferences.edit();

        tvf = findViewById(R.id.textViewF);
        tvs = findViewById(R.id.textViewS);
        tvt = findViewById(R.id.textViewT);


        Button fbutton = findViewById(R.id.firstNotification);
        Button sbutton = findViewById(R.id.secondNotification);
        Button tbutton = findViewById(R.id.thirdNotification);

        final Switch s1 = findViewById(R.id.switch1);
        final Switch s2 = findViewById(R.id.switch2);
        final Switch s3 = findViewById(R.id.switch3);

        language = findViewById(R.id.languageSwitch);

        if (!switchPreferences.getBoolean("Created", false)) {
            mEditor.putBoolean("s1", true);
            mEditor.commit();
            mEditor.putBoolean("s2", true);
            mEditor.commit();
            mEditor.putBoolean("s3", true);
            mEditor.commit();
            mEditor.putBoolean("language", true);
            mEditor.commit();
            mEditor.putBoolean("Created", true);
            mEditor.commit();

            try {
                timeNotification("timePicker1", 8, 00);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            try {
                timeNotification("timePicker2", 12, 00);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            try {
                timeNotification("timePicker3", 17, 00);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        } else {
            tvf.setText(switchPreferences.getString("tvf", "nothing"));
            tvs.setText(switchPreferences.getString("tvs", "nothing"));
            tvt.setText(switchPreferences.getString("tvt", "nothing"));
        }


        s1.setChecked(switchPreferences.getBoolean("s1", false));
        s2.setChecked(switchPreferences.getBoolean("s2", false));
        s3.setChecked(switchPreferences.getBoolean("s3", false));
        language.setChecked(switchPreferences.getBoolean("language", false));

        if (!s1.isChecked()) {
            tvf.setVisibility(View.INVISIBLE);
        }
        if (!s2.isChecked()) {
            tvs.setVisibility(View.INVISIBLE);
        }
        if (!s3.isChecked()) {
            tvt.setVisibility(View.INVISIBLE);
        }


        s1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tvf.setVisibility(View.VISIBLE);
                } else {
                    tvf.setVisibility(View.INVISIBLE);
                    if (tagId.containsKey("timePicker1")) {
                        killTag("timePicker1", tagId.get("timePicker1"));
                        tvf.setText("nothing");
                    }
                }
                mEditor.putBoolean("s1", isChecked);
                mEditor.commit();
            }
        });

        s2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tvs.setVisibility(View.VISIBLE);
                    tvs.setText("nothing");
                } else {
                    tvs.setVisibility(View.INVISIBLE);
                    if (tagId.containsKey("timePicker2")) {
                        killTag("timePicker2", tagId.get("timePicker2"));
                    }
                }
                mEditor.putBoolean("s2", isChecked);
                mEditor.commit();
            }
        });

        s3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tvt.setVisibility(View.VISIBLE);
                    tvt.setText("nothing");
                } else {
                    tvt.setVisibility(View.INVISIBLE);
                    if (tagId.containsKey("timePicker3")) {
                        killTag("timePicker3", tagId.get("timePicker3"));
                    }
                }

                mEditor.putBoolean("s3", isChecked);
                mEditor.commit();
            }
        });


        language.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                @Override
                                                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                                    mEditor.putBoolean("language", isChecked);
                                                    mEditor.commit();
                                                }
                                            }

        );
        fbutton.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           if (s1.isChecked()) {
                                               tagCalled = "timePicker1";
                                               DialogFragment timePicker = new TimePickerFragment();
                                               timePicker.show(getSupportFragmentManager(), "timePicker1");
                                           }
                                       }
                                   }

        );

        sbutton.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           if (s2.isChecked()) {
                                               tagCalled = "timePicker2";
                                               DialogFragment timePicker = new TimePickerFragment();
                                               timePicker.show(getSupportFragmentManager(), "timePicker2");
                                           }
                                       }
                                   }

        );

        tbutton.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           if (s3.isChecked()) {
                                               tagCalled = "timePicker3";
                                               DialogFragment timePicker = new TimePickerFragment();
                                               timePicker.show(getSupportFragmentManager(), "timePicker3");
                                           }
                                       }
                                   }

        );
    }

   /* @Override
    public void onResume() {
        super.onResume();
        final Switch s1 = findViewById(R.id.switch1);
        final Switch s2 = findViewById(R.id.switch2);
        final Switch s3 = findViewById(R.id.switch3);

        s1.setChecked(switchPreferences.getBoolean("s1",false));
        s2.setChecked(switchPreferences.getBoolean("s2",false));
        s3.setChecked(switchPreferences.getBoolean("s3",false));
        language.setChecked(switchPreferences.getBoolean("language",false));

        tvf.setText(switchPreferences.getString("tvf","cipolla"));
        tvs.setText(switchPreferences.getString("tvs","nothing"));
        tvt.setText(switchPreferences.getString("tvt","nothing"));


    }*/

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        try {
            timeNotification(tagCalled, hourOfDay, minute);
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    public void timeNotification(String tag, int hours, int minutes) throws ParseException {

        String hour;
        String minute;
        if (hours < 10)
            hour = "0" + hours;
        else
            hour = hours + "";

        if (minutes < 10)
            minute = "0" + minutes;
        else
            minute = "" + minutes;

        switch (tag) {
            case "timePicker1":
                tvf.setText(hour + ":" + minute);
                mEditor.putString("tvf", hour + ":" + minute);
                mEditor.commit();
                break;
            case "timePicker2":
                tvs.setText(hour + ":" + minute);
                mEditor.putString("tvs", hour + ":" + minute);
                mEditor.commit();
                break;
            case "timePicker3":
                tvt.setText(hours + ":" + minute);
                mEditor.putString("tvt", hour + ":" + minute);
                mEditor.commit();
                break;
        }
        if (tagId.containsKey(tag)) {
            killTag(tag, tagId.get(tag));
        }

        createTimeEvent(tag, hours, minutes);


    }

    private void killTag(String tag, Timer timer) {
        timer.cancel();
        tagId.remove(tag);
    }

    private void createTimeEvent(String tag, int hours, int minutes) throws ParseException {

        Calendar now = Calendar.getInstance();
        int nowHours = now.get(Calendar.HOUR_OF_DAY);
        int nowMinutes = now.get(Calendar.MINUTE);

        //Intent passTimer = new Intent(this, ServiceNotifications.class);
        Bundle bundle = new Bundle();
        if (hours > nowHours || hours == nowHours && minutes >= nowMinutes) {
            delay = TimeUnit.HOURS.toMillis(hours - nowHours) + TimeUnit.MINUTES.toMillis(Math.abs(minutes - nowMinutes));
        } else {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
            String nowTime = nowHours + ":" + nowMinutes + "00";
            String gotTime = hours + ":" + minutes + "00";

            Date date1 = format.parse(nowTime);
            Date date2 = format.parse(gotTime);

            delay = date1.getTime() - date2.getTime();

        }

        delay -= TimeUnit.SECONDS.toMillis(now.get(Calendar.SECOND));

     /*   TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // task to run goes here
                sendOnChannel();
            }
        };*/
        Timer timer = new Timer();
        intervalPeriod = TimeUnit.HOURS.toMillis(24);
        // schedules the task to be run in an interval
        //   timer.scheduleAtFixedRate(task, delay,intervalPeriod);

        AsyncTaskNotification atn = new AsyncTaskNotification();
        atn.execute(delay, intervalPeriod);

        bundle.putLong("delay", delay);
        bundle.putLong("intervalPeriod", intervalPeriod);

        //passTimer.putExtras(bundle);
     //   startService(passTimer);
        tagId.put(tag, timer);
    }

    public void sendOnChannel() {
        String titolo = "Nothing really";
        String text = switchPreferences.getBoolean("language", false) ? sen.getSentenceIta() : sen.getSentence();
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_stat_luxorten)
                .setContentTitle(titolo)
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setAutoCancel(false)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(text))
                .build();
        notificationManager.notify(NOTIFICATION_ID, notification);

    }

    private class AsyncTaskNotification extends AsyncTask<Long, Long, Void> {

        @Override
        protected Void doInBackground(Long... longs) {
            Long delay = longs[0];
            Long intervalPeriod = longs[1];

            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    // task to run goes here
                    sendOnChannel1();
                }
            };
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(task, delay, intervalPeriod);
            return null;
        }

        public void sendOnChannel1() {
            String titolo = "Nothing really";
            String text = switchPreferences.getBoolean("language", false) ? sen.getSentenceIta() : sen.getSentence();
            Notification notification = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_1_ID)
                    .setSmallIcon(R.drawable.ic_stat_luxorten)
                    .setContentTitle(titolo)
                    .setContentText(text)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                    .setAutoCancel(false)
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText(text))
                    .build();
            notificationManager.notify(NOTIFICATION_ID, notification);
        }
    }

}
