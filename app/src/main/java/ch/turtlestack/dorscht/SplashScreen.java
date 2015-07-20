package ch.turtlestack.dorscht;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;

import ch.turtlestack.dorscht.classPackage.MyApplication;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class SplashScreen extends Activity {

    String now_playing, earned;
    String userInfo = "userInformationFile";
    File userFile;
    MyApplication myApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(ch.turtlestack.dorscht.R.layout.activity_splashscreen);

        /**
         * Showing splashscreen while making network calls to download necessary
         * data before launching the app Will use AsyncTask to make http call
         */
        new PrefetchData().execute();
    }


    /**
     * Async Task to make http call
     */
    private class PrefetchData extends AsyncTask<Void, Void, Void> {
        MyApplication myApplication = (MyApplication)getApplicationContext();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // before making http calls

        }

        @Override
        protected Void doInBackground(Void... arg0) {

            try {
                //Sign In Process
                if (checkForLocalUser()){
                    //If local user information exist
                    //Load local user information
                    loadLocalUser();
                    System.out.println("local user loaded");

                    //Check if internet connection is available
                    if(checkForInternetCon()){
                        //If internet connection is available
                        // TODO: Start google+ authentication
                        System.out.println("local user and internet available");
                        //Check if google+ authentication was successful
                        // TODO: (if/else) Synchronize Data or start app

                    }else {
                        //If internet connection is not available
                        // TODO: Start app
                        System.out.println("local user without internet available");
                    }

                }else {
                    //If local user information do not exist
                    //Check if internet connection is available
                    if(checkForInternetCon()){
                        //If internet connection is available
                        // TODO: Start google+ authentication
                        System.out.println("NO local user but internet available");
                        //getGoogleData();
                        //setLocalUser als Platzhalter
                        setLocalUser();
                        //Check if google+ authentication was successful
                        // TODO: (if/else) Load Data or ask for manual input
                    }else {
                       //If internet connection is not available
                       // TODO: Ask for manual input
                       // setLocalUser();
                        System.out.println("NO local user and NO internet available");
                    }
                }


                Thread.sleep(5000,0);

                now_playing = "xy";
                earned = "afafa";


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // After completing http call
            // will close this activity and launch main activity
            System.out.println("loading GooglePlayActivity");
            Intent i = new Intent(SplashScreen.this, GooglePlayServicesActivity.class);
            i.putExtra("now_playing", now_playing);
            i.putExtra("earned", earned);
            startActivity(i);

            // close this activity
            finish();
        }

        protected boolean checkForLocalUser(){
                // Check if local user information exist
                File myFile = new File(MyApplication.getAppContext().getFilesDir(), userInfo);
                return(myFile.isFile());
        }
        protected void loadLocalUser(){
            StringBuilder text = new StringBuilder();
                try {
                    try {
                        File userFile = new File(MyApplication.getAppContext().getFilesDir(), userInfo);
                        BufferedReader br = new BufferedReader(new FileReader(userFile));
                        String line;
                        while ((line = br.readLine()) != null) {
                            text.append(line);
                            text.append('\n');
                        }
                        br.close();
                        JSONObject jUser = new JSONObject(text.toString());
                        System.out.println(jUser);
                    } catch (IOException e) {
                        //You'll need to add proper error handling here
                        System.out.println(e);
                    }
                } catch (JSONException e) {
                    System.out.println(e);
                }
            }
        private boolean checkForInternetCon(){
            ConnectivityManager connectivityManager
                    = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        private void getGoogleData(){
            //TODO: Use GooglePlayServicesActivity to get google+ data
        }
        private void setLocalUser(){
            JSONObject jUser = myApplication.getUserAccount();


            //Create userInformationFile to save json object into
            //File newUserFile = new File(MyApplication.getAppContext().getFilesDir(), userInfo);
            //newUserFile.mkdir();
            File newUserFile = new File(MyApplication.getAppContext().getFilesDir(), userInfo); //Getting a file within the dir.

            // Create an output stream to write the json object into the file
            try {
                FileOutputStream out = new FileOutputStream(newUserFile);
                try {
                    out.write(jUser.toString().getBytes());
                    out.close();
                    System.out.println("file written");
                } catch (IOException e){
                    System.out.println(e);
                }
            } catch (FileNotFoundException e){
                System.out.println(e);
            }
        }
    }


}

