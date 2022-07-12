package com.example.searchfilter;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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
                List<Movie> movieList = response.body();
                movieAdapter = new MovieAdapter(movieList);
                recyclerView.setAdapter(movieAdapter);


                /*
                for (int i=0; i<movieList.size();i++){
                    recyclerView.append(
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

    // this is working
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

    // option item selected
    /*@Override
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
    }*/


}