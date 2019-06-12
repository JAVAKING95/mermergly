package com.songlee.mermerglee;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class FoodItemView extends FrameLayout {
    private TextView textView;
    private ImageView imageView;
    private FrameLayout layout;

    public FoodItemView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.item_grid_food, this);
        textView = getRootView().findViewById(R.id.itemText);
        imageView = getRootView().findViewById(R.id.itemImage);
        layout = getRootView().findViewById(R.id.layout);
    }

    public void display(String text, String image, boolean isSelected) {
        textView.setText(text);
        Context context = imageView.getContext();
        int imgRes = context.getResources().getIdentifier(image, "drawable", context.getPackageName());
        imageView.setAlpha(0.6f);
        imageView.setImageResource(imgRes);
        display(isSelected);
    }

    public void display(boolean isSelected) {
        // 아이템 클릭 시 이벤트
        layout.setBackgroundResource(isSelected ? R.drawable.square_selected : R.drawable.square_unselected);
    }

}
