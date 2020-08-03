package com.example.learnandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final String EXCEPTION = "Exception";
    private int mCount = 0;
    private TextView mShowCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(LOG_TAG, "Run onCreate");

        try {
            int x = 1;
            int y = 0;
            int z = x / y;
        } catch (ArithmeticException e) {
            System.err.println("Arithmetic exception");
            Log.e(EXCEPTION, "Arithmetic exception");
        }

        Log.e(EXCEPTION, "Logged error");

        mShowCount = findViewById(R.id.show_count);
    }

    public void showToast(View view) {
        Toast toast = Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void countUp(View view) {
        ++mCount;
        if (mShowCount != null) {
            mShowCount.setText(Integer.toString(mCount));
        }
    }

    public void resetCounter(View view) {
        if (mShowCount != null) {
            mShowCount.setText(R.string.count_initial_value);
            mCount = 0;
        }
    }
}