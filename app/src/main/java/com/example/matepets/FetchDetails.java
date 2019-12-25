package com.example.matepets;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.matepets.models.pets;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class FetchDetails extends AppCompatActivity {
    Button goToPets;
    //Fetching pet details
    FirebaseDatabase fdb=FirebaseDatabase.getInstance();
    DatabaseReference owner_ref=fdb.getReference("owners");
    DatabaseReference pet_ref;
    //authentication
    FirebaseAuth mAuth;
    int i;
    String petCount;
    //fetching pet images
    FirebaseStorage store = FirebaseStorage.getInstance();
    StorageReference ref = store.getReference("images");
    //fetching filters
    String type,breed,gender,city;
    String found="false";

    public ArrayList<String> slide_image = new ArrayList<String>();
    public ArrayList<String> slide_pet_name = new ArrayList<String>();
    public ArrayList<String> slide_type = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_details);
        goToPets = findViewById(R.id.button2);

        //Fetching filters
        type = getIntent().getStringExtra("type");
        breed = getIntent().getStringExtra("breed");
        gender = getIntent().getStringExtra("gender");


        final ProgressDialog pd = new ProgressDialog(FetchDetails.this);
        pd.setMessage("Loading...");
        pd.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                goToPets.performClick();
            }
        }, 8000);
        mAuth = FirebaseAuth.getInstance();
        final String userId = ""+mAuth.getCurrentUser().getUid();
        pet_ref=owner_ref.child(mAuth.getCurrentUser().getUid()).child("pets");
//        Toast.makeText(getApplicationContext(),userId,Toast.LENGTH_LONG).show();


        owner_ref.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                city = dataSnapshot.child("city").getValue().toString();
                Toast.makeText(FetchDetails.this, city, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        owner_ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (final DataSnapshot snapshot : dataSnapshot.getChildren()) {
                          if(userId.equals(snapshot.getKey())){
                              //Toast.makeText(getApplicationContext(),snapshot.getKey(),Toast.LENGTH_LONG).show();

                          }
                          else{
                                DatabaseReference ref = owner_ref.child(snapshot.getKey()).child("pets");
                                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                                        for (DataSnapshot snapshot1 : dataSnapshot1.getChildren()) {
                                            String pet_name = snapshot1.child("name").getValue().toString();
                                            String pet_type = snapshot1.child("type").getValue().toString();
                                            String pet_img = snapshot1.child("imageId").getValue().toString();
//                                            pets obj_pets = new pets();
//                                            obj_pets.setName(pet_name);
//                                            obj_pets.setType(pet_type);
//                                            obj_pets.setImageId(pet_img);
                                            pets obj_pets = snapshot1.getValue(pets.class);
                                            if(obj_pets.getType().equals(type) && obj_pets.getGender().equals(gender) && obj_pets.getBreed().equals(breed)){
                                                slide_pet_name.add(pet_name);
                                                slide_type.add(pet_type);
                                                slide_image.add(pet_img);
                                                found = "true";
                                            }
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                          }
//                            Toast.makeText(getApplicationContext(),snapshot.getKey(),Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });






//        pet_ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                petCount = String.valueOf(dataSnapshot.getChildrenCount());
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//        for ( i=1;i<=4;i++){
//            String count = String.valueOf(i);
//            pet_ref.child(count).addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    String pet_name = dataSnapshot.child("name").getValue().toString();
//                    String pet_type = dataSnapshot.child("type").getValue().toString();
//                    String pet_img = dataSnapshot.child("imageId").getValue().toString();
//                    //Toast.makeText(getApplicationContext(),pet_name,Toast.LENGTH_SHORT).show();
//                    slide_pet_name.add(pet_name);
//                    slide_type.add(pet_type);
//                    slide_image.add(pet_img);
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                }
//            });
//        }

        goToPets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),HomeTestActivity.class);
                i.putStringArrayListExtra("slide_image",slide_image);
                i.putStringArrayListExtra("slide_pet_name",slide_pet_name);
                i.putStringArrayListExtra("slide_type",slide_type);
                i.putExtra("found",found);
                //passing filters
                i.putExtra("breed",breed);
                i.putExtra("gender",gender);
                i.putExtra("type",type);
                startActivity(i);
                finish();
            }
        });
    }
}
