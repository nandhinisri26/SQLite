package com.sonacollegeotechnology.sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText name,phone;
    Button save;


    private List<Contact> contacts = new ArrayList<Contact>();
    private ContactAdapter contactAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    public DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.dept);
        recyclerView.setNestedScrollingEnabled(false);
        linearLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        contactAdapter = new ContactAdapter(MainActivity.this, contacts);
        recyclerView.setAdapter(contactAdapter);

        db = new DatabaseHandler(this);

        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        save = findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(name.getText().toString()) || TextUtils.isEmpty(phone.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Please fill all the details", Toast.LENGTH_SHORT).show();
                } else {
                    db.addContact(new Contact(name.getText().toString(), phone.getText().toString()));
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        addContacts();
    }

    private void addContacts() {

        List<Contact> c = db.getAllContacts();

        for (Contact cn : c) {

            Contact contact = new Contact();

            contact.setName(cn.getName());
            contact.setPhoneNumber(cn.getPhoneNumber());
            contact.setID(cn.getID());

            contacts.add(contact);
        }

        contactAdapter.notifyDataSetChanged();

    }
}