package com.example.censusap_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewholder> {
    private Context context;
    private ArrayList person_id, name,age,gender;

    CustomAdapter(Context context, ArrayList person_id, ArrayList name, ArrayList age){
        this.context = context;
        this.person_id =person_id;
        this.name=name;
        this.age=age;

    }


    @NonNull
    @Override
    public CustomAdapter.MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewholder holder, int position) {

        holder.person_id_txt.setText(String.valueOf(person_id.get(position)));
        holder.name_txt.setText(String.valueOf(name.get(position)));
        holder.age_txt.setText(String.valueOf(age.get(position)));
    }

    @Override
    public int getItemCount() {
        return person_id.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {
    TextView person_id_txt,name_txt,age_txt;
        public MyViewholder(@NonNull View itemView) {
            super(itemView);

            person_id_txt = itemView.findViewById(R.id.textView);
            name_txt = itemView.findViewById(R.id.name);
            age_txt= itemView.findViewById(R.id.age);

        }
    }
}
