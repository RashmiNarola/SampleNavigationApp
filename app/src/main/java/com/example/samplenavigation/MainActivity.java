package com.example.samplenavigation;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.samplenavigation.fragment.Test1Fragment;
import com.example.samplenavigation.fragment.Test2Fragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Test1Fragment test1Fragment;
    Test2Fragment test2Fragment;
    DrawerLayout drawer;

    FragmentManager fragmentManager;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();

        test1Fragment = new Test1Fragment();
        test2Fragment = new Test2Fragment();

        toolbar.setTitle("Test 1");
        if (test1Fragment != null) {
            fragmentManager.beginTransaction().setCustomAnimations(R.anim.activity_exit,R.anim.activity_enter).replace(R.id.frame_container, test1Fragment).commit();
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }
        navigationView.getMenu().getItem(0).setChecked(true);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_test1) {
            toolbar.setTitle("Test 1");
            if (test1Fragment != null) {
                fragmentManager.beginTransaction().setCustomAnimations(R.anim.activity_exit,R.anim.activity_enter).replace(R.id.frame_container, test1Fragment).commitAllowingStateLoss();
                drawer.closeDrawer(GravityCompat.START);
            } else {
                Log.e("MainActivity", "Error in creating fragment");
            }
        } else if (id == R.id.nav_test2) {
            toolbar.setTitle("Navigation");
            if (test2Fragment != null) {
                fragmentManager.beginTransaction().setCustomAnimations(R.anim.activity_exit,R.anim.activity_enter).replace(R.id.frame_container, test2Fragment).commitAllowingStateLoss();
                drawer.closeDrawer(GravityCompat.START);
            } else {
                Log.e("MainActivity", "Error in creating fragment");
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}