package com.example.vasarlas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ItemDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        Button backButton = findViewById(R.id.backButton);
        TextView nameTextView = findViewById(R.id.nameTextView);
        TextView quantityTextView = findViewById(R.id.quantityTextView);

        String name = getIntent().getStringExtra("name");
        int quantity = getIntent().getIntExtra("quantity", 0);

        nameTextView.setText(name);
        quantityTextView.setText(String.valueOf(quantity));

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ItemDetailActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
