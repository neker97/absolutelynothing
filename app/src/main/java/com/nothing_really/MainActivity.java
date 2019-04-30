package com.nothing_really;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nothing_really.absolutelynothing.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity {
    public final String CHANNEL_ID = "personal_notification";
    private final int id = 001;
    private ImageButton gift;
    private TextView clickHere;
    private ImageView hand;
    private static final String TAG = "MainActivity";

    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addListenerOnButton();
        textViewClicked();
        handClicked();

        mAdView = findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
       // MobileAds.initialize(this, "ca-app-pub-1346926195307365~3895367738");


    }

    private void handClicked() {
        hand =  findViewById(R.id.hand);
        hand.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Are you kidding me?",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void textViewClicked() {
        clickHere =  findViewById(R.id.clickHere);
        clickHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Are you stupid or what?",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addListenerOnButton() {
        this.gift =  findViewById(R.id.gift);
        gift.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Toast.makeText(MainActivity.this,
                                                "special gift opened", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(MainActivity.this, sentence.class));
                                    }
                                }

        );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id==R.id.impostazioni){
            startActivity(new Intent(MainActivity.this, Settings.class));
        }
        if(id==R.id.send_us_mail)
        {
            startActivity(new Intent(MainActivity.this,sendUSMail.class));
        }

        return super.onOptionsItemSelected(item);

    }
}
