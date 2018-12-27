package com.bignerdranch.android.myread.ReadAtovity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;;
import com.bignerdranch.android.myread.Adapter.ReadPageAdapter;
import com.bignerdranch.android.myread.R;
import com.bignerdranch.android.myread.readfragment.ReadFindActivity;
import com.bignerdranch.android.myread.readfragment.ReadForumActivity;
import com.bignerdranch.android.myread.readfragment.ReadListActivity;

import java.util.ArrayList;
public class ReadActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ViewPager viewPager;
    private ReadListActivity readListActivity;
    private ReadForumActivity readForumActivity;
    private ReadFindActivity readFindActivity;
    private ReadPageAdapter rAdapter;
    private ArrayList<Fragment> fragments;
    private ActionBar actionBar;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_dashboard:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_notifications:
                    viewPager.setCurrentItem(2);
                    return true;
                default: break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
//        actionBar = getSupportActionBar();
//        actionBar.hide();
        final BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//        toolbar = (Toolbar)findViewById(R.id.toolbar);
//        toolbar.setTitle("设置");
        viewPager = (ViewPager)findViewById(R.id.view_viewpage);
        readListActivity = new ReadListActivity();
        readForumActivity = new ReadForumActivity();
        readFindActivity = new ReadFindActivity();
        fragments = new ArrayList<Fragment>();
        fragments.add(readListActivity);
        fragments.add(readForumActivity);
        fragments.add(readFindActivity);
        rAdapter = new ReadPageAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(rAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }
            @Override
            public void onPageSelected(int i) {
                navigation.getMenu().getItem(i).setChecked(true);
            }
            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater  = getMenuInflater();
        inflater.inflate(R.menu.action_menu_bar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_a:
//                Intent intent = new Intent(ReadActivity.this,FlieBookListActivity.class);
                Intent intent = new Intent(ReadActivity.this,FlieBookListActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
