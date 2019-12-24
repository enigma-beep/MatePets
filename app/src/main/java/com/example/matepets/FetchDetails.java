package com.example.matepets;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FetchDetails extends AppCompatActivity {
    Button goToPets;
    FirebaseDatabase fdb=FirebaseDatabase.getInstance();
    DatabaseReference owner_ref=fdb.getReference("owners");
    DatabaseReference pet_ref;
    FirebaseAuth mAuth;
    int i;
    String petCount;
    public ArrayList<Integer> slide_image = new ArrayList<Integer>();
    public ArrayList<String> slide_pet_name = new ArrayList<String>();
    public ArrayList<String> slide_type = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_details);
        goToPets = findViewById(R.id.button2);

        final ProgressDialog pd = new ProgressDialog(FetchDetails.this);
        pd.setMessage("Loading...");
        pd.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                goToPets.performClick();
            }
        }, 4000);
        mAuth = FirebaseAuth.getInstance();
        pet_ref=owner_ref.child(mAuth.getCurrentUser().getUid()).child("pets");

        pet_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                petCount = String.valueOf(dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        for ( i=1;i<=4;i++){
            String count = String.valueOf(i);
            pet_ref.child(count).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String pet_name = dataSnapshot.child("name").getValue().toString();
                    String pet_type = dataSnapshot.child("type").getValue().toString();
                    String pet_img = dataSnapshot.child("imageId").getValue().toString();
                    //Toast.makeText(getApplicationContext(),pet_name,Toast.LENGTH_SHORT).show();
                    slide_pet_name.add(pet_name);
                    slide_type.add(pet_type);
                    slide_image.add(R.drawable.dog1);
                    Toast.makeText(getApplicationContext(),"Fetched!",Toast.LENGTH_SHORT).show();

                    // Toast.makeText(getApplicationContext(),"Constructor!",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        goToPets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),HomeTestActivity.class);
                i.putIntegerArrayListExtra("slide_image",slide_image);
                i.putStringArrayListExtra("slide_pet_name",slide_pet_name);
                i.putStringArrayListExtra("slide_type",slide_type);
                i.putExtra("petCount",petCount);
                startActivity(i);
            }
        });
    }
}
