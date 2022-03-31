package com.example.ticketing_app_facetoface.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticketing_app_facetoface.R;

import com.example.ticketing_app_facetoface.Model.TicketList;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.CustomViewHolder> {
    private Context context;
    private TicketList[] listdata;
    int lastpostion = -1;
 
    private RecyclerView.ViewHolder viewHolder;
    private Object CustomViewHolder;
    private RecyclerView.ViewHolder ViewHolder;

    public TicketAdapter(Context context, TicketList[] circle_category_image) {
        this.context=context;
        this.listdata=circle_category_image;
}
    @NonNull
    @Override
    public TicketAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.recyclerviewhome, parent, false);
        CustomViewHolder CustomViewHolder = new CustomViewHolder(listItem);
        return CustomViewHolder;
    }
    @Override
    public void onBindViewHolder(final CustomViewHolder holder, final int position) {
        final TicketList myListData = listdata[position];
       // holder.textView.setText(listdata[position].getvCategoryName());
       /* if (holder.getAdapterPosition()>lastpostion){

           Animation animation = AnimationUtils.loadAnimation(context,R.anim.slider_view );
            ((CustomViewHolder) ViewHolder).itemView.startAnimation(animation);
            lastpostion = ViewHolder.getAdapterPosition();

        }*/

        holder.imageView.setImageResource(listdata[position].getImgId());
    }

    @Override
    public int getItemCount() {
        return listdata.length;
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView,textView3;
         CardView cardview_tickrt_add;
       // public RelativeLayout relativeLayout;

        public CustomViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.cr1);
            this.textView = (TextView) itemView.findViewById(R.id.txtview6 );
            this.textView3 = (TextView) itemView.findViewById(R.id.status2 );
            this.cardview_tickrt_add = (CardView) itemView.findViewById(R.id.cardview_tickrt_add );

            // relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relativeLayout);
        }

        public void bindlistdata(final TicketAdapter listdata) {


        }
    }
}



