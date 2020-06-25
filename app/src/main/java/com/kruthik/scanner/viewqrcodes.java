package com.kruthik.scanner;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

public class viewqrcodes extends AppCompatActivity {
ArrayList<items> items=new ArrayList<>();
ListView listView;
    Bitmap bitmap;
String kk;
    QRGEncoder qrgEncoder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewqrcodes);
        listView=findViewById(R.id.list);
        SharedPreferences sharedpreferences=getSharedPreferences("login",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        if (sharedpreferences.contains("name")) {
            kk=sharedpreferences.getString("name", "");
            Toast.makeText(this,kk,Toast.LENGTH_LONG).show();
        }
        new Async().execute();

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
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.showqr, null);
            LinearLayout l = v.findViewById(R.id.linear);
//            if(position%2==0){
//                l.setBackgroundColor(Color.parseColor("#e7dabd"));
//            }else {
//                l.setBackgroundColor(Color.parseColor("#d2b5e2"));
//            }

            final TextView tv = v.findViewById(R.id.name);



            TextView actors = v.findViewById(R.id.verified);
            tv.setText(items.get(position).getMOVIENAME());
            if(items.get(position).getVerified().equals("verified")){
                actors.setText("QR Code already used");
            }else{
                actors.setText("QR Code not used");
            }



            Button btn=v.findViewById(R.id.btn);



            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
generateqrcode(items.get(position).getCode());
                    qrdialog exampleDialog = new qrdialog();
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);

                    byte[] byteArray = stream.toByteArray();

                    Bundle b = new Bundle();
                    b.putByteArray("image",byteArray);
                    exampleDialog.setArguments(b);
                    exampleDialog.show(getSupportFragmentManager(), "qr dialog");

                }
            });
            return v;
        }
    }


    class Async extends AsyncTask<Void, Void, Void> {



        String records = "",error="";

        @Override

        protected Void doInBackground(Void... voids) {

            try

            {

                Class.forName("com.mysql.jdbc.Driver");

                Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.0.107:3306/testdb", "andro", "andro");

                PreparedStatement preparedStatement= connection.prepareStatement("select * from persons where Name=?");
                preparedStatement.setString(1,kk);

                ResultSet count = preparedStatement.executeQuery();

                while(count.next()) {

                    items.add(new items(count.getString(2),count.getString(3),count.getString(4),count.getString(5)));

                }



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
            Toast.makeText(viewqrcodes.this,error.toString(),Toast.LENGTH_LONG).show();
         custom cu = new custom();
            listView.setAdapter(cu);
           // Toast.makeText(viewqrcodes.this,items.get(0).getMOVIENAME(),Toast.LENGTH_LONG).show();
            //getSupportActionBar().setTitle("QR Code");

        }



    }
    public void generateqrcode(String code){
        WindowManager manager=(WindowManager)getSystemService(WINDOW_SERVICE);
        Display display=manager.getDefaultDisplay();
        Point point=new Point();
        display.getSize(point);
        int width=point.x;
        int height=point.y;
        int smallerdimesion=width<height ? width:height;
        smallerdimesion = smallerdimesion *3/4;
        qrgEncoder=new QRGEncoder(code,null, QRGContents.Type.TEXT,smallerdimesion);
        try {
            bitmap=qrgEncoder.encodeAsBitmap();



        }catch (Exception e){

        }
    }
}