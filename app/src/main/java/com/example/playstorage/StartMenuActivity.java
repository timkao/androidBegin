package com.example.playstorage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class StartMenuActivity extends AppCompatActivity {

    private static final int ADD_WORD_REQUEST = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_menu);
    }

    public void handleGoPlayGame(View view) {
        // go to the main activity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void handleGoAddWord(View view) {
        // go to add word activity
        Intent intent = new Intent(this, AddWordActivity.class);
        intent.putExtra("initialText", "firstText");
        startActivityForResult(intent, 1234);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_WORD_REQUEST) {
            String newWord = data.getStringExtra("newWord");
            String newDefn = data.getStringExtra("newDefn");
            Toast.makeText(this, newWord + newDefn, Toast.LENGTH_LONG).show();
        }
    }
}
