package com.songlee.mermerglee;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;

import static com.songlee.mermerglee.MainActivity.foodsImg;
import static com.songlee.mermerglee.MainActivity.foodsText;

public class SelectedItemsActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecteditems);

        ImageView animateImage = findViewById(R.id.animatedImage);
        final AnimationDrawable drawable = (AnimationDrawable) animateImage.getBackground();
        TextView selectedText = findViewById(R.id.selectedText);
        ImageView selectedImage = findViewById(R.id.selectedImage);
        Button restartButton = findViewById(R.id.btnRestart);
        // 애니메이션 처리
        drawable.start();
        // 결과 데이터 가져오기
        // 선택된 리스트 가져오기

        ArrayList<String> stringArrayListText = getIntent().getStringArrayListExtra("SELECTED_FOOD_TEXT");
        assert stringArrayListText != null;
        int minimumValue = 0;
        int maximumValue = stringArrayListText.size()-1;

        // 랜덤 추첨 처리
        Random random = new Random();
        int randomValue = random.nextInt(maximumValue - minimumValue + 1) + minimumValue;
        int currentPosition = 0;
        String currentText = stringArrayListText.get(randomValue);
        for (int i=0; i<foodsText.length; i++) {
            if (foodsText[i].equals(currentText)) {
                currentPosition = i;
                break;
            }
        }
        selectedText.setText(currentText);
        String currentImage = foodsImg[currentPosition];
        selectedImage.setImageResource(this.getResources().getIdentifier(currentImage, "drawable", this.getPackageName()));

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectedItemsActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
