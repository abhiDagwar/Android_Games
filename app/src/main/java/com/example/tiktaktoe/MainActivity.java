package com.example.tiktaktoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    //0: empty, 1: yellow, 2: red

    int [] gameState = {0, 0, 0, 0, 0, 0, 0, 0, 0};

    int [][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    int activePlayer = 1;

    boolean isGameActive = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playAgain(View view) {
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

        playAgainButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
            gameState[i] = 0;
        }

        activePlayer = 1;
        isGameActive = true;
    }

    public void selectMove(View view) {
        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 0 && isGameActive) {

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1500);

            if (activePlayer == 1) {
                counter.setImageResource(R.drawable.yellow);

                activePlayer = 2;
            } else {
                counter.setImageResource(R.drawable.red);

                activePlayer = 1;
            }

            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

            for (int[] winningPosotion : winningPositions) {

                if (gameState[winningPosotion[0]] == gameState[winningPosotion[1]] && gameState[winningPosotion[1]] == gameState[winningPosotion[2]] && gameState[winningPosotion[0]] != 0) {
                    //someone has won
                    isGameActive = false;
                    String winner = "";

                    if (activePlayer == 1) {
                        winner += "Red";
                    } else {
                        winner += "Yellow";
                    }

                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

                    winnerTextView.setText(winner + " has won!");

                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);
                } else if (gameState[0] != 0 && gameState[1] != 0 && gameState[2] != 0 && gameState[3] != 0 && gameState[4] != 0 && gameState[5] != 0 && gameState[6] != 0 && gameState[7] != 0 && gameState[8] != 0) {
                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

                    winnerTextView.setText("Game Over! Play Again!!!");

                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);
                }
            }
        }
    }
}