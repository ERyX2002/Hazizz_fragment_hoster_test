package com.indeed.hazizz.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.indeed.hazizz.Communication.MiddleMan;
import com.indeed.hazizz.Communication.POJO.Response.CustomResponseHandler;
import com.indeed.hazizz.Communication.POJO.Response.POJOgroup;
import com.indeed.hazizz.Communication.POJO.Response.POJOme;
import com.indeed.hazizz.R;
import com.indeed.hazizz.SharedPrefs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView textView_userName;
    private TextView textView_email;

    private List<Integer> groupIDs;
    private List<POJOgroup> groups;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
     //   setSupportActionBar(toolbar);

        groupIDs = new ArrayList<Integer>();
        groups = new ArrayList<POJOgroup>();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.bringToFront();

        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.textView_userName);
        TextView navEmail = (TextView) headerView.findViewById(R.id.textView_email);

        CustomResponseHandler responseHandler = new CustomResponseHandler() {
            @Override
            public void onResponse(HashMap<String, Object> response) {

            }

            @Override
            public void onResponse1(Object response) {
                navUsername.setText(((POJOme) response).getUsername());
                navEmail.setText(((POJOme) response).getEmailAddress());

                for(POJOgroup g : ((POJOme) response).getGroups()){
                    groupIDs.add(g.getId());
                    Log.e("hey", "added groupID");
                }
        }

            @Override
            public void onFailure() {
                Log.e("hey", "4");
                Log.e("hey", "got here onFailure");
            }
            @Override
            public void onErrorResponse(HashMap<String, Object> errorResponse) {
                Log.e("hey", "onErrorResponse");
            }
        };

        MiddleMan sendRegisterRequest = new MiddleMan(getBaseContext(), "me", null, responseHandler);
        sendRegisterRequest.sendRequest2();
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
            Log.e("hey", "action_settings");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Log.e("hey", "chechk1");
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            groups = getGroups();
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public List<POJOgroup> getGroups(){
        List<POJOgroup> groupsList = new ArrayList<POJOgroup>();
        Log.e("hey", "atleast here 2");
        CustomResponseHandler responseHandler = new CustomResponseHandler() {
            @Override
            public void onResponse(HashMap<String, Object> response) {

            }

            @Override
            public void onResponse1(Object response) {
                groupsList.add((POJOgroup) response);
                Log.e("hey", "getGroups works");
            }

            @Override
            public void onFailure() {
                Log.e("hey", "4");
                Log.e("hey", "got here onFailure");
            }

            @Override
            public void onErrorResponse(HashMap<String, Object> errorResponse) {
                Log.e("hey", "onErrorResponse");
            }
        };
        if(groupIDs == null){
            Log.e("hey", "group id is null 1");

        }
        for(int groupID : groupIDs) {
            Log.e("hey", "here 1");
            MiddleMan sendRegisterRequest = new MiddleMan(getBaseContext(), "getGroup", null, responseHandler, groupID);
            sendRegisterRequest.sendRequest2();
        }

       /* Context context = getActivity();
        SharedPreferences sharedPref = getActivity().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);); */

        Log.e("hey", SharedPrefs.getString(getApplicationContext(), "token", "token"));


        return groupsList;
    }
}
