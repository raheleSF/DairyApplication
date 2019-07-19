package com.navin.dairyapplication;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.navin.dairyapplication.adapter.RecyclerViewAdapter;
import com.navin.dairyapplication.database.DiaryDatabase;
import com.navin.dairyapplication.database.DiaryDbadapter;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    FloatingActionButton add;

    DrawerLayout drawer;

    DiaryDatabase dairyDatabase;

    RecyclerView recyclerView;


    DiaryDbadapter diaryDbadapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dairyDatabase = new DiaryDatabase(getApplicationContext());

        diaryDbadapter = new DiaryDbadapter(getApplicationContext());

        toolbar = findViewById(R.id.toolbar);
        add = findViewById(R.id.floatingbutton);
        drawer = findViewById(R.id.drawer);
        recyclerView = findViewById(R.id.recycler_main);


        setSupportActionBar(toolbar);


        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                MainActivity.this, drawer, toolbar, R.string.open,
                R.string.close);


        drawerToggle.syncState();


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), TaskActivity.class);
                startActivity(intent);
            }
        });

        //RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getApplicationContext().)
    }

    @Override
    protected void onResume() {
        super.onResume();


        RecyclerViewAdapter recyclerViewAdapter = new
                RecyclerViewAdapter(getApplicationContext(),diaryDbadapter.showAll() );

        recyclerView.setAdapter(recyclerViewAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);

        recyclerView.setLayoutManager(layoutManager);


    }

    @Override
    public ActionMode startActionMode(ActionMode.Callback callback) {
        return super.startActionMode(callback);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.topmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
