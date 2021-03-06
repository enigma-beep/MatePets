package com.example.matepets;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

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

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    RecyclerView recview;
    FirebaseDatabase fdb=FirebaseDatabase.getInstance();
    DatabaseReference dref=fdb.getReference("owners");
    DatabaseReference reff;
    String gender,breed,type,found;
    BottomNavigationView navigation;
    public ArrayList<String> slide_image = new ArrayList<String>();
    public ArrayList<String> slide_pet_name = new ArrayList<String>();
    public ArrayList<String> slide_type = new ArrayList<String>();

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

        //fetching filters
        type = getIntent().getStringExtra("type");
        breed = getIntent().getStringExtra("breed");
        gender = getIntent().getStringExtra("gender");
        found=getIntent().getStringExtra("found");

        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);

//        Fade fade=new Fade();
//        View decor =getWindow().getDecorView();
//        fade.excludeTarget(R.id.discover,true);
//        fade.excludeTarget(R.id.bottomNavBar,true);
//        getWindow().setEnterTransition(fade);
//        getWindow().setExitTransition(fade);


        slide_image = getIntent().getStringArrayListExtra("slide_image");
        slide_pet_name = getIntent().getStringArrayListExtra("slide_pet_name");
        slide_type = getIntent().getStringArrayListExtra("slide_type");

        recview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "recview clicked", Toast.LENGTH_SHORT).show();
            }
        });

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        Intent a = new Intent(HomeActivity.this, HomeTestActivity.class);
                        a.putStringArrayListExtra("slide_image", slide_image);
                        a.putStringArrayListExtra("slide_pet_name", slide_pet_name);
                        a.putStringArrayListExtra("slide_type", slide_type);
                        //passing filters
                        a.putExtra("breed",breed);
                        a.putExtra("gender",gender);
                        a.putExtra("type",type);
                        a.putExtra("found",found);
                        startActivity(a);
                        break;
                    case R.id.action_shop:
                        break;
                    case R.id.vet:
                        break;
                    case R.id.action_profile:
                        Intent b = new Intent(HomeActivity.this, AccountActivity.class);
                        b.putStringArrayListExtra("slide_image", slide_image);
                        b.putStringArrayListExtra("slide_pet_name", slide_pet_name);
                        b.putStringArrayListExtra("slide_type", slide_type);
                        //passing filters
                        b.putExtra("breed",breed);
                        b.putExtra("gender",gender);
                        b.putExtra("type",type);
                        b.putExtra("found",found);
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
