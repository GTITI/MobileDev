package com.example.gocztiti.petfriends;

import android.app.DownloadManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.gocztiti.petfriends.R.id.nameInput;
import static com.example.gocztiti.petfriends.R.id.descriptionInput;
public class PetType extends AppCompatActivity {

    public static ArrayList<PetTypes> Pets =new ArrayList<PetTypes>();
    private Button refresh;
    private Button save ;
    private EditText nameTxt;

    private EditText descriptionTxt;
    PetTypes a=new PetTypes("Cats","small, typically furry, carnivorous mammal");
    PetTypes b=new PetTypes("Dogs","domesticated canine, selectively bred over millennia");
    public static GridView show;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_type);

        nameTxt = (EditText) findViewById(nameInput);

        descriptionTxt = (EditText) findViewById(descriptionInput);
        Pets.add(a);
        Pets.add(b);
        show=(GridView) findViewById(R.id.listPetType);
        save = (Button) findViewById(R.id.addBtn);

        refresh=(Button) findViewById(R.id.refreshBtn);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Pets.isEmpty()){
                    Toast.makeText(getBaseContext(), "List is empty", Toast.LENGTH_LONG).show();
                }else{
                    ArrayAdapter<PetTypes> adapter = new ArrayAdapter<PetTypes>(PetType.this, android.R.layout.simple_list_item_1, Pets);
                    show.setAdapter(adapter);
                }
            }
        });



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


    }

