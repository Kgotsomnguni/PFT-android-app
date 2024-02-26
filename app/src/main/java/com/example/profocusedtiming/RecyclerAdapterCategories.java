package com.example.profocusedtiming;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class RecyclerAdapterCategories extends RecyclerView.Adapter<RecyclerAdapterCategories.MyViewHolder> {

    private ArrayList<Category> categories;
     ArrayList<String> categoriesId;
    private View.OnClickListener mOnItemClickListener;

    public RecyclerAdapterCategories(ArrayList<Category> categories, ArrayList<String> categoriesId){
        this.categories = categories;
        this.categoriesId = categoriesId;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView txtCategoryName, txtMinGoal,txtMaxGoal;
        private View view;

        public MyViewHolder(final View view){
            super(view);

            this.view = view;
            txtCategoryName  = view.findViewById(R.id.txtCategoryName);
            txtMinGoal = view.findViewById(R.id.txtMinGoal);
            txtMaxGoal = view.findViewById(R.id.txtMaxGoal);

            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);

        }

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_categories, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        String category = categories.get(position).getName();
        int categoryMin = categories.get(position).getMinGoal();
         int categoryMax = categories.get(position).getMaxGoal();

        holder.txtCategoryName.setText(category);
        holder.txtMinGoal.setText(categoryMin );
        holder.txtMaxGoal.setText(categoryMax);
        /*

         */
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }



}

