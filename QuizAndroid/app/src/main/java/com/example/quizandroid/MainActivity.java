package com.example.quizandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    TextView pytanieView;
    Button prawdaBtn,falszBtn;
    String pytanie;
    Boolean prawidlowaOdpowiedz;
    Integer punkty;

    ArrayList<Pytanie> pytanieArrayList;
    int iterator;
    Boolean koniec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iterator = 1;

        Toast.makeText(this, System.getProperty("user.dir"), Toast.LENGTH_SHORT).show();

        pytanieArrayList = new ArrayList<>();

        pytanieArrayList.add(new Pytanie("Czy zielony to green",true));
        pytanieArrayList.add(new Pytanie("Czy zolty to yellow",true));
        pytanieArrayList.add(new Pytanie("Czy grabarz jest",true));
        pytanieArrayList.add(new Pytanie("Czy grzegorz maly",false));
        pytanieArrayList.add(new Pytanie("Czy patryk czerwona bluza",true));

        pytanieView = findViewById(R.id.Pytanie);
        prawdaBtn = findViewById(R.id.Prawda);
        falszBtn = findViewById(R.id.Falsz);

        pytanie = "Czy slonce jest zólte";
        prawidlowaOdpowiedz = false;
        punkty=0;
        koniec = false;
        wypisanie(iterator);




        prawdaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(koniec==false) {
                    if(prawidlowaOdpowiedz==true){
                        punkty++;
                    }
                    if(iterator<pytanieArrayList.size()-1) {
                        iterator++;
                        wypisanie(iterator);
                    }
                    else
                        koniec=true;


                }
                else
                    Toast.makeText(MainActivity.this, "Twoja ilosc punktow to: "+punkty, Toast.LENGTH_SHORT).show();



            }
        });

        falszBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(koniec==false) {
                    if(prawidlowaOdpowiedz==false){
                        punkty++;
                    }
                    if(iterator<pytanieArrayList.size()-1) {
                        iterator++;
                        wypisanie(iterator);
                    }
                    else
                        koniec=true;


                }
                else
                    Toast.makeText(MainActivity.this, "Twoja ilosc punktow to: "+punkty, Toast.LENGTH_SHORT).show();



            }
        });

    }

    public void wypisanie(int iterator){
//        pytanie = pytanieArrayList.get(iterator).pytanie;
//        prawidlowaOdpowiedz = pytanieArrayList.get(iterator).prawidlowaOdpowiedz;
//        pytanieView.setText(pytanie);

        try {
            pytanie = Files.readAllLines(Paths.get("dane.txt")).get(iterator);
            prawidlowaOdpowiedz =Boolean.parseBoolean(Files.readAllLines(Paths.get("dane.txt")).get(iterator+1));
            iterator+=2;
            pytanieView.setText(pytanie);

        } catch (IOException e) {
            e.printStackTrace();
            koniec=true;
        }
//        pytanie = pytanieArrayList.get(iterator).pytanie;
//        prawidlowaOdpowiedz = pytanieArrayList.get(iterator).prawidlowaOdpowiedz;
//        pytanieView.setText(pytanie);
    }
}