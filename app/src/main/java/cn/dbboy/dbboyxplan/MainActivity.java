package cn.dbboy.dbboyxplan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Arrays;

import cn.dbboy.dbboyxplan.dragview.DragViewActivity;
import cn.dbboy.dbboyxplan.joker.JokerActivity;
import cn.dbboy.dbboyxplan.main.MainAdapter;
import cn.dbboy.dbboyxplan.transformation.TransformationActivity;

/**
 * Created by DB_BOY on 2019/3/5.
 */
public class MainActivity extends BaseActivity {
    String[] s = {"DragView", "LazyDemo", "Transformation"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recy);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        
        MainAdapter adapter = new MainAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setList(Arrays.asList(s));

        adapter.setListener(new MainAdapter.ItemClickListener() {
            @Override
            public void click(int position) {
                clickItem(position);
            }
        });


    }

    private void clickItem(int position) {
        Intent intent = null;
        switch (position) {
            case 0:
                intent = DragViewActivity.createIntent(MainActivity.this);
                break;
            case 1:
                intent = JokerActivity.createIntent(MainActivity.this);
                break;
            case 2:
                intent = TransformationActivity.createIntent(MainActivity.this);
                break;
        }
        startActivity(intent);
    }
}
