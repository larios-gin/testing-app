package cat.itb.tanderapp.helper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import cat.itb.tanderapp.R;
import cat.itb.tanderapp.model.User;

public class CardStackViewAdapter extends RecyclerView.Adapter<CardStackViewAdapter.CardViewHolder> {
    List<User> users;

    public CardStackViewAdapter(List<User> users){
        this.users = users;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_card, parent, false);
        return new CardViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        holder.bindData(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    class CardViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView city;
        ImageView image;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_name);
            city = itemView.findViewById(R.id.item_city);
            image = itemView.findViewById(R.id.item_image);

        }
        public void bindData(User user){
            name.setText(user.getName()+", "+ user.getAge());
            city.setText(user.getDescription());
            Glide.with(image)
                    .load(user.getUrls().get(0))
                    .into(image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), user.getName(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
