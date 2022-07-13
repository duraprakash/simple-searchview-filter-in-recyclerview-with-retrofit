package com.example.searchfilter;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    // global variable
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private List<Movie> movieList;
    private RecyclerView.LayoutManager mlayoutManager;

    EditText etSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // assign
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        // set recyclerView adapter
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        // calling getData method
        getData();

        etSearch = (EditText) findViewById(R.id.etSearch);
        // edit text search filter
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                // calling method
                editTextFilter(s.toString());
            }
        });


    }

    // method created for editText filter
    private void editTextFilter(String searchText) {
        List<Movie> filteredList = new ArrayList<>();
        for (Movie item : movieList){
            if(item.getTitle().toLowerCase().contains(searchText.toLowerCase())){
                filteredList.add(item);
            }
        }
        movieAdapter.filterList(filteredList);
    }

    // method created for searchView filter
/*
    private void Filter(String searchText) {
        for (Movie movie : movieList){
            Log.i(TAG, "Filter: "+movie);
//            if (Integer.toString(movie.getId()).equals(searchText)){
            if (movie.getTitle().toString().toLowerCase().contains(searchText)){
            }
        }
        recyclerView.setAdapter(new MovieAdapter(filterList));
        movieAdapter.notifyDataSetChanged();
    }
*/

    // getData method created
    private void getData() {
        movieList = new ArrayList<>();
        Call<List<Movie>> call = apicontroller
                .getInstance()
                .getapi()
                .getdata();

        // 4.5 execute call
        // rvc response data in simple model type list [6]
        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                movieList = response.body();
                movieAdapter = new MovieAdapter(movieList);
                recyclerView.setAdapter(movieAdapter);
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Sorry! " + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    // this is working
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem menuitem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuitem.getActionView();

        // After creating filter method in adapter
        // generate two method
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                movieAdapter.getFilter().filter(s.toString());
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
*/
}