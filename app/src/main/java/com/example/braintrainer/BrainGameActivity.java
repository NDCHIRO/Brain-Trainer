package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class BrainGameActivity extends AppCompatActivity {
    int rand1;
    int rand2;
    Random random;
    TextView problemTextView;
    GridLayout gridLayout;
    TextView TextViewX;
    TextView correctTextView;
    TextView answersTextView;
    TextView countDownTimer;
    Button playAgain;
    int correctAnswer=0;
    int noOfAnswers=0;
    public void checkResult(View view)
    {
        TextView resultTextView=(TextView) view;
        int correctResult=rand1+rand2;
        if(resultTextView.getText().equals(correctResult+""))
        {
            correctTextView.setText("correct");
            correctAnswer++;
        }
        else
        {
            correctTextView.setText("Wrong");
        }

        noOfAnswers++;
        answersTextView.setText(correctAnswer+"/"+noOfAnswers);
        repeatProcess();
    }

    public void startGameAgain(View view)
    {
        startGame();
    }

    public int createRandom()
    {
        random=new Random();
        return random.nextInt(100);
    }

    public int getRandomTextView()
    {
        random = new Random();
        return random.nextInt(5 - 1) ;
    }

    public void repeatProcess()
    {
        rand1 = createRandom();
        rand2 = createRandom();
        problemTextView.setText(rand1+" + "+rand2+" =");
        int randomTextView = getRandomTextView();
        TextViewX =  (TextView)gridLayout.getChildAt(randomTextView);
        TextViewX.setText(rand1+rand2+"");
        int x;
        for(int i=0; i < gridLayout.getChildCount() ; i++) {
            if (i != randomTextView) {
                x= createRandom();
                TextViewX = (TextView) gridLayout.getChildAt(i);
                TextViewX.setText(x+ "");
            }
        }
    }

    public void startGame()
    {
        reset();
        playAgain.setVisibility(View.INVISIBLE);
        new CountDownTimer(30000,1000)
        {

            @Override
            public void onTick(long l) {
                countDownTimer.setText(l/1000+" s");
            }

            @Override
            public void onFinish() {
                correctTextView.setText("Done");
                playAgain.setVisibility(View.VISIBLE);
            }
        }.start();
        repeatProcess();
    }

    public void reset()
    {
        correctTextView.setText("");
        answersTextView.setText("");
        noOfAnswers=0;
        correctAnswer=0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brain_game);

        problemTextView = findViewById(R.id.problemTextView);
        gridLayout = findViewById(R.id.gridLayout);
        correctTextView = findViewById(R.id.correctTextView);
        answersTextView = findViewById(R.id.answerTextView);
        countDownTimer = findViewById(R.id.countDownTextView);
        playAgain = findViewById(R.id.playAgainButton);


        startGame();
    }
}