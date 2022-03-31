package com.example.ticketing_app_facetoface;

import android.content.Context;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import com.example.ticketing_app_facetoface.Adapter.TicketAdapter;
import com.example.ticketing_app_facetoface.Model.TicketList;

public class TicketFragment extends Fragment {
    public static Context context;
    CardView cardview_tickrt_add;
    TicketAdapter circle_image_adapter ;
    List<TicketList> Items_1 ;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public TicketFragment() {

    }

    RecyclerView recyclerView_category_foods ;
    LinearLayoutManager HorizontalLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ticket, container, false);

        cardview_tickrt_add = view.findViewById(R.id.cardview_tickrt_add);
        /*cardview_tickrt_add.setOnTouchListener(new );*/



///////add button its navigate to add ticket page
        ImageView imageView = view.findViewById(R.id.homeimage);
        imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
              Fragment fragment = new AddTicketFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }

        });
        recyclerView_category_foods = (RecyclerView) view.findViewById(R.id.recycle);

        TicketList[] circle_category_image = new TicketList[]{
                new TicketList("F2FLogo",R.drawable.logo),
                new TicketList("F2FLogo",R.drawable.profile_image),
                new TicketList("F2FLogo",R.drawable.logo),
                new TicketList("F2FLogo",R.drawable.butterfly),
                new TicketList("F2FLogo",R.drawable.butterflynew),
                new TicketList("F2FLogo",R.drawable.logo),
                new TicketList("F2FLogo",R.drawable.butterflynew),
        };


        TicketAdapter circle_image_adapter = new TicketAdapter(getContext(), circle_category_image);
        recyclerView_category_foods.setHasFixedSize(true);
        recyclerView_category_foods.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        recyclerView_category_foods.setLayoutManager(new LinearLayoutManager(context));
        recyclerView_category_foods.setAdapter(circle_image_adapter);
        HorizontalLayout = new LinearLayoutManager(TicketFragment.context, LinearLayoutManager.VERTICAL, false);
        recyclerView_category_foods.setLayoutManager(HorizontalLayout);


        return view;
    }

        public static TicketFragment newInstance(String param1, String param2) {
        TicketFragment fragment = new TicketFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

   /* private void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.AddTicketFragment,fragment);
        transaction.commit();
    }*/
   /* private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.AddTicketFragment, fragment);
        // transaction.addToBackStack(null);
        transaction.commit();
    }*/

}