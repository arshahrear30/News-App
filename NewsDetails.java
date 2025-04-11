package com.example.newsapp;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NewsDetails extends AppCompatActivity {

    ImageView coverImage;
    TextView tvTitle, tvDes;
    FloatingActionButton voiceButton;

    public static String TITLE ="";
    public static String DESCRIPTION ="";
    public static Bitmap MY_BITMAP = null;

    TextToSpeech textToSpeech;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_news_details);

        coverImage = findViewById(R.id.coverImage);
        tvTitle = findViewById(R.id.tvTitle);
        tvDes = findViewById(R.id.tvDes);
        voiceButton = findViewById(R.id.voiceButton);


        tvTitle.setText(TITLE);
        tvDes.setText(DESCRIPTION);




        if(MY_BITMAP!=null) coverImage.setImageBitmap(MY_BITMAP);


        textToSpeech =new TextToSpeech(NewsDetails.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

            }
        });

        voiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text = tvDes.getText().toString();
                textToSpeech.speak(text,textToSpeech.QUEUE_FLUSH,null,null);

            }
        });





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
