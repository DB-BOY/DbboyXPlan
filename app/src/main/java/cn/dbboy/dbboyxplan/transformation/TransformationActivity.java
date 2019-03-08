package cn.dbboy.dbboyxplan.transformation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import cn.dbboy.dbboyxplan.BaseActivity;
import cn.dbboy.dbboyxplan.R;

public class TransformationActivity extends BaseActivity {
    private ViewPager mViewPager;
    private List<Fragment> mFragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transformation);
        mViewPager = findViewById(R.id.pager);
        mFragments = new ArrayList<>();
        mFragments.add(new GuideFragment1());
        mFragments.add(new GuideFragment2());
        mFragments.add(new GuideFragment3());
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        });

        mViewPager.setPageTransformer(false, new GuideTransformation());
    }
    public static Intent createIntent(Context ctx) {
        Intent intent =  new Intent(ctx,TransformationActivity.class);
        return intent;
    }
}
