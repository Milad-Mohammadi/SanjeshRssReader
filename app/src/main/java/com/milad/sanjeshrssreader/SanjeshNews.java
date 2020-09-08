package com.milad.sanjeshrssreader;

public class SanjeshNews {
  private String title;
  private String pubDate;
  private String link;

  public SanjeshNews() {}
  public SanjeshNews(String title, String pubDate, String link) {
    this.title = title;
    this.pubDate = pubDate;
    this.link = link;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getPubDate() {
    return pubDate;
  }

  public void setPubDate(String pubDate) {
    this.pubDate = pubDate;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }
}
