package com.example.hi_and;

//어뎁터 클래스에서 뷰홀더와 어뎁터를 구현한다.
//어뎁터는 필요에 따라 뷰 홀더객체를 만들고, 이러한 뷰에 데이터를 설정하기도 한다.
//뷰를 요청 한 후에, 어댑터에서 메서드를 호출하여, 뷰를 뷰의 데이터에바인딩한다.

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.CustomViewHolder> {
    private ArrayList<ImageData> arrayList;

    public ImageAdapter(ArrayList<ImageData> arrayList) {
        this.arrayList = arrayList;
    }


    @NonNull
    @Override
    public ImageAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_list,parent,false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.CustomViewHolder holder, int position) {
        holder.img.setImageResource(arrayList.get(position).getImg());
        holder.img_name.setText(arrayList.get(position).getImg_name());

    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder{

        protected ImageView img;
        protected TextView img_name;
        //  protected LinearLayout imgContainer;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.img = (ImageView)itemView.findViewById(R.id.img);
            this.img_name=(TextView) itemView.findViewById(R.id.img_name);
        }
    }
}
