package com.example.cerina.flashcardapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Change view to answer card when question card gets clicked on
        findViewById(R.id.flashcard_question).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.rootView).setBackgroundColor(getResources().getColor(R.color.colorLightBlueBG));
                findViewById(R.id.flashcard_question).setVisibility(View.INVISIBLE);
                findViewById(R.id.flashcard_answer).setVisibility(View.VISIBLE);
            }
        });

        //Change view back to question card when answer card gets clicked on
        findViewById(R.id.flashcard_answer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.rootView).setBackgroundColor(getResources().getColor(R.color.colorWhite));
                findViewById(R.id.flashcard_answer).setVisibility(View.INVISIBLE);
                findViewById(R.id.flashcard_question).setVisibility(View.VISIBLE);
            }
        });

        //Lines 37 - 66 are for the single page answer type
        //
//        ImageView icon = findViewById(R.id.toggle_choices_visibility);
//        icon.setImageResource(R.drawable.ic_presidential_eagle);

//        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                findViewById(R.id.button3).setBackgroundColor(getResources().getColor(R.color.colorCorrect));
//                findViewById(R.id.button2).setBackgroundColor(getResources().getColor(R.color.colorIncorrect));
//                findViewById(R.id.button2).setBackgroundColor(getResources().getColor(R.color.colorIncorrect));
//            }
//        });
//
//        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                findViewById(R.id.button2).setBackground(getResources().getDrawable(R.drawable.answer_background));
//                findViewById(R.id.button3).setBackgroundColor(getResources().getColor(R.color.colorCorrect));
//                //findViewById(R.id.button2).setBackgroundColor(getResources().getColor(R.color.colorIncorrect));
//                //findViewById(R.id.button2).setBackgroundColor(getResources().getColor(R.color.colorIncorrect));
//            }
//        });
//
//        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                findViewById(R.id.button3).setBackgroundColor(getResources().getColor(R.color.colorCorrect));
//                findViewById(R.id.button2).setBackgroundColor(getResources().getColor(R.color.colorIncorrect));
//                findViewById(R.id.button1).setBackgroundColor(getResources().getColor(R.color.colorIncorrect));
//            }
//        });



    }
}
