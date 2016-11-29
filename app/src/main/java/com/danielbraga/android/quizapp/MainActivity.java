package com.danielbraga.android.quizapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /**
     * Total de acertos possíveis
     */
    static final int TOTAL = 14;
    static final String STATE_SCORE = "quizScore";
    static final String STATE_VISIBILITY = "quizVisibility";
    /**
     * Total de pontos
     */
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            score = savedInstanceState.getInt(STATE_SCORE);
            if (savedInstanceState.getBoolean(STATE_VISIBILITY)) {
                displayScore(score);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        TextView scoreTextView = (TextView) findViewById(R.id.score_text_view);

        // Salva o estado atual da atividade
        savedInstanceState.putInt(STATE_SCORE, score);
        savedInstanceState.putBoolean(STATE_VISIBILITY, scoreTextView.getVisibility() == View.VISIBLE);

        super.onSaveInstanceState(savedInstanceState);
    }

    /**
     * Limpa a interface para ser respondida novamente
     *
     * @param view Botão que disparou o evento
     */
    public void resetScore(View view) {
        score = 0;

        CheckBox question1Answer1 = (CheckBox) findViewById(R.id.q1_a1);
        CheckBox question1Answer2 = (CheckBox) findViewById(R.id.q1_a2);
        CheckBox question2Answer1 = (CheckBox) findViewById(R.id.q2_a1);
        CheckBox question2Answer2 = (CheckBox) findViewById(R.id.q2_a2);
        CheckBox question2Answer3 = (CheckBox) findViewById(R.id.q2_a3);
        CheckBox question3Answer1 = (CheckBox) findViewById(R.id.q3_a1);
        CheckBox question3Answer2 = (CheckBox) findViewById(R.id.q3_a2);
        RadioGroup question4Answer = (RadioGroup) findViewById(R.id.q4_a);
        RadioGroup question5Answer = (RadioGroup) findViewById(R.id.q5_a);
        RadioGroup question6Answer = (RadioGroup) findViewById(R.id.q6_a);
        RadioGroup question7Answer = (RadioGroup) findViewById(R.id.q7_a);
        RadioGroup question8Answer = (RadioGroup) findViewById(R.id.q8_a);
        RadioGroup question9Answer = (RadioGroup) findViewById(R.id.q9_a);
        RadioGroup question10Answer = (RadioGroup) findViewById(R.id.q10_a);
        TextView yourScoreTextView = (TextView) findViewById(R.id.your_score_text_view);
        TextView scoreTextView = (TextView) findViewById(R.id.score_text_view);

        question1Answer1.setChecked(false);
        question1Answer2.setChecked(false);
        question2Answer1.setChecked(false);
        question2Answer2.setChecked(false);
        question2Answer3.setChecked(false);
        question3Answer1.setChecked(false);
        question3Answer2.setChecked(false);
        question4Answer.clearCheck();
        question5Answer.clearCheck();
        question6Answer.clearCheck();
        question7Answer.clearCheck();
        question8Answer.clearCheck();
        question9Answer.clearCheck();
        question10Answer.clearCheck();

        yourScoreTextView.setVisibility(View.INVISIBLE);
        scoreTextView.setVisibility(View.INVISIBLE);
    }

    /**
     * Valida as respostas e exibe o percentual de acerto
     *
     * @param view Bot"ao que disparou o evento
     */
    public void submit(View view) {
        float rightAnswers = 0;

        CheckBox question1Answer1 = (CheckBox) findViewById(R.id.q1_a1);
        CheckBox question1Answer2 = (CheckBox) findViewById(R.id.q1_a2);
        CheckBox question2Answer1 = (CheckBox) findViewById(R.id.q2_a1);
        CheckBox question2Answer2 = (CheckBox) findViewById(R.id.q2_a2);
        CheckBox question2Answer3 = (CheckBox) findViewById(R.id.q2_a3);
        CheckBox question3Answer1 = (CheckBox) findViewById(R.id.q3_a1);
        CheckBox question3Answer2 = (CheckBox) findViewById(R.id.q3_a2);
        RadioGroup question4Answer = (RadioGroup) findViewById(R.id.q4_a);
        RadioGroup question5Answer = (RadioGroup) findViewById(R.id.q5_a);
        RadioGroup question6Answer = (RadioGroup) findViewById(R.id.q6_a);
        RadioGroup question7Answer = (RadioGroup) findViewById(R.id.q7_a);
        RadioGroup question8Answer = (RadioGroup) findViewById(R.id.q8_a);
        RadioGroup question9Answer = (RadioGroup) findViewById(R.id.q9_a);
        RadioGroup question10Answer = (RadioGroup) findViewById(R.id.q10_a);

        if (question1Answer1.isChecked()) {
            rightAnswers++;
        }
        if (question1Answer2.isChecked()) {
            rightAnswers++;
        }
        if (question2Answer1.isChecked()) {
            rightAnswers++;
        }
        if (question2Answer2.isChecked()) {
            rightAnswers++;
        }
        if (question2Answer3.isChecked()) {
            rightAnswers++;
        }
        if (question3Answer1.isChecked()) {
            rightAnswers++;
        }
        if (question3Answer2.isChecked()) {
            rightAnswers++;
        }
        if (question4Answer.getCheckedRadioButtonId() == R.id.q4_a3) {
            rightAnswers++;
        }
        if (question5Answer.getCheckedRadioButtonId() == R.id.q5_a1) {
            rightAnswers++;
        }
        if (question6Answer.getCheckedRadioButtonId() == R.id.q6_a2) {
            rightAnswers++;
        }
        if (question7Answer.getCheckedRadioButtonId() == R.id.q7_a2) {
            rightAnswers++;
        }
        if (question8Answer.getCheckedRadioButtonId() == R.id.q8_a4) {
            rightAnswers++;
        }
        if (question9Answer.getCheckedRadioButtonId() == R.id.q9_a3) {
            rightAnswers++;
        }
        if (question10Answer.getCheckedRadioButtonId() == R.id.q10_a2) {
            rightAnswers++;
        }

        score = Math.round(rightAnswers / TOTAL * 100);
        displayScore(score);
        displayToast(score);
    }

    /**
     * Exibe a pontuação do usuário
     *
     * @param number Pontuação a ser exibida
     */
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
