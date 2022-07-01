package com.example.hi_and;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.CustomViewHolder> {

    private ArrayList<Contact> arrayList = new ArrayList<>();

    //생성자 선언
    public ContactAdapter(ArrayList<Contact> arrayList) {
        this.arrayList = arrayList;
    }
    public ContactAdapter() {

    }
    @NonNull
    @Override
    //처음으로 생성될 때
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list,parent,false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    public void itemAdd(Contact c){
        arrayList.add(c);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Log.d("holder", "------ holder = " + (holder.photoid));
        Log.d("holder", "------ holder = " + (arrayList.get(position).getName()));

        holder.photoid = (ImageView)holder.itemView.findViewById(R.id.photoid);
        holder.phonenum = (TextView)holder.itemView.findViewById(R.id.phonenum);
        holder.name = (TextView)holder.itemView.findViewById(R.id.name);


        holder.photoid.setImageResource(R.drawable.img2);
        holder.phonenum.setText(arrayList.get(position).getPhonenum());
        holder.name.setText(arrayList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView photoid;
        protected TextView phonenum;
        protected TextView name;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            // Log.d("찾아야돼",itemView.findViewById(R.id.photoid).toString());
            this.photoid = (ImageView)itemView.findViewById(R.id.photoid);
            this.phonenum = (TextView)itemView.findViewById(R.id.phonenum);
            Log.d("찾아야돼",""+((ImageView)itemView.findViewById(R.id.photoid)==null));
            this.name = (TextView)itemView.findViewById(R.id.name);
        }
    }
}
