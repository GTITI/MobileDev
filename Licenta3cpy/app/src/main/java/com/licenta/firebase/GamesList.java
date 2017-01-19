package com.licenta.firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Iterator;

public class GamesList extends AppCompatActivity {
    public static LinearLayout nivele;
    private ImageButton G11,G12,G13,G21,G22,G23,G31,G32,G33,G41,G42,G43,G51,G52,G53;
    private int g11,g12,g13,g21,g22,g23,g31,g32,g33,g41,g42,g43,g51,g52,g53;
    private ArrayList<Integer> Scores= new ArrayList<Integer>();
    private ArrayList<ImageButton> Buttons= new ArrayList<ImageButton>();
    public ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_list);


        /*adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nivele);
        nivele =(LinearLayout) findViewById(R.id.activity_games_list);
        nivele
        nivele.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Punctaj a = (Punctaj) parent.getItemAtPosition(position);

                int[] b = {(a.getPuncte())};

                Intent intent = new Intent(GamesList.this, EditActivity.class);
                String message = "abc";
                //intent.putExtra(EXTRA_MESSAGE, message);
                intent.putExtra("pet", b);
                intent.putExtra("pos", position);
                startActivity(intent);
            }
        });
*/


        G11=(ImageButton) findViewById(R.id.imageButton11);
        G11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PetTypes.class);
                startActivity(intent);
            }

        });




        G12=(ImageButton) findViewById(R.id.imageButton12);
        G13=(ImageButton) findViewById(R.id.imageButton13);
        G21=(ImageButton) findViewById(R.id.imageButton21);
        G22=(ImageButton) findViewById(R.id.imageButton22);
        G23=(ImageButton) findViewById(R.id.imageButton23);
        G31=(ImageButton) findViewById(R.id.imageButton31);
        G32=(ImageButton) findViewById(R.id.imageButton32);
        G33=(ImageButton) findViewById(R.id.imageButton33);
        G41=(ImageButton) findViewById(R.id.imageButton41);
        G42=(ImageButton) findViewById(R.id.imageButton42);
        G43=(ImageButton) findViewById(R.id.imageButton43);
        G51=(ImageButton) findViewById(R.id.imageButton51);
        G52=(ImageButton) findViewById(R.id.imageButton52);
        G53=(ImageButton) findViewById(R.id.imageButton53);
        Buttons.add(G11);
        Buttons.add(G12);
        Buttons.add(G13);
        Buttons.add(G21);
        Buttons.add(G22);
        Buttons.add(G23);
        Buttons.add(G31);
        Buttons.add(G32);
        Buttons.add(G33);
        Buttons.add(G41);
        Buttons.add(G42);
        Buttons.add(G43);
        Buttons.add(G51);
        Buttons.add(G52);
        Buttons.add(G53);
        /*for (ImageButton btn: Buttons
             ) {btn.setEnabled(false);

        }*/

        //read the score in gxx
        /*g11=0;
        g21=0;
        g31=0;*/
        Integer a=0;
        a--;
        //add them to list foe easy verification
        Scores.add(g11);//0
        Scores.add(g12);
        Scores.add(g13);
        Scores.add(g21);//3
        Scores.add(g22);
        Scores.add(g23);
        Scores.add(g31);//6
        Scores.add(g32);
        Scores.add(g33);
        Scores.add(g41);
        Scores.add(g42);
        Scores.add(g43);
        Scores.add(g51);
        Scores.add(g52);
        Scores.add(g53);
        for (int pos=0;pos<Scores.size();pos++)
        {
            Scores.set(pos,a);
        }
        Scores.set(0,0);
        Scores.set(3,0);
        Scores.set(6,0);
        for (int pos=0;pos<Scores.size();pos++)
        {
            //int pos=Scores.indexOf(score);
            ImageButton btn=Buttons.get(pos);
            if(Scores.get(pos)>=0)
            {
                btn.setEnabled(true);
            }
            else
            {
                btn.setEnabled(false);
            }

        }


    }
}
