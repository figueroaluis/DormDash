package com.figueroaluis.dormdash;

import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ServerConnect extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String[] params) {
            try{URL url = new URL("http://www.yahoo.com");
                HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();
                httpConn.setRequestMethod("GET");
                InputStream inputStream = httpConn.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = bufferedReader.readLine();
                httpConn.disconnect();
                System.out.println("Made It");



            }
            catch(MalformedURLException MURL){}
            catch (IOException io){}

            return null;
        }


    @Override
    protected void onPostExecute(String message) {
            //process message
        }


}
