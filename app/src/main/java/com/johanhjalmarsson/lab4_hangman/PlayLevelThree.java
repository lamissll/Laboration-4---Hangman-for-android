package com.johanhjalmarsson.lab4_hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PlayLevelThree extends AppCompatActivity {
    Game game3 = new Game("restaurant","department","programmer","catamaran","situation");
    //"restaurant","department","programmer","catamaran","situation"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_level_three);


        game3.initCharList();

        TextView textView = (TextView) findViewById(R.id.displayWord);
        textView.setText(game3.printCharList());


    }
    public void sendString(View view) {
        EditText guessBox = (EditText) findViewById(R.id.guessBox);
        String guess = guessBox.getText().toString();

        takeTurn(guess);
        guessBox.setText(null);

    }
    public void takeTurn(String s) {
        TextView errorBox = (TextView) findViewById(R.id.errorBox);
        errorBox.setVisibility(View.INVISIBLE);
        TextView textView = (TextView) findViewById(R.id.displayWord);
        TextView usedLetters = (TextView) findViewById(R.id.usedLetterBox);
        TextView triesLeft = (TextView) findViewById(R.id.triesLeftBox);
        RelativeLayout hangTheMan = (RelativeLayout) findViewById(R.id.hangManBox);


        if (!usedLetter(s) && !toManyLetters(s)) {
            boolean correct = game3.compareWords(s);
            if (correct) {
                textView.setText(game3.printCharList());
            }
            else if (!correct) {
                usedLetters.setText(game3.getUsedLetters());
                triesLeft.setText(game3.getTriesLeftString());
                switch (game3.getTriesLeft()) {
                    case 0:
                        hangTheMan.setBackgroundResource(R.drawable.hang0);
                        break;
                    case 1:
                        hangTheMan.setBackgroundResource(R.drawable.hang1);
                        break;
                    case 2:
                        hangTheMan.setBackgroundResource(R.drawable.hang2);
                        break;
                    case 3:
                        hangTheMan.setBackgroundResource(R.drawable.hang3);
                        break;
                    case 4:
                        hangTheMan.setBackgroundResource(R.drawable.hang4);
                        break;
                    case 5:
                        hangTheMan.setBackgroundResource(R.drawable.hang5);
                        break;
                    case 6:
                        hangTheMan.setBackgroundResource(R.drawable.hang6);
                        break;
                    case 7:
                        hangTheMan.setBackgroundResource(R.drawable.hang7);
                        break;
                    case 8:
                        hangTheMan.setBackgroundResource(R.drawable.hang8);
                        break;
                    case 9:
                        hangTheMan.setBackgroundResource(R.drawable.hang9);
                        break;
                }

            }
            winOrLoose();

        }

    }

    public void winOrLoose() {
        String secretWord = game3.getSecretWord();
        String triesLeft = game3.getTriesLeftString();
        if (game3.youLose()) {
            Intent intentLoose = new Intent(this, YouLoose.class);
            intentLoose.putExtra(YouLoose.theWord, secretWord);
            intentLoose.putExtra(YouLoose.theInt, triesLeft);
            startActivity(intentLoose);
        }
        else if(game3.youWin()) {
            Intent intentWin = new Intent(this, YouWin.class);
            intentWin.putExtra(YouWin.theWord, secretWord);
            intentWin.putExtra(YouWin.theInt, triesLeft);
            startActivity(intentWin);
        }
    }
    public boolean usedLetter(String s) {
        if (game3.alreadyUsedLetter(s)) {
            TextView errorBox = (TextView) findViewById(R.id.errorBox);
            errorBox.setText("Letter already used!");
            errorBox.setVisibility(View.VISIBLE);
        }

        return game3.alreadyUsedLetter(s);
    }

    public boolean toManyLetters(String s) {
        if (game3.tooManyLetters(s)) {
            TextView errorBox = (TextView) findViewById(R.id.errorBox);
            errorBox.setText("To many letters!");
            errorBox.setVisibility(View.VISIBLE);
        }
        return game3.tooManyLetters(s);
    }

}