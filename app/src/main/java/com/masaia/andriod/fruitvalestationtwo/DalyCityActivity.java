package com.masaia.andriod.fruitvalestationtwo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DalyCityActivity extends AppCompatActivity {

    TextView firstTrainTextView;
    TextView secondTrainTextView;
    TextView thirdTrainTextView;

    TextView firstPlatformTextView;
    TextView secondPlatformTextView;
    TextView thirdPlatformTextView;

    TextView firstLengthTextView;
    TextView secondLengthTextView;
    TextView thirdLengthTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daly_city);
        firstTrainTextView = findViewById(R.id.first_train);
        secondTrainTextView = findViewById(R.id.second_train);
        thirdTrainTextView = findViewById(R.id.third_train);

        firstPlatformTextView = findViewById(R.id.first_platform);
        secondPlatformTextView = findViewById(R.id.second_platform);
        thirdPlatformTextView = findViewById(R.id.third_platform);

        firstLengthTextView = findViewById(R.id.first_length);
        secondLengthTextView = findViewById(R.id.second_length);
        thirdLengthTextView = findViewById(R.id.third_length);

    }

    public void checkTimes(View view) {
// ...

// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://api.bart.gov/api/etd.aspx?cmd=etd&orig=ftvl&json=y&key=MW9S-E7SL-26DU-VV8V";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject responseJson = new JSONObject(response);
                            JSONObject root = responseJson.getJSONObject("root");
                            JSONArray station = root.getJSONArray("station");
                            JSONObject fruitvale = station.getJSONObject(0);
                            JSONArray etd = fruitvale.getJSONArray("etd");
                            JSONObject dalyCity = etd.getJSONObject(0);
                            JSONArray estimate = dalyCity.getJSONArray("estimate");

                            JSONObject firstTrain = estimate.getJSONObject(0);
                            String firstTrainMinutes = firstTrain.getString("minutes");
                            firstTrainTextView.setText("First Train: " + firstTrainMinutes + " minutes");
                            String firstTrainPlatform = firstTrain.getString("platform");
                            firstPlatformTextView.setText("Platform " + firstTrainPlatform);
                            String firstTrainLength = firstTrain.getString("length");
                            firstLengthTextView.setText("Length " + firstTrainLength);

                            JSONObject secondTrain = estimate.getJSONObject(1);
                            String secondTrainMinutes = secondTrain.getString("minutes");
                            secondTrainTextView.setText("Second Train: " + secondTrainMinutes + " minutes");
                            String secondTrainPlatform = secondTrain.getString("platform");
                            secondPlatformTextView.setText("Platform " + secondTrainPlatform);
                            String secondTrainLength = firstTrain.getString("length");
                            secondLengthTextView.setText("Length " + secondTrainLength);

                            JSONObject thirdTrain = estimate.getJSONObject(2);
                            String thirdTrainMinutes = thirdTrain.getString("minutes");
                            thirdTrainTextView.setText("Third Train: " + thirdTrainMinutes + " minutes");
                            String thirdTrainPlatform = thirdTrain.getString("platform");
                            thirdPlatformTextView.setText("Platform " + thirdTrainPlatform);
                            String thirdTrainLength = firstTrain.getString("length");
                            thirdLengthTextView.setText("Length " + thirdTrainLength);




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                firstTrainTextView.setText("That didn't work!");
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
