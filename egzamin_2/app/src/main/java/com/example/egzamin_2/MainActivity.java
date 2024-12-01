package com.example.egzamin_2;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView dice1, dice2, dice3, dice4, dice5;
    private TextView wynik_rzutu, wynik_gry;
    private Button przycisk_rzutu_kostka, przycisk_resetu;

    private int wynik_ogolny = 0;
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dice1 = findViewById(R.id.dice1);
        dice2 = findViewById(R.id.dice2);
        dice3 = findViewById(R.id.dice3);
        dice4 = findViewById(R.id.dice4);
        dice5 = findViewById(R.id.dice5);

        wynik_rzutu = findViewById(R.id.roll_result);
        wynik_gry = findViewById(R.id.game_result);

        przycisk_rzutu_kostka = findViewById(R.id.roll_dice_button);
        przycisk_resetu = findViewById(R.id.reset_button);


        przycisk_rzutu_kostka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rzut_kostka();
            }
        });


        przycisk_resetu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }


    private void rzut_kostka() {
        List<Integer> diceResults = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            diceResults.add(random.nextInt(6) + 1);
        }


        ustaw_dice(dice1, diceResults.get(0));
        ustaw_dice(dice2, diceResults.get(1));
        ustaw_dice(dice3, diceResults.get(2));
        ustaw_dice(dice4, diceResults.get(3));
        ustaw_dice(dice5, diceResults.get(4));


        int wynik_obecnych = oblicz_wynik(diceResults);
        wynik_rzutu.setText("Wynik tego losowania: " + wynik_obecnych);


        wynik_ogolny += wynik_obecnych;
        wynik_gry.setText("Wynik gry: " + wynik_ogolny);
    }


    private void resetGame() {
        wynik_ogolny = 0;
        wynik_rzutu.setText("Wynik tego losowania: 0");
        wynik_gry.setText("Wynik gry: 0");

        dice1.setImageResource(R.drawable.zapytania);
        dice2.setImageResource(R.drawable.zapytania);
        dice3.setImageResource(R.drawable.zapytania);
        dice4.setImageResource(R.drawable.zapytania);
        dice5.setImageResource(R.drawable.zapytania);
    }


    private void ustaw_dice(ImageView dice, int value) {
        int resId = getResources().getIdentifier("dice" + value, "drawable", getPackageName());
        dice.setImageResource(resId);
    }


    private int oblicz_wynik(List<Integer> diceResults) {
        int wynik = 0;
        for (int i = 1; i <= 6; i++) {
            int frequency = Collections.frequency(diceResults, i);
            if (frequency >= 2) {
                wynik += i * frequency;
            }
        }
        return wynik;
    }
}
