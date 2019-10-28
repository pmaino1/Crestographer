package com.example.tableproject;
import java.util.List;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {
    private List<Pair<String,String>> values;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView firstLine;
        public TextView secondLine;
        public View layout;             //stores the WHOLE view (from text_view_holdee.xml)

        public ViewHolder(View v) {
            super(v);
            layout = v;
            firstLine = v.findViewById(R.id.firstLine);
            secondLine = v.findViewById(R.id.secondLine);
        }
    }

    public void add(int position, Pair<String,String> item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RVAdapter(List<Pair<String,String>> md) {
        values = md;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.text_view_holdee, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Pair<String,String> p = values.get(position);
        final String name = p.first;
        final String data = p.second;
        holder.firstLine.setText(name);
        /* CAN BE USED TO ADD TOUCH
        holder.txtHeader.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }); */

        holder.secondLine.setText(DataActivity.stat + ": " + data);
    }

    // Return the size of your dataset
    @Override
    public int getItemCount() {
        return values.size();
    }

}