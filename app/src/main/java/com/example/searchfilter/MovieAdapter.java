package com.example.searchfilter;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> implements Filterable {

    // global variable
    private List<Movie> movieList; // mExampleList
    private List<Movie> movieListFiltered;

    // this is working
    public MovieAdapter(List<Movie> movieList) {
        this.movieList = movieList;
        movieListFiltered = new ArrayList<>(movieList);
    }

    // three method of adapter
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movielist_adapter,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Movie currentItem = movieList.get(position);
        holder.title.setText("title: " +movieList.get(position).getTitle());
//        holder.tvurl.setText("url: " +movieList.get(position).getTitle());
        holder.image.setImageResource(R.drawable.ic_launcher_background);
//        Glide.with(holder.image).load(movieList.get(position).getThumbnailUrl()).apply(RequestOptions.centerCropTransform()).into(holder.image);
    }

    // filter search code2
    @Override
    public int getItemCount() {
        return movieList.size();
    }

    // this is working
    @Override
    public Filter getFilter() {
        // calling method
        return FilterUser;
    }

    private Filter FilterUser = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            // variable
            String searchText = charSequence.toString().toLowerCase();
            List<Movie> tempList = new ArrayList<>();
            if (searchText.length() == 0 || searchText.isEmpty()){
                tempList.addAll(movieListFiltered);
            } else {
                for (Movie item: movieListFiltered){
                    if (item.getTitle().toLowerCase().contains(searchText)){
                        tempList.add(item);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = tempList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            // clearing the data in movieList
            movieList.clear();
            movieList.addAll((Collection<? extends Movie>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    // MyViewHolder class
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, tvurl;
        ImageView image;
        // constructor
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            image = (ImageView) itemView.findViewById(R.id.image);
            tvurl = (TextView) itemView.findViewById(R.id.tvurl);
        }
    }

    // edit text filter
    public void filterList(List<Movie> filteredList){
        movieList = filteredList;
        notifyDataSetChanged();
    }
}
