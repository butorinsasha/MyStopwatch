package local.hfad.mystopwatch;

import static android.widget.Toast.LENGTH_SHORT;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class StopwatchActivity extends Activity {

    private int seconds = 0;
    private boolean isRunning; // false (default)
    private boolean wasRunning; // false (default)
    private View greenBulb;
    private View yellowBulb;
    private View redBulb;
    Button startStopButton;
    TextView timeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i(this.getLocalClassName(), "is onCreate()");
        Toast.makeText(this, "is onCreate()", LENGTH_SHORT).show();

        setContentView(R.layout.activity_stopwatch);

        greenBulb = findViewById(R.id.green_bulb_view);
        yellowBulb = findViewById(R.id.yellow_bulb_view);
        redBulb = findViewById(R.id.red_bulb_view);
        startStopButton = findViewById(R.id.start_stop_button);
        timeView = findViewById(R.id.time_view);

        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            isRunning = savedInstanceState.getBoolean("isRunning");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }

        Log.i(this.getLocalClassName(), "isRunning = " + isRunning);
        Log.i(this.getLocalClassName(), "wasRunning = " + wasRunning);

        runTimer();
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.i(this.getLocalClassName(), "is onStart()");
        Toast.makeText(this, "is onStart()", LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.i(this.getLocalClassName(), "is onResume()");
        Toast.makeText(this, "is onResume()", LENGTH_SHORT).show();

        if(wasRunning){
            isRunning = true;
            startStopButton.setText(R.string.stop);
        } else {
            setTime(seconds);
            lightBulb(seconds);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.i(this.getLocalClassName(), "is onPause()");
        Toast.makeText(this, "is onPause()", LENGTH_SHORT).show();

        wasRunning = isRunning;
        isRunning = false;
    }

    // If onStop() is called because the activity’s going to be destroyed, onSaveInstanceState() gets called before onStop().
    // ^ Tested: onSaveInstanceState() is called before onStop()
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        Log.i(this.getLocalClassName(), "is onSaveInstanceState()");
        Toast.makeText(this, "is onSaveInstanceState()", LENGTH_SHORT).show();

        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("isRunning", isRunning);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.i(this.getLocalClassName(), "is onStop()");
        Toast.makeText(this, "is onStop()", LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.i(this.getLocalClassName(), "is onRestart()");
        Toast.makeText(this, "is onRestart()", LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.i(this.getLocalClassName(), "is onDestroy()");
        Toast.makeText(this, "is onDestroy()", LENGTH_SHORT).show();
    }

    public void onClickStartStop(View view) {
        Log.i(this.getLocalClassName(), "At the start of onClickStartStop(); isRunning=" + isRunning);
        if (!isRunning) {
            isRunning = true;
            startStopButton.setText(R.string.stop);
        } else {
            isRunning = false;
            startStopButton.setText(R.string.start);
        }
        Log.i(this.getLocalClassName(), "At the end of onClickStartStop(); isRunning=" + isRunning);
    }

//    public void onClickStop(View view) {
//        isRunning = false;
//    }

    public void onClickReset(View view) {
        isRunning = false;
        seconds = 0;
        setTime(seconds);
        startStopButton.setText(R.string.start);
        greenBulb.setBackgroundColor(getResources().getColor(R.color.grey));
        yellowBulb.setBackgroundColor(getResources().getColor(R.color.grey));
        redBulb.setBackgroundColor(getResources().getColor(R.color.grey));
    }

    private void setTime(int seconds) {
        int hours = seconds / 3600;
        int mins = (seconds % 3600) / 60;
        int secs = seconds % 60;
        String time = String.format("%d:%02d:%02d", hours, mins, secs);
        timeView.setText(time);
    }

    private void runTimer() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
                         @Override
                         public void run() {
                             Log.i(this.getClass().getName(), "is in run() of runTimer()");
                             if (isRunning) {
                                 Log.i(this.getClass().getName(), "isRunning = " + isRunning);
                                 setTime(seconds);
                                 lightBulb(seconds);
                             }
                             Log.i(this.getClass().getName(), "isRunning = " + isRunning);
                             handler.postDelayed(this, 1000);
                         }
                     }
        );
    }

    private void lightBulb(int seconds) {
        if (seconds == 0) {
            Log.i(this.toString(), "Switch green");
            greenBulb.setBackgroundColor(getResources().getColor(R.color.grey));
            yellowBulb.setBackgroundColor(getResources().getColor(R.color.grey));
            redBulb.setBackgroundColor(getResources().getColor(R.color.grey));
        } else {
            switch (seconds % 3){
                case (1):
                    Log.i(this.toString(), "Switch green");
                    greenBulb.setBackgroundColor(getResources().getColor(R.color.green));
                    yellowBulb.setBackgroundColor(getResources().getColor(R.color.grey));
                    redBulb.setBackgroundColor(getResources().getColor(R.color.grey));
                    break;
                case (2):
                    Log.i(this.getClass().getName(), "Switch yellow");
                    greenBulb.setBackgroundColor(getResources().getColor(R.color.grey));
                    yellowBulb.setBackgroundColor(getResources().getColor(R.color.yellow));
                    redBulb.setBackgroundColor(getResources().getColor(R.color.grey));
                    break;
                case (0):
                    Log.i(this.getClass().getName(), "Switch red");
                    greenBulb.setBackgroundColor(getResources().getColor(R.color.grey));
                    yellowBulb.setBackgroundColor(getResources().getColor(R.color.grey));
                    redBulb.setBackgroundColor(getResources().getColor(R.color.red));
                    break;
            }
        }
    }
}