package com.example.notesapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvList;
    private FloatingActionButton fabAdd;

    private List<NoteModel> listNote = new ArrayList<>();
    private NoteAdapter adapter;
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new Database(this);

        initView();
        getNotes();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getNotes();
    }

    private void initView() {
        rvList = findViewById(R.id.rv_list);
        fabAdd = findViewById(R.id.fab_add);

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
                startActivity(intent);
            }
        });

        adapter = new NoteAdapter(this);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvList.setAdapter(adapter);
    }

    private void getNotes() {
        listNote.clear();

        for (NoteModel noteModel : db.getNotes()) {
            int id = noteModel.getNoteID();
            String title = noteModel.getNoteTitle();
            String desc = noteModel.getNoteDesc();
            String date = noteModel.getNoteDate();

            listNote.add(new NoteModel(id, title, desc, date));
        }

        adapter.setListNote(listNote);
    }
}