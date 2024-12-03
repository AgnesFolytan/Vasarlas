package com.example.vasarlas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button addItemButton;
    private static final ArrayList<Item> items = new ArrayList<>();
    private TextInputLayout itemQuantity;
    private TextInputLayout itemName;
    private ItemsAdapter itemsAdapter;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Init();
        itemsAdapter = new ItemsAdapter(items, this);
        listView.setAdapter(itemsAdapter);

        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItemToList();
            }
        });


    }

    public void Init(){
        addItemButton =findViewById(R.id.addItemButton);
        itemQuantity = findViewById(R.id.itemQuantity);
        itemName = findViewById(R.id.itemName);
        listView = findViewById(R.id.listView);
    }

    private void addItemToList() {
        TextInputEditText itemNameEditText = (TextInputEditText) itemName.getEditText();
        TextInputEditText itemQuantityEditText = (TextInputEditText) itemQuantity.getEditText();

        if (itemNameEditText != null && itemQuantityEditText != null) {
            String name = itemNameEditText.getText().toString().trim();
            String quantityStr = itemQuantityEditText.getText().toString().trim();

            if (!name.isEmpty() && !quantityStr.isEmpty()) {
                try {
                    int quantity = Integer.parseInt(quantityStr);
                    Item newItem = new Item(name, quantity);
                    items.add(newItem);
                    itemsAdapter.notifyDataSetChanged();
                } catch (NumberFormatException e) {
                    Toast.makeText(this, "Please enter a valid quantity", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Please fill in both fields", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
