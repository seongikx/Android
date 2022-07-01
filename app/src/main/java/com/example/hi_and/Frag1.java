package com.example.hi_and;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.CpuUsageInfo;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//fragment는 onCreateView로 생성한다.
public class Frag1 extends Fragment {
    private View rootView;

    private ArrayList<MainData> arrayList;
    private ArrayList<Contact> arrayList2;
    private MainAdapter mainAdapter;
    private ContactAdapter contactAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    EditText et_name;
    EditText et_number;
    TextView phonenum;
    TextView name;

    private ArrayList<Contact> getContactList() {

        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        String[] projection = new String[] {
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID, // 연락처 ID -> 사진 정보 가져오는데 사용
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME }; // 연락처 이름.

        String[] selectionArgs = null;

        String sortOrder = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
                + " COLLATE LOCALIZED ASC";

        Cursor contactCursor = getActivity().getContentResolver().query(uri, projection, null, selectionArgs, sortOrder);

        ArrayList<Contact> contactlist = new ArrayList<Contact>();

        if (contactCursor.moveToFirst()) {
            do {
                String phonenumber = contactCursor.getString(1).replaceAll("-",
                        "");
                if (phonenumber.length() == 10) {
                    phonenumber = phonenumber.substring(0, 3) + "-"
                            + phonenumber.substring(3, 6) + "-"
                            + phonenumber.substring(6);
                } else if (phonenumber.length() > 8) {
                    phonenumber = phonenumber.substring(0, 3) + "-"
                            + phonenumber.substring(3, 7) + "-"
                            + phonenumber.substring(7);
                }


                Contact acontact = new Contact();
                acontact.setPhotoid(contactCursor.getLong(0));
                acontact.setPhonenum(phonenumber);
                acontact.setName(contactCursor.getString(2));

                contactlist.add(acontact);
            } while (contactCursor.moveToNext());
        }
        Log.d("contactlist",String.valueOf(contactlist.size()));
        return contactlist;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //xml파일과 연결 시도
        rootView = inflater.inflate(R.layout.frag1,container,false);

        // et_name = (EditText)rootView.findViewById(R.id.et_name);
        // et_number = (EditText)rootView.findViewById(R.id.et_number);

        phonenum = (TextView)rootView.findViewById(R.id.phonenum);
        name =(TextView)rootView.findViewById(R.id.name);
        //리싸이클러뷰 갖고오기
        recyclerView = (RecyclerView)rootView.findViewById(R.id.rv);

        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        //데이터넣을 array생성
        //   arrayList = new ArrayList<>();
        arrayList2= new ArrayList<>();
        //데이터 넣을 어뎁터 생성
        //    mainAdapter = new MainAdapter(arrayList);

        //리사이클러뷰에 어뎁터 넣기
        arrayList2 = getContactList();
        Log.d(null, "--------------------------------" + arrayList2.size());
        // contactAdapter = new ContactAdapter();
        contactAdapter = new ContactAdapter(arrayList2);
        recyclerView.setAdapter(contactAdapter);


        //  for(int i = 0; i < arrayList2.size(); i++){
        //      contactAdapter.itemAdd(arrayList2.get(i));
        //    }


        Button btn_add =(Button)rootView.findViewById(R.id.btn_add);
        Button btn_load = (Button)rootView.findViewById(R.id.btn_load);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = et_name.getText().toString();
                String number = et_number.getText().toString();
                MainData mainData = new MainData(R.drawable.ic_launcher_background,name,number);
                //  arrayList.add(mainData);
                //    mainAdapter.notifyDataSetChanged();
            }
        });
        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayList2 = getContactList();
                Log.d("?????",String.valueOf(arrayList2.size()));
                arrayList2 = getContactList();
                contactAdapter.notifyDataSetChanged();
            }
        });
        return rootView;
    }
}
