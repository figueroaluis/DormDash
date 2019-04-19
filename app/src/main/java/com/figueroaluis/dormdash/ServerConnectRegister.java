package com.figueroaluis.dormdash;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

public class ServerConnectRegister extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String[] params) {
        try{
            System.out.println(params[0]);
            System.out.println(params[1]);

            URL page = new URL("http://10.0.2.2:8080/register?username=" + params[0] + "&password=" + params[1]);
            StringBuffer text = new StringBuffer();
            HttpURLConnection conn = (HttpURLConnection) page.openConnection();
            conn.connect();
            InputStreamReader in = new InputStreamReader((InputStream) conn.getContent());
            BufferedReader buff = new BufferedReader(in);

            String line;
            do {
                line = buff.readLine();
                text.append(line + "\n");
            } while (line != null);

            System.out.println(text.toString());


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
