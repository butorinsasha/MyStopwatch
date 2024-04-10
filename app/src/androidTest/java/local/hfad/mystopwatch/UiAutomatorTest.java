package local.hfad.mystopwatch;


import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertEquals;

import android.util.Log;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.Until;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class UiAutomatorTest {
    @Rule
    public ActivityScenarioRule<StopwatchActivity> rule = new ActivityScenarioRule<>(StopwatchActivity.class);

    public UiDevice device = UiDevice.getInstance(getInstrumentation());

    private final long TIME_MATCHES_TIMEOUT = 11000L;
    private final String TIME_MATCH = "0:00:10";

    private static final String TARGET_PACKAGE = androidx.test.core.app.ApplicationProvider.getApplicationContext().getPackageName();

    @Test
    public void startAndStopStopwatchTest() {

        UiObject2 stopButtonUiObject2 = device.findObject(By.res(TARGET_PACKAGE, "start_stop_button"));
        stopButtonUiObject2.click();

        UiObject2 timeViewUiObject2 = device.findObject(By.res("local.hfad.mystopwatch:id/time_view"));
        timeViewUiObject2.wait(Until.textMatches(TIME_MATCH), TIME_MATCHES_TIMEOUT);

        stopButtonUiObject2.click();

        assertEquals(timeViewUiObject2.getText(), TIME_MATCH);

        Log.i(this.getClass().getName(), "TARGET_PACKAGE =" + TARGET_PACKAGE);
    }
}
