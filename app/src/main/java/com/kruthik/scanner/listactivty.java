package com.kruthik.scanner;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class listactivty extends AppCompatActivity {
    ListView list;
    String kk;
    ArrayList<MovieItems> items = new ArrayList<MovieItems>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listactivty);
        getSupportActionBar().setTitle("Movies");
        list = findViewById(R.id.list);
        items.add(new MovieItems("Baahubali 2: The Conclusion", "https://m.media-amazon.com/images/M/MV5BOGNlNmRkMjctNDgxMC00NzFhLWIzY2YtZDk3ZDE0NWZhZDBlXkEyXkFqcGdeQXVyODIwMDI1NjM@._V1_QL50_SY1000_CR0,0,562,1000_AL_.jpg", " Prabhas \n Rana Daggubati \n Anushka Shetty", "8.2"));
        items.add(new MovieItems("Baahubali: The Beginning", "https://m.media-amazon.com/images/M/MV5BYWVlMjVhZWYtNWViNC00ODFkLTk1MmItYjU1MDY5ZDdhMTU3XkEyXkFqcGdeQXVyODIwMDI1NjM@._V1_QL50_.jpg", " Prabhas \n Rana Daggubati \n Anushka Shetty", "8.1"));

        final custom cu = new custom();
        list.setAdapter(cu);


    }

    public class custom extends BaseAdapter {


        @Override
        public int getCount() {
            return items.size();
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
        public View getView(final int position, View convertView, ViewGroup parent) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items, null);
            LinearLayout l = v.findViewById(R.id.linear);
//            if(position%2==0){
//                l.setBackgroundColor(Color.parseColor("#e7dabd"));
//            }else {
//                l.setBackgroundColor(Color.parseColor("#d2b5e2"));
//            }

            final TextView tv = v.findViewById(R.id.name);
            ImageView img = v.findViewById(R.id.youtubeimg);

            TextView rating = v.findViewById(R.id.rating);
            TextView actors = v.findViewById(R.id.actors);
            tv.setText(items.get(position).getName());
            String url = items.get(position).getImgurl();
            rating.setText(items.get(position).getRating());
            actors.setText(items.get(position).getActors());
            Button btn=v.findViewById(R.id.btn);

            Glide.with(v.getContext()).load(url).into(img);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
Intent i = new Intent(listactivty.this,MainActivity.class);
i.putExtra("name",items.get(position).getName());
i.putExtra("img",items.get(position).getImgurl());
i.putExtra("rating",items.get(position).getRating());
i.putExtra("actors",items.get(position).getActors());
startActivity(i);

                }
            });
            return v;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.optionsmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.item1:
                Intent i = new Intent(listactivty.this,Login.class);
                SharedPreferences sharedpreferences=getSharedPreferences("login",
                        Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("name", null);
                editor.commit();


                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                return true;
            case R.id.item2:
                Intent i1 = new Intent(listactivty.this,viewqrcodes.class);
                startActivity(i1);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}