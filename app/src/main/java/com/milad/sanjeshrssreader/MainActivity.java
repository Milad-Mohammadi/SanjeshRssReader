package com.milad.sanjeshrssreader;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import fr.arnaudguyon.xmltojsonlib.XmlToJson;

public class MainActivity extends AppCompatActivity {
  RecyclerView sanjeshRecycler;
  List<SanjeshNews> news;
  private static String URL = "http://www.sanjesh.org/rss/rss.aspx";
  SanjeshRecyclerAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    sanjeshRecycler = findViewById(R.id.sanjesh_recycler);
    news = new ArrayList<>();
    extractNewsData();
  }

  private void extractNewsData() {
    RequestQueue requestQueue = Volley.newRequestQueue(this);
    StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
      @Override
      public void onResponse(String response) {
        try {
          XmlToJson xmlToJson = new XmlToJson.Builder(response).build();

          JSONObject jsonObject = xmlToJson.toJson();
          JSONObject rssObject = jsonObject.getJSONObject("rss");
          JSONObject channelObject = rssObject.getJSONObject("channel");

          JSONArray newsArray = channelObject.getJSONArray("item");
          for (int i = 0; i < newsArray.length(); i++) {
            SanjeshNews sanjeshNews = new SanjeshNews();
            JSONObject newsObject = newsArray.getJSONObject(i);
            sanjeshNews.setTitle(newsObject.getString("title"));
            sanjeshNews.setPubDate(newsObject.getString("pubDate"));
            sanjeshNews.setLink(newsObject.getString("link"));
            news.add(sanjeshNews);
          }

          sanjeshRecycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));
          adapter = new SanjeshRecyclerAdapter(MainActivity.this, news);
          sanjeshRecycler.setAdapter(adapter);

        } catch (JSONException e) {
          e.printStackTrace();
        }
      }
    }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
        Log.i("VOLLEY", "onErrorResponse: " + error);
      }
    });
    requestQueue.add(stringRequest);
  }
}