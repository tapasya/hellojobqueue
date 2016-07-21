package com.gnosishub.hellojobqueue;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.birbit.android.jobqueue.CancelReason;
import com.birbit.android.jobqueue.Job;
import com.birbit.android.jobqueue.Params;
import com.birbit.android.jobqueue.RetryConstraint;
import com.birbit.android.jobqueue.TagConstraint;
import com.jakewharton.rxbinding.view.RxView;

import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends AppCompatActivity {

    private CompositeSubscription mCompositeSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCompositeSubscription = new CompositeSubscription();

        mCompositeSubscription.add(RxView.clicks(findViewById(R.id.btn_illegal_ex))
                .observeOn(Schedulers.io())
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        triggerIllegalStateException();
                    }
                }));

        mCompositeSubscription.add(RxView.clicks(findViewById(R.id.btn_sql_ex))
                .observeOn(Schedulers.io())
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        triggerSqlException();
                    }
                }));
    }

    private void triggerSqlException() {
        for (int i = 0; i < 5000; i++) {
            HelloJobQueueApp.getInstance().getJobManager().cancelJobsInBackground(null, TagConstraint.ANY, "PostTweetDelayJob");
            HelloJobQueueApp.getInstance().getJobManager().addJobInBackground(new PostTweetDelayJob("text"));
        }
    }

    private void triggerIllegalStateException() {
        for (int i = 0; i < 200; i++) {
            HelloJobQueueApp.getInstance().getJobManager().addJobInBackground(new PostTweetJob(i));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeSubscription.unsubscribe();
    }
}
