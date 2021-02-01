package com.example.pastore.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pastore.MainActivity3;
import com.example.pastore.Model.Model;
import com.example.pastore.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyRecyclerView extends RecyclerView.Adapter<MyRecyclerView.MyViewHolder> {

    ArrayList<Model> list = new ArrayList<>();
    Context context;

    public MyRecyclerView( Context context, ArrayList<Model> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(context.getResources().getLayout(R.layout.recycler_view_row), parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Model model = list.get(position);
        holder.title.setText(model.getTitle());
        holder.description.setText(model.getDescription());

        switch (model.getImg()){
            case "google": holder.image.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.google));
                        break;
            case "facebook": holder.image.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.fb));
                    break;
            case "linkedin": holder.image.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.linkedin));
                    break;
            case "whatsapp": holder.image.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.whatsapp));
                    break;
            case "instagram": holder.image.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.instagram));
                break;
            default:  holder.image.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.fb_msgr));
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView title, description;
        ImageView image;
        ConstraintLayout parent;

        MyViewHolder(View itemView){
            super(itemView);

            itemView.setOnClickListener(this);

            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.desc);
            image = itemView.findViewById(R.id.image);
            parent = itemView.findViewById(R.id.parent);

            parent.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int position = this.getAdapterPosition();
            Model model = list.get(position);
//            Toast.makeText(context, "  "
//                    + model.getTitle() +"\n"
//                    + model.getDescription() +"\n"
//                    + model.getUsername() +"\n"
//                    + model.getPassword() +"\n"
//                    + model.getImg() , Toast.LENGTH_LONG).show();

            Intent intent = new Intent(v.getContext(), MainActivity3.class);
            intent.putExtra("position", position+1);
            v.getContext().startActivity(intent);

        }



    }
}
