package com.example.ticketing_app_facetoface;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Register_screen extends AppCompatActivity {

    EditText name, contact, dob, address, gmail, mobile;
    //Spinner spinner;
    Button insert, view;
    DBHelper DB;
    TextView login;
    ImageView regtologin;
    boolean isAllFieldsChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);




        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

      /* FragmentManager fragmentManager = getSupportFragmentManager();              //// first model ///pass new method111111111111
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        final ProfileFragment myFragment = new ProfileFragment();*/

        name = findViewById(R.id.name);
        contact = findViewById(R.id.contact);
        dob = findViewById(R.id.dob);
        address = findViewById(R.id.address);
        gmail = findViewById(R.id.gmail);
        mobile = findViewById(R.id.mobile);
        insert = findViewById(R.id.btInsert);
        view = findViewById(R.id.btView);
        regtologin =findViewById(R.id.regtologin);
        login = findViewById(R.id.regbutton2);
        DB = new DBHelper(this);
        regtologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register_screen.this,Login_page.class);
                startActivity(intent);
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register_screen.this, Login_page.class);
                Toast.makeText(getApplicationContext(), "Enter user Login details", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = name.getText().toString();
                String pass = contact.getText().toString();
                String company = dob.getText().toString();
                String address1 = address.getText().toString();
                String gmail1 = gmail.getText().toString();
                String mobile1 = mobile.getText().toString();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(Register_screen.this, "", Toast.LENGTH_SHORT).show();
                else {
                    if (pass.equals("")) {
                        Boolean checkuser = DB.checkusername(user);
                        if (checkuser == false) {
                            Boolean insert = DB.insertuserdata(user,pass,company,address1,gmail1,mobile1);
                            if (insert == true) {
                                Toast.makeText(Register_screen.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Home_Screen.class);
                                startActivity(intent);
                            } else{
                                Toast.makeText(Register_screen.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(Register_screen.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Register_screen.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                }
////////////////////////////////////////////////////////////
               /*Bundle b = new Bundle();
                b.putString("message", name.getText().toString());
                myFragment.setArguments(b);
                fragmentTransaction.add(R.id.frameLayout, myFragment).commit();*/
////////////////////////////////////////////////////////////////////
                isAllFieldsChecked = CheckAllFields();/////////pass value android studio method
                if (isAllFieldsChecked) {
                    Intent i = new Intent(Register_screen.this, Home_Screen.class);
                    startActivity(i);
                }/////////pass value method

                String nameTXT = name.getText().toString();
                String contactTXT = contact.getText().toString();
                String dobTXT = dob.getText().toString();
                String addressTXT = address.getText().toString();
                String gmailTXT = gmail.getText().toString();
                String mobileTXT = mobile.getText().toString();

                Boolean checkinsertdata = DB.insertuserdata(nameTXT, contactTXT, dobTXT, addressTXT, gmailTXT, mobileTXT);
                if (checkinsertdata == true)

                    Toast.makeText(Register_screen.this, "Enter all details", Toast.LENGTH_SHORT).show();

                else
                    Toast.makeText(Register_screen.this, "New Enter Not Inserted", Toast.LENGTH_SHORT).show();
                /*Intent intent = new Intent(Register_screen.this, Home_Screen.class);
                startActivity(intent);
                finish();*/
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if (res.getCount() == 0) {
                    Toast.makeText(Register_screen.this, "No Entry Existed", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Name :" + res.getString(0) + "\n");
                    buffer.append("Password :" + res.getString(1) + "\n");
                    buffer.append("Company :" + res.getString(2) + "\n");
                    buffer.append("Address :" + res.getString(3) + "\n");
                    buffer.append("Gmail :" + res.getString(4) + "\n");
                    buffer.append("Mobile :" + res.getString(5) + "\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(Register_screen.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }

    boolean isname(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }
//////pass value method used here///////////
        private boolean CheckAllFields() {
            if (name.length() == 0) {
                name.setError("Username field is required");
                return false;
            }
            if (contact.length() == 0) {
                contact.setError("Password is required");
                return false;
            } else if (contact.length() < 8) {
                contact.setError("Password must be minimum 8 characters");
                return false;
            }
            if (dob.length() == 0) {
                dob.setError("Company field is required");
                return false;
            }

            if (address.length() == 0) {
                address.setError("Address is required");
                return false;
            }
            if (gmail.length() == 0) {
                gmail.setError("Gmail is required");
                return false;
            }
            if (mobile.length() == 0) {
                mobile.setError("Mobile number is required");
                return false;
            } else if (mobile.length() < 10) {
                mobile.setError("Mobilenumber must be minimum 10 characters");
                return false;
            }



            // after all validation return true.
            return true;
        }
    }



