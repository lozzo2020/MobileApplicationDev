package com.example.tutorial4and5;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Map;

public class fragment_map extends Fragment {

    MapData mapData;
    fragment_selector fragmentSelector;
    MapHolderView mapHolderView;


    public fragment_map(MapData mapData, fragment_selector fragmentSelector) {
        this.mapData = mapData;
        this.fragmentSelector = fragmentSelector;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_map, container, false);
        //setting up the RecyclerView
        RecyclerView rv = view.findViewById(R.id.mapRecyclerView);
        rv.setLayoutManager(new LinearLayoutManager(
                getActivity(), LinearLayoutManager.HORIZONTAL, false
        ));
        //having the data ready -- will this be the selector elecments??
        MyAdapter adapter = new MyAdapter(mapData);
        rv.setAdapter(adapter);


        // Inflate the layout for this fragment
        return view;
    }



    //BELOW HERE IS THE CODE FOR TE ADAPTOR - SLIDE 21
    private class MyAdapter extends RecyclerView.Adapter<MapHolderView>
    {
        MapData data;

        public MyAdapter(MapData data){
            this.data = data;
        }

        @Override
        public MapHolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.grid_cell, parent,false);
            //MyDataVHolder myDataVHolder = new MyDataVHolder(view, parent);
            return new MapHolderView(layoutInflater,parent);
        }

        @Override
        //used when Recyler view needes to rewrite a particular list row
        public void onBindViewHolder(@NonNull MapHolderView viewHolder, int position) {

            int row = position % mapData.HEIGHT;
            int column = position / mapData.HEIGHT;

            viewHolder.bindingView(data.get(row, column));

        }

        @Override
        public int getItemCount() {
            return mapData.WIDTH * mapData.HEIGHT;
        }

    }
    //this is viewholder
    private class MapHolderView extends RecyclerView.ViewHolder {

        MapElement mapElement;
        ImageView bottomLeft;
        ImageView bottomRight;
        ImageView topLeft;
        ImageView topRight;
        ImageView overallImage;
        Structure structure;



        public MapHolderView(@NonNull LayoutInflater layoutInflater, ViewGroup parent) {
            super(layoutInflater.inflate(R.layout.grid_cell, parent,false));

            int Size = parent.getMeasuredHeight() / mapData.HEIGHT;

            ViewGroup.LayoutParams lp = itemView.getLayoutParams();

            lp.width = Size;
            lp.height = Size;

            bottomLeft = itemView.findViewById(R.id.terrainImage3);
            bottomRight = itemView.findViewById(R.id.terrainImage4);
            topLeft = itemView.findViewById(R.id.terrainImage1);
            topRight = itemView.findViewById(R.id.terrainImage2);
            overallImage = itemView.findViewById(R.id.entireArea);





        }
        public void bindingView(MapElement mapEl)
        {
            mapElement = mapEl;
            topLeft.setImageResource(mapEl.getNorthWest());
            topRight.setImageResource(mapEl.getNorthEast());
            bottomLeft.setImageResource(mapEl.getSouthWest());
            bottomRight.setImageResource(mapEl.getSouthEast());

            if(mapEl.getStructure() == null)
            {
                overallImage.setVisibility(View.VISIBLE);
            }
            else
            {
                overallImage.setVisibility(View.VISIBLE);
                //overallImage.setImageResource(fragment_selector.getStructure().getDrawableId());
            }
        }

    }


}