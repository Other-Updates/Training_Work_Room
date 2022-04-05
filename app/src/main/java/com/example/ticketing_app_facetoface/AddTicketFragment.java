package com.example.ticketing_app_facetoface;

import static android.app.Activity.RESULT_OK;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddTicketFragment extends Fragment {
    private DatePickerDialog mDateSetListener;

    EditText description, ticketnumberchange;
    TextView datepick_addticket;
    ImageView addticktohome;
    final Calendar myCalendar = Calendar.getInstance();
    MyAddDBHelper MyDB;
    ImageView imageView1;
    TextView next, previous;
    Button select, save, viewadd;
    ImageSwitcher imageView;

    int PICK_IMAGE_MULTIPLE = 1;
    String imageEncoded;
    TextView total;
    ArrayList<Uri> mArrayUri;
    int position = 0;
    List<String> imagesEncodedList;
    boolean isAllFieldsChecked = false;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public AddTicketFragment() {


    }

    // Button timepicker;

    public static AddTicketFragment newInstance(String param1, String param2) {
        AddTicketFragment fragment = new AddTicketFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_ticket, container, false);

        description = view.findViewById(R.id.description);
        ticketnumberchange = view.findViewById(R.id.ticketnumberchange);
        viewadd = view.findViewById(R.id.viewadd);
        MyDB = new MyAddDBHelper(getContext());

        imageView = view.findViewById(R.id.image);
        imageView1 = view.findViewById(R.id.imageView1);
        datepick_addticket = view.findViewById(R.id.datepick_addticket);
        previous = view.findViewById(R.id.previous);
        addticktohome = view.findViewById(R.id.addticktohome);
        addticktohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Home_Screen.class);
                startActivity(intent);
            }
        });

        mArrayUri = new ArrayList<Uri>();

        save = view.findViewById(R.id.save);

        datepick_addticket.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String _year = String.valueOf(year);
                        String _month = (month + 1) < 10 ? "0" + (month + 1) : String.valueOf(month + 1);
                        String _date = dayOfMonth < 10 ? "0" + dayOfMonth : String.valueOf(dayOfMonth);
                        String _pickedDate = year + "-" + _month + "-" + _date;
                        Log.e("PickedDate: ", "Date: " + _pickedDate); //2019-02-12

                        String current_date = parseDateToddMMyyyy(_pickedDate);
                        datepick_addticket.setText(current_date);
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.MONTH));
                dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                dialog.show();
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = ticketnumberchange.getText().toString();
                String pass = description.getText().toString();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(getActivity(),"" , Toast.LENGTH_SHORT).show();
                else {
                    if (pass.equals("")) {
                        Boolean checkuser = MyDB.checkusername(user);
                        if (checkuser == false) {
                            Boolean insert = MyDB.insertuserdata(user,pass);
                            if (insert == true) {
                            Toast.makeText(getActivity(), "Registered successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), Home_Screen.class);
                            startActivity(intent);
                            } else{
                                Toast.makeText(getActivity(), "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getActivity(), "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getActivity(), "Register successfully", Toast.LENGTH_SHORT).show();
                    }
                }
                isAllFieldsChecked = CheckAllFields();/////////pass value android studio method
                if (isAllFieldsChecked) {
                    Intent i = new Intent(getActivity(), Home_Screen.class);
                    startActivity(i);

            }
                String nameTXT = ticketnumberchange.getText().toString();
                String contactTXT = description.getText().toString();
                Boolean checkinsertdata = MyDB.insertuserdata(nameTXT, contactTXT);
                if (checkinsertdata == true)
                    Toast.makeText(getActivity(), "Enter all details", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity(), "New Enter Not Inserted", Toast.LENGTH_SHORT).show();
            }
        });
         viewadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = MyDB.getdata();
                if (res.getCount() == 0) {
                    Toast.makeText(getActivity(), "No Entry Existed", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Name :" + res.getString(0) + "\n");
                    buffer.append("discription :" + res.getString(1) + "\n");

                }
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });



        imageView.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView1 = new ImageView(getActivity());

                return imageView1;
            }
        });

        next = view.findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position < mArrayUri.size() - 1) {
                    // increase the position by 1
                    position++;
                    imageView.setImageURI(mArrayUri.get(position));
                } else {
                    Toast.makeText(getContext(), "Last Image Already Shown", Toast.LENGTH_SHORT).show();
                }
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position > 0) {
                    position--;
                    imageView.setImageURI(mArrayUri.get(position));
                } else {
                    Toast.makeText(getContext(), "Previous Image Already Shown", Toast.LENGTH_SHORT).show();
                }

            }
        });
        imageView = view.findViewById(R.id.image);

        // click here to select image
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // initialising intent
                Intent intent = new Intent();

                // setting type to select to be image
                intent.setType("image/*");

                // allowing multiple image to be selected
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_MULTIPLE);

            }
        });


        return view;
    }


    private boolean CheckAllFields() {
        if (ticketnumberchange.length() == 0) {
            ticketnumberchange.setError("Username field is required");
            return false;
        }
        if (description.length() == 0) {
            description.setError("Password is required");
            return false;
        } else if (description.length() < 8) {
            description.setError("Password must be minimum 8 characters");
            return false;
        }

        return true;
    }

    public void UIScreen(View view) {
        datepick_addticket = view.findViewById(R.id.datepick_addticket);


        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);

        datepick_addticket.setText(formattedDate);
    }


    private boolean validate_date() {
        String name = datepick_addticket.getText().toString();
        if (datepick_addticket.getText().toString().trim().isEmpty() || datepick_addticket.length() < 3) {
            datepick_addticket.setError("Please enter valid date");
            datepick_addticket.requestFocus();

            return false;
        } else {
        }
        return true;
    }


    private void updateLabel() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        datepick_addticket.setText(dateFormat.format(myCalendar.getTime()));
    }


    public String parseDateToddMMyyyy(String time) {
        String inputPattern = "yyyy-MM-dd";
        String outputPattern = "dd-MM-yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        Date date = null;
        String str = null;
        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // When an Image is picked
        if (requestCode == PICK_IMAGE_MULTIPLE && resultCode == RESULT_OK && null != data) {
            // Get the Image from data
            if (data.getClipData() != null) {
                ClipData mClipData = data.getClipData();
                int cout = data.getClipData().getItemCount();
                for (int i = 0; i < cout; i++) {
                    // adding imageuri in array
                    Uri imageurl = data.getClipData().getItemAt(i).getUri();
                    mArrayUri.add(imageurl);
                }
                // setting 1st selected image into image switcher
                imageView.setImageURI(mArrayUri.get(0));
                position = 0;
            } else {
                Uri imageurl = data.getData();
                mArrayUri.add(imageurl);
                imageView.setImageURI(mArrayUri.get(0));
                position = 0;
            }
        } else {
            // show this if no image is selected
            Toast.makeText(getContext(), "You haven't picked Image", Toast.LENGTH_LONG).show();
        }


    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
}