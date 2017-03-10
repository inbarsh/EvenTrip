package com.eventrip;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by inbarsh on 3/10/2017.
 */

public class DownloadTask extends AsyncTask<String, Void, Integer> {

    @Override
    protected Integer doInBackground(String... strings) {
        Integer result = 0;
        HttpURLConnection urlConnection;
        try {
            URL url = new URL(strings[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            int statusCode = urlConnection.getResponseCode();

            // 200 represents HTTP OK
            if (statusCode == 200) {
                BufferedReader r = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = r.readLine()) != null) {
                    response.append(line);
                }
                parseResult(response.toString());
                result = 1; // Successful
            } else {
                result = 0; //"Failed to fetch data!";
            }
        } catch (Exception e) {
            Log.d("download task", e.getLocalizedMessage());
        }
        return result; //"Failed to fetch data!";
    }
    private void parseResult(String result) {
        try {
            JSONObject response = new JSONObject(result);
            //JSONArray posts = response.optJSONArray("posts");
            //feedsList = new ArrayList<>();

//            for (int i = 0; i < posts.length(); i++) {
//                JSONObject post = posts.optJSONObject(i);
//                FeedItem item = new FeedItem();
//                item.setTitle(post.optString("title"));
//                item.setThumbnail(post.optString("thumbnail"));
//                feedsList.add(item);
//            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
