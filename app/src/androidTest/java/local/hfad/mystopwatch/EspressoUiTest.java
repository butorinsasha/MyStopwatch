package local.hfad.mystopwatch;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

public class EspressoUiTest {
    @Rule
    public ActivityScenarioRule<StopwatchActivity> rule = new ActivityScenarioRule<>(StopwatchActivity.class);

    private final String TIME_MATCH = "0:00:10";

    @Test
    public void startAndStopStopwatchTest() throws InterruptedException {
        onView(withId(R.id.start_stop_button)).perform(click());

        Thread.sleep(10000L); // Don't know how to wait in Espresso

        onView(withId(R.id.time_view)).check(matches(withText(TIME_MATCH)));

        onView(withId(R.id.start_stop_button)).perform(click());
    }
}
