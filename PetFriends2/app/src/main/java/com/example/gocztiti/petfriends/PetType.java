package com.example.gocztiti.petfriends;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import static com.example.gocztiti.petfriends.R.id.nameInput;
import static com.example.gocztiti.petfriends.R.id.descriptionInput;
public class PetType extends AppCompatActivity {

    private static final String EXTRA_MESSAGE ="" ;
    public static ArrayList<PetTypes> Pets =new ArrayList<PetTypes>();
    public ArrayAdapter adapter;
    private Button refresh;
    private Button save ;
    private EditText nameTxt;

    private EditText descriptionTxt;
    /*PetTypes a=new PetTypes("Cats","small, typically furry, carnivorous mammal");
    PetTypes b=new PetTypes("Dogs","domesticated canine, selectively bred over millennia");*/
    public static GridView show;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_type);
        try {
            readFile(PetType.this);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        nameTxt = (EditText) findViewById(nameInput);

        descriptionTxt = (EditText) findViewById(descriptionInput);


        adapter = new ArrayAdapter<>(PetType.this, android.R.layout.simple_list_item_1, Pets);
        //Pets.add(a);
        //Pets.add(b);
        show = (GridView) findViewById(R.id.listPetType);
        save = (Button) findViewById(R.id.addBtn);

        refresh = (Button) findViewById(R.id.refreshBtn);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Pets.isEmpty()) {
                    Toast.makeText(getBaseContext(), "List is empty", Toast.LENGTH_LONG).show();
                } else {

                    show.setAdapter(adapter);
                }
            }
        });
        //show.setAdapter(adapter);
        updateFragment();
        show.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                PetTypes a = (PetTypes) parent.getItemAtPosition(position);

                String[] b = {a.getName(),a.getMinidesc()};

                Intent intent = new Intent(PetType.this, EditActivity.class);
                String message = "abc";
                //intent.putExtra(EXTRA_MESSAGE, message);
                intent.putExtra("pet", b);
                intent.putExtra("pos", position);
                startActivity(intent);
            }
        });

        //show.setAdapter(adapter);



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getName = nameTxt.getText().toString();

                String getDescription = descriptionTxt.getText().toString();

                PetTypes r = new PetTypes(getName, getDescription);
                boolean exists=Pets.contains(r);
                if (exists) {
                    Toast.makeText(getBaseContext(), "Item already in the list", Toast.LENGTH_LONG).show();
                } else if (getName == null || getName.trim().equals("") ||
                        getDescription == null || getDescription.equals("")) {
                    Toast.makeText(getBaseContext(), "Some input is empty", Toast.LENGTH_LONG).show();
                } else {

                    Pets.add(r);
                    ArrayAdapter<PetTypes> adapter = new ArrayAdapter<PetTypes>(PetType.this, android.R.layout.simple_list_item_1, Pets);
                    show.setAdapter(adapter);
                    ((EditText) findViewById(R.id.nameInput)).setText("");

                    ((EditText) findViewById(R.id.descriptionInput)).setText("");

                }
            }
        });


    }





    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
        try {
            createFile( PetType.this);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    public void updateFragment(){
        adapter =new ArrayAdapter<PetTypes>(this.getBaseContext(),android.R.layout.simple_expandable_list_item_1, Pets);

        show.setAdapter(adapter);
    }
    /*public void updateFragment1() {
        // trying to update when came back here

        arrayAdapterStations = new ArrayAdapter<Station>(this.getContext(),
                android.R.layout.simple_list_item_1, MainActivity.arrayOfStation);

        stations.setAdapter(arrayAdapterStations);

    }*/



    public static void createFile( Context ctx) throws IOException, JSONException {
        JSONArray data = new JSONArray();
        JSONObject ap;
        //Pets =new ArrayList<PetTypes>();
        for (int i = 0; i < Pets.size(); i++) {
            ap = new JSONObject();
            ap.put("Name", Pets.get(i).getName());
            ap.put("Desc", Pets.get(i).getMinidesc());
            //ap.put("roomNr", arrayList.get(i).getNrCam());
            data.put(ap);
        }

        String text = data.toString();

        FileOutputStream fos = ctx.openFileOutput("petsFile", MODE_PRIVATE);
        fos.write(text.getBytes());
        fos.close();
    }


    public static void readFile( Context ctx) throws IOException, JSONException {

        FileInputStream fis = ctx.openFileInput("petsFile");
        BufferedInputStream bis = new BufferedInputStream(fis);
        StringBuffer b = new StringBuffer();
        while (bis.available() != 0) {
            char c = (char) bis.read();
            b.append(c);
        }
        bis.close();
        fis.close();
        JSONArray data = new JSONArray(b.toString());

        for (int i = 0; i < data.length(); i++) {
            String name = data.getJSONObject(i).getString("Name");
            String desc = data.getJSONObject(i).getString("Desc");
            //String roomNr = data.getJSONObject(i).getString("roomNr");
            PetTypes pet = new PetTypes(name, desc);
            //PetTypes b=new PetTypes("Dogs","domesticated canine, selectively bred over millennia");
            Pets.add(pet);
        }



    }







    }

