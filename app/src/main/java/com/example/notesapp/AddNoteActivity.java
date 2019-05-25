package com.example.notesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddNoteActivity extends AppCompatActivity {
    private EditText etTitle, etNote;

    private Database db;

    private Intent dataIntent;
    private boolean isEdit = false;
    private int noteID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        db = new Database(this);

        dataIntent = getIntent();
        isEdit = dataIntent.getBooleanExtra("edit", false);

        initView();
    }

    private void initView() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tambah Note");

        etTitle = findViewById(R.id.et_title);
        etNote = findViewById(R.id.et_note);

        if (isEdit) {
            int id = dataIntent.getIntExtra("noteID", 0);
            if (id != 0) {
                NoteModel noteModel = db.getNote(id);
                noteID = id;
                etTitle.setText(noteModel.getNoteTitle());
                etNote.setText(noteModel.getNoteDesc());
            }
        }
    }

    private boolean isInputValid() {
        if (TextUtils.isEmpty(etTitle.getText()) || TextUtils.isEmpty(etNote.getText())) {
            if (TextUtils.isEmpty(etTitle.getText())) {
                etTitle.setError("Judul tidak boleh kosong!");
            }

            if (TextUtils.isEmpty(etNote.getText())) {
                etNote.setError("Note tidak boleh kosong!");
            }

            return false;
        }

        return true;
    }

    private void saveNote(String title, String note) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        String date = sdf.format(calendar.getTime());

        NoteModel noteModel = new NoteModel(title, note, date);
        int success = db.addNote(noteModel);

        String message = "Note gagal disimpan";

        if (success != 0) {
            message = "Note berhasil disimpan";
            finish();
        }

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void updateNote(int noteID, String title, String note) {
        NoteModel noteModel = new NoteModel(noteID, title, note);
        int success = db.updateNote(noteModel);

        String message = "Note gagal di update";

        if (success != 0) {
            message = "Note berhasil di update";
            finish();
        }

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void deleteNote() {
        int success = db.deleteNote(noteID);
        String message = "Note gagal di hapus";
        if (success != 0) {
            message = "Note berhasil di hapus";
            finish();
        }

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_note_menu, menu);
        MenuItem delete = menu.findItem(R.id.delete);
        if (!isEdit) {
            delete.setVisible(false);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.save:
                String title = etTitle.getText().toString();
                String note = etNote.getText().toString();
                if (isInputValid()) {
                    if (isEdit) {
                        updateNote(noteID, title, note);
                    } else {
                        saveNote(title, note);
                    }
                }
                break;
            case R.id.delete:
                deleteNote();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
