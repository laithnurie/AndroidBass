package com.example.android.bass;

import com.example.android.bass.ui.activity.MainActivity;
import com.example.android.bass.ui.fragment.MainFragment;
import com.example.android.bass.ui.fragment.SettingsFragment;

public interface BassGraph {

    void inject(MainActivity mainActivity);

    void inject(MainFragment mainFragment);

    void inject(SettingsFragment settingsFragment);

}
