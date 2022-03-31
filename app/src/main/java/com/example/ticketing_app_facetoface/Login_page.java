package com.example.ticketing_app_facetoface;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login_page extends AppCompatActivity {
    Button submit;
    TextView register,logintext;
    EditText editbui,editbui1;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        /*logintext = findViewById(R.id.logintext);
        logintext.setPaintFlags(logintext.getPaintFlags() | Paint.ANTI_ALIAS_FLAG);
        logintext.setText("Username");*/
        submit = findViewById(R.id.button);
        register = findViewById(R.id.register);
        editbui = (EditText) findViewById(R.id.editbui);
        editbui1= (EditText) findViewById(R.id.editbui1);
        DB = new DBHelper(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                
                Intent intent= new Intent(Login_page.this,Register_screen.class);
                Toast.makeText(getApplicationContext(),"Please Update Your Details",Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }

        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = editbui.getText().toString();
                String pass = editbui1.getText().toString();
                if(user.equals("")||pass.equals(""))
                    Toast.makeText(Login_page.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
               else {
                    Boolean checkuserpass = DB.checkusernamepassword(user,pass);
                    if (checkuserpass == true) {
                        Toast.makeText(Login_page.this, "Sign in successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Home_Screen.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Login_page.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();

                    }

       }

            }
        });
    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}