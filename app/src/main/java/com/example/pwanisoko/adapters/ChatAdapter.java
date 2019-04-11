package com.example.pwanisoko.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    public void onBindViewHolder(@NonNull ChatAdapter.MyHolder myHolder, int i) {
        myHolder.chatID.setText(list.remove(i).getUserName());

    }

    @Override
    public int getItemCount() {
      return   list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        TextView chatID;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            chatID = itemView.findViewById(R.id.chatID);


        }
    }
}
