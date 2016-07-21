package com.gnosishub.hellojobqueue;

import android.support.annotation.NonNull;

import com.birbit.android.jobqueue.JobManager;
import com.birbit.android.jobqueue.scheduling.FrameworkJobSchedulerService;

/**
 * Created by Tapasya on 7/21/16.
 */
public class MyJobService extends FrameworkJobSchedulerService {
    @NonNull
    @Override
    protected JobManager getJobManager() {
        return HelloJobQueueApp.getInstance().getJobManager();
    }
}