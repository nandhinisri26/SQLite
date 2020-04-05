package com.sonacollegeotechnology.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    EditText name,phone;
    Button edit,delete;
    Contact contact;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        contact = new Contact();
        db = new DatabaseHandler(this);

        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        edit = findViewById(R.id.edit);
        delete = findViewById(R.id.delete);

        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");
        final String namee = intent.getStringExtra("name");
        final String phonee = intent.getStringExtra("phone");

        name.setText(namee);
        phone.setText(phonee);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(name.getText().toString()) || TextUtils.isEmpty(phone.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Please fill all the details", Toast.LENGTH_SHORT).show();
                } else {
                    contact.setID(Integer.parseInt(id));
                    contact.setName(name.getText().toString());
                    contact.setPhoneNumber(phone.getText().toString());
                    db.updateContact(contact);
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contact.setID(Integer.parseInt(id));
                contact.setName(name.getText().toString());
                contact.setPhoneNumber(phone.getText().toString());
                db.deleteContact(contact);
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
