package com.example.shop;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shop.adapters.GoodsAdapter;
import com.example.shop.interfaces.OnChangeListener;
import com.example.shop.models.Good;

public class MainActivity extends AppCompatActivity implements OnChangeListener, View.OnClickListener {
    private LayoutInflater layoutInflater;
    private View view_header, view_footer;
    private Button btnShow;
    private TextView footerCount;

    private ListView listView;
    private GoodsAdapter goodsAdapter;
    private ArrayList<Good> arr_goods = new ArrayList<Good>();
    private ArrayList<Good> arr_checked_goods = new ArrayList<Good>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        createMyListView();
    }
    private void createMyListView() {
        fillData();
        goodsAdapter = new GoodsAdapter(this, arr_goods, this);

        layoutInflater = LayoutInflater.from(this);
        view_header = layoutInflater.inflate(R.layout.header, null);
        view_footer = layoutInflater.inflate(R.layout.footer, null);

        btnShow = view_footer.findViewById(R.id.btnShow);
        btnShow.setOnClickListener(this);
        footerCount = view_footer.findViewById(R.id.footerCount);

        listView.addHeaderView(view_header);
        listView.addFooterView(view_footer);
        listView.setAdapter(goodsAdapter);
    }
    private void fillData(){
        arr_goods.add(new Good(1, "Laptop", 1199.99, false));
        arr_goods.add(new Good(2, "Smartphone", 850.50, false));
        arr_goods.add(new Good(3, "Tablet", 600.00, false));
        arr_goods.add(new Good(4, "Smartwatch", 250.90, false));
        arr_goods.add(new Good(5, "Wireless Earbuds", 150.00, false));
        arr_goods.add(new Good(6, "Gaming Console", 499.99, false));
        arr_goods.add(new Good(7, "Desktop Computer", 1300.50, false));
        arr_goods.add(new Good(8, "Bluetooth Speaker", 99.90, false));
        arr_goods.add(new Good(9, "4K TV", 1000.00, false));
        arr_goods.add(new Good(10, "External Hard Drive", 69.99, false));
        arr_goods.add(new Good(11, "Printer", 200.50, false));
        arr_goods.add(new Good(12, "Router", 120.00, false));
        arr_goods.add(new Good(13, "Keyboard", 49.90, false));
        arr_goods.add(new Good(14, "Mouse", 29.99, false));
        arr_goods.add(new Good(15, "Webcam", 90.50, false));
    }
    @Override
    public void onDataChanged() {
        int size = goodsAdapter.getCheckedGoods().size();
        footerCount.setText("Count of goods = " + size);
    }

    @Override
    public void onClick(View view) {
        arr_checked_goods = goodsAdapter.getCheckedGoods();
        if (arr_checked_goods.isEmpty()) {
            Toast.makeText(this, "The basket is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(this, BasketActivity.class);
        intent.putParcelableArrayListExtra("List", arr_checked_goods);
        startActivityForResult(intent, 1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
    }
}