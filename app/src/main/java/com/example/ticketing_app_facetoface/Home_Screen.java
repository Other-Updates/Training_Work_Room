package com.example.ticketing_app_facetoface;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class Home_Screen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottom_navigation;
    Fragment fragment;
    private int menu_id;
    String check="Dashboard";
    Home_Screen dashboardFragment;
    FrameLayout fragment_container ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        fragment_container = findViewById(R.id.fragment_container);
        bottom_navigation = findViewById(R.id.bottom_navigation);
        bottom_navigation.setOnNavigationItemSelectedListener(selectedId);
        bottom_navigation.setSelectedItemId(R.id.dashboardFragment);
        bottom_navigation.getMenu().findItem(R.id.dashboardFragment).setChecked(true);
        fragment = new TicketFragment();
    }
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        // transaction.addToBackStack(null);
        transaction.commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener selectedId
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            menu_id = menuItem.getItemId();
            for (int i = 0; i < bottom_navigation.getMenu().size(); i++) {
                MenuItem item = bottom_navigation.getMenu().getItem(i);
                boolean isChecked = menuItem.getItemId() == item.getItemId();
                menuItem.setChecked(isChecked);
            }
            switch (menu_id) {
                case R.id.dashboardFragment:

                    //   getSupportActionBar().setTitle("Home");
                    fragment = new TicketFragment();
                    loadFragment(fragment);
                    break;

                case R.id.AddTicketFragment:

                    //   getSupportActionBar().setTitle("Search");
                    fragment = new AddTicketFragment();
                    loadFragment(fragment);
                    break;

                case R.id.ProfileFragment:

                    //   getSupportActionBar().setTitle("Cart");
                    fragment = new ProfileFragment();
                    loadFragment(fragment);
                    break;

            }
            return false;
        }};

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        return false;
    }
}

