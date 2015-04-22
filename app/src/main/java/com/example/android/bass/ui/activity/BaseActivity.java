package com.example.android.bass.ui.activity;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.Optional;
import com.example.android.bass.R;

public abstract class BaseActivity extends ActionBarActivity implements ToolbarProvider {

    @Optional
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.inject(this);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
    }

    @Override
    public Toolbar getToolbar() {
        return mToolbar;
    }

}
