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


        Log.i(this.getClass().getName(), "1. wasRunning = " + wasRunning);
        Log.i(this.getClass().getName(), "2. isRunning = " + isRunning);

        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            isRunning = savedInstanceState.getBoolean("isRunning");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }

        Log.i(this.getClass().getName(), "3. wasRunning = " + wasRunning);
        Log.i(this.getClass().getName(), "4. isRunning = " + isRunning);

        runTimer();

        Log.i(this.getClass().getName(), "5. wasRunning = " + wasRunning);
        Log.i(this.getClass().getName(), "6. isRunning = " + isRunning);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.i(this.getLocalClassName(), "is onStart()");
        Toast.makeText(this, "is onStart()", LENGTH_SHORT).show();
        if(wasRunning) {
            Log.i(this.getClass().getName(), "7. wasRunning = " + wasRunning);
            Log.i(this.getClass().getName(), "8. isRunning = " + isRunning);

            isRunning = true;

            Log.i(this.getClass().getName(), "9. wasRunning = " + wasRunning);
            Log.i(this.getClass().getName(), "10. isRunning = " + isRunning);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.i(this.getLocalClassName(), "is onResume()");
        Toast.makeText(this, "is onResume()", LENGTH_SHORT).show();

        Log.i(this.getClass().getName(), "11. wasRunning = " + wasRunning);
        Log.i(this.getClass().getName(), "12. isRunning = " + isRunning);

        if (wasRunning) {
            isRunning = true;
        }

        Log.i(this.getClass().getName(), "13. wasRunning = " + wasRunning);
        Log.i(this.getClass().getName(), "14. isRunning = " + isRunning);
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.i(this.getLocalClassName(), "is onPause()");
        Toast.makeText(this, "is onPause()", LENGTH_SHORT).show();

        Log.i(this.getClass().getName(), "15. wasRunning = " + wasRunning);
        Log.i(this.getClass().getName(), "isRunning = " + isRunning);

        wasRunning = isRunning;
        isRunning = false;

        Log.i(this.getClass().getName(), "16. wasRunning = " + wasRunning);
        Log.i(this.getClass().getName(), "17. isRunning = " + isRunning);
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.i(this.getClass().getName(), "18. wasRunning = " + wasRunning);
        Log.i(this.getClass().getName(), "19. isRunning = " + isRunning);

        wasRunning = isRunning;
        isRunning = false;

        Log.i(this.getClass().getName(), "20. wasRunning = " + wasRunning);
        Log.i(this.getClass().getName(), "21. isRunning = " + isRunning);

        Log.i(this.getLocalClassName(), "is onStop()");
        Toast.makeText(this, "is onStop()", LENGTH_SHORT).show();
    }

    // If onStop() is called because the activity’s going to be destroyed, onSaveInstanceState() gets called before onStop().
    // ^ Tested: onSaveInstanceState() is called after onStop()
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        Log.i(this.getLocalClassName(), "is onSaveInstanceState()");
        Toast.makeText(this, "is onSaveInstanceState()", LENGTH_SHORT).show();


        Log.i(this.getClass().getName(), "22. wasRunning = " + wasRunning);
        Log.i(this.getClass().getName(), "23. isRunning = " + isRunning);

        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("isRunning", isRunning);
        savedInstanceState.putBoolean("wasRunning", wasRunning);

        Log.i(this.getClass().getName(), "24. wasRunning = " + wasRunning);
        Log.i(this.getClass().getName(), "25. isRunning = " + isRunning);
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
            Log.i(this.getLocalClassName(), "at if");
            isRunning = true;
            startStopButton.setText(R.string.stop);
        } else {
            Log.i(this.getLocalClassName(), "at else");
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
                                 switch (seconds % 3){
                                     case (0):
                                         Log.i(this.toString(), "Switch green");
                                         greenBulb.setBackgroundColor(getResources().getColor(R.color.green));
                                         yellowBulb.setBackgroundColor(getResources().getColor(R.color.grey));
                                         redBulb.setBackgroundColor(getResources().getColor(R.color.grey));
                                         break;
                                     case (1):
                                         Log.i(this.getClass().getName(), "Switch yellow");
                                         greenBulb.setBackgroundColor(getResources().getColor(R.color.grey));
                                         yellowBulb.setBackgroundColor(getResources().getColor(R.color.yellow));
                                         redBulb.setBackgroundColor(getResources().getColor(R.color.grey));
                                         break;
                                     case (2):
                                         Log.i(this.getClass().getName(), "Switch red");
                                         greenBulb.setBackgroundColor(getResources().getColor(R.color.grey));
                                         yellowBulb.setBackgroundColor(getResources().getColor(R.color.grey));
                                         redBulb.setBackgroundColor(getResources().getColor(R.color.red));
                                         break;
                                 }
                                 seconds++;
                             }
                             Log.i(this.getClass().getName(), "isRunning = " + isRunning);
                             handler.postDelayed(this, 1000);
                         }
                     }
        );
    }
}