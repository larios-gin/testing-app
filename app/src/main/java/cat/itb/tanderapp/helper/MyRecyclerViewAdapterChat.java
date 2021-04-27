package cat.itb.tanderapp.helper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import cat.itb.tanderapp.R;
import cat.itb.tanderapp.model.User;
import de.hdodenhof.circleimageview.CircleImageView;

public class MyRecyclerViewAdapterChat extends RecyclerView.Adapter<MyRecyclerViewAdapterChat.ProfileViewHolder> {

    List<User> users;

    public MyRecyclerViewAdapterChat(List<User> users){
        this.users = users;
    }


    @NonNull
    @Override
    public MyRecyclerViewAdapterChat.ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_chat, parent, false);
        return new ProfileViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewAdapterChat.ProfileViewHolder holder, int position) {
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
        TextView name, chat;
        CircleImageView image;

        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.profile_name);
            chat = itemView.findViewById(R.id.profile_name2);
            image = itemView.findViewById(R.id.profile_image);

        }
        public void bindData(User user){
            name.setText(user.getName());
            chat.setText(user.getDescription());
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
