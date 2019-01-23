package com.lxj.easyadapter.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lxj.easyadapter.CommonAdapter;
import com.lxj.easyadapter.MultiItemTypeAdapter;
import com.lxj.easyadapter.ViewHolder;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    List<User> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //prepare data
        for (int i = 0; i < 20; i++) {
            userList.add(new User("本杰明 - " + i, i * 2));
        }


//        testHeader();

    }

    void testHeader(){
        CommonAdapter<User> commonAdapter = new CommonAdapter<User>(R.layout.item, userList) {
            @Override
            protected void convert(ViewHolder holder, User user, int position) {
                holder.setText(R.id.tv_name, "name: " + user.name)
                        .setText(R.id.tv_age, "age: " + user.age);
            }
        };
        commonAdapter.setOnItemClickListener(new MultiItemTypeAdapter.SimpleOnItemClickListener(){
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                super.onItemClick(view, holder, position);

            }
        });
        final TextView textView = new TextView(this);
        textView.setPadding(80,80,80,80);
        textView.setText("header1");

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, textView.getText().toString(), Toast.LENGTH_LONG).show();
            }
        });

        TextView textView2 = new TextView(this);
        textView2.setPadding(80,80,80,80);
        textView2.setText("header2");

        commonAdapter.addHeaderView(textView);
        commonAdapter.addHeaderView(textView2);
        // 必须使用wrapper方法返回的adapter，否则无效
        recyclerView.setAdapter(commonAdapter.wrapper());
    }
}
