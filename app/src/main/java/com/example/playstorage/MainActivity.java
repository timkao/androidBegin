package com.example.playstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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


    }




}
