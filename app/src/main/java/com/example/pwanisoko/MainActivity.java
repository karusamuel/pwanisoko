package com.example.pwanisoko;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.pwanisoko.Fragments.AccountFragment;
import com.example.pwanisoko.Fragments.ChatsFragment;
import com.example.pwanisoko.Fragments.ExploreFragment;
import com.example.pwanisoko.Fragments.MyADS;
import com.example.pwanisoko.Fragments.SellFragment;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView  bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);

        setFragment(new ExploreFragment());
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.chats:
                    return setFragment(new ChatsFragment());
                    case R.id.explore:
                    return    setFragment(new ExploreFragment());
                    case R.id.my_account:
                    return setFragment(new AccountFragment());
                    case R.id.my_ads:
                    return setFragment(new MyADS());
                    case R.id.sell:
                        startActivity(new Intent(getApplicationContext(),SellActivity.class));
                    return false;

                }
                return false;
            }
        });


    }

    public Boolean setFragment(Fragment fragment){
        bottomNavigationView = findViewById(R.id.bottomNavigationView);Fragment fragment1 = new ExploreFragment();
        FragmentManager manager = this.getSupportFragmentManager();
        manager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.homeMainView,fragment).commit();
        bottomNavigationView.setLabelVisibilityMode(View.VISIBLE);
        return true;
    }

        public void toSettings(View view) {

            startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
        }
        public void toEditProfile(View view) {

            startActivity(new Intent(getApplicationContext(), EditProfile.class));
        }
        public void toFeedback(View view) {

            startActivity(new Intent(getApplicationContext(), FeedbackActivity.class));
        }
        public void toNotifications(View view) {

            startActivity(new Intent(getApplicationContext(), NotificationsAcivity.class));
        }
        public void toMynetwork(View view) {

            startActivity(new Intent(getApplicationContext(), MyNetworkActivity.class));
        }

}
