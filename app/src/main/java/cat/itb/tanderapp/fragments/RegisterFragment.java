package cat.itb.tanderapp.fragments;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.slider.RangeSlider;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import cat.itb.tanderapp.R;
import cat.itb.tanderapp.activities.MainActivity;
import cat.itb.tanderapp.model.User;

import static cat.itb.tanderapp.activities.MainActivity.topAppBar;

public class RegisterFragment extends Fragment {
    private TextView textViewAge;
    private FloatingActionButton buttonBack;
    private EditText nameEdit, emailEdit, passwordEdit, rPasswordEdit, birthEdit;
    private Spinner genderSpinner, preferenceSpinner;
    private com.google.android.material.slider.RangeSlider rangeAge;
    private Button registerBtn;
    ProgressBar progressBar;
    HomeFragment homeFragment;
    FirebaseDatabase database;
    DatabaseReference myRef;
    private Calendar calendar;


    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_register, container, false);
        rangeAge = v.findViewById(R.id.age_slider);
        textViewAge = v.findViewById(R.id.textView_age);
        nameEdit = v.findViewById(R.id.editText_name_R);
        emailEdit = v.findViewById(R.id.editText_email_R);
        passwordEdit = v.findViewById(R.id.editText_password_R);
        rPasswordEdit = v.findViewById(R.id.editText_repeatPass_R);
//        birthEdit = v.findViewById(R.id.datePicker);
        genderSpinner = v.findViewById(R.id.spinner_gender);
        preferenceSpinner = v.findViewById(R.id.spinner_preferences);
        registerBtn = v.findViewById(R.id.register_button_R);
        progressBar = v.findViewById(R.id.progressBar_signUp);
        buttonBack = v.findViewById(R.id.floatingActionButton);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().popBackStack();
            }
        });
        homeFragment = new HomeFragment();
        database = FirebaseDatabase.getInstance();
        MainActivity.firebaseAuth = FirebaseAuth.getInstance();
        myRef = database.getReference("User");
//        birthEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showDatePickerDialog();
//            }
//        });

        rangeAge.addOnChangeListener(new RangeSlider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull RangeSlider slider, float value, boolean fromUser) {
                textViewAge.setText(rangeAge.getValues().get(0).intValue()+"-"+rangeAge.getValues().get(1).intValue());
            }
        });
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        topAppBar.setVisibility(View.INVISIBLE);
    }

    public void createUser(){
        try{
            if(!emailEdit.getText().toString().isEmpty()&&
                    !passwordEdit.getText().toString().isEmpty() &&
                    !rPasswordEdit.getText().toString().isEmpty()&&
                    !nameEdit.getText().toString().isEmpty()&&
//                    !birthEdit.getText().toString().isEmpty()&&
                    genderSpinner.getSelectedItemPosition()!= 0&&
                    preferenceSpinner.getSelectedItemPosition()!= 0){
                if (passwordEdit.getText().toString().equals(rPasswordEdit.getText().toString())){
                    progressBar.setVisibility(View.VISIBLE);
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    registerBtn.setEnabled(false);
                    MainActivity.firebaseAuth.createUserWithEmailAndPassword(emailEdit.getText().toString(), passwordEdit.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Toast.makeText(getContext(), "User created successfully", Toast.LENGTH_SHORT).show();
                            String key = myRef.push().getKey();
                            User u = new User(key, emailEdit.getText().toString(), nameEdit.getText().toString(), calendar, genderSpinner.getSelectedItem().toString(), preferenceSpinner.getSelectedItem().toString(), rangeAge.getValues().get(0).intValue(), rangeAge.getValues().get(1).intValue());
                            u.setId(key);
                            myRef.child(key).setValue(u);
                            progressBar.setVisibility(View.INVISIBLE);
                            registerBtn.setEnabled(true);
                            emailEdit.setText("");
                            passwordEdit.setText("");
                            rPasswordEdit.setText("");
                            if(MainActivity.firebaseAuth.getCurrentUser()!= null){
                                MainActivity.firebaseAuth.signOut();
                            }
                            replaceFragment(homeFragment);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressBar.setVisibility(View.INVISIBLE);
                            registerBtn.setEnabled(true);
                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
                }else{
                    Toast.makeText(getContext(), "Passwords are not the same", Toast.LENGTH_SHORT).show();
                }

            }else{
                Toast.makeText(getContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

//    private void showDatePickerDialog() {
//        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
//                // +1 because January is zero
//                if(month< 10) {
//                    final String selectedDate = day + "/0" + (month + 1) + "/" + year;
//                    calendar.set(year, month, day);
//                    birthEdit.setText(selectedDate);
//                }
//                else{
//                    final String selectedDate = day + "/" + (month + 1) + "/" + year;
//                    birthEdit.setText(selectedDate);
//                }
//            }
//        });
//
//        newFragment.show(getParentFragmentManager(), "datePicker");
//    }

    private void replaceFragment(Fragment fragment){
        try{
            FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.nav_host_fragment, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();

        }catch (Exception e){
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT);
        }
    }
}