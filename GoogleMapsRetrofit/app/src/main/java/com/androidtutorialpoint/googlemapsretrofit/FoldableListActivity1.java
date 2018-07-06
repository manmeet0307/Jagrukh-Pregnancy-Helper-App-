package com.androidtutorialpoint.googlemapsretrofit;

import android.content.Intent;
import android.os.Bundle;

import com.alexvasilkov.android.commons.utils.Views;
import com.alexvasilkov.foldablelayout.FoldableListLayout;

public class FoldableListActivity1 extends BaseActivity1 {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foldable_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FoldableListLayout foldableListLayout = Views.find(this, R.id.foldable_list);
        foldableListLayout.setAdapter(new PaintingsAdapter(this));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}