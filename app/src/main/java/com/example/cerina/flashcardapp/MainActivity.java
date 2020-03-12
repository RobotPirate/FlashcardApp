package com.example.cerina.flashcardapp;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    //Get instance of our database
    FlashcardDatabase flashcardDB;

    //Holds the flashcard objects
    List<Flashcard> allFlashcards;

    //Index of current card
    int currentCardDisplayedIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Flashcard DB
        flashcardDB = new FlashcardDatabase(getApplicationContext());

        //List of all flashcards
        allFlashcards = flashcardDB.getAllCards();

        //Check if allFlashcards is empty. If it's not empty, display a flashcard
        if(allFlashcards != null && allFlashcards.size() > 0){
            ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(0).getQuestion());
            ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashcards.get(0).getAnswer());
        }

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

        //Next button
        ImageView next = (ImageView)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentCardDisplayedIndex++;

                //make sure we don't get IndexOutOfBoundsError
                if(currentCardDisplayedIndex > allFlashcards.size() - 1){
                    currentCardDisplayedIndex = 0;
                }
                System.out.println("db size: " + allFlashcards.size());
                System.out.println("curr index: " +  currentCardDisplayedIndex);
                //Need to add check where it only displays from the database if it's not empty

                //set question and answer TextViews with q and a from the database
                TextView q =  findViewById(R.id.flashcard_question);
                q.setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());
                q.setVisibility(View.VISIBLE);
                findViewById(R.id.rootView).setBackgroundColor(getResources().getColor(R.color.colorWhite));

                TextView a = findViewById(R.id.flashcard_answer);
                a.setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
                a.setVisibility(View.INVISIBLE);

            }
        });


        //Following are for the single page answer type (Lab 1 optional)
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

        ImageView addCardButton = findViewById(R.id.addCardButton);
        addCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                MainActivity.this.startActivityForResult(intent, 100);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100 && resultCode == RESULT_OK) { // this 100 needs to match the 100 we used when we called startActivityForResult!
            String newQuestion = data.getExtras().getString("question"); // 'string1' needs to match the key we used when we put the string in the Intent
            String newAnswer = data.getExtras().getString("answer");

            ((TextView)findViewById(R.id.flashcard_question)).setText(newQuestion);
            ((TextView)findViewById(R.id.flashcard_answer)).setText(newAnswer);


            findViewById(R.id.rootView).setBackgroundColor(getResources().getColor(R.color.colorWhite));
            findViewById(R.id.flashcard_question).setVisibility(View.VISIBLE);
            findViewById(R.id.flashcard_answer).setVisibility(View.INVISIBLE);

            //save to the DB
            flashcardDB.insertCard(new Flashcard(newQuestion, newAnswer));
            allFlashcards = flashcardDB.getAllCards();
        }
    }
}
