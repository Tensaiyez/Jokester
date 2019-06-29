package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.androidlibrary.AndroidActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

// Sources for AsyncTask Testing: http://marksunghunpark.blogspot.com/2015/05/how-to-test-asynctask-in-android.html $ https://stackoverflow.com/questions/2321829/android-asynctask-testing-with-android-test-framework

public class EndpointsAsyncTask extends AsyncTask<Context, Void, String> {
    private static MyApi myApiService = null;
    private Context context;
    private TaskListener taskListener = null;

    @Override
    protected String doInBackground(Context... params) {
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }


        try {
            return myApiService.tellJoke().execute().getData();

        } catch (IOException e) {
            Log.d("ExceptionCheck :",e.getMessage());
            return null;
        }
    }

    public  EndpointsAsyncTask setListener(TaskListener listener) {
        this.taskListener = listener;
        return this;
    }

    public EndpointsAsyncTask(TaskListener listener) {
        taskListener = listener;
    }

    @Override
    protected void onPostExecute(String result) {
        if (taskListener != null)
            taskListener.returnJoke(result);
    }
    public interface TaskListener {
        void returnJoke(String result);
    }
}

