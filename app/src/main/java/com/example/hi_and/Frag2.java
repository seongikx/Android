package com.example.hi_and;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//fragment는 onCreateView로 생성한다.
public class Frag2 extends Fragment {
    private View rootView;

    private ArrayList<ImageData> arrayList;
    private ImageAdapter imageAdapter;
    private RecyclerView recyclerView;
    //  private LinearLayoutManager linearLayoutManager;

    private TextView img_name;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //xml파일과 연결 시도
        rootView = inflater.inflate(R.layout.frag2, container, false);

        //리싸이클러뷰 갖고오기
        recyclerView = (RecyclerView) rootView.findViewById(R.id.rv2);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        //데이터넣을 array생성
        arrayList = new ArrayList<>();
        arrayList.add(new ImageData(R.drawable.im3,"string"));
        arrayList.add(new ImageData(R.drawable.img,"string"));
        arrayList.add(new ImageData(R.drawable.img2,"string"));
        arrayList.add(new ImageData(R.drawable.ic_launcher_background,"string"));
        arrayList.add(new ImageData(R.drawable.ic_launcher_background,"string"));
        arrayList.add(new ImageData(R.drawable.ic_launcher_background,"string"));


        //데이터 넣을 어뎁터 생성
        imageAdapter = new ImageAdapter(arrayList);
        recyclerView.setAdapter(imageAdapter);
        imageAdapter.notifyDataSetChanged();
        //리사이클러뷰에 어뎁터 넣기
        return rootView;
    }
}
