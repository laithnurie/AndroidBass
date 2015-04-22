package uk.andrewjack.android.bass;

import uk.andrewjack.android.bass.ui.activity.MainActivity;
import uk.andrewjack.android.bass.ui.fragment.MainFragment;
import uk.andrewjack.android.bass.ui.fragment.SettingsFragment;

public interface BassGraph {

    void inject(MainActivity mainActivity);

    void inject(MainFragment mainFragment);

    void inject(SettingsFragment settingsFragment);

}
