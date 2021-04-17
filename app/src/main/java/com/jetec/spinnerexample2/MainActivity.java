package com.jetec.spinnerexample2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**=======================================================================================*/
        /**手動製造資料*/
        ArrayList<Data> mData = new ArrayList<>();
        mData.add(new Data("Sam",19,true));
        mData.add(new Data("Eddies",25,false));
        mData.add(new Data("Sally",17,true));
        mData.add(new Data("Noah",25,false));
        mData.add(new Data("Tina",30,false));
        /**=======================================================================================*/
        /**設置Spinner*/
        Spinner spList = findViewById(R.id.spinner);
        MySpinnerAdapter mAdapter = new MySpinnerAdapter(this,mData);//使用自定義的ArrayAdapter
        spList.setAdapter(mAdapter);
        /**=======================================================================================*/
        /**設置Spinner點擊事件*/
        spList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {//Spinner點擊後
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tvInfo = findViewById(R.id.textView);
                Data mData = (Data) parent.getItemAtPosition(position);
                tvInfo.setText("名字：\t"+mData.getName()+"\n"
                        +"年齡：\t"+mData.getAge() +"\n"
                        +"是否為學生？\t"+mData.getStudent());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });//點擊事件
    }
    //自定義ArrayAdapter,方法為繼承原有的ArrayAdapter後修改他的介面
    private class MySpinnerAdapter extends ArrayAdapter {

        //建構子
        public MySpinnerAdapter(@NonNull Context context, @NonNull List<Data> mData) {
            super(context, 0, mData);
        }
        //getView為設置未點開時的Spinner畫面
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return createView(position, convertView, parent,false);
        }
        //getDropDownView為設置點開後的畫面
        @Override
        public View getDropDownView(int position, @Nullable View convertView, @NonNull
                ViewGroup parent) {
            return createView(position, convertView, parent,true);
        }

        //因為兩個介面通常都長差不多...所以新增介面的我把它寫在一起，差別只在最後的true/false
        @SuppressLint("SetTextI18n")
        private View createView(int position, View convertView
                , ViewGroup parent,Boolean ageDisplay){
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.spinner_item, parent, false);
            TextView tvName = convertView.findViewById(R.id.textViewTitle);
            TextView tvAge = convertView.findViewById(R.id.textViewAge);
            Data item = (Data) getItem(position);
            if (item != null) {
                tvName.setText(item.getName());
                tvAge.setText(item.getAge()+"y");
                if (ageDisplay) tvAge.setVisibility(View.VISIBLE);
                else tvAge.setVisibility(View.GONE);

            }
            return convertView;
        }//複寫介面

    }//class MySpinnerAdapter

}
