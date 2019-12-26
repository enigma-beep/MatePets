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

public class FilterActivity extends AppCompatActivity {
    EditText mType,mBreed,mGender,mHeight,mWeight,mCity;
    Button bType, bBreed, bGender,bCity;
    Button mSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        mType=findViewById(R.id.etType);
        mBreed=findViewById(R.id.etBreed);
        mGender=findViewById(R.id.etGender);

        mHeight=findViewById(R.id.etHeight);
        mWeight=findViewById(R.id.etWeight);
        mCity = findViewById(R.id.etCity);

        bType=findViewById(R.id.chType);
        bBreed=findViewById(R.id.chBreed);
        bGender=findViewById(R.id.chGender);
        bCity = findViewById(R.id.chCity);

        mSearch=findViewById(R.id.btnAdd);

        bType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBreed.setText("");
                final PopupMenu popupMenu=new PopupMenu(FilterActivity.this,bType);
                popupMenu.getMenuInflater().inflate(R.menu.pettype,popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        //Toast.makeText(getApplicationContext(),""+ menuItem.getTitle(),Toast.LENGTH_LONG).show();
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
                    final PopupMenu popupMenu=new PopupMenu(FilterActivity.this,bBreed);
                    popupMenu.getMenuInflater().inflate(R.menu.dogbreed,popupMenu.getMenu());

                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            //Toast.makeText(getApplicationContext(),""+ menuItem.getTitle(),Toast.LENGTH_LONG).show();
                            mBreed.setText(menuItem.getTitle());
                            return true;
                        }
                    });
                    popupMenu.show();

                }
                if(sType.equals("Cat")){
                    final PopupMenu popupMenu=new PopupMenu(FilterActivity.this,bBreed);
                    popupMenu.getMenuInflater().inflate(R.menu.catbreed,popupMenu.getMenu());

                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                          //  Toast.makeText(getApplicationContext(),""+ menuItem.getTitle(),Toast.LENGTH_LONG).show();
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
                final PopupMenu popupMenu=new PopupMenu(FilterActivity.this,bGender);
                popupMenu.getMenuInflater().inflate(R.menu.gender,popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                       // Toast.makeText(getApplicationContext(),""+ menuItem.getTitle(),Toast.LENGTH_LONG).show();
                        mGender.setText(menuItem.getTitle());
                        return true;
                    }
                });
                popupMenu.show();
            }
        });

        bCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final PopupMenu popupMenu=new PopupMenu(FilterActivity.this,bCity);
                popupMenu.getMenuInflater().inflate(R.menu.citymenu,popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        //Toast.makeText(getApplicationContext(),""+ menuItem.getTitle(),Toast.LENGTH_LONG).show();
                        mCity.setText(menuItem.getTitle());
                        return true;
                    }
                });
                popupMenu.show();
            }
        });

        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pbreed=mBreed.getText().toString().trim();
                String ptype=mType.getText().toString().trim();
                String pcity=mCity.getText().toString().trim();
                String pgen=mGender.getText().toString().trim();
                Intent i = new Intent(getApplicationContext(),FetchDetails.class);
                i.putExtra("breed",pbreed);
                i.putExtra("gender",pgen);
                i.putExtra("type",ptype);
                startActivity(i);
            }
        });
    }
}
