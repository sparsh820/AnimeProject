package com.example.animeproject;

import android.content.Context;
import android.net.ConnectivityManager;
import android.text.TextUtils;
import android.util.Log;

import com.example.animeproject.Models.Anime;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class QueryUtils {
    public static final String LOG_TAG = QueryUtils.class.getSimpleName();


    private QueryUtils() {
    }


    public static ArrayList<Anime> extractAnimesFromJson(String AnimeJSON) {

        if (TextUtils.isEmpty(AnimeJSON)) {
            return null;
        }


        ArrayList<Anime> animes = new ArrayList<>();


        try {

            JSONObject basejsonresponse = new JSONObject(AnimeJSON);
            JSONArray AnimeArray = basejsonresponse.optJSONArray("results");

            for(int i=0; i < AnimeArray.length(); i++){
                JSONObject currentAnime = AnimeArray.getJSONObject(i);
                String image_url=currentAnime.getString("image_url");
                Log.i("imagUrl",image_url);
                String title=currentAnime.getString("title");
                Log.i("title",title);
                String desc=currentAnime.getString("synopsis");
                Log.i("desc",desc);
                String noe=currentAnime.getString("episodes");
                String url=currentAnime.getString("url");
                String score=currentAnime.getString("score");
                Anime anime=new Anime(score,url,desc,title,image_url,noe);
                animes.add(anime);

            }




        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results",e);
        }


        return animes;
    }

    public static ArrayList<Anime> extractAnimes(String RequestUrl) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        URL url = createUrl(RequestUrl);


        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }


        ArrayList<Anime> anime = extractAnimesFromJson(jsonResponse);

        return anime;
    }

    private static String makeHttpRequest(URL url)throws IOException {
        String jsonResponse = "";


        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the  Anime JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static URL createUrl(String requestUrl) {

        URL url = null;
        try {
            url = new URL(requestUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL ", e);
        }
        return url;


    }





}
