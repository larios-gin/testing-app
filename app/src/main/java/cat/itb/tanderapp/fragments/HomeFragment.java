package cat.itb.tanderapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.StackFrom;
import com.yuyakaido.android.cardstackview.SwipeableMethod;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cat.itb.tanderapp.R;
import cat.itb.tanderapp.activities.MainActivity;
import cat.itb.tanderapp.helper.CardStackViewAdapter;
import cat.itb.tanderapp.model.User;

import static cat.itb.tanderapp.activities.MainActivity.topAppBar;


public class HomeFragment extends Fragment implements CardStackListener {
    private CardStackView cardStackView;
    private CardStackLayoutManager manager;
    private CardStackViewAdapter adapter;
    private List<User> users;
    private FloatingActionButton rewind, dislike, superlike, like, boost;
    public HomeFragment() {
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
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        Calendar c = Calendar.getInstance();
        c.set(2001, 6, 14);
        users = new ArrayList<>();
        List<String> urls = new ArrayList<>();
        urls.add("https://images.unsplash.com/photo-1481349518771-20055b2a7b24?ixid=MXwxMjA3fDB8MHxzZWFyY2h8M3x8cmFuZG9tfGVufDB8fDB8&ixlib=rb-1.2.1&w=1000&q=80");
        for(int i = 0; i< 30; i++){
            users.add(new User("dsdadwadsd","jonylecha@gmail.com", "Joan", c,"Male", "Female", 18, 24));
            users.get(i).setUrls(urls);
            users.get(i).setDescription("Drinking drinking?");
        }
        rewind = v.findViewById(R.id.rewindFAB);
        dislike = v.findViewById(R.id.dislikeFAB);
        superlike = v.findViewById(R.id.superlikeFAB);
        like = v.findViewById(R.id.likeFAB);
        boost = v.findViewById(R.id.boostFAB);
        cardStackView = v.findViewById(R.id.cardStackView);
        manager = new CardStackLayoutManager(getContext(), this);
        adapter = new CardStackViewAdapter(users);
        cardStackView.setLayoutManager(manager);
        cardStackView.setAdapter(adapter);
        setupCardStackView();
        manager.setStackFrom(StackFrom.Top);
        manager.setVisibleCount(4);
        manager.setTranslationInterval(8.0f);
//        setupButton();
        topAppBar.setVisibility(View.VISIBLE);
        return v;
    }

    @Override
    public void onCardDragging(Direction direction, float ratio) {

    }

    @Override
    public void onCardSwiped(Direction direction) {

    }

    @Override
    public void onCardRewound() {

    }

    @Override
    public void onCardCanceled() {

    }

    @Override
    public void onCardAppeared(View view, int position) {

    }

    @Override
    public void onCardDisappeared(View view, int position) {

    }
    private void setupCardStackView(){
        manager.setStackFrom(StackFrom.None);
        manager.setVisibleCount(3);
        manager.setTranslationInterval(8.0f);
        manager.setScaleInterval(0.95f);
        manager.setSwipeThreshold(0.3f);
        manager.setMaxDegree(20.0f);
        manager.setDirections(Direction.HORIZONTAL);
        manager.setCanScrollHorizontal(true);
        manager.setCanScrollVertical(true);
        manager.setSwipeableMethod(SwipeableMethod.AutomaticAndManual);
        manager.setOverlayInterpolator(new LinearInterpolator());
        DefaultItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setSupportsChangeAnimations(false);
        cardStackView.setItemAnimator(itemAnimator);
    }
}
