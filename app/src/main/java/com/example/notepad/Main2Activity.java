package com.example.notepad;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    private Button btnAdd;
    private ListView lvNote;
    private NoteDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        findViews();
        //setListeners();
        setUpDatabase();
        setUpAdapter();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            finish();
            return true;
        }
        if (id == R.id.add_note) {
            Intent intent = new Intent(this, Add_Note_Avtivity.class);
            this.startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        AddNoteAdapter adapter = (AddNoteAdapter) lvNote.getAdapter();
        adapter.updateData(dbHelper.getAllNote());
    }

    private void findViews(){
        lvNote = findViewById(R.id.list_view_note);
        btnAdd  = findViewById(R.id.add_note);

    }

  /*  private void setListeners(){
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main2Activity.this, Add_Note_Avtivity.class);
                startActivity(i);
            }
        });
    }*/

    private void setUpDatabase(){
        dbHelper = new NoteDBHelper(this);
    }

    private void setUpAdapter(){
        ArrayList<AddNote> NoteList = new ArrayList<>();
        NoteList = dbHelper.getAllNote();

        AddNoteAdapter adapter = new AddNoteAdapter(this,NoteList);
        lvNote.setAdapter(adapter);
    }

}
