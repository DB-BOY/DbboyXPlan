package cn.dbboy.dbboyxplan.joker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.dbboy.dbboyxplan.BaseActivity;
import cn.dbboy.dbboyxplan.R;
import cn.dbboy.dbboyxplan.utils.DBLog;


/**
 * Created by DB_BOY on 2019/1/24.
 */
public class JokerActivity extends BaseActivity {
    PagerListAdapter adapter;
    ViewPager vp;
    List<String> fragments;

    public static Intent createIntent(Context ctx) {
        Intent intent = new Intent(ctx, JokerActivity.class);
        return intent;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        DBLog.i("--Destroy-------------------------------");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lazy);

        DBLog.i("----Create-----------------------------");
        vp = findViewById(R.id.viewpager);
        fragments = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            fragments.add(("Joker: " + i));
        }
        adapter = new PagerListAdapter(getSupportFragmentManager());
        vp.setAdapter(adapter);
        adapter.updateData(fragments);
        vp.setOffscreenPageLimit(0);
        
        
        
        findViewById(R.id.tv_).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragments.remove(((int) (Math.random() * (fragments.size() - 1))));
                adapter.updateData(fragments);
            }
        });
    }

}