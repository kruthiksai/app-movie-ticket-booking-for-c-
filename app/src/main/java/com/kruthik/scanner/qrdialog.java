package com.kruthik.scanner;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class qrdialog extends AppCompatDialogFragment {
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.qrdialog, null);
        ImageView imageView=view.findViewById(R.id.qr);

        TextView title = new TextView(getActivity());
// You Can Customise your Title here
        title.setText("QR Code");
        title.setGravity(Gravity.CENTER);
        title.setTextSize(20);

        builder.setView(view)
                .setCustomTitle(title);

    byte[] byteArray = getArguments().getByteArray("image");
    Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    imageView.setImageBitmap(bmp);


        return builder.create();
    }


    }

