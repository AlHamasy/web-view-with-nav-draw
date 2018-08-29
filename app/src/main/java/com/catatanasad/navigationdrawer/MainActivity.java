package com.catatanasad.navigationdrawer;


import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.catatanasad.navigationdrawer.fragment.DaftarFragment;
import com.catatanasad.navigationdrawer.fragment.HomeFragment;
import com.catatanasad.navigationdrawer.fragment.JadwalFragment;
import com.catatanasad.navigationdrawer.fragment.TrainingFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        LoginActivity.Preference preference = new LoginActivity.Preference(MainActivity.this);
        String nama = preference.getNama();

        if (TextUtils.isEmpty(nama)){
            Toast.makeText(this, "Login dahulu", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }
        else {
            pindahFragment(new HomeFragment());
            getSupportActionBar().setTitle("Home");
        }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent configure = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(configure);

            return true;
        }

        // todo keluar
        if (id == R.id.action_logout){
            LoginActivity.Preference preference = new LoginActivity.Preference(MainActivity.this);
            preference.logout();

            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

            // todo panggil fragment v4
            pindahFragment(new HomeFragment());
            getSupportActionBar().setTitle("Home");

        } else if (id == R.id.nav_gallery) {

            // todo panggil fragment v4
            pindahFragment(new JadwalFragment());
            getSupportActionBar().setTitle("Jadwal");

        } else if (id == R.id.nav_slideshow) {

            // todo panggil fragment v4
            pindahFragment(new TrainingFragment());
            getSupportActionBar().setTitle("Training");

        } else if (id == R.id.nav_manage) {

            // todo panggil fragment v4
            pindahFragment(new DaftarFragment());
            getSupportActionBar().setTitle("Daftar");

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void pindahFragment(Fragment fragmentTujuan){
        Fragment fragment = fragmentTujuan;
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.container_layout,fragment).commit();
    }
}
