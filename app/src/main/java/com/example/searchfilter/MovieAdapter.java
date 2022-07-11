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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> implements Filterable {

    // global variable
    private List<Movie> movieList;
    private List<Movie> movieListFiltered;
    private Context context;

    // search filter code1
    // method
    public void setMovieList(Context applicationContext, List<Movie> movieList) {
        this.context = context;
        if (this.movieList == null){
            this.movieList = movieList;
            this.movieListFiltered = movieListFiltered;
            notifyDataSetChanged();
        }else{
            final DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                // four method generated
                @Override
                public int getOldListSize() {
                    return MovieAdapter.this.movieList.size();
                }

                @Override
                public int getNewListSize() {
                    return movieList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return MovieAdapter.this.movieList.get(oldItemPosition).getTitle() == movieList.get(newItemPosition).getTitle();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {

                    Movie newMovie = MovieAdapter.this.movieList.get(oldItemPosition);

                    Movie oldMovie = movieList.get(newItemPosition);

                    return newMovie.getTitle() == oldMovie.getTitle();
                }
            });
            this.movieList = movieList;
            this.movieListFiltered = movieList;
            result.dispatchUpdatesTo(this);
        }
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
        holder.title.setText(movieListFiltered.get(position).getTitle());
//        Glide.with(context).load(movieList.get(position).geturl()).apply(RequestOptions.centerCropTransform()).into(holder.image);
    }

    // filter search code2
    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: " +movieList.size());
        if (movieList != null)
            return movieListFiltered.size();
        return 0;

    }

    // filter search code3
    // getFilter method created
    @Override
    public Filter getFilter() {
        return new Filter() {
            // two method generated
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                // get the enter character
                String charString = charSequence.toString();
                if (charString.isEmpty()){
                    movieListFiltered = movieList;
                } else{
                    // store the filter data in filteredList variable
                    List<Movie> filteredList = new ArrayList<>();
                    // for each loop
                    for (Movie movie : movieList){
                        if (movie.getTitle().toLowerCase().contains(charString.toLowerCase())){
                            filteredList.add(movie);
                        }
                    }
                    movieListFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = movieListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                movieListFiltered = (ArrayList<Movie>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    // MyViewHolder class
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView image;
        // constructor
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            image = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}
