package com.example.pranay.quiz_trivia;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Button btn_true, btn_false;
    TextView txt_question,txt_score;
    ProgressBar pro_bar;
    int score=0;


    //project variable
        int index, question;
    private TrueFalse[] mQuestionBank = new TrueFalse[]
            {
                    new TrueFalse(R.string.question_1,true),
                    new TrueFalse(R.string.question_2,true),
                    new TrueFalse(R.string.question_3,true),
                    new TrueFalse(R.string.question_4,true)
            };

    final  int PROGRESSBAR_INCREAMENT = 100/mQuestionBank.length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

   if (savedInstanceState != null)
   {
       score = savedInstanceState.getInt("scoreKey");
       index = savedInstanceState.getInt("indexKey");
   }
   else
   {
       score = 0;
       index = 0;
   }


   // getting the resource_id
    btn_true = findViewById(R.id.true_button);
    btn_false = findViewById(R.id.false_button);

    //GETTING THE PROGRESSBAR
        pro_bar = findViewById(R.id.progressBar);


    //getting the text_field

    txt_question = findViewById(R.id.questions);
    txt_score = findViewById(R.id.score);
    txt_question.setText(R.string.question_1);


    //initializing the values in case the app is restart
        txt_score.setText("Score" + score + "/" + mQuestionBank.length);




    //defining the functions
    btn_true.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d("quiz","true is pressed");

            update_question();
            check_answer(true);


           // Toast.makeText(getApplicationContext(),
             //       "true button is pressed",Toast.LENGTH_SHORT).show();

        }
    });

    btn_false.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Toast.makeText(getApplicationContext(),
              //      "false button is pressed",Toast.LENGTH_LONG).show();

            update_question();
            check_answer(false);


        }
    });

    }

    public void update_question()
    {
        index = (index + 1)% mQuestionBank.length;

        if(index == 0)
        {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Game Over");
            alert.setMessage("You score" + score + "points !");
            alert.setPositiveButton("Close the app", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alert.show();
        }

        question = mQuestionBank[index].getQuestionId();
        txt_question.setText(question);
        pro_bar.incrementProgressBy(PROGRESSBAR_INCREAMENT);
    }

    public void check_answer(boolean userSelection)
    {
        boolean correctAnswer = mQuestionBank[index].isAnswer();

        if(userSelection == correctAnswer)
        {
            Toast.makeText(getApplicationContext(),R.string.correct_toast,Toast.LENGTH_SHORT).show();
            score = score +1;
            txt_score.setText("score" + score + "/" + mQuestionBank.length);
        }
        else
        {
            Toast.makeText(getApplicationContext(),R.string.incorrect_answer,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

        outState.putInt("scoreKey",score);
        outState.putInt("indexKey",index);
    }
}
