package com.gnosishub.hellojobqueue;

import android.support.annotation.NonNull;

import com.birbit.android.jobqueue.JobManager;
import com.birbit.android.jobqueue.scheduling.GcmJobSchedulerService;

/**
 * Created by Tapasya on 7/21/16.
 */
public class MyGcmJobService extends GcmJobSchedulerService {
    @NonNull
    @Override
    protected JobManager getJobManager() {
        return HelloJobQueueApp.getInstance().getJobManager();
    }
}