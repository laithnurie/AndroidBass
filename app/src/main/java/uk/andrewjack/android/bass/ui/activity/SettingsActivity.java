package uk.andrewjack.android.bass.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import uk.andrewjack.android.bass.R;
import uk.andrewjack.android.bass.ui.fragment.SettingsFragment;


public class SettingsActivity extends BaseActivity {

    public static Intent getSettingsIntent(Context context) {
        return new Intent(context, SettingsActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (getFragmentManager().findFragmentById(R.id.container) == null) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, SettingsFragment.newInstance())
                    .commit();
        }
    }
}
