package com.example.mynotes;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mynotes.adapters.MyFragmentStateAdapter;
import com.example.mynotes.database.DB;
import com.example.mynotes.models.Note;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ViewPager2 pager;
    MyFragmentStateAdapter pagerAdapter;
    private DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initDB();
        initPager();
    }
    private void initDB() {
        db = new DB(this);
        db.open();
    }
    private void initPager(){
        pager = findViewById(R.id.pager);
        pagerAdapter = new MyFragmentStateAdapter(this, db);
        pager.setAdapter(pagerAdapter);

        setupTabLayoutWithViewPager(R.id.pagerTabStrip1);
        setupTabLayoutWithViewPager(R.id.pagerTabStrip2);
        setupTabLayoutWithViewPager(R.id.pagerTabStrip3);
        setupTabLayoutWithViewPager(R.id.pagerTabStrip4);
    }

    private void setupTabLayoutWithViewPager(int tabLayoutId) {
        TabLayout tabLayout = findViewById(tabLayoutId);
        new TabLayoutMediator(tabLayout, pager, (tab, position) -> {
            tab.setText(pagerAdapter.getTabTitle(position));
        }).attach();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}