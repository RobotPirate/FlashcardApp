package com.example.cerina.flashcardapp;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.content.Intent;


public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        //Grab the 2 strings from input
        final String questionInput = ((EditText) findViewById(R.id.addQuestionText)).getText().toString();
        final String answerInput = ((EditText) findViewById(R.id.addAnswerText)).getText().toString();


        ImageView closeButton = findViewById(R.id.closeButton);
        closeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });

        ImageView saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if(){
//
//                }
//                else{
//
//                }

                //Put strings into Intent to pass to MainActivity
                Intent data = new Intent(); // create a new Intent, this is where we will put our data
                data.putExtra("question", questionInput); // puts one string into the Intent, with the key as 'question'
                data.putExtra("answer", answerInput); // puts another string into the Intent, with the key as 'answer'
                setResult(RESULT_OK, data); // set result code and bundle data for response
                finish(); // closes and pass data to the original activity that launched this activity (MainActivity)
            }
        });

    }
}
