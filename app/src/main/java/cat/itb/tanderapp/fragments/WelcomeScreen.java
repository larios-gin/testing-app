package cat.itb.tanderapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import cat.itb.tanderapp.R;

import static cat.itb.tanderapp.activities.MainActivity.topAppBar;


public class WelcomeScreen extends Fragment {
    android.widget.Button buttonLogin, buttonRegister;
    public WelcomeScreen() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_welcome_screen, container, false);
        buttonLogin = v.findViewById(R.id.button_login);
        buttonRegister = v.findViewById(R.id.button_register);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment f = new LoginFragment();
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment, f, f.getClass().getSimpleName()).addToBackStack(null)
                        .commit();
            }
        });
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment f = new RegisterFragment();
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment, f, f.getClass().getSimpleName()).addToBackStack(null)
                        .commit();
            }
        });
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        topAppBar.setVisibility(View.INVISIBLE);
    }
}