package cn.dbboy.dbboyxplan.joker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;

import java.util.List;

import cn.dbboy.dbboyxplan.utils.DBLog;


/**
 * Created by joker on 16/8/19.
 * 重写 onFirstVisible 把初始化网络之类的放进去
 * onVisible和onInvisible替代onResume 和 onPause
 */
public abstract class JokerLazyFragment extends Fragment {
    public static String BR = "asdasdfasfsdafd";
    public int position;
    LocalBroadcastManager broadcastManager;
    BroadcastReceiver mAdDownLoadReceiver;
    private String TAG = "----------------------------";
    private boolean isVisible = false;
    //第一次能显示的时候不显示
    private boolean isFirstCanVisible = true;
    private boolean isShowFirst = false;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isVisible = false;

        DBLog.i(TAG);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DBLog.i(TAG + "  viewcreated " + position);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isFirstCanVisible) {
            isFirstCanVisible = false;
        } else {
            _onVisible();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        _onInvisible();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        DBLog.i(TAG + "---isVisibleToUser: " + isVisibleToUser + "     " + position);
        if (isVisibleToUser) {
            if (isFirstCanVisible) {
                isFirstCanVisible = false;
            } else {
                _onVisible();
            }
        } else {
            _onInvisible();
        }
    }

    private void _onVisible() {
        if (!isVisible && getUserVisibleHint()) {
            if (!isShowFirst) {
                onFirstVisible();
                isShowFirst = true;
            }
            onVisible();
            isVisible = true;
        }
    }

    public abstract void updateName(String name);

    public void onFirstVisible() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DBLog.i(TAG + "fragment Create" + "     " + position);
        broadcastManager = LocalBroadcastManager.getInstance(getActivity());
        mAdDownLoadReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String userID = intent.getStringExtra("data");
                updateName(userID);
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BR);
        broadcastManager.registerReceiver(mAdDownLoadReceiver, intentFilter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        DBLog.i(TAG + "fragment Destroy:" + "     " + position);
        broadcastManager.unregisterReceiver(mAdDownLoadReceiver);
    }

    public abstract void onVisible();

    private void _onInvisible() {
        if (isVisible) {
            isVisible = false;
            onInvisible();
        }
    }

    public abstract void onInvisible();

    public abstract void updata(List<String> s, int i);


}
