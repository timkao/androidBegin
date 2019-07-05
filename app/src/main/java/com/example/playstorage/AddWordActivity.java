package com.example.playstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class AddWordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        Intent intent = getIntent();
        String newWord = intent.getStringExtra("initialText");
        EditText box = findViewById(R.id.newWord);
        box.setText(newWord);
    }

    public void handleAddWord(View view) throws FileNotFoundException {
        EditText wordText = findViewById(R.id.newWord);
        String word = wordText.getText().toString();
        EditText defText = findViewById(R.id.newDef);
        String defn = defText.getText().toString();

        PrintStream output = new PrintStream(openFileOutput("addedWords.txt", MODE_APPEND | MODE_PRIVATE));
        output.println(word + "\t" + defn);
        output.close();

        Intent goBack = new Intent();
        goBack.putExtra("newWord", word);
        goBack.putExtra("newDefn", defn);
        setResult(RESULT_OK, goBack);
        finish();
    }
}
