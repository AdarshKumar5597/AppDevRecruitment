package com.example.figmaui;

import static com.example.figmaui.R.drawable.settings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;

import com.example.figmaui.Adapters.Adapter1;
import com.example.figmaui.Adapters.Adapter2;
import com.example.figmaui.Fragments.JourneyFragment;
import com.example.figmaui.Fragments.MonthFragment;
import com.example.figmaui.Fragments.StatisticsFragment;
import com.example.figmaui.Fragments.WeekFragment;
import com.example.figmaui.Fragments.YearFragment;
import com.google.android.material.tabs.TabLayout;

public class HomeActivity extends AppCompatActivity {
    TabLayout tabLayout1, tabLayout2;
    ViewPager viewPager1, viewPager2;
    Adapter1 adapter1;
    Adapter2 adapter2;
    Toolbar toolbar;
    SharedPreferenceManager sharedPreferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //hiding action bar
        //getSupportActionBar().hide();

        //hiding status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //initializing variables
        tabLayout1 = findViewById(R.id.tablayout1);
        viewPager1 = findViewById(R.id.viewpager1);
        tabLayout2 = findViewById(R.id.tablayout2);
        viewPager2 = findViewById(R.id.viewpager2);
        toolbar = findViewById(R.id.toolbar);

        //initialize adapters
        adapter1 = new Adapter1(getSupportFragmentManager());
        adapter2 = new Adapter2(getSupportFragmentManager());

        //add fragments
        adapter1.addfragmentandstring(new StatisticsFragment(), "Statistics");
        adapter1.addfragmentandstring(new JourneyFragment(), "Journey");

        adapter2.addFragmentandString(new WeekFragment(), "WEEK");
        adapter2.addFragmentandString(new MonthFragment(), "MONTH");
        adapter2.addFragmentandString(new YearFragment(), "YEAR");

        //connecting viewpager to adapter
        viewPager1.setAdapter(adapter1);
        viewPager2.setAdapter(adapter2);

        //connecting tablayout to viewpager
        tabLayout1.setupWithViewPager(viewPager1);
        tabLayout2.setupWithViewPager(viewPager2);

        //setting up toolbar
        setSupportActionBar(toolbar);

        //changing icon to settings icon as per given ui
        toolbar.setOverflowIcon(ContextCompat.getDrawable(getApplicationContext(), settings));

        //calling sharedpreferncemanager constructor to pass this activity context
        sharedPreferenceManager = new SharedPreferenceManager(getApplicationContext());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.logout){
            logoutuser();
        }
        return true;
    }

    private void logoutuser() {
        sharedPreferenceManager.isloggedout();
        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}