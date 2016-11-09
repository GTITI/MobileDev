package com.example.gocztiti.petfriends;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

public class PetType extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_type);
    populateListView();
    }

    private void populateListView() {
        String[] PetTypes ={"Cats","Dogs","Birds","Rodents",};

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,PetTypes);
        

        GridView list=(GridView) findViewById(R.id.listPetType);
        list.setAdapter(adapter);
    }
}
