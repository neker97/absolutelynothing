package com.nothing_really;

import android.app.Notification;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.nothing_really.absolutelynothing.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.nothing_really.AppNotifications.CHANNEL_1_ID;

public class sentence extends AppCompatActivity {

    private List<String> sentences = new ArrayList<>();
    private List<String> sentencesIta = new ArrayList<>();
    private NotificationManagerCompat notificationManager;
    private Random nSentences = new Random();
    private static final int NOTIFICATION_ID = 1;
    private Switch language;
    private SharedPreferences switchPreferences;
    private SharedPreferences.Editor mEditor;
    private TextView sentence;
    private AdView mAdView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sentence);

        final LayoutInflater factory = getLayoutInflater();

        final View textEntryView = factory.inflate(R.layout.activity_settings, null);

        switchPreferences = getSharedPreferences("switchPreferences",0);
        mEditor = switchPreferences.edit();

        language = (Switch) textEntryView.findViewById(R.id.languageSwitch);
        notificationManager = NotificationManagerCompat.from(this);
        sentence = findViewById(R.id.sentence);
        sentence.setText(switchPreferences.getBoolean("language",true)? getSentenceIta():getSentence());

        mAdView = findViewById(R.id.adViewSentence);

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

    }

    public sentence(){


        sentences.add("You probably have forgot something important..."+"\n"+"but it doesn't matter , probably it wasn't important ~LuxorTen");
        sentences.add("You have made your daily good action ~LuxorTen");
        sentences.add("You can become a super saiyaRich now ~LuxorTen");
        sentences.add("Making good actions makes you sexier ~LuxorTen");
        sentences.add("Moneys collected will avoid that \"curillin\" dies again ~LuxorTen");
        sentences.add("Comment with amen and share ~LuxorTen");
        sentences.add("Pizza, pasta , mandolinoooo!!! ~LuxorTen");
        sentences.add("Buongiornissimo kaffeeeeeeeeee????? ~LuxorTen");
        sentences.add("1€ at day , brings the wealth far away ~LuxorTen");
        sentences.add("Ooops, there must been an error , "+"\n"+"the sentences didn't came in... "+"\n"+ "btw you are a nice guy ~LuxorTen");
        sentences.add("Stop making drama,you're not shakespeare ~Wiii");
        sentences.add("I don't do anything but still I'm sure you love me ~LuxorTen");
        sentences.add("You can't put on weight if you eat sweets only in your mind ~LuxorTen");
        sentences.add("You've got a so powerful uselessness! ~LuxorTen");
        sentences.add("That's amazing! ~LuxorTen");
        sentences.add("Some people never get mad. Their lives must be so horrible ~LuxorTen");
        sentences.add("All you need is love. But sometimes a bite of chocolate can't hurt anyone ~LuxorTen");
        sentences.add("The biggest pleasure in life is to do what people say you can't do ~LuxorTen");
        sentences.add("Accept who you are. Unless you're a serial killer ~LuxorTen");
        sentences.add("Life is pain and everybody who says it's not like this is probably trying to sell you something ~LuxorTen");
        sentences.add("You should never discuss with an idiot: he brings you down to his level and he defeats you with his experience  ~LuxorTen");
        sentences.add("Behind every great man there's a woman who rolls her eyes ~LuxorTen");
        sentences.add("During this life someone will love you. Someone will hate you. And nothing of it all has something to do with you. ~LuxorTen");
        sentences.add("If life is right behind the corner, my life must be a circle ~LuxorTen");
        sentences.add("Only time will give you the answers that you're looking for, but when it will give you them you'll have forgot your questions ~LuxorTen");
        sentences.add("Have you ever noticed that who goes slower than you is an idiot but anybody who goes faster is crazy? ~LuxorTen");
        sentences.add("If someday you feel sad, just think that once you were the fastest spermatozoon. ~LuxorTen");
        sentences.add("Don't take life too seriously, you won't come out alive ~LuxorTen");
        sentences.add("Money can't bring you happiness but they give you a feeling similar to happiness, which is necessary a real good specialist to understand the difference ~LuxorTen");
        sentences.add("The most beautiful things in life either are immoral, or illegal or the make you fat ~LuxorTen");
        sentences.add("Today your horoscope says: \"Look, just ignore me, if it will get any better I'll let you know\" ~LuxorTen");
        sentences.add("Life is a dream for the wise, it's a play for the stupid, it's a comedy for the rich and it's a tragedy for the poor. ~LuxorTen");
        sentences.add("The experience teaches that people never learn from experiences  ~LuxorTen");
        sentences.add("Life is like a roll of toilet paper. You wish it will last longer but it always ends in the wrong moment ~LuxorTen");
        sentences.add("People say money aren't the key for happiness, but I've always thought that if you have enough money, you can have a double ~LuxorTen");
        sentences.add("Even if you decide to stop drinking, smoking and having sex, you won't live longer: life just seems longer ~LuxorTen");
        sentences.add("Life is just a terrible quarter of a hour made of delicious moments ~LuxorTen");
        sentences.add("Life is a journey. And it's better if you travel in the first class ~LuxorTen");
        sentences.add("Frog's favourite metal is pound ~LuxorTen");

        sentencesIta.add("I soldi raccolti saranno usati per impedire a crilin di morire ~LuxorTen");
        sentencesIta.add("Scrivi amen e condividi ~LuxorTen");
        sentencesIta.add("Pizza, pasta, mandolino! ~LuxorTen");
        sentencesIta.add("Buongiornissimo kaffeeeee? ~LuxorTen");
        sentencesIta.add("Un 1€ al giorno leva la ricchezza di torno ~LuxorTen");
        sentencesIta.add("Tu probabilmente hai dimenticato qualcosa d'importante...ma non importa, probabilmente non lo era. ~LuxorTen");
        sentencesIta.add("Hai fatto la tua buona azione quotidiana ~LuxorTen");
        sentencesIta.add("Ora sei un supersaiyaRicco. ~LuxorTen");
        sentencesIta.add("Fare buone azioni ti rende piu sexy ~LuxorTen");
        sentencesIta.add("Oops ci dev'ssere stato un errore, la frase non è arrivata...comunque sei una brava persona. ~LuxorTen");
        sentencesIta.add("Smettila di farne un dramma, non sei shakespeare. ~LuxorTen");
        sentencesIta.add("Non faccio nulla, ma sono sicuro che mi vuoi bene comunque. ~LuxorTen");
        sentencesIta.add("Non ingrassi se mangi dolci usando la testa. ~LuxorTen");
        sentencesIta.add("Ha un'inutilità potentissima!!!! ~LuxorTen");
        sentencesIta.add("Ma è strabiliante! ~LuxorTen");
        sentencesIta.add("Alcune persone non impazziscono mai. Che vite veramente orribili devono condurre. ~LuxorTen");
        sentencesIta.add("Tutto ciò di cui hai bisogno è l’amore. Ma un po’ di cioccolato ogni tanto non fa male. ~LuxorTen");
        sentencesIta.add("Il piacere più grande nella vita è fare ciò che le persone dicono che non puoi fare. ~LuxorTen");
        sentencesIta.add("Accetta chi sei. A meno che tu non sia un serial killer. ~LuxorTen");
        sentencesIta.add("La vita è dolore. Chiunque dica il contrario ti sta vendendo qualcosa. ~LuxorTen");
        sentencesIta.add("Mai discutere con un idiota, ti trascina al suo livello e ti batte con l’esperienza. ~LuxorTen");
        sentencesIta.add("Dietro ogni grande uomo c’è una donna che alza gli occhi al cielo ~LuxorTen");
        sentencesIta.add("In questa vita la gente ti amerà. La gente ti odierà. E niente di tutto ciò avrà a che fare con te. ~LuxorTen");
        sentencesIta.add("Se la felicità è dietro l’angolo, la mia vita è un cerchio. ~LuxorTen");
        sentencesIta.add("Solo il tempo ti darà le risposte che stai cercando, ma te le darà quando avrai dimenticato le domande. ~LuxorTen");
        sentencesIta.add("Hai mai notato che chiunque vada più lento di te è un idiota, ma chiunque vada più veloce è un pazzo? ~LuxorTen");
        sentencesIta.add("Se un giorno ti senti triste e giù di corda, pensa che una volta eri lo spermatozoo più veloce di tutti. ~LuxorTen");
        sentencesIta.add("Non prendere la vita troppo sul serio. Non ne uscirai vivo. ~LuxorTen");
        sentencesIta.add("Il denaro non dà la felicità, ma procura una sensazione così simile alla felicità, che è necessario uno specialista molto avanzato per capirne la differenza. ~LuxorTen");
        sentencesIta.add("Le cose più belle della vita o sono immorali, o sono illegali, oppure fanno ingrassare. ~LuxorTen");
        sentencesIta.add("Il tuo oroscopo stamattina dice: “Guarda, lascia perdere, se migliora qualcosa mi faccio sentire io”. ~LuxorTen");
        sentencesIta.add("La vita è un sogno per il saggio, un gioco per lo stupido, una commedia per il ricco e una tragedia per il povero ~LuxorTen");
        sentencesIta.add("L’esperienza insegna che gli uomini non hanno mai imparato nulla dall’esperienza. ~LuxorTen");
        sentencesIta.add("La vita è come un rotolo di carta igienica. Speri che sia lunga e godibile, ma finisce sempre nel momento sbagliato. ~LuxorTen");
        sentencesIta.add("La gente dice che il denaro non è la chiave della felicità, ma ho sempre pensato che se hai abbastanza soldi, puoi avere un duplicato. ~LuxorTen");
        sentencesIta.add("Se decidi di smettere di bere, fumare e fare l’amore, non è che vivi più a lungo: la vita ti sembra più lunga. ~LuxorTen");
        sentencesIta.add("La vita non è altro che un brutto quarto d’ora, composto da attimi squisiti. ~LuxorTen");
        sentencesIta.add("La vita è un viaggio. E se viaggi in prima è meglio. ~LuxorTen");
        sentencesIta.add("Il metallo preferito dalle rane è lo stagno ~LuxorTen");

    }

    public String getSentence(){return sentences.get(nSentences.nextInt(sentences.size()));}

    public String getSentenceIta(){return sentencesIta.get(nSentences.nextInt(sentencesIta.size()));}

    public void sendOnChannell(){
        String titolo = "Nothing really";
        String text = language.isChecked()? getSentenceIta():getSentence();
        Notification notification = new NotificationCompat.Builder(this,CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_stat_luxorten)
                .setContentTitle(titolo)
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setAutoCancel(false)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(text))
                .build();
        notificationManager.notify(NOTIFICATION_ID,notification);
    }

    public void setSentence(View v){
        sentence.setText(switchPreferences.getBoolean("language",true)? getSentenceIta():getSentence());
        Toast.makeText(getApplicationContext(), "tie'",
                Toast.LENGTH_SHORT).show();
    }




}
