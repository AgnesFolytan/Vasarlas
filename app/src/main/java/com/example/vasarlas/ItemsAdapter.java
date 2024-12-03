package com.example.vasarlas;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ItemsAdapter extends BaseAdapter {

    private List<Item> items;
    private Context context;

    public ItemsAdapter(List<Item> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = LayoutInflater.from(context).inflate(R.layout.items_list_item, parent, false);

        TextView nameTextView = view.findViewById(R.id.nameTextView);
        TextView quantityTextView = view.findViewById(R.id.quantityTextView);

        Item currentItem = items.get(position);
        nameTextView.setText(currentItem.getName());
        quantityTextView.setText(String.valueOf(currentItem.getQuantity()));

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                items.remove(position);
                notifyDataSetChanged();
                return true;
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item item = items.get(position);
                Intent intent = new Intent(context, ItemDetailActivity.class);
                intent.putExtra("name", currentItem.getName());
                intent.putExtra("quantity", currentItem.getQuantity());
                context.startActivity(intent);
            }
        });

        return view;
    }
}
