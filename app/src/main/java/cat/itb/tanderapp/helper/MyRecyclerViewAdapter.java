package cat.itb.tanderapp.helper;

import android.content.Context;
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
import cat.itb.tanderapp.fragments.ChatFragment;
import cat.itb.tanderapp.model.User;
import de.hdodenhof.circleimageview.CircleImageView;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ProfileViewHolder> {

    List<User> users;

    public MyRecyclerViewAdapter(List<User> users){
        this.users = users;
    }


    @NonNull
    @Override
    public MyRecyclerViewAdapter.ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        return new ProfileViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewAdapter.ProfileViewHolder holder, int position) {
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

    class ProfileViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        CircleImageView image;

        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.profile_name);
            image = itemView.findViewById(R.id.profile_image);

        }
        public void bindData(User user){
            name.setText(user.getName());
            Glide.with(image)
                    .load(user.getUrls().get(0))
                    .into(image);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(v.getContext(), user.getName(), Toast.LENGTH_SHORT).show();
//                }
//            });
        }
    }
}
