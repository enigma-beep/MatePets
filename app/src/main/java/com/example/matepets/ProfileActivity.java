//package com.example.matepets;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.net.Uri;
//import android.os.Bundle;
//import android.provider.MediaStore;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.Toast;
//
//import com.example.matepets.models.owners;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//import java.io.IOException;
//
//public class ProfileActivity extends AppCompatActivity {
//    ImageView img;
//    EditText name,city,ph;
//    FirebaseAuth mAuth;
//    Uri uriProfileImage;
//    DatabaseReference databaseReference;
//    FirebaseDatabase firebaseDatabase;
//    String Iemail="Hello@123";
//    Button reg;
//    //    String Iemail = getIntent().getStringExtra("passEmail");
//    private static final int CHOOSE_IMAGE=101;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_profile);
//        img = (ImageView)findViewById(R.id.imageView);
//        name = (EditText)findViewById(R.id.editText3);
//        city = (EditText)findViewById(R.id.editText6);
//        ph = (EditText)findViewById(R.id.editText7);
//        reg = (Button)findViewById(R.id.button3);
//        mAuth = FirebaseAuth.getInstance();
//
////        databaseReference = FirebaseDatabase.getInstance().getReference(owners");
//
////        reg.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
//////                Toast.makeText(getApplicationContext(),"Working")
////                String Iname = name.getText().toString().trim();
////                String Icity = city.getText().toString().trim();
////                String Iph = ph.getText().toString().trim();
////
////                owners info = new owners(
////                        Iname,
////                        Iemail,
////                        Icity,
////                        Iph
////                );
////
////                FirebaseDatabase.getInstance().getReference("owners")
////                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
////                        .setValue(info).addOnCompleteListener(new OnCompleteListener<Void>() {
////                    @Override
////                    public void onComplete(@NonNull Task<Void> task) {
////                        if(task.isSuccessful()){
////                            Toast.makeText(getApplicationContext(),"Data inserted!",Toast.LENGTH_SHORT).show();
////                        }
////                    }
////                });
//
//
//
//
//
//
//
//
//
//
//
////
////            }
////        });
////
////        img.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                chooseImage();
////            }
////
////
////        });
////    }
////
////    //Image choosing, image has not been uploaded to firebase yet
////    @Override
////    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
////        super.onActivityResult(requestCode, resultCode, data);
////
////        if(requestCode==CHOOSE_IMAGE && resultCode==RESULT_OK && data!=null && data.getData()!=null){
////            uriProfileImage = data.getData();
////
////            try {
////                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uriProfileImage);
////                img.setImageBitmap(bitmap);
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
////
////        }
////    }
////
////    private void chooseImage() {
////        Intent i = new Intent();
////        i.setType("image/*");
////        i.setAction(Intent.ACTION_GET_CONTENT);
////        startActivityForResult(Intent.createChooser(i,"Select profile Image"),CHOOSE_IMAGE);
////    }
////}
////            }
////        });
////
////        img.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                chooseImage();
////            }
////
////
////        });
////    }
////
////    //Image choosing, image has not been uploaded to firebase yet
////    @Override
////    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
////        super.onActivityResult(requestCode, resultCode, data);
////
////        if(requestCode==CHOOSE_IMAGE && resultCode==RESULT_OK && data!=null && data.getData()!=null){
////            uriProfileImage = data.getData();
////
////            try {
////                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uriProfileImage);
////                img.setImageBitmap(bitmap);
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
////
////        }
////    }
////
////    private void chooseImage() {
////        Intent i = new Intent();
////        i.setType("image/*");
////        i.setAction(Intent.ACTION_GET_CONTENT);
////        startActivityForResult(Intent.createChooser(i,"Select profile Image"),CHOOSE_IMAGE);
////    }
////}
