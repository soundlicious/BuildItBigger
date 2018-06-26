import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.udacity.gradle.builditbigger.MainActivity;
import com.udacity.gradle.builditbigger.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import udacity.com.gotham.ArkhamAsylumActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.ComponentNameMatchers.hasClassName;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(AndroidJUnit4.class)
public class AndroidTestLib {
    @Rule
    public IntentsTestRule<MainActivity> mIntentsRule = new IntentsTestRule<>(MainActivity.class);

    @Test
    public void test_if_string_extra_from_intent_is_not_empty() {
        onView(withId(R.id.button_telljoke)).perform(click());
        intended(hasComponent(hasClassName(ArkhamAsylumActivity.class.getName())));
        intended(hasExtra(equalTo(ArkhamAsylumActivity.JOKER_VENOM), notNullValue()));
        intended(hasExtra(equalTo(ArkhamAsylumActivity.JOKER_VENOM),  not(withText(""))));
    }
}
