package com.example.tutorial4and5;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class fragment_selector extends Fragment {

    StructureData structureData;
    Structure structure;

    public fragment_selector() {

        // Required empty public constructor

    }
    public fragment_selector(StructureData structureData)
    {
        this.structureData = structureData;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //will return a view
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_selector, container, false);
        //setting up the RecyclerView
        RecyclerView rv = view.findViewById(R.id.selectorRecyclerView);
        //from setting up a grid
        rv.setLayoutManager(new LinearLayoutManager(
                getActivity(), LinearLayoutManager.HORIZONTAL, false
        ));
        //having the data ready -- will this be the selector elecments??
        MyAdapter adapter = new MyAdapter(structureData);
        rv.setAdapter(adapter);

        // Inflate the layout for this fragment
        return view;
    }
    public Structure getStructure()
    {
        return structure;
    }




    //BELOW HERE IS THE CODE FOR TE ADAPTOR - SLIDE 21
    private class MyAdapter extends RecyclerView.Adapter<MyDataVHolder>
    {
        StructureData data;
        public MyAdapter(StructureData data){
            this.data = data;
        }

        @Override
        public MyDataVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_selection, parent,false);
            //MyDataVHolder myDataVHolder = new MyDataVHolder(view, parent);
            return new MyDataVHolder(view,parent);
        }

        @Override
        //used when Recyler view needes to rewrite a particular list row
        public void onBindViewHolder(@NonNull MyDataVHolder viewHolder, int position) {

            viewHolder.bindingView(data.get(position));

        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }

    //this is viewholder
    private class MyDataVHolder extends RecyclerView.ViewHolder {

        public ImageView structureImage;
        public TextView structureCaption;


        public MyDataVHolder(@NonNull View layoutInflater, ViewGroup parent) {
            super(layoutInflater);



        }
        public void bindingView(Structure structure)
        {
            structureImage = itemView.findViewById(R.id.structureimage);
            structureCaption = itemView.findViewById(R.id.structurecaption);

            structureCaption.  setText(structure.getDrawableId());
            structureImage.setImageResource(structure.getDrawableId());



            structureImage.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view) {
                    //
                }
            });
        }

    }



}