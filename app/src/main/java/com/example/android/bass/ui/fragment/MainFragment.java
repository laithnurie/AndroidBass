package com.example.android.bass.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.bass.BassApp;
import com.example.android.bass.R;
import com.example.android.bass.data.api.FiveHundredPxApi;
import com.example.android.bass.data.bus.ExampleEvent;
import com.example.android.bass.data.bus.RxBus;
import com.example.android.bass.data.model.FiveHundredPxPhoto;
import com.example.android.bass.data.model.FiveHundredPxSearchResult;
import com.example.android.bass.ui.adapter.MainAdapter;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.app.AppObservable;
import rx.subscriptions.Subscriptions;
import timber.log.Timber;

public class MainFragment extends BaseFragment implements MainAdapter.Listener, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    FiveHundredPxApi api;
    @Inject
    Picasso picasso;
    @Inject
    RxBus bus;

    @InjectView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @InjectView(R.id.refresh_layout)
    SwipeRefreshLayout mRefreshLayout;

    private Subscription mostPopularSubscription = Subscriptions.empty();

    public static Fragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BassApp.get(getActivity()).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.inject(this, rootView);

        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setColorSchemeResources(R.color.primary, R.color.accent, R.color.primary_dark);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        fetchData();

        bus.send(new ExampleEvent(false));

        return rootView;
    }

    @Override
    public void onRefresh() {
        fetchData();
    }

    private void fetchData() {
        Observable<FiveHundredPxSearchResult> mostPopularObservable = AppObservable.bindFragment(this, api.mostPopular());
        mostPopularSubscription = mostPopularObservable.subscribe(new Subscriber<FiveHundredPxSearchResult>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mRefreshLayout.setRefreshing(false);
                Timber.e(e, "error");
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(FiveHundredPxSearchResult fiveHundredPxSearchResult) {
                mRefreshLayout.setRefreshing(false);
                mRecyclerView.setAdapter(new MainAdapter(picasso, fiveHundredPxSearchResult.getPhotos(), MainFragment.this));
            }
        });
    }

    @Override
    public void onDestroyView() {
        ButterKnife.reset(this);
        mostPopularSubscription.unsubscribe();
        super.onDestroyView();
    }

    @Override
    public void onItemClicked(FiveHundredPxPhoto photo) {
        // use android.support.v7.app.AlertDialog for material styling
        new AlertDialog.Builder(getActivity())
                .setTitle("Hello!")
                .setPositiveButton("OK", null).show();
    }
}
