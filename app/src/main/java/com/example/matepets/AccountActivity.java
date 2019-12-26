package com.example.matepets;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AccountActivity extends AppCompatActivity {
    TextView name, email, city, ph;
    ImageView img;
    Button yp, addPet;
    BottomNavigationView navigation;
    FirebaseAuth mAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("owners");
    String petCount;
    String type,breed,gender,found;
    FirebaseStorage store = FirebaseStorage.getInstance();
    StorageReference ref = store.getReference("owner_images");
    public ArrayList<String> slide_image = new ArrayList<String>();
    public ArrayList<String> slide_pet_name = new ArrayList<String>();
    public ArrayList<String> slide_type = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        name = (TextView) findViewById(R.id.textView3);
        email = (TextView) findViewById(R.id.textView4);
        city = (TextView) findViewById(R.id.textView5);
        ph = (TextView) findViewById(R.id.textView6);
        yp = (Button) findViewById(R.id.button6);
        addPet = (Button) findViewById(R.id.button7);
        mAuth = FirebaseAuth.getInstance();
        navigation = findViewById(R.id.bottomNavBar);
        img = (ImageView) findViewById(R.id.imageView);
        DatabaseReference newref = myRef.child(mAuth.getCurrentUser().getUid());

        //passing valeus for HomeTestActivity
        slide_image = getIntent().getStringArrayListExtra("slide_image");
        slide_pet_name = getIntent().getStringArrayListExtra("slide_pet_name");
        slide_type = getIntent().getStringArrayListExtra("slide_type");
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


        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        Intent i = new Intent(AccountActivity.this, HomeTestActivity.class);
                        i.putStringArrayListExtra("slide_image",slide_image);
                        i.putStringArrayListExtra("slide_pet_name",slide_pet_name);
                        i.putStringArrayListExtra("slide_type",slide_type);
                        //passing filters
                        i.putExtra("breed",breed);
                        i.putExtra("gender",gender);
                        i.putExtra("type",type);
                        i.putExtra("found",found);
                        startActivity(i);
                        finish();
                        break;
                    case R.id.action_shop:
                        break;
                    case R.id.vet:
                        break;
                    case R.id.action_profile:
                        Intent b = new Intent(AccountActivity.this, AccountActivity.class);
                        startActivity(b);
                        break;
                }
                return false;
            }
        });


        newref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String iname = dataSnapshot.child("name").getValue().toString();
                String iemail = dataSnapshot.child("email").getValue().toString();
                String icity = dataSnapshot.child("city").getValue().toString();
                String iph = dataSnapshot.child("phno").getValue().toString();
                String imageId = dataSnapshot.child("ownimg").getValue().toString();

                email.setText(iemail);
                name.setText(iname);
                city.setText(icity);
                ph.setText(iph);

                try {
                    final File file = File.createTempFile("img", ".jpg");
                    ref.child(imageId).getFile(file).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                            img.setImageBitmap(bitmap);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Image Failed to Load", Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        yp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference petRef = myRef.child(mAuth.getCurrentUser().getUid()).child("pets");
                petRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        petCount = String.valueOf(dataSnapshot.getChildrenCount());
                        Toast.makeText(getApplicationContext(), petCount, Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                        i.putStringArrayListExtra("slide_image", slide_image);
                        i.putStringArrayListExtra("slide_pet_name", slide_pet_name);
                        i.putStringArrayListExtra("slide_type", slide_type);
                        //passing filters
                        i.putExtra("breed",breed);
                        i.putExtra("gender",gender);
                        i.putExtra("type",type);
                        i.putExtra("found",found);
                        startActivity(i);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                /*Toast.makeText(getApplicationContext(),petCount,Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),YourPets.class);
                i.putExtra("petcount",petCount);*/
                //startActivity(i);
            }
        });

        addPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), AddPetsActivity.class);
                startActivity(i);
            }
        });
    }
}
