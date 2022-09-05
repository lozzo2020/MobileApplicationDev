package com.example.tutorial4and5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //need to get te data from StructureData, put it in te new fragment
        StructureData instanceStruData = StructureData.get();
        MapData mapData = MapData.get();

        //adding Map Fragment
        FragmentManager fm = getSupportFragmentManager();
        fragment_map fragMap = (fragment_map) fm.findFragmentById(R.id.Map);
        fragment_selector fragSelect = (fragment_selector) fm.findFragmentById(R.id.Selector) ;


        if(fragMap == null)
        {
            fragMap = new fragment_map(mapData, fragSelect);
            fm.beginTransaction().add(R.id.Map, fragMap).commit();
        }

        if(fragSelect == null)
        {
            fragSelect = new fragment_selector(instanceStruData);
            fm.beginTransaction().add(R.id.Selector, fragSelect).commit();
        }




    }
}