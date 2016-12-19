package tent.assist.idleclicker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ResultProvider extends AppCompatActivity{
    EditText clicksText;
    EditText timeText;
    int counter = 0;
    String timeCounter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        clicksText = (EditText) findViewById(R.id.editText);
        timeText = (EditText) findViewById(R.id.editText2);
        counter = (int) getIntent().getSerializableExtra("CLK");
        timeCounter = (String) getIntent().getSerializableExtra("TMR");
    }

    public void getResults (View view) {
        int colon = timeText.getText().length()-3;
        if (clicksText.getText().length()>0 && timeText.getText().length()>3
                && !timeText.getText().toString().substring(0,colon).contains(":")
                && !timeText.getText().toString().substring(colon+1).contains(":") && timeText.getText().toString().charAt(colon) == ':'
                && Integer.parseInt(timeText.getText().toString().substring(colon+1))<60) {
            Intent intent = new Intent(this, ResultReceiver.class);
            intent.putExtra("NEW_CLK", clicksText.getText().toString());
            intent.putExtra("NEW_TMR", timeText.getText().toString());
            intent.putExtra("CLK", counter);
            intent.putExtra("TMR", timeCounter);
            startActivity(intent);
        }
        else Toast.makeText(this, getText(R.string.format_error), Toast.LENGTH_LONG).show();
    }
}