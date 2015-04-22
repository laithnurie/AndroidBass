package uk.andrewjack.android.bass.ui.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import uk.andrewjack.android.bass.BassApp;
import uk.andrewjack.android.bass.R;
import uk.andrewjack.android.bass.data.event.ExampleEvent;
import uk.andrewjack.android.bass.ui.fragment.MainFragment;

import static uk.andrewjack.android.bass.ui.activity.SettingsActivity.getSettingsIntent;

public class MainActivity extends BaseActivity {

    @Inject
    Bus bus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BassApp.get(this).inject(this);
        setContentView(R.layout.activity_default);

        if (getFragmentManager().findFragmentById(R.id.container) == null) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commit();
        }

        bus.register(this);
    }

    @Subscribe
    public void ohReally(ExampleEvent event) {
        Toast.makeText(this, "Message from somewhere else in the app", Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            startActivity(getSettingsIntent(this));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        bus.unregister(this);
        super.onDestroy();
    }
}
