package com.example.matepets;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.matepets.models.pets;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AddPetsActivity extends AppCompatActivity {

    EditText mName,mType,mBreed,mGender,mAge,mHeight,mWeight,mColor;
    Button bType, bBreed, bGender,kProfilePic;
    Button mAdd;
    FirebaseAuth mAuth;
    DatabaseReference reff;

    String imageId;
    ImageView img;
    String imgLink;
    public Uri uriProfileImage;
    private static final int CHOOSE_IMAGE=101;
    long maxid=0;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pets);
        mName=findViewById(R.id.etName);
        mType=findViewById(R.id.etType);
        mBreed=findViewById(R.id.etBreed);
        mGender=findViewById(R.id.etGender);
        mAge=findViewById(R.id.etAge);
        mHeight=findViewById(R.id.etHeight);
        mWeight=findViewById(R.id.etWeight);
        mColor = findViewById(R.id.etColour);

        bType=findViewById(R.id.chType);
        bBreed=findViewById(R.id.chBreed);
        bGender=findViewById(R.id.chGender);
        kProfilePic = findViewById(R.id.btProfilePic);
        mAdd=findViewById(R.id.btnAdd);
        mAuth = FirebaseAuth.getInstance();

        storageReference = FirebaseStorage.getInstance().getReference("images");

        img = (ImageView)findViewById(R.id.ivPetImg);



        reff= FirebaseDatabase.getInstance().getReference().child("owners").child(mAuth.getCurrentUser().getUid()).child("pets");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    maxid=dataSnapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        kProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });

        bType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBreed.setText("");
                final PopupMenu popupMenu=new PopupMenu(AddPetsActivity.this,bType);
                popupMenu.getMenuInflater().inflate(R.menu.pettype,popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        Toast.makeText(getApplicationContext(),""+ menuItem.getTitle(),Toast.LENGTH_LONG).show();
                        mType.setText(menuItem.getTitle());
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
        bBreed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sType=mType.getText().toString();
                if(sType.equals("Dog")){
                    final PopupMenu popupMenu=new PopupMenu(AddPetsActivity.this,bBreed);
                    popupMenu.getMenuInflater().inflate(R.menu.dogbreed,popupMenu.getMenu());

                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            Toast.makeText(getApplicationContext(),""+ menuItem.getTitle(),Toast.LENGTH_LONG).show();
                            mBreed.setText(menuItem.getTitle());
                            return true;
                        }
                    });
                    popupMenu.show();

                }
                if(sType.equals("Cat")){
                    final PopupMenu popupMenu=new PopupMenu(AddPetsActivity.this,bBreed);
                    popupMenu.getMenuInflater().inflate(R.menu.catbreed,popupMenu.getMenu());

                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            Toast.makeText(getApplicationContext(),""+ menuItem.getTitle(),Toast.LENGTH_LONG).show();
                            mBreed.setText(menuItem.getTitle());
                            return true;
                        }
                    });
                    popupMenu.show();

                }
                if(sType.equals("")){
                    Toast.makeText(getApplicationContext(),"Please Select Type",Toast.LENGTH_LONG).show();

                }
            }
        });
        bGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final PopupMenu popupMenu=new PopupMenu(AddPetsActivity.this,bGender);
                popupMenu.getMenuInflater().inflate(R.menu.gender,popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        Toast.makeText(getApplicationContext(),""+ menuItem.getTitle(),Toast.LENGTH_LONG).show();
                        mGender.setText(menuItem.getTitle());
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mName.getText().toString().equals("")){
                    mName.setError("Name can't be blank");
                }
                else if(mType.getText().toString().equals("")){
                    mType.setError("choose Type");
                }
                else if(mBreed.getText().toString().equals("")){
                    mBreed.setError("choose Breed");
                }
                else if(mGender.getText().toString().equals("")){
                    mGender.setError("choose Gender");
                }
                else if(mAge.getText().toString().equals("")){
                    mAge.setError("choose Age");
                }
                else{

                    String pname=mName.getText().toString().trim();
                    String pbreed=mBreed.getText().toString().trim();
                    String ptype=mType.getText().toString().trim();
                    String pcolor=mColor.getText().toString().trim();
                    String page=mAge.getText().toString().trim();
                    String pgen=mGender.getText().toString().trim();
                    //UploadImage section

                    uploadImage();

                    pets det=new pets(

                            pname,
                            ptype,
                            pbreed,
                            pcolor,
                            page,
                            pgen,
                            imgLink
                    );
                    reff.child(String.valueOf(++maxid)).setValue(det);

                    Intent i=new Intent(AddPetsActivity.this,HomeActivity.class);
                    startActivity(i);
                }


            }
        });


    }
    private void chooseImage() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i,101);
    }

    private String getExtension(Uri uri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }

    private void uploadImage(){
        imageId = System.currentTimeMillis()+"."+getExtension(uriProfileImage);
        final StorageReference ref=storageReference.child(imageId);
        if(uriProfileImage!=null){

            ref.putFile(uriProfileImage)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(getApplicationContext(),"Image Uploaded!",Toast.LENGTH_LONG).show();
                            //Inserting image link in database
                            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    imgLink = uri.toString();
                                    reff.child(String.valueOf(maxid)).child("imageId").setValue(imgLink);

                                }
                            });

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                            // ...
                        }
                    });
        }
        else{
            Toast.makeText(getApplicationContext(),"No Image Selected",Toast.LENGTH_LONG).show();
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CHOOSE_IMAGE && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            uriProfileImage = data.getData();
            img.setImageURI(uriProfileImage);
        }
    }
}
