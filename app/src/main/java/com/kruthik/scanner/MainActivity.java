package com.kruthik.scanner;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.zxing.qrcode.encoder.QRCode;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.sql.*;

public class MainActivity extends AppCompatActivity {
ImageView image;
String input="";
Bitmap bitmap;
String name;
Button bookticket;
LinearLayout l1;
LinearLayout l2;
String kk;
QRGEncoder qrgEncoder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = findViewById(R.id.name);
        ImageView img = findViewById(R.id.img);
        getSupportActionBar().setTitle("Book ur Ticket");
l1=findViewById(R.id.linear);
l2=findViewById(R.id.linear1);
        TextView rating = findViewById(R.id.rating);
        TextView actors = findViewById(R.id.actors);
        image = findViewById(R.id.qrcode);

        bookticket = findViewById(R.id.bookticket);
        SharedPreferences sharedpreferences=getSharedPreferences("login",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        if (sharedpreferences.contains("name")) {
            kk=sharedpreferences.getString("name", "");
            Toast.makeText(this,kk,Toast.LENGTH_LONG).show();
        }
        if (getIntent() != null) {
            name = getIntent().getStringExtra("name");
            String url = getIntent().getStringExtra("img");
            rating.setText(getIntent().getStringExtra("rating"));
            actors.setText(getIntent().getStringExtra("actors"));
            Glide.with(this).load(url).into(img);

        }
        tv.setText(name);

        bookticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DateFormat df = new SimpleDateFormat("d MMM yyyy,HH:mm:ss");
                String date = df.format(Calendar.getInstance().getTime());
                String time=date.toString();

                input=time+kk;


                if(input.length()>0){
                    WindowManager manager=(WindowManager)getSystemService(WINDOW_SERVICE);
                    Display display=manager.getDefaultDisplay();
                    Point point=new Point();
                    display.getSize(point);
                    int width=point.x;
                    int height=point.y;
                    int smallerdimesion=width<height ? width:height;
                    smallerdimesion = smallerdimesion *3/4;
                    qrgEncoder=new QRGEncoder(input,null, QRGContents.Type.TEXT,smallerdimesion);
                    try {
                        bitmap=qrgEncoder.encodeAsBitmap();
                        image.setImageBitmap(bitmap);
                        new Async().execute();

                        l1.setVisibility(View.VISIBLE);
                        l2.setVisibility(View.GONE);
                        bookticket.setVisibility(View.GONE);
                    }catch (Exception e){

                    }
                }else{
                    Toast.makeText(MainActivity.this,"failed",Toast.LENGTH_LONG).show();
                }

            }

        });


    }

    class Async extends AsyncTask<Void, Void, Void> {



        String records = "",error="";

        @Override

        protected Void doInBackground(Void... voids) {

            try

            {

                Class.forName("com.mysql.jdbc.Driver");

                Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.0.107:3306/testdb", "andro", "andro");

                PreparedStatement  preparedStatement= connection.prepareStatement("INSERT INTO persons(MOVIENAME, Name, code,verified) VALUES (?, ?, ?,?)");
                preparedStatement.setString(1,name);
                preparedStatement.setString(2,kk);
                preparedStatement.setString(3,input);
                preparedStatement.setString(4,"not verified");
                int count = preparedStatement.executeUpdate();



            }

            catch(Exception e)

            {

                error = e.toString();

            }


            return null;

        }



        @Override

        protected void onPostExecute(Void aVoid) {

//            text.setText(records);
//
//            if(error != "")
//
//                errorText.setText(error);

            super.onPostExecute(aVoid);
            Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_LONG).show();
            getSupportActionBar().setTitle("QR Code");

        }





    }

}