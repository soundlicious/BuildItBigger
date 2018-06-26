package udacity.com.gotham;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ArkhamAsylumActivity extends AppCompatActivity {
    public static final String JOKER_VENOM = "com.udacity.builtitbigger.JOKER_VENOM";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arkham);
        final String jokerVenom = getIntent().getStringExtra(JOKER_VENOM);
        TextView jokerVenomText = findViewById(R.id.textview_jokerVenom);
        if (jokerVenom != null)
            jokerVenomText.setText(jokerVenom);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent upIntent = NavUtils.getParentActivityIntent(this);
                if (upIntent != null && NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    // This activity is NOT part of this app's task, so create a new task
                    // when navigating up, with a synthesized back stack.
                    TaskStackBuilder.create(this)
                            // Add all of this activity's parents to the back stack
                            .addNextIntentWithParentStack(upIntent)
                            // Navigate up to the closest parent
                            .startActivities();
                } else if (upIntent != null) {
                    // This activity is part of this app's task, so simply
                    // navigate up to the logical parent activity.
                        NavUtils.navigateUpTo(this, upIntent);
                } else
                    onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
