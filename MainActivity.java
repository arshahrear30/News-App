package com.example.newsapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    HashMap<String,String>hashMap=new HashMap<>();
    ArrayList<HashMap<String,String>> arrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        listView =findViewById(R.id.listView);

        createTable(); //onCreate() মেথডে createTable() কল করুন MyAdapter তৈরি করার আগে।
        MyAdapter myAdapter=new MyAdapter();
        listView.setAdapter(myAdapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    //=======================
    private class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return arrayList.size();
        }
        @Override
        public Object getItem(int position) {
            return null;
        }
        @Override
        public long getItemId(int position) {
            return 0;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater= (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View myView = inflater.inflate(R.layout.item, parent, false);

            ImageView itemCover=myView.findViewById(R.id.itemCover);
            TextView itemCat=myView.findViewById(R.id.itemCat);
            TextView itemTitle=myView.findViewById(R.id.itemTitle);
            TextView itemDes=myView.findViewById(R.id.itemDes);
            LinearLayout layItem=myView.findViewById(R.id.layItem);

            HashMap<String,String> hashMap=arrayList.get(position);
            String cat=hashMap.get("cat");
            String image_url=hashMap.get("image_url");
            String title=hashMap.get("title");
            String des=hashMap.get("des");

            Glide.with(itemCover.getContext()).load(image_url)
                    //placeholder এর কাজ হচ্ছে যতক্ষণ পর্যন্ত না ওই ছবিটার লোড হবে ততক্ষণ পর্যন্ত কি দেখাবে তা set করবে
                    .placeholder(R.drawable.tfs)
                    .into(itemCover);


            itemCat.setText(cat);
            itemTitle.setText(title);
            itemDes.setText(des);


            // For Generate Random Colour
            Random rnd = new Random();
            int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            itemCat.setBackgroundColor(color);

            // আমরা চাই layItem ক্লিক করলে একটা কিছু ঘটুক সেজন্য এ setOnClickListener নিলাম
            layItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // যেহেতু আমরা NewsDetails.java class public static ধরেছি তাই এটাকে
                    // আমরা MainActivity.java অথবা যে কোন class থেকে access করতে পারব যেমনটা এখানে করেছি।
                    NewsDetails.TITLE=title;
                    NewsDetails.DESCRIPTION=des;

                    // একটা ImageView থেকে Bitmap বের করা ফর্মুলা
                    Bitmap bitmap=((BitmapDrawable) itemCover.getDrawable()).getBitmap();
                    NewsDetails.MY_BITMAP=bitmap;

                    // এখানে ঘটনাটা যা ঘটছে আমরা MainActivity.java তে একটা বাটন ক্রিয়েট করছিলাম সেটার
                    // ভিতর একটা ছবি ছিল সেই ছবিটা NewsDetails.java নিয়ে এসেছি এজন্য public static ধরেছি bitmap ইউজ করেছি ।

                    startActivity(new Intent(MainActivity.this, NewsDetails.class ));

                }
            });



            return myView;
        }
    }

//=======================

    private void createTable(){

        hashMap=new HashMap<>();
        hashMap.put("cat","Football");
        hashMap.put("image_url","https://www.tbsnews.net/sites/default/files/styles/big_3/public/images/2025/01/28/screenshot_2025-01-28_111430.png");
        hashMap.put("title","হামজার প্রশংসায় পঞ্চমুখ শিলংবাসী\n");
        hashMap.put("des","শিলংয়ের পথে প্রান্তরে বুধবারের ম্যাচ নিয়ে আলোচনার সময় দেখা হয় স্ট্যাট ব্যাংক অব ইন্ডিয়ায় কর্মরত এক ফুটবল ভক্তের সঙ্গে। নাম জানতে চাইলে তিনি বলেন, আমাকে আপনি ফুটবল ভক্ত হিসেবেই মনে রাখবেন।\n");
        arrayList.add(hashMap);

        hashMap=new HashMap<>();
        hashMap.put("cat","Shoes");
        hashMap.put("title","Where Style Meets Comfort.");
        hashMap.put("des","\nCrafted with precision for the modern lifestyle.\n" +
                "Luxury design made affordable for everyone.\n" +
                "Built to last, designed to impress.\n\n" +
                "Smaka – Step into confidence, every day.\n");
        arrayList.add(hashMap);





    }

    //==========================
}
