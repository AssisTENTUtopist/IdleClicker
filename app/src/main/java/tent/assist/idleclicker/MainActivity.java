package tent.assist.idleclicker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final int ANSWER_TO_THE_ULTIMATE_QUESTION_OF_LIFE_THE_UNIVERSE_AND_EVERYTHING = 42;
    Button butt, bottomButt;
    int counter = 0;
    int secs = 0;
    int seconds = 0;
    int minutes = 0;
    boolean active = false;

    public void setResults(View view) {
        Intent intent = new Intent(MainActivity.this, ResultProvider.class);
        intent.putExtra("CLK", counter);
        minutes = seconds / 60;
        secs = seconds % 60;
        if (seconds < 10) {
            intent.putExtra("TMR", "" + minutes + ":0" + secs);
        } else {
            intent.putExtra("TMR", "" + minutes + ":" + secs);
        }
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        butt = (Button) findViewById(R.id.button);
        bottomButt = (Button) findViewById(R.id.button3);

        View.OnClickListener buttSlap = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (active) counter++;
                else Toast.makeText(MainActivity.this, R.string.toast, Toast.LENGTH_SHORT).show();
            }
        };

        View.OnClickListener fButtSlap = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!active) new Thread() {
                    public void run() {
                        while (ANSWER_TO_THE_ULTIMATE_QUESTION_OF_LIFE_THE_UNIVERSE_AND_EVERYTHING==42) {
                            active=true;
                            seconds++;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }.start();
                else Toast.makeText(MainActivity.this, R.string.its_time_to_stop, Toast.LENGTH_SHORT).show();
            }
        };

        bottomButt.setOnClickListener(fButtSlap);
        butt.setOnClickListener(buttSlap);
    }
}