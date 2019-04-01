package com.example.pwanisoko.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.LinearLayoutManager;

import com.example.pwanisoko.R;
import com.example.pwanisoko.adapters.ChatAdapter;
import com.example.pwanisoko.objects.LayoutManager;

public class ChatsFragment extends Fragment {
    RecyclerView recyclerView;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);


    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstance){
        View view = inflater.inflate(R.layout.fragment_chats_layout,container,false);
        recyclerView = view.findViewById(R.id.basic_recyclerView);

        recyclerView.setLayoutManager(new LayoutManager().getLinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(new ChatAdapter(getContext()));

        return view;
    }
}
