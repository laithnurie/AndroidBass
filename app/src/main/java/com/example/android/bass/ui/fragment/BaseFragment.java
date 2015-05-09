package com.example.android.bass.ui.fragment;

import android.app.Fragment;

import com.example.android.bass.BassApp;
import com.squareup.leakcanary.RefWatcher;

// Enforces the type of fragment being used
public abstract class BaseFragment extends Fragment {

    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = BassApp.getRefWatcher(getActivity());
        refWatcher.watch(this);
    }
}
