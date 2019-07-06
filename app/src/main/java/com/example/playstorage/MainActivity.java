package com.example.playstorage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.view.View;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {


    private MediaPlayer mp;
    private int points = 0;
    private int highscore = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println(getLifecycle().toString());

        InputStream lines = getResources().openRawResource(R.raw.practice);
        Scanner scan = new Scanner(lines);
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            System.out.println(line);
        }

        try {
            Scanner scan2 = new Scanner(openFileInput("addedWords.txt"));
            while (scan2.hasNextLine()) {
                String line = scan2.nextLine();
                System.out.println(line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        mp = MediaPlayer.create(this, R.raw.van_sliding_door_daniel_simon);
        mp.start();
        System.out.println(points);
        System.out.println(highscore);

        SharedPreferences prefs = getSharedPreferences("prefsFile", MODE_PRIVATE);
        highscore = prefs.getInt("highScore", 100);
        System.out.println(highscore);

    }

    public void handleAdd(View view) {
        Intent intent = new Intent(this, AddWordActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onPause() {
        super.onPause();
        mp.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mp.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences prefs = getSharedPreferences("prefsFile", MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        highscore = 10;
        prefsEditor.putInt("highscore", highscore);
        prefsEditor.apply();
        mp.pause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // deal with rotation issue
        outState.putInt("points", points);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        points = savedInstanceState.getInt("points", 9);
    }
}
