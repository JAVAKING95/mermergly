package com.songlee.mermerglee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> selectedStrings;
    // 음식 리스트 (텍스트)
    public static final String[] foodsText = new String[]{
            "굶기", "비빔밥", "떡볶이", "삼겹살", "시리얼", "햄버거", "돈까스", "케잌", "김밥",
            "브로콜리", "국밥", "자장면", "라면", "냉면", "토스트", "파스타", "초밥", "피자",
            "볶음밥", "치킨", "사과", "바비큐", "김치찌개", "스프"};
    // 음식 리스트 (이미지 파일명)
    public static final String[] foodsImg = new String[]{
            "nofood", "bibimbab", "tteokbokki", "pork", "cereal", "burger", "donkkas", "cake", "gimbab",
            "broccoli", "gugbab", "chinanoodle", "ramen", "nengmyeon", "toast", "pasta", "sushi", "pizza",
            "bokkembab", "chicken", "apple", "barbecue", "kimchigook", "soup"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 그리드 아이템 초기화
        final GridView gridView;
        Button selectBtn;
        Button selectAllBtn;
        gridView = findViewById(R.id.mainItemGrid);
        selectBtn = findViewById(R.id.selectButton);
        selectAllBtn = findViewById(R.id.selectAllButton);

        selectedStrings = new ArrayList<>();

        final FoodViewAdapter adapter = new FoodViewAdapter(foodsText, foodsImg, this);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                int selectedIndex = adapter.selectedPositions.indexOf(position);
                if (selectedIndex > -1) {
                    adapter.selectedPositions.remove(selectedIndex);
                    ((FoodItemView) v).display(false);
                    selectedStrings.remove((String) parent.getItemAtPosition(position));
                } else {
                    adapter.selectedPositions.add(position);
                    ((FoodItemView) v).display(true);
                    selectedStrings.add((String) parent.getItemAtPosition(position));
                }
            }
        });

        selectAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int foodLength = foodsImg.length;
                for (int i=0; i<foodLength; i++) {
                    adapter.selectedPositions.add(i);
                    FoodItemView fiv = new FoodItemView(getApplicationContext());
                    fiv.display(true);
                    selectedStrings.add((String) gridView.getItemAtPosition(i));
                }
                gridView.setAdapter(adapter);
            }
        });

        // set listener for Button event
        selectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 아무 것도 선택되지 않았거나 1개만 선택했을 때
                if (selectedStrings.size() < 2) {
                    Context context = getApplicationContext();
                    CharSequence toastText = "적어도 두 개 이상의 음식을 선택해야 합니다!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, toastText, duration);
                    toast.setGravity(Gravity.TOP, 0, 100);
                    toast.show();
                } else {
                    // 다음 화면으로 이동
                    Intent intent = new Intent(MainActivity.this, SelectedItemsActivity.class);
                    intent.putStringArrayListExtra("SELECTED_FOOD_TEXT", selectedStrings);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
