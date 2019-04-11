package com.example.pwanisoko;

import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.pwanisoko.adapters.MessageAdapter;
import com.example.pwanisoko.models.AppUser;
import com.example.pwanisoko.models.ChatList;
import com.example.pwanisoko.models.ChatsModel;
import com.example.pwanisoko.objects.LayoutManager;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.view.View;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ChatActivity extends AppCompatActivity {
    boolean newChat;
    String receiverID;
    FirebaseDatabase database;
    DatabaseReference senderRef;
    DatabaseReference receiverRef;
    DatabaseReference lastChatSender;
    DatabaseReference lastChatReceiver;
    FirebaseAuth  mAuth;
    EditText editText;
    ImageView sendButton;
    AppUser receiver;
    AppUser sender;
    ArrayList<ChatsModel> chatsModels;
    RecyclerView messageList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
         newChat = getIntent().getBooleanExtra("New_Chat",false);
         receiverID = getIntent().getStringExtra("ReceiverID");
         database = FirebaseDatabase.getInstance();
         editText = findViewById(R.id.message);
         messageList = findViewById(R.id.messageList);
         sendButton = findViewById(R.id.sendButton);
         messageList.setLayoutManager(new LayoutManager().getLinearLayoutManager(this, LinearLayoutManager.VERTICAL));




          database.getReference().child("Users").child(receiverID).addValueEventListener(new ValueEventListener() {
              @Override
              public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    receiver = dataSnapshot.getValue(AppUser.class);
                  loadChats();


              }

              @Override
              public void onCancelled(@NonNull DatabaseError databaseError) {

              }
          });
          database.getReference().child("Users").child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
              @Override
              public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                      sender =dataSnapshot.getValue(AppUser.class);


              }

              @Override
              public void onCancelled(@NonNull DatabaseError databaseError) {

              }
          });



         sendButton.setOnClickListener(new android.view.View.OnClickListener() {
             @Override
             public void onClick(android.view.View v) {
                 if(editText.getText().toString().length()!=0) {
                     if(sender!=null && receiver!=null){


                         final String message = editText.getText().toString().trim();
                         ChatsModel model = new ChatsModel(message,receiverID,mAuth.getUid());

                         senderRef = database.getReference().child("Chats").child(mAuth.getUid());
                         receiverRef = database.getReference().child("Chats").child(receiverID);
                         senderRef.child(receiverID).push().setValue(model);
                         receiverRef.child(mAuth.getUid()).push().setValue(model);
                         lastChatSender = database.getReference().child("Last_Chat").child(mAuth.getUid()).child(receiverID);
                         lastChatSender.setValue(new ChatList(receiver.getName(),message,new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date()),mAuth.getUid(),receiverID,false));


                         lastChatReceiver = database.getReference().child("Last_Chat").child(receiverID).child(mAuth.getUid());
                         lastChatReceiver.setValue(new ChatList(sender.getName(),message,new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date()),mAuth.getUid(),mAuth.getUid(),true))
                                 .addOnSuccessListener(new OnSuccessListener<Void>() {
                                     @Override
                                     public void onSuccess(Void aVoid) {

                                         Toast.makeText(getApplicationContext(),"sent",Toast.LENGTH_SHORT).show();
                                         editText.setText("");
                                         loadChats();

                                     }
                                 })
                                 .addOnFailureListener(new OnFailureListener() {
                                     @Override
                                     public void onFailure(@NonNull Exception e) {

                                     }
                                 });



                     }else {

                         Toast.makeText(getApplicationContext(),"Connecting",Toast.LENGTH_SHORT).show();
                     }






                 }

             }
         });



    }

    public void loadChats(){
      database.getReference().child("Chats").child(mAuth.getUid()).child(receiverID).addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              chatsModels = new ArrayList<>();
              for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                  chatsModels.add(snapshot.getValue(ChatsModel.class));


              }
              messageList.setAdapter(new MessageAdapter(getApplicationContext(),chatsModels,mAuth.getUid(),receiver.getName()));


          }

          @Override
          public void onCancelled(@NonNull DatabaseError databaseError) {

          }
      });


    }

}
