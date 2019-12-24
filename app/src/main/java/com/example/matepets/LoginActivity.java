package com.example.matepets;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.matepets.models.owners;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {
    EditText mCity,mPhone,mEmail,mName,mPass,mRePass;
    ImageView mProfile;
    Button cCity;
    Button mOTP,mEmailbtn,mProfilePic;
    private FirebaseAuth mAuth;
    Uri uriProfileImage;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("owners");
    String ownimg;
    StorageReference storageReference;
    private static final int CHOOSE_IMAGE=101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mProfilePic=findViewById(R.id.btProfilePic);
        mName=findViewById(R.id.etName);
        mPass=findViewById(R.id.etPass);
        mRePass=findViewById(R.id.etRePass);
        mPhone=(EditText) findViewById(R.id.etPhone);
        mEmail=(EditText) findViewById(R.id.etEmail);
        cCity=findViewById(R.id.chCity);
        mCity=findViewById(R.id.etCity);
        mProfile=findViewById(R.id.ivProfile);

        storageReference = FirebaseStorage.getInstance().getReference("owner_images");
        mAuth = FirebaseAuth.getInstance();
        mProfile.setImageResource(R.drawable.profile);

        mProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });





        mOTP=findViewById(R.id.btnOTP);
        mEmailbtn=findViewById(R.id.btnEMAIL);
        mOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mName.getText().toString().equals("")){
                    mName.setError("Name can't be blank");
                }
                else if(mCity.getText().toString().equals("")){
                    mCity.setError("choose City");
                }
                else if(mPhone.getText().toString().equals("")){
                    mPhone.setError("Phone number can't be blank");
                    mPhone.requestFocus();
                }
                else if(mPhone.getText().toString().length()<10){
                    mPhone.setError("Enter a valid Phone number");
                    mPhone.requestFocus();
                }
                else if(mPass.getText().toString().equals("")){
                    mPass.setError("Password can't be blank");
                }
                else if(mRePass.getText().toString().equals("")){
                    mRePass.setError("Re-enter Password");
                }
                else if(!mRePass.getText().toString().equals(mPass.getText().toString())){
                    mRePass.setError("Password doesn't match with above Password");
                }
                else{
                    final String phone="+91"+mPhone.getText().toString();
                    Intent i =new Intent(LoginActivity.this,LogActivity.class);
                    i.putExtra("phon",phone);
                    i.putExtra("name",mName.getText().toString());
                    i.putExtra("city",mCity.getText().toString());
                    i.putExtra("email",mEmail.getText().toString());
                    i.putExtra("pass",mPass.getText().toString());
                    i.putExtra("ownimg",ownimg);

                    String Iname = mName.getText().toString().trim();
                    String Icity = mCity.getText().toString().trim();
                    String Iph = mPhone.getText().toString().trim();
                    String Iemail=mEmail.getText().toString().trim();
//                                String strTemp="Hello";
                    uploadimg();
                    owners info = new owners(
                            Iname,
                            Iemail,
                            Icity,
                            Iph,
                            ownimg
//                                        strTemp
                    );

                    myRef.child(mAuth.getCurrentUser().getUid())
                            .setValue(info).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"Data inserted!",Toast.LENGTH_SHORT).show();
//                                            Intent i=new Intent(getApplicationContext(),.class);
//                                            startActivity(i);
//                                            finish();
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Data Not inserted!",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    startActivity(i);
                }





            }
        });
        mEmailbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mName.getText().toString().equals("")){
                    mName.setError("Name can't be blank");
                }
                else if(mCity.getText().toString().equals("")){
                    mCity.setError("choose City");
                }
                else if(mEmail.getText().toString().equals("")){
                    mEmail.setError("Email can't be blank");
                }
                else if(mPass.getText().toString().equals("")){
                    mPass.setError("Password can't be blank");
                }
                else if(mRePass.getText().toString().equals("")){
                    mRePass.setError("Re-enter Password");
                }
                else if(!mRePass.getText().toString().equals(mPass.getText().toString())){
                    mRePass.setError("Password doesn't match with above Password");
                }
                else{
                    final ProgressDialog pd = new ProgressDialog(LoginActivity.this);
                    pd.setMessage("Loading...");
                    pd.show();
                    register();
                }
            }
        });

        cCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final PopupMenu popupMenu=new PopupMenu(LoginActivity.this,cCity);
                popupMenu.getMenuInflater().inflate(R.menu.citymenu,popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        Toast.makeText(getApplicationContext(),""+ menuItem.getTitle(),Toast.LENGTH_LONG).show();
                        mCity.setText(menuItem.getTitle());
                        return true;
                    }
                });
                popupMenu.show();
            }
        });

    }

    public void register() {
        final String email = mEmail.getText().toString().trim();
        // pemail = email;
        final String pass = mPass.getText().toString().trim();

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("Please Enter a Valid Email!");
            mEmail.requestFocus();
            return;
        }

        if (mPass.length() < 6) {
            mPass.setError("Please Enter a valid Password!");
            mPass.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()) {

                                String Iname = mName.getText().toString().trim();
                                String Icity = mCity.getText().toString().trim();
                                String Iph = mPhone.getText().toString().trim();
                                String Iemail=mEmail.getText().toString().trim();
//                                String strTemp="Hello";
                                uploadimg();
                                owners info = new owners(
                                        Iname,
                                        Iemail,
                                        Icity,
                                        Iph,
                                        ownimg
//                                        strTemp
                                );

                                myRef.child(mAuth.getCurrentUser().getUid())
                                        .setValue(info).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(getApplicationContext(),"Data inserted!",Toast.LENGTH_SHORT).show();
//                                            Intent i=new Intent(getApplicationContext(),.class);
//                                            startActivity(i);
//                                            finish();
                                        }
                                        else{
                                            Toast.makeText(getApplicationContext(),"Data Not inserted!",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });






                                Toast.makeText(getApplicationContext(),"Registration successful, please check your email for verfication!",Toast.LENGTH_LONG).show();
                                Intent i = new Intent(LoginActivity.this, EmailActivity.class);
                                i.putExtra("Pass",pass);
                                i.putExtra("Email",email);
                                startActivity(i);
                            }
                            else{
                                Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }
                else{

                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(),"This email is already registered!",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    private String getExtension(Uri uri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }

    private void uploadimg() {
        ownimg = System.currentTimeMillis()+"."+getExtension(uriProfileImage);
        final StorageReference ref=storageReference.child(ownimg);
        if(uriProfileImage!=null){

            ref.putFile(uriProfileImage)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(getApplicationContext(),"Image Uploaded!",Toast.LENGTH_LONG).show();
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

            try {
//                Matrix matrix=new Matrix();
//                matrix.postRotate(90);
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uriProfileImage);
//                Bitmap rotated=Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
//                mProfile.setImageBitmap(rotated);
                mProfile.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    private void chooseImage() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i,"Select profile Image"),CHOOSE_IMAGE);
    }
}
