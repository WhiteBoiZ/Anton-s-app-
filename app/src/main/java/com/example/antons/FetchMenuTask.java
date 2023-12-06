package com.example.antons;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FetchMenuTask extends AsyncTask<Void, Void, List<Food>> {
    private FoodAdapter FoodAdapter;

    public FetchMenuTask() {
        this.FoodAdapter = FoodAdapter;
    }

    @Override
    protected List<Food> doInBackground(Void... voids) {
        String urlString = "http://10.82.252.98:8080/Antons-Skafferi-Webb-1.0-SNAPSHOT/api/alacarte/all";
        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                String jsonResponse = readStream(in);
                return parseJson(jsonResponse);
            } finally {
                urlConnection.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Food> result) {
        if (result != null) {
            FoodAdapter.updateData(result);
        } else {
            // Visa felmeddelande eller försök hämta data igen

           // FoodError();
        }
    }

    private String readStream(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append('\n');
        }
        return sb.toString();
    }

    private List<Food> parseJson(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        List<Food> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            Food item = new Food(
                    obj.getInt("id"),
                    obj.getString("titel"),
                    obj.getString("beskrivning"),
                    obj.getString("type"),
                    obj.getBoolean("vald"),
                    obj.getInt("pris")
            );
            list.add(item);
        }
        return list;
    }
}
