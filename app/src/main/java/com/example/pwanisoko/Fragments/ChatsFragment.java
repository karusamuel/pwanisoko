package com.example.pwanisoko.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.LinearLayoutManager;

import com.example.pwanisoko.R;
import com.example.pwanisoko.adapters.ChatAdapter;
import com.example.pwanisoko.models.ChatList;
import com.example.pwanisoko.models.ChatsModel;
import com.example.pwanisoko.objects.LayoutManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatsFragment extends Fragment {
    RecyclerView recyclerView;
    FirebaseDatabase mDatabase;
    FirebaseAuth mAuth;
    DatabaseReference mRef;
    ArrayList<ChatList> list;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mRef = mDatabase.getReference().child("Last_Chat").child(mAuth.getUid());



    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstance){
        View view = inflater.inflate(R.layout.fragment_chats_layout,container,false);
        recyclerView = view.findViewById(R.id.basic_recyclerView);
        recyclerView.setLayoutManager(new LayoutManager().getLinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL));

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                     list .add(snapshot.getValue(ChatList.class));





                }
                recyclerView.setAdapter(new ChatAdapter(getContext(),list));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });








        return view;
    }
}
