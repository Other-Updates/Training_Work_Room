package com.example.ticketing_app_facetoface;

import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.menu.MenuView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;


public class ProfileFragment extends Fragment {


    EditText nameprofile,password,companyprofile,addressprofile,emailprofile,mobileprofile;
    AlertDialog.Builder builder;
    Button logout;
    ImageView prototic;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;
    /*private String mParam3;                   online method
    private String mParam4;
    private String mParam5;
    private String mParam6;*/

    public ProfileFragment() {
        // Required empty public constructor
    }


    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
           /* String strtext = getArguments().getString("name");///stack overflow method*/

        View view = inflater.inflate(R.layout.fragment_profile, container, false);


    /*  Bundle bundle = getArguments();
        String message = bundle.getString("message");
        nameprofile.setText(message);
*/
        prototic = view.findViewById(R.id.prototic);
        prototic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),AddTicketFragment.class);
                startActivity(intent);
            }
        });
        nameprofile = view.findViewById(R.id.nameprofile);
        password = view.findViewById(R.id.password);
        companyprofile = view.findViewById(R.id.companyprofile);
        addressprofile = view.findViewById(R.id.address);
        emailprofile = view.findViewById(R.id.emailprofile);
        mobileprofile = view.findViewById(R.id.mobileprofile);
        logout = view.findViewById(R.id.logout);
        builder = new AlertDialog.Builder(getActivity());

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    builder.setMessage("Are you surly want to exit Alert Dialog").setTitle("Alertbox");
                    builder.setMessage("do you want to exit");
                    builder.setCancelable(true);
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            Toast.makeText(getActivity(), "Your are selected YES", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(),Login_page.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent);
                        }


                    });
                    builder.setCancelable(false);
                    builder.setNegativeButton("No", null);
                    AlertDialog alert = builder.create();
                    alert.setTitle("Alert box");
                    alert.show();

                }
            });

   return view;
}




        /*Log.e("check", String.valueOf(nameprofile));
        Bundle bundle = getArguments();                                     pass value new method11111111111111111111
        String message = bundle.getString("message");
        nameprofile.setText(message);
        return view;
}
        ////////////////////////////////////////////////////
        Intent intent =getIntent();
        String profilename = getIntent().getStringExtra("keyname");    // pass value new not used
        String pass = getIntent().getStringExtra("keyname");
        String company = getIntent().getStringExtra("keyname");
        String address = getIntent().getStringExtra("keyname");
        String email = getIntent().getStringExtra("keyname");
        String mobile = getIntent().getStringExtra("keyname");
        TextInputEditText.setText(profilename);
        TextInputEditText.setText(pass);
        TextInputEditText.setText(company);
        TextInputEditText.setText(address);
        TextInputEditText.setText(email);
        TextInputEditText.setText(mobile);*/



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
          /*  mParam1 = getArguments().getString("name");
            mParam2 = getArguments().getString("Contact");
            mParam3 = getArguments().getString("dob");          online method not worked
            mParam4 = getArguments().getString("address");
            mParam5 = getArguments().getString("email");
            mParam6 = getArguments().getString("mobile");
*/


        }
    }}