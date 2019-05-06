package com.example.pwanisoko.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pwanisoko.ChatActivity;
import com.example.pwanisoko.R;
import com.example.pwanisoko.models.ChatList;
import com.example.pwanisoko.models.ChatsModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyHolder> {

    Context context;
    ArrayList<ChatList> list;
    ArrayList<String>  keys;
    DatabaseReference ref;
    DatabaseReference ref2;
    FirebaseAuth mAuth;

    public ChatAdapter(Context context,ArrayList<ChatList> list,ArrayList<String> keys){
        this.context = context;
        this.list = list;
        this.keys = keys;
        mAuth = FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance().getReference().child("Last_Chat").child(mAuth.getUid());
        ref2 = FirebaseDatabase.getInstance().getReference().child("Chats").child(mAuth.getUid());
    }


    @NonNull
    @Override
    public ChatAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.chat_strip,viewGroup,false);
        return new ChatAdapter.MyHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final ChatAdapter.MyHolder myHolder, final int i) {
        myHolder.chatID.setText(list.get(i).getUserName());
        myHolder.lastMessage.setText(list.get(i).getLastMessage());
        myHolder.time.setText(list.get(i).getMessageTime());
        myHolder.chatStrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ChatActivity.class);
                intent.putExtra("ReceiverID",list.get(i).getSenderId());
                context.startActivity(intent);

            }
        });
        myHolder.chatStrip.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context,myHolder.time);
                popupMenu.getMenuInflater().inflate(R.menu.delete_conversation_menu,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.delete_convesation:
                                ref.child(keys.get(i)).removeValue();
                                ref2.child(list.get(i).getSenderId()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                      Toast.makeText(context,"Deleted",Toast.LENGTH_LONG).show();
                                    }
                                });
                                break;

                        }



                        return true;
                    }
                });
                popupMenu.show();




                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
      return   list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        TextView chatID,lastMessage,time;
        ImageView imageView;
        LinearLayout chatStrip;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            chatID = itemView.findViewById(R.id.chatID);
            chatStrip = itemView.findViewById(R.id.chatStrip);
            lastMessage = itemView.findViewById(R.id.chatLastMessage);
            time = itemView.findViewById(R.id.chat_date);


        }
    }
}
