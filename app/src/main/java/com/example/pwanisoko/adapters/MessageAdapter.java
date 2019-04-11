package com.example.pwanisoko.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.pwanisoko.R;
import com.example.pwanisoko.models.ChatsModel;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyHolder> {
    Context context;
    ArrayList<ChatsModel> list;
    String uid;
    String name;

    public MessageAdapter(Context context, ArrayList<ChatsModel> list, String uid,String name) {
        this.context = context;
        this.list = list;
        this.uid = uid;
        this.name = name;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);

        return new MyHolder( inflater.inflate(R.layout.chat_layout_strip,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        ChatsModel model = list.get(i);
        if(model.getSenderId().equals(uid)){
            myHolder.inMessage.setVisibility(View.GONE);
            myHolder.messsageTextOut.setText(model.getText());
            myHolder.messageUserOut.setText(model.getText());
            myHolder.timeTextOut.setText(model.getText());





        }else {
            myHolder.outMessage.setVisibility(View.GONE);
            myHolder.messsageTextIn.setText(model.getText());
            myHolder.messageUserIn.setText(model.getText());
            myHolder.timeTextIn.setText(model.getText());



        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        RelativeLayout inMessage,outMessage;
        TextView  messageUserIn,messsageTextIn,timeTextIn;
        TextView  messageUserOut,messsageTextOut,timeTextOut;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            inMessage = itemView.findViewById(R.id.inMesaage);
            messageUserIn = itemView.findViewById(R.id.message_user_in);
            messsageTextIn = itemView.findViewById(R.id.message_text_in);
            timeTextIn = itemView.findViewById(R.id.message_time_in);


            outMessage = itemView.findViewById(R.id.outMessage);
            messageUserOut = itemView.findViewById(R.id.message_user_out);
            messsageTextOut = itemView.findViewById(R.id.message_text_in_out);
            timeTextOut= itemView.findViewById(R.id.message_time_out);



        }
    }
}
