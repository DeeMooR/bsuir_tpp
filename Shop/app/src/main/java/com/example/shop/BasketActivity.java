package com.example.shop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shop.adapters.BasketGoodsAdapter;
import com.example.shop.models.Good;

import java.util.ArrayList;

public class BasketActivity extends AppCompatActivity {
    private ListView basketList;
    private BasketGoodsAdapter basketGoodsAdapter;
    private ArrayList<Good> arr_checked_goods = new ArrayList<Good>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        basketList = findViewById(R.id.basketList);
        arr_checked_goods = getIntent().getParcelableArrayListExtra("List");
        fillBasketList();
    }
    private void fillBasketList() {
        basketGoodsAdapter = new BasketGoodsAdapter(this, arr_checked_goods);
        basketList.setAdapter(basketGoodsAdapter);
    }
    public void clickBack(View view) {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }
}