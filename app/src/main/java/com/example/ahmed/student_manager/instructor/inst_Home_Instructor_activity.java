package com.example.ahmed.student_manager.instructor;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.ahmed.student_manager.R;

public class inst_Home_Instructor_activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    static int sInstHomeInstructorActivity=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inst_activity_home_instructor_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        inst_Info inst_info = new inst_Info();
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft= fm.beginTransaction();
        ft.replace(R.id.content_inst__home__instructor_activity,inst_info);
        ft.commit();

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
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else if (sInstHomeInstructorActivity==1)
        {
         finish();
        }
        else if (sInstHomeInstructorActivity==2)
        {
            inst_Info inst_info = new inst_Info();
            FragmentManager fm=getSupportFragmentManager();
            FragmentTransaction ft= fm.beginTransaction();
            ft.replace(R.id.content_inst__home__instructor_activity,inst_info);
            ft.commit();
        }else if (sInstHomeInstructorActivity==3)
        {
            inst_Info inst_info = new inst_Info();
            FragmentManager fm=getSupportFragmentManager();
            FragmentTransaction ft= fm.beginTransaction();
            ft.replace(R.id.content_inst__home__instructor_activity,inst_info);
            ft.commit();
        }
        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.inst__home__instructor_activity, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            display_off_courses display_off_courses = new display_off_courses();
            FragmentManager fm=getSupportFragmentManager();
            FragmentTransaction ft= fm.beginTransaction();
            ft.replace(R.id.content_inst__home__instructor_activity,display_off_courses);
            ft.commit();
        } else if (id == R.id.nav_gallery) {
            inst_Assign_Course_Appointment assign_course_appointment = new inst_Assign_Course_Appointment();
            FragmentManager fm=getSupportFragmentManager();
            FragmentTransaction ft= fm.beginTransaction();
            ft.replace(R.id.content_inst__home__instructor_activity,assign_course_appointment);
            ft.commit();
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
