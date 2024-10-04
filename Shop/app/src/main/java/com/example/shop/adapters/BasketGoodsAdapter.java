package com.example.shop.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.shop.R;
import com.example.shop.models.Good;

import java.util.ArrayList;

public class BasketGoodsAdapter extends BaseAdapter {
    private ArrayList<Good> arr_checked_goods;

    private LayoutInflater layoutInflater;
    public BasketGoodsAdapter(Context context, ArrayList<Good> arr_checked_goods) {
        this.arr_checked_goods = arr_checked_goods;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return arr_checked_goods.size();
    }
    @Override
    public Object getItem(int position) {
        return arr_checked_goods.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.list_item, null, false);
        }
        Good good = arr_checked_goods.get(position);
        TextView id = view.findViewById(R.id.itemId);
        id.setText(good.getId() + "");
        TextView name = view.findViewById(R.id.itemName);
        name.setText(good.getName());
        TextView cost = view.findViewById(R.id.itemCost);
        cost.setText(good.getCost() + "$");
        CheckBox checkBox = view.findViewById(R.id.itemCheckBox);
        checkBox.setVisibility(View.INVISIBLE);
        return view;
    }
}
