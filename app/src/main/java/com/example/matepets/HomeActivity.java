package com.example.matepets;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.matepets.models.pets;
import com.example.matepets.models.rc;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    RecyclerView recview;
    FirebaseDatabase fdb=FirebaseDatabase.getInstance();
    DatabaseReference dref=fdb.getReference("owners");
    DatabaseReference reff;
    BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recview=findViewById(R.id.rview);
        recview.setHasFixedSize(true);
        recview.setLayoutManager(new LinearLayoutManager(this));
        mAuth=FirebaseAuth.getInstance();
        reff=dref.child(mAuth.getCurrentUser().getUid()).child("pets");
        navigation = findViewById(R.id.bottomNavBar);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        Intent a = new Intent(HomeActivity.this, HomeTestActivity.class);
                        startActivity(a);
                        break;
                    case R.id.action_shop:
                        break;
                    case R.id.vet:
                        break;
                    case R.id.action_profile:
                        Intent b = new Intent(HomeActivity.this, AccountActivity.class);
                        startActivity(b);
                        break;
                }
                return false;
            }
        });


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
