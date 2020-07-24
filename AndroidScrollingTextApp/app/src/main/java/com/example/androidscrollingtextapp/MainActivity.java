package com.example.androidscrollingtextapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addCommentToast(View view) {
        Toast commentToast = Toast.makeText(this, R.string.comment_added, Toast.LENGTH_LONG);
        commentToast.show();
    }
}