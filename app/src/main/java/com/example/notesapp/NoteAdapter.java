package com.example.notesapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder>{
    private Context context;
    private List<NoteModel> listNote;

    public NoteAdapter(Context context) {
        this.context = context;
        listNote = new ArrayList<>();
    }

    public void setListNote(List<NoteModel> listNote) {
        this.listNote = listNote;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_list, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        NoteModel noteModel = listNote.get(i);

        final int id = noteModel.getNoteID();
        String title = noteModel.getNoteTitle();
        String desc = noteModel.getNoteDesc();
        String date = noteModel.getNoteDate();

        viewHolder.tvTitle.setText(title);
        viewHolder.tvDesc.setText(desc);
        viewHolder.tvDate.setText(date);

        viewHolder.rlItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddNoteActivity.class);
                intent.putExtra("edit", true);
                intent.putExtra("noteID", id);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listNote.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout rlItem;
        private TextView tvTitle, tvDesc, tvDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rlItem = itemView.findViewById(R.id.rl_item);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDesc = itemView.findViewById(R.id.tv_desc);
            tvDate = itemView.findViewById(R.id.tv_date);
        }
    }
}
