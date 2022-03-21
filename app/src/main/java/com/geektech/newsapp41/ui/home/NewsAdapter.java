package com.geektech.newsapp41.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.newsapp41.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import interfaces.OnItemClickListener;
import models.News;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private ArrayList<News> list;
    private OnItemClickListener onItemClickListener;

    public NewsAdapter() {
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list.get(position));
        if (position % 2 == 0){
            holder.itemView.setBackgroundColor(Color.WHITE);

        }else {
            holder.itemView.setBackgroundColor(Color.YELLOW);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItem(News news) {
        list.add(0, news);
        notifyItemInserted(list.indexOf(news));
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public News getItem(int position) {
        return list.get(position);

    }
    public void updateItem(News news, int position){
        list.set(position,news);
        notifyItemChanged(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textTitle, date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.title_text);
            date = itemView.findViewById(R.id.date);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onClick(getAdapterPosition());
                    Bundle bundle = new Bundle();
                    bundle.putString("text",textTitle.getText().toString());
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                     new AlertDialog.Builder(view.getContext()).setTitle("Delete").setMessage("Вы уверены").setNegativeButton("No",null)
                             .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                 @Override
                                 public void onClick(DialogInterface dialogInterface, int i) {
                                     list.remove(getAdapterPosition());
                                     notifyDataSetChanged();
                                 }
                             }).show();
                    return true;
                }
            });

        }

        public void bind(News news) {
            textTitle.setText(news.getTitle());
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH : mm : ss , dd MMM yyyy", Locale.ROOT);
            String date1 = String.valueOf(dateFormat.format(news.getCreatedAt()));
            date.setText(date1);
        }
    }
}

