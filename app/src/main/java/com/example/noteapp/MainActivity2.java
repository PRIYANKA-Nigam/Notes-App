package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;

public class MainActivity2 extends AppCompatActivity {
EditText editText;int noteId;@Override
    protected void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main2);
        editText=(EditText)findViewById(R.id.editTextTextMultiLine);
        Intent intent=getIntent();
       noteId =intent.getIntExtra("id",-1);
        if (noteId!=-1){
            editText.setText(MainActivity.notes.get(noteId));
        }else {
            MainActivity.notes.add("");noteId=MainActivity.notes.size()-1;
            MainActivity.arrayAdapter.notifyDataSetChanged();
        }
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
         MainActivity.notes.set(noteId,String.valueOf(s));
         MainActivity.arrayAdapter.notifyDataSetChanged();
                SharedPreferences sh=getApplicationContext().getSharedPreferences("com.example.noteapp", Context.MODE_PRIVATE);
                HashSet<String> set=new HashSet<>(MainActivity.notes);
                sh.edit().putStringSet("notes",set).apply(); } @Override
            public void afterTextChanged(Editable s) { }}); }
}