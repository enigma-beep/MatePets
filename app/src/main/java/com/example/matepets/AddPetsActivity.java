package com.example.matepets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

public class AddPetsActivity extends AppCompatActivity {

    EditText mName,mType,mBreed,mGender,mAge,mHeight,mWeight;
    Button bType, bBreed, bGender;
    Button mAdd;

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

        bType=findViewById(R.id.chType);
        bBreed=findViewById(R.id.chBreed);
        bGender=findViewById(R.id.chGender);
        mAdd=findViewById(R.id.btnAdd);

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
                mBreed.setText("");
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
                    Intent i=new Intent(AddPetsActivity.this,HomeActivity.class);
                    startActivity(i);
                }


            }
        });


    }
}
