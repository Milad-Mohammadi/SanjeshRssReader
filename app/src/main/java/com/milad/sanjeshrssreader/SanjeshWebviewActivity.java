package com.milad.sanjeshrssreader;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SanjeshWebviewActivity extends AppCompatActivity {
  WebView webView;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sanjesh_webview);
    getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
    webView = findViewById(R.id.webview);
    loadPage();
  }

  private void loadPage() {
    Intent intent = getIntent();
    Bundle bundle = intent.getExtras();
    if (intent.hasExtra("link") && intent.hasExtra("title")) {
      String link = bundle.getString("link");
      String title = bundle.getString("title");
      webView.setInitialScale(100);
      webView.getSettings().setSupportZoom(true);
      webView.getSettings().setBuiltInZoomControls(true);
      webView.getSettings().setDisplayZoomControls(false);

      webView.loadUrl(link);
      getSupportActionBar().setTitle(title);
    }
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
  }
}