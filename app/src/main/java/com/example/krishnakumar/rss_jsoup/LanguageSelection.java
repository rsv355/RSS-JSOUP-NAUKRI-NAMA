package com.example.krishnakumar.rss_jsoup;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.krishnakumar.rss_jsoup.helper.PrefUtils;

public class LanguageSelection extends AppCompatActivity {
    private CardView cardEnglish,cardHindi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changelanguage);

        cardEnglish = (CardView)findViewById(R.id.cardEnglish);
        cardHindi = (CardView)findViewById(R.id.cardHindi);

        cardEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrefUtils.saveLanguage(LanguageSelection.this,0);
                startActivity(new Intent(LanguageSelection.this, MainActivity.class));
                finish();
            }
        });

        cardHindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrefUtils.saveLanguage(LanguageSelection.this,1);
                startActivity(new Intent(LanguageSelection.this,MainActivity.class));
                finish();
            }
        });
    }






//end of main class
}
