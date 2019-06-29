package com.udacity.gradle.builditbigger;

import android.support.test.runner.AndroidJUnit4;
import android.text.TextUtils;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class EndpointsAsyncTaskTest {
    // Sources for AsyncTask Testing: http://marksunghunpark.blogspot.com/2015/05/how-to-test-asynctask-in-android.html $ https://stackoverflow.com/questions/2321829/android-asynctask-testing-with-android-test-framework
   private String mJoke=null;
    @Test
    public void testAsyncTask()throws InterruptedException {


        final CountDownLatch signal = new CountDownLatch(1);
        EndpointsAsyncTask AsyncTask=new EndpointsAsyncTask(new EndpointsAsyncTask.TaskListener() {
            @Override
            public void returnJoke(String result) {
             mJoke=result;
                signal.countDown();
            }
        });
        AsyncTask.execute();
       try{
            signal.await();
           assertNotNull("Joke is null",mJoke);
           assertFalse("Myclass Joke[] is an empty strings",TextUtils.isEmpty(mJoke));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}