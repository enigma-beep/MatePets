package com.example.matepets;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.matepets.models.pets;
import com.example.matepets.models.rc;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    RecyclerView recview;
    FirebaseDatabase fdb=FirebaseDatabase.getInstance();
    DatabaseReference dref=fdb.getReference("owners");
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recview=findViewById(R.id.rview);
        recview.setHasFixedSize(true);
        recview.setLayoutManager(new LinearLayoutManager(this));
        mAuth=FirebaseAuth.getInstance();
        reff=dref.child(mAuth.getCurrentUser().getUid()).child("pets");

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<pets, rc> fca=new FirebaseRecyclerAdapter<pets, rc>(
                pets.class,
                R.layout.row,
                rc.class,
                reff
        ) {
            @Override
            protected void populateViewHolder(rc rc, pets pets, int i) {

                rc.setDetails(getApplicationContext(),pets.getName(),pets.getType(),pets.getBreed(), pets.getAge(),pets.getGender(),pets.getImageId());


            }
        };
        recview.setAdapter(fca);
    }
}
