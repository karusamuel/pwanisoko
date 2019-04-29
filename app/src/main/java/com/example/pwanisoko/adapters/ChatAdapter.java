package com.example.pwanisoko.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pwanisoko.ChatActivity;
import com.example.pwanisoko.R;
import com.example.pwanisoko.models.ChatList;
import com.example.pwanisoko.models.ChatsModel;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyHolder> {

    Context context;
    ArrayList<ChatList> list;

    public ChatAdapter(Context context,ArrayList<ChatList> list){
        this.context = context;
        this.list = list;

    }


    @NonNull
    @Override
    public ChatAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.chat_strip,viewGroup,false);
        return new ChatAdapter.MyHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.MyHolder myHolder, final int i) {
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
