package com.example.quiz_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView questiontv;
    TextView totalquestv;
    Button ansA, ansB, ansC, ansD, sub;
    int score = 0;
    int totalques = QuesAns.question.length;
    int currentquesindex = 0;
    String selectedans = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        questiontv = findViewById(R.id.textView);
        ansA = findViewById(R.id.button);
        ansB = findViewById(R.id.button2);
        ansC = findViewById(R.id.button3);
        ansD = findViewById(R.id.button4);
        sub = findViewById(R.id.button5);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        sub.setOnClickListener(this);

        totalquestv.setText("Toatal Questions : " + totalques);
        loadNewQuestion();
    }

    @Override
    public void onClick(View view) {
        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);
        Button clickedButton = (Button) view;
        if (clickedButton.getId() == R.id.button5) {
            if (selectedans.equals(QuesAns.correctans[currentquesindex])) {
                score++;
            }
            currentquesindex++;
            loadNewQuestion();

        } else {
            selectedans = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.CYAN);
        }
    }

    void loadNewQuestion() {
        if (currentquesindex == totalques) {
            finishQuiz();
            return;
        }
        questiontv.setText(QuesAns.question[currentquesindex]);
        ansA.setText(QuesAns.choices[currentquesindex][0]);
        ansB.setText(QuesAns.choices[currentquesindex][1]);
        ansC.setText(QuesAns.choices[currentquesindex][2]);
        ansD.setText(QuesAns.choices[currentquesindex][3]);
    }

    void finishQuiz() {
        String passStatus = "";
        if (score > totalques * 0.60) {
            passStatus = "Passed";
        } else {
            passStatus = "Failed";
        }
        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score is "+score+" out of "+totalques)
                .setPositiveButton("Restart",(dialogInterface, i) -> restartQuiz())
                .setCancelable(false)
                .show();

    }
    void restartQuiz(){
        score = 0;
        currentquesindex = 0;
        loadNewQuestion();
    }
}
