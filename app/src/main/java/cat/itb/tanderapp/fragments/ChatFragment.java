package cat.itb.tanderapp.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cat.itb.tanderapp.R;
import cat.itb.tanderapp.helper.CardStackViewAdapter;
import cat.itb.tanderapp.helper.MyRecyclerViewAdapter;
import cat.itb.tanderapp.helper.MyRecyclerViewAdapterChat;
import cat.itb.tanderapp.model.User;

import static cat.itb.tanderapp.activities.MainActivity.topAppBar;


public class ChatFragment extends Fragment {

    private List<User> users;
    private RecyclerView recyclerViewPhotos, recyclerViewChat;

    public ChatFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_chat, container, false);
        recyclerViewPhotos = v.findViewById(R.id.recyclerView);
        recyclerViewChat = v.findViewById(R.id.recyclerView2);
        users = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        c.set(2001, 6, 14);
        List<String> urls = new ArrayList<>();
        urls.add("https://images.unsplash.com/photo-1481349518771-20055b2a7b24?ixid=MXwxMjA3fDB8MHxzZWFyY2h8M3x8cmFuZG9tfGVufDB8fDB8&ixlib=rb-1.2.1&w=1000&q=80");
        for(int i = 0; i< 30; i++){
            users.add(new User("dsdadwadsd","jonylecha@gmail.com", "Joan", c,"Male", "Female", 18, 24));
            users.get(i).setUrls(urls);
            users.get(i).setDescription("Drinking drinking?");
        }
        recyclerViewChat.setAdapter(new MyRecyclerViewAdapterChat(users));
        recyclerViewChat.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewPhotos.setAdapter(new MyRecyclerViewAdapter(users));
        recyclerViewPhotos.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, true));
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        topAppBar.setVisibility(View.VISIBLE);
    }
}