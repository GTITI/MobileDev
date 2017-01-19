package com.example.gocztiti.petfriends;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.example.gocztiti.petfriends.R.id.textView6;
import static com.example.gocztiti.petfriends.R.id.textView7;
import static com.example.gocztiti.petfriends.R.id.textView8;

/**
 * Created by Gocz Titi on 11.11.2016.
 */
public class Pop extends Activity {
    private Button send;
    private EditText subject;
    private EditText from;
    private EditText text;
    @Override
    protected void onCreate(Bundle savedInstancesState) {
        super.onCreate(savedInstancesState);
        setContentView(R.layout.popupmail);

        subject=(EditText) findViewById(textView7);
        from=(EditText) findViewById(textView6);
        text=(EditText) findViewById(textView8);


        DisplayMetrics dm= new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        send=(Button) findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null, chooser = null;
                String getsubject=subject.getText().toString();
                String getfrom=from.getText().toString();
                String gettext=text.getText().toString();

                intent = new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse("mailto:"));
                String[] to = {"titigocz@gmail.com.com"};
                intent.putExtra(Intent.EXTRA_EMAIL, to);
                intent.putExtra(Intent.EXTRA_SUBJECT,getsubject);
                intent.putExtra(Intent.EXTRA_TEXT,gettext);
                intent.setType("message/rfc822");
                chooser = Intent.createChooser(intent, "Send email");
                startActivity(chooser);
            }
        });






        int width=dm.widthPixels;
        int height=dm.heightPixels;
        getWindow().setLayout((int)(width*.5),(int)(height*.5));
    }
}
