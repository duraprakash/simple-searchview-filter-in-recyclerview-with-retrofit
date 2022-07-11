package com.example.searchfilter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    // global variable
    private SearchView searchView;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private List<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // assign
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        // set recyclerView adapter
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        movieAdapter = new MovieAdapter();
        recyclerView.setAdapter(movieAdapter);

        // calling getData method
        getData();
    }

    // getData method created
    private void getData() {
// TODO: new array list object
        movieList = new ArrayList<>();
        Call<List<Movie>> call = apicontroller
                .getInstance()
                .getapi()
                .getdata();

        // 4.5 execute call
        // rvc response data in simple model type list [6]
        call.enqueue(new Callback<List<Movie>>() {
            @Override
//            TODO: 5. change responsemodel
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                movieList = response.body();

                movieAdapter.setMovieList(getApplicationContext(),movieList);

                /*
                for (int i=0; i<movieList.size();i++){
                    tv.append(
                            "\n\nid: " +movieList.get(i).getTitle()
                                    + "\ntitle: " +movieList.get(i).getTitle()
                                    + "\nbody: " +movieList.get(i).geturl()
                    );
                }
                */
            }

            @Override
//            TODO: 6. change responsemodel
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Sorry! " + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    // filter search code1
    // generated onCreateOptionMenu method
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // act when text is entered
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // two method generated
            @Override
            public boolean onQueryTextSubmit(String query) {
                // getFilter should be created in MovieAdapter
                movieAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                movieAdapter.getFilter().filter(query);
                return false;
            }
        });

        return true;
    }

    // option item selected
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // back press
    @Override
    public void onBackPressed() {
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }

}