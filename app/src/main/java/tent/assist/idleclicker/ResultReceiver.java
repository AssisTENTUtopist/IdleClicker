package tent.assist.idleclicker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class ResultReceiver extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView timeView, clicksView;

        timeView = (TextView) findViewById(R.id.timeView);
        clicksView = (TextView) findViewById(R.id.clicksView);

        int counter = (int) getIntent().getSerializableExtra("CLK");
        String timeCounter = (String) getIntent().getSerializableExtra("TMR");
        String playersClicks = (String) getIntent().getSerializableExtra("NEW_CLK");
        String playersTime = (String) getIntent().getSerializableExtra("NEW_TMR");

        int clicksResidual = Math.abs(Integer.parseInt(playersClicks)-counter);
        if (clicksResidual<2) clicksView.setBackgroundColor(getResources().getColor(R.color.colorVeryClose));
        else if (clicksResidual<10) clicksView.setBackgroundColor(getResources().getColor(R.color.colorAverageDistance));
        else clicksView.setBackgroundColor(getResources().getColor(R.color.colorTooFar));

        int timeSecs = (Integer.parseInt(timeCounter.substring(0,timeCounter.indexOf(":"))))*60
                + Integer.parseInt((timeCounter.charAt(timeCounter.length()-2))+""
                +timeCounter.charAt(timeCounter.length()-1));
        int playersTimeSecs = (Integer.parseInt(playersTime.substring(0,playersTime.indexOf(":"))))*60
                + Integer.parseInt((playersTime.charAt(playersTime.length()-2))+""
                +playersTime.charAt(playersTime.length()-1));
        int timeResidual = Math.abs(timeSecs-playersTimeSecs);
        if (timeResidual<10) timeView.setBackgroundColor(getResources().getColor(R.color.colorVeryClose));
        else if (timeResidual<30) timeView.setBackgroundColor(getResources().getColor(R.color.colorAverageDistance));
        else timeView.setBackgroundColor(getResources().getColor(R.color.colorTooFar));

        clicksView.setText(String.format(Locale.getDefault(), "%s\n\n%s\t%d\n%s\t%s",
                getText(R.string.clicks_text), getText(R.string.real), counter, getText(R.string.expected), playersClicks));
        timeView.setText(String.format(Locale.getDefault(), "%s\n\n%s\t%s\n%s\t%s",
                getText(R.string.time_text), getText(R.string.real), timeCounter, getText(R.string.expected), playersTime));
    }

    /**if (timeResidual%60 < 10) {
     timeResidualString = timeResidual/60 + ":0" + timeResidual%60;
     } else {
     timeResidualString = timeResidual/60 + ":" + timeResidual%60;
     }
     */

    public void getBack (View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
