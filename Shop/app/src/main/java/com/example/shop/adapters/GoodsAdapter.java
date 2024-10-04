package com.example.shop.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.shop.R;
import com.example.shop.interfaces.OnChangeListener;
import com.example.shop.models.Good;

import java.util.ArrayList;

public class GoodsAdapter extends BaseAdapter implements CompoundButton.OnCheckedChangeListener{
    private Context context;
    private OnChangeListener onChangeListener;
    private ArrayList<Good> arr_goods;
    private ArrayList<Good> arr_checked_goods = new ArrayList<Good>();

    private LayoutInflater layoutInflater;
    public GoodsAdapter(Context context, ArrayList<Good> arr_goods, OnChangeListener onChangeListener) {
        this.context = context;
        this.arr_goods = arr_goods;
        this.layoutInflater = LayoutInflater.from(context);
        this.onChangeListener = onChangeListener;
    }

    @Override
    public int getCount() {
        return arr_goods.size();
    }
    @Override
    public Object getItem(int position) {
        return arr_goods.get(position);
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
        Good good = arr_goods.get(position);
        TextView id = view.findViewById(R.id.itemId);
        id.setText(good.getId() + "");
        TextView name = view.findViewById(R.id.itemName);
        name.setText(good.getName());
        TextView cost = view.findViewById(R.id.itemCost);
        cost.setText(good.getCost() + "$");
        CheckBox checkBox = view.findViewById(R.id.itemCheckBox);
        checkBox.setChecked(good.isCheck());
        checkBox.setTag(position);
        checkBox.setOnCheckedChangeListener(this);
        return view;
    }
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if (compoundButton.isShown()) {
            int i = (int) compoundButton.getTag();
            arr_goods.get(i).setCheck(isChecked);
            notifyDataSetChanged();
            if (isChecked) arr_checked_goods.add(arr_goods.get(i));
            else arr_checked_goods.remove(arr_goods.get(i));
            onChangeListener.onDataChanged();
        }
    }
    public ArrayList<Good> getCheckedGoods() {
        return arr_checked_goods;
    }
}
