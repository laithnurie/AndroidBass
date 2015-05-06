package com.example.android.bass.ui.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.android.bass.BassApp;
import com.example.android.bass.R;
import com.example.android.bass.data.bus.ExampleEvent;
import com.example.android.bass.data.bus.RxBus;
import com.example.android.bass.ui.fragment.MainFragment;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.app.AppObservable;
import rx.functions.Action1;
import rx.subscriptions.Subscriptions;

import static com.example.android.bass.ui.activity.SettingsActivity.getSettingsIntent;

public class MainActivity extends BaseActivity {

    @Inject
    RxBus bus;

    private Subscription busSubscription = Subscriptions.empty();

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

        resisterRxBus();
    }

    private void resisterRxBus() {
        Observable<Object> busObservable = AppObservable.bindActivity(this, bus.toObservable());
        busSubscription = busObservable.subscribe(new Action1<Object>() {
            @Override
            public void call(Object event) {
                if (event instanceof ExampleEvent) {
                    Toast.makeText(MainActivity.this, "Message from somewhere else in the app", Toast.LENGTH_SHORT).show();
                }
            }
        });
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
        busSubscription.unsubscribe();
        super.onDestroy();
    }
}
