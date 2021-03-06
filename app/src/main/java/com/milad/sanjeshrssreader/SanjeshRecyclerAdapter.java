package com.milad.sanjeshrssreader;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class SanjeshRecyclerAdapter extends RecyclerView.Adapter<SanjeshRecyclerAdapter.ViewHolder> {
  LayoutInflater inflater;
  List<SanjeshNews> SanjeshNews;
  Context context;

  public SanjeshRecyclerAdapter(Context context, List<SanjeshNews> news) {
    this.inflater = LayoutInflater.from(context);
    this.SanjeshNews = news;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = inflater.inflate(R.layout.news_item_row, parent, false);
    context = parent.getContext();
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
    holder.txtTitle.setText(SanjeshNews.get(position).getTitle());
    holder.txtDate.setText(SanjeshNews.get(position).getPubDate());

    holder.parentRow.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), SanjeshWebviewActivity.class);
        intent.putExtra("link", SanjeshNews.get(position).getLink());
        intent.putExtra("title", SanjeshNews.get(position).getTitle());
        view.getContext().startActivity(intent);
      }
    });
  }

  @Override
  public int getItemCount() {
    return SanjeshNews.size();
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {
    TextView txtTitle, txtDate;
    CardView parentRow;
    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      txtTitle = itemView.findViewById(R.id.txt_title);
      txtDate = itemView.findViewById(R.id.txt_date);
      parentRow = itemView.findViewById(R.id.parent_row);
    }
  }
}
