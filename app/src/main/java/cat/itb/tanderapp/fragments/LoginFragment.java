package cat.itb.tanderapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;

import cat.itb.tanderapp.R;

import static cat.itb.tanderapp.activities.MainActivity.firebaseAuth;
import static cat.itb.tanderapp.activities.MainActivity.topAppBar;

public class LoginFragment extends Fragment {
    private FloatingActionButton buttonBack;
    private TextView emailEdittext, passwordEdittext;
    private Button logIn;
    private ProgressBar progressBar;
    private HomeFragment homeFragment;
    public LoginFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View v = inflater.inflate(R.layout.fragment_login, container, false);
        buttonBack = v.findViewById(R.id.floatingActionButton);
        logIn = v.findViewById(R.id.log_in_button);
        emailEdittext = v.findViewById(R.id.editText_email);
        passwordEdittext = v.findViewById(R.id.editText_password);
        progressBar = v.findViewById(R.id.progressBar_login);
        homeFragment = new HomeFragment();
        topAppBar.setVisibility(View.INVISIBLE);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().popBackStack();
            }
        });
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInUser();
            }
        });
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        topAppBar.setVisibility(View.INVISIBLE);
    }

    private void signInUser(){
        try{
            if(!emailEdittext.getText().toString().isEmpty() && !passwordEdittext.getText().toString().isEmpty()){
                if (firebaseAuth != null){
                    progressBar.setVisibility(View.VISIBLE);
                    logIn.setEnabled(false);
                    firebaseAuth.signInWithEmailAndPassword(emailEdittext.getText().toString(), passwordEdittext.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            progressBar.setVisibility(View.INVISIBLE);
                            logIn.setEnabled(true);
                            replaceFragment(homeFragment);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressBar.setVisibility(View.INVISIBLE);
                            logIn.setEnabled(true);

                        }
                    });
                }
            }else{
                Toast.makeText(getContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    private void replaceFragment(Fragment fragment){
        try{
            FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.nav_host_fragment, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();

        }catch (Exception e){
            //Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT);
        }
    }
}