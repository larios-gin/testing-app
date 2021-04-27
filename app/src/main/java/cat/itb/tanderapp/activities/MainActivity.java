package cat.itb.tanderapp.activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import cat.itb.tanderapp.R;
import cat.itb.tanderapp.fragments.ChatFragment;
import cat.itb.tanderapp.fragments.HomeFragment;
import cat.itb.tanderapp.fragments.ProfileFragment;
import cat.itb.tanderapp.fragments.RegisterFragment;
import cat.itb.tanderapp.fragments.WelcomeScreen;

public class MainActivity extends AppCompatActivity {
    public static BottomNavigationView topAppBar;
    Fragment f;
    public static FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_TanderApp);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null){
            f = getSupportFragmentManager().getFragment(savedInstanceState, "lastFragment");
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment, f, f.getClass().getSimpleName())
                    .commit();
        }
        else{
            f = new WelcomeScreen();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment, f, f.getClass().getSimpleName()).addToBackStack(null)
                    .commit();
        }

        firebaseAuth = FirebaseAuth.getInstance();
        topAppBar = findViewById(R.id.top_app_bar);
        topAppBar.setVisibility(View.INVISIBLE);
        topAppBar.setSelectedItemId(R.id.home_page);
        topAppBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                switch (item.getItemId()){
                    case R.id.profile_page:
                        Fragment fragment = new ProfileFragment();
                        transaction.replace(R.id.nav_host_fragment, fragment, fragment.getClass().getSimpleName());
                        transaction.commit();
                        return true;
                    case R.id.home_page:
                        Fragment fragment2 = new HomeFragment();
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.nav_host_fragment, fragment2, fragment2.getClass().getSimpleName());
                        transaction.commit();
                        return true;
                    case R.id.chat_page:
                        Fragment fragment3 = new ChatFragment();
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.nav_host_fragment, fragment3, fragment3.getClass().getSimpleName());
                        transaction.commit();
                        return true;

                }
                return true;
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        for(int i = 0; i< getSupportFragmentManager().getFragments().size(); i++ ){
            if(getSupportFragmentManager().getFragments().get(i).isVisible()){
                Fragment f =  getSupportFragmentManager().getFragments().get(i);
                getSupportFragmentManager().putFragment(savedInstanceState, "lastFragment", f);
            }
        }
    }

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        super.onAttachFragment(fragment);
        if (firebaseAuth.getCurrentUser() != null) {
            topAppBar.setVisibility(View.VISIBLE);
        }
    }
}