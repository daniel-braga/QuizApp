package com.danielbraga.android.quizapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static final int MAX_RIGHT_ANSWERS = 15;
    static final String STATE_SCORE = "quizScore";
    static final String STATE_VISIBILITY = "quizVisibility";
    static final String STATE_ANSWER11 = "quizAnswer11";
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText question11Answer = (EditText) findViewById(R.id.q11_a);

        if (savedInstanceState != null) {
            score = savedInstanceState.getInt(STATE_SCORE);
            question11Answer.setText(savedInstanceState.getString(STATE_ANSWER11));
            if (savedInstanceState.getBoolean(STATE_VISIBILITY)) {
                displayScore(score);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        TextView scoreTextView = (TextView) findViewById(R.id.score_text_view);
        EditText question11Answer = (EditText) findViewById(R.id.q11_a);

        savedInstanceState.putInt(STATE_SCORE, score);
        savedInstanceState.putBoolean(STATE_VISIBILITY, scoreTextView.getVisibility() == View.VISIBLE);
        savedInstanceState.putString(STATE_ANSWER11, question11Answer.getText().toString());

        super.onSaveInstanceState(savedInstanceState);
    }

    private void uncheckCheckBox(int resourceId) {
        CheckBox check = (CheckBox) findViewById(resourceId);
        check.setChecked(false);
    }

    private void uncheckRadioGroup(int resourceId) {
        RadioGroup radio = (RadioGroup) findViewById(resourceId);
        radio.clearCheck();
    }

    private void clearAnswers() {
        EditText question11Answer = (EditText) findViewById(R.id.q11_a);
        question11Answer.setText("");

        uncheckCheckBox(R.id.q1_a1);
        uncheckCheckBox(R.id.q1_a2);
        uncheckCheckBox(R.id.q2_a1);
        uncheckCheckBox(R.id.q2_a2);
        uncheckCheckBox(R.id.q2_a3);
        uncheckCheckBox(R.id.q3_a1);
        uncheckCheckBox(R.id.q3_a2);
        uncheckRadioGroup(R.id.q4_a);
        uncheckRadioGroup(R.id.q5_a);
        uncheckRadioGroup(R.id.q6_a);
        uncheckRadioGroup(R.id.q7_a);
        uncheckRadioGroup(R.id.q8_a);
        uncheckRadioGroup(R.id.q9_a);
        uncheckRadioGroup(R.id.q10_a);
    }

    public void resetScore(View view) {
        score = 0;

        TextView scoreTextView = (TextView) findViewById(R.id.score_text_view);
        TextView yourScoreTextView = (TextView) findViewById(R.id.your_score_text_view);

        clearAnswers();
        yourScoreTextView.setVisibility(View.INVISIBLE);
        scoreTextView.setVisibility(View.INVISIBLE);
    }

    public int validateAnswerFromCheckBox(int resourceId) {
        CheckBox answer = (CheckBox) findViewById(resourceId);

        if (resourceId == R.id.q1_a1 && answer.isChecked()) {
            return 1;
        } else if (resourceId == R.id.q1_a2 && answer.isChecked()) {
            return 1;
        } else if (resourceId == R.id.q2_a1 && answer.isChecked()) {
            return 1;
        } else if (resourceId == R.id.q2_a2 && answer.isChecked()) {
            return 1;
        } else if (resourceId == R.id.q2_a3 && answer.isChecked()) {
            return 1;
        } else if (resourceId == R.id.q3_a1 && answer.isChecked()) {
            return 1;
        } else if (resourceId == R.id.q3_a2 && answer.isChecked()) {
            return 1;
        }
        return 0;
    }

    public int validateAnswerFromRadioGroup(int resourceId) {
        RadioGroup answer = (RadioGroup) findViewById(resourceId);

        if (answer.getCheckedRadioButtonId() == R.id.q4_a3) {
            return 1;
        } else if (answer.getCheckedRadioButtonId() == R.id.q5_a1) {
            return 1;
        } else if (answer.getCheckedRadioButtonId() == R.id.q6_a2) {
            return 1;
        } else if (answer.getCheckedRadioButtonId() == R.id.q7_a2) {
            return 1;
        } else if (answer.getCheckedRadioButtonId() == R.id.q8_a4) {
            return 1;
        } else if (answer.getCheckedRadioButtonId() == R.id.q9_a3) {
            return 1;
        } else if (answer.getCheckedRadioButtonId() == R.id.q10_a2) {
            return 1;
        }
        return 0;
    }

    public float calculateRightAnswers() {
        EditText question11Answer = (EditText) findViewById(R.id.q11_a);
        float rightAnswers = 0;

        rightAnswers += validateAnswerFromCheckBox(R.id.q1_a1);
        rightAnswers += validateAnswerFromCheckBox(R.id.q1_a2);
        rightAnswers += validateAnswerFromCheckBox(R.id.q2_a1);
        rightAnswers += validateAnswerFromCheckBox(R.id.q2_a2);
        rightAnswers += validateAnswerFromCheckBox(R.id.q2_a3);
        rightAnswers += validateAnswerFromCheckBox(R.id.q3_a1);
        rightAnswers += validateAnswerFromCheckBox(R.id.q3_a2);
        rightAnswers += validateAnswerFromRadioGroup(R.id.q4_a);
        rightAnswers += validateAnswerFromRadioGroup(R.id.q5_a);
        rightAnswers += validateAnswerFromRadioGroup(R.id.q6_a);
        rightAnswers += validateAnswerFromRadioGroup(R.id.q7_a);
        rightAnswers += validateAnswerFromRadioGroup(R.id.q8_a);
        rightAnswers += validateAnswerFromRadioGroup(R.id.q9_a);
        rightAnswers += validateAnswerFromRadioGroup(R.id.q10_a);

        if (question11Answer.getText().toString().compareTo("$") == 0) {
            rightAnswers++;
        }

        return rightAnswers;
    }

    public void submit(View view) {
        float rightAnswers = calculateRightAnswers();
        score = Math.round(rightAnswers / MAX_RIGHT_ANSWERS * 100);
        displayScore(score);
        displayToast(score);
    }

    private void displayScore(int number) {
        TextView yourScoreTextView = (TextView) findViewById(R.id.your_score_text_view);
        TextView scoreTextView = (TextView) findViewById(R.id.score_text_view);

        yourScoreTextView.setVisibility(View.VISIBLE);
        scoreTextView.setVisibility(View.VISIBLE);
        scoreTextView.setText(String.format("%d", number));
    }

    private void displayToast(int number) {
        Context context = getApplicationContext();
        CharSequence text = "Your score is " + number + " of 100";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
