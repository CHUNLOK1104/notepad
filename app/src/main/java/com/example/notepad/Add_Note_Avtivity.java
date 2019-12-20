package com.example.notepad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Add_Note_Avtivity extends AppCompatActivity {
    private Button btnSave,btnCancel;
    private EditText edtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__note__avtivity);
        findViews();
        setListeners();
    }

    private void findViews(){
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);
        edtName = findViewById(R.id.edtName);
    }

    private void setListeners(){
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edtName.getText().toString().isEmpty()){
                    NoteDBHelper dbHelper = new NoteDBHelper(Add_Note_Avtivity.this);
                    AddNote addnote = new AddNote();
                    addnote.setNote(edtName.getText().toString());
                    dbHelper.insertContact(addnote);
                    Add_Note_Avtivity.this.finish();
                } else {
                    if (edtName.getText().toString().isEmpty()){
                        edtName.setError("Cannot be empty");
                    }
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Add_Note_Avtivity.this, Main2Activity.class);
                startActivity(i);
                finish();
            }
        });

    }
}
