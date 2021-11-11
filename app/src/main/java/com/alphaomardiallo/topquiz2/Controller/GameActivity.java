package com.alphaomardiallo.topquiz2.Controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alphaomardiallo.topquiz2.Model.Question;
import com.alphaomardiallo.topquiz2.Model.QuestionBank;
import com.alphaomardiallo.topquiz2.R;

import java.util.Arrays;

public class GameActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView mTextViewQuestion;
    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private Button mButton4;
    private QuestionBank mQuestionBank;
    private int mRemainingQuestionCount;
    private Question mCurrentQuestion;
    private int mScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mTextViewQuestion = findViewById(R.id.gameTextViewQuestion);
        mButton1 = findViewById(R.id.gameButton1);
        mButton2 = findViewById(R.id.gameButton2);
        mButton3 = findViewById(R.id.gameButton3);
        mButton4 = findViewById(R.id.gameButton4);

        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        mButton4.setOnClickListener(this);

        mQuestionBank  = generateQuestionBank();

        mCurrentQuestion = mQuestionBank.getCurrentQuestion();
        displayQuestion(mQuestionBank.getCurrentQuestion());

        mRemainingQuestionCount = 3;
        mScore = 0;
    }
    private void displayQuestion(final Question question) {
        mTextViewQuestion.setText(question.getQuestion());
        mButton1.setText(question.getChoiceList().get(0));
        mButton2.setText(question.getChoiceList().get(1));
        mButton3.setText(question.getChoiceList().get(2));
        mButton4.setText(question.getChoiceList().get(3));
    }

    private QuestionBank generateQuestionBank(){
        Question question1 = new Question(
                "Who is the creator of Android?",
                Arrays.asList(
                        "Andy Rubin",
                        "Steve Wozniak",
                        "Jake Wharton",
                        "Paul Smith"
                ),
                0
        );

        Question question2 = new Question(
                "When did the first man land on the moon?",
                Arrays.asList(
                        "1958",
                        "1962",
                        "1967",
                        "1969"
                ),
                3
        );

        Question question3 = new Question(
                "What is the house number of The Simpsons?",
                Arrays.asList(
                        "42",
                        "101",
                        "666",
                        "742"
                ),
                3
        );
        Question question4 = new Question(
                "How many oscars did The Titanic movie got?",
                Arrays.asList(
                        "9",
                        "10",
                        "11",
                        "12"
                ),
                2
        );
        Question question5 = new Question(
                "Who is the director of Reservoir Dogs?",
                Arrays.asList(
                        "Quentin Tarantino",
                        "Sergio Leone",
                        "Woody Allen",
                        "Stephen Spielberg"
                ),
                0
        );
        Question question6 = new Question(
                "In what year was Google launched on the web?",
                Arrays.asList(
                        "1996",
                        "1997",
                        "1998",
                        "1999"
                ),
                2
        );
        Question question7 = new Question(
                "Who was the first man to fly around the earth with a spaceship?",
                Arrays.asList(
                        "Armstrong",
                        "Goodwin",
                        "Pesquet",
                        "Gagarin"
                ),
                3
        );
        Question question8 = new Question(
                "How matches did Mohammed Ali lose in his career?",
                Arrays.asList(
                        "None",
                        "One",
                        "Two",
                        "Three"
                ),
                1
        );
        return new QuestionBank(Arrays.asList(question1, question2, question3, question4, question5, question6, question7, question8));
    }

    @Override
    public void onClick(View view) {
        int index;

        if (view == mButton1) {
            index = 0;
        } else if (view == mButton2) {
            index = 1;
        } else if (view == mButton3) {
            index = 2;
        } else if (view == mButton4) {
            index = 3;
        } else {
            throw new IllegalStateException("Unknown clicked view : " + view);
        }

        if (index == mQuestionBank.getCurrentQuestion().getAnswerIndex()) {
            mScore++;
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show();
        }

        mRemainingQuestionCount--;

        if (mRemainingQuestionCount > 0) {
            mCurrentQuestion = mQuestionBank.getNextQuestion();
            displayQuestion(mCurrentQuestion);
        } else {
            // No question left, end the game
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Well done!")
                    .setMessage("Your score is " + mScore)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .create()
                    .show();
        }

    }
}