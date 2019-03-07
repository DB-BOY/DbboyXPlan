package cn.dbboy.dbboyxplan.joker;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.dbboy.dbboyxplan.R;
import cn.dbboy.dbboyxplan.utils.DBLog;


/**
 * Created by DB_BOY on 2019/1/24.
 */
public class JokeyTestFragment extends JokerLazyFragment {
    
    private View view;
    private List<String> titleList;
    private List<TextView> viewList;
    public JokeyTestFragment() {
    }

    @SuppressLint("ValidFragment")
    public JokeyTestFragment(List<String> strings,int position) {
        super();
        this.titleList = strings;
        this.position = position;
    }

    public static JokeyTestFragment instance(List<String> strings,int position) {
        DBLog.i("----instance :position: "+position);
        return new JokeyTestFragment(strings,position);
    }

    public void updata(List<String> strings, int position) {
        this.titleList = strings;
        show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DBLog.i("----createview:position: "+position);
        DBLog.i("----"+titleList);
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_four, container, false);
            viewList = new ArrayList<>();
            viewList.add((TextView) view.findViewById(R.id.tv1));
            viewList.add((TextView) view.findViewById(R.id.tv2));
            viewList.add((TextView) view.findViewById(R.id.tv3));
            viewList.add((TextView) view.findViewById(R.id.tv4));
        }
        return view;
    }
    @Override
    public void onVisible() {
        DBLog.i("onVisible: "+position);
        show();
        
    }

    private void show() {
        if(viewList==null){
            return;
        }
        DBLog.i(titleList);
        
        int i = 0;
        for (i = 0; i < titleList.size(); i++) {
            viewList.get(i).setText(titleList.get(i));
        }
        for (; i < viewList.size(); i++) {
            viewList.get(i).setText("-------");
        }
    }

    @Override
    public void onInvisible() {
        DBLog.i("fragment onInvisible :");
    }

    @Override
    public void updateName(String name) {
    }

    @Override
    public void onFirstVisible() {
            super.onFirstVisible();
        DBLog.i("fragment onFirstVisible:");
    }

}
