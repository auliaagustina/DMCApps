package com.example.labta;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecommendationAdapter extends RecyclerView.Adapter<RecommendationAdapter.ViewHolder> {

    private Article[] articles;

    public RecommendationAdapter(Article[] articles) {
        this.articles = articles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recomendation, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Article article = articles[position];

        holder.articleImageView.setImageResource(article.getImageResource());
        holder.articleTitleTextView.setText(article.getTitle());
        holder.articleDateTextView.setText(article.getDate());
    }

    @Override
    public int getItemCount() {
        return articles.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView articleImageView;
        public TextView articleTitleTextView;
        public TextView articleDateTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            articleImageView = itemView.findViewById(R.id.articleImageView);
            articleTitleTextView = itemView.findViewById(R.id.articleTitleTextView);
            articleDateTextView = itemView.findViewById(R.id.articleDateTextView);
        }
    }
}
