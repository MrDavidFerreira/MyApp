package com.naujcoder.myapp;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class MainActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m2ejemplo1_layout);

        Button button = findViewById(R.id.enviar);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        new SendPostRequest().execute();
    }

    public class SendPostRequest extends AsyncTask<String, Void, String> {

        protected void onPreExecute(){}

        protected String doInBackground(String... arg0) {
            EditText editTextURL = findViewById(R.id.url);
            EditText editTextSalida = findViewById(R.id.salida);

            try {

                URL url = new URL(editTextURL.getText().toString());
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader in = new BufferedReader(inputStreamReader);

                StringBuffer sb = new StringBuffer("");
                String lineaLeida = "";
                while ((lineaLeida = in.readLine()) != null) {
                    sb.append(lineaLeida + "\n");
                }
                in.close();
                editTextSalida.setText(sb);
                return "Ok";
            } catch (Exception e) {
                editTextSalida.setText("Error: " + e.toString());
                return "Error";
            }
        }

        @Override
        protected void onPostExecute(String result) {}
    }
}
