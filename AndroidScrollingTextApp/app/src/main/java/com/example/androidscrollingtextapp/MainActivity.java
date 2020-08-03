package com.example.androidscrollingtextapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView articleText = findViewById(R.id.article);
        registerForContextMenu(articleText);
    }

    public void addCommentToast(View view) {
        Toast commentToast = Toast.makeText(this, R.string.comment_added, Toast.LENGTH_LONG);
        commentToast.show();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_context, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.context_edit:
                displayToast(getString(R.string.edit_choice));
                return true;

            case R.id.context_share:
                displayToast(getString(R.string.share_choice));
                return true;

            case R.id.context_delete:
                displayToast(getString(R.string.delete_choice));
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }

    private void displayToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}