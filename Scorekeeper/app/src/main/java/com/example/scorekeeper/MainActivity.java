package com.example.scorekeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int mScore1;
    private int mScore2;

    private TextView mScoreText1;
    private TextView mScoreText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mScoreText1 = findViewById(R.id.score_1);
        mScoreText2 = findViewById(R.id.score_2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void decreaseScore(View view) {
        int viewID = view.getId();

        switch (viewID) {
            case R.id.decreaseTeam1:
                mScore1--;

                if (mScore1 < 0) {
                    mScore1 = 0;
                    mScoreText1.setText(R.string.initial_score);
                } else {
                    mScoreText1.setText(String.valueOf(mScore1));
                }

                break;

            case R.id.decreaseTeam2:
                mScore2--;

                if (mScore2 < 0) {
                    mScore2 = 0;
                    mScoreText2.setText(R.string.initial_score);
                } else {
                    mScoreText2.setText(String.valueOf(mScore2));
                }

                break;
        }
    }

    public void increaseScore(View view) {
        int viewID = view.getId();

        switch (viewID) {
            case R.id.increaseTeam1:
                mScore1++;
                mScoreText1.setText(String.valueOf(mScore1));
                break;

            case R.id.increaseTeam2:
                mScore2++;
                mScoreText2.setText(String.valueOf(mScore2));
                break;
        }
    }
}