package com.example.android.bass.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import javax.inject.Inject;

import de.psdev.licensesdialog.LicensesDialog;
import com.example.android.bass.BassApp;
import com.example.android.bass.R;
import com.example.android.bass.data.AppInfoHelper;

public class SettingsFragment extends PreferenceFragment {

    @Inject
    AppInfoHelper appInfoHelper;

    public static Fragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BassApp.get(getActivity()).inject(this);
        addPreferencesFromResource(R.xml.preferences);

        setOnClickEvents();
        setVersionName();
    }

    private void setOnClickEvents() {
        findPreference(getString(R.string.key_os_licences))
                .setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                    @Override
                    public boolean onPreferenceClick(Preference preference) {
                        showLicencesDialog();
                        return true;
                    }
                });
    }

    private void setVersionName() {
        Preference versionName = findPreference(getString(R.string.key_version));
        String name = appInfoHelper.getVersionName();
        if (name != null) {
            versionName.setSummary(name);
        } else {
            versionName.setSummary(getString(R.string.version_name_error));
        }
    }

    private void showLicencesDialog() {
        new LicensesDialog.Builder(getActivity()).setNotices(R.raw.notices).setIncludeOwnLicense(true).build().show();
    }

}