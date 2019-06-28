package com.example.todoapp.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.todoapp.DataBase.Models.Todo;
import com.example.todoapp.R;

import java.util.List;

public class TodoRecyclerAdapter extends RecyclerView.Adapter<TodoRecyclerAdapter.ViewHolder> {



    onItemCLickListener onItemViewClickListener;

    public void setOnItemViewClickListener(onItemCLickListener onItemViewClickListener) {
        this.onItemViewClickListener = onItemViewClickListener;
    }

    List<Todo> list;

    public TodoRecyclerAdapter(List<Todo> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_item_todo, viewGroup, false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        final Todo todo = list.get(i);
        viewHolder.title.setText(todo.getName());
        viewHolder.time.setText(todo.getTime());
        viewHolder.date.setText(todo.getDate());

        if (onItemViewClickListener!=null){

            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemViewClickListener.onItemClick(i,todo);
                }
            });
        }


    }

    @Override
    public int getItemCount() {

        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void changeLIst ( List<Todo> newList){

       this.list= newList;
       notifyDataSetChanged();

    }


    public Todo getTodo( int position){

        Todo todo = list.get(position);
        return todo;
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView date;
        TextView time;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);

        }
    }



    public interface onItemCLickListener{

        public void onItemClick (int pos, Todo todo);


    }


}
