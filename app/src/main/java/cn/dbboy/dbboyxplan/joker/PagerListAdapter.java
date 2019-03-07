package cn.dbboy.dbboyxplan.joker;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.dbboy.dbboyxplan.utils.DBLog;


/**
 * Created by DB_BOY on 2019/2/22.
 */
public class PagerListAdapter extends FragmentPagerAdapter {
    
//    List<JokerLazyFragment> fList;
    int pageCount =0;

    private ArrayList<ArrayList<String>> usrVideoIdGroups;
    private ArrayList<String> usrVideoIdGroup;
    
    
    public PagerListAdapter(FragmentManager fm) {
        super(fm);
        usrVideoIdGroups = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int i) {
        DBLog.i("-----Item---position "+i);
        return JokeyTestFragment.instance(usrVideoIdGroups.get(i),i) ;
    }

    @Override
    public int getCount() {
        return pageCount;
    }

    public void updateData(List<String> dataList) {
        notifyList(dataList);
        ArrayList<JokerLazyFragment> fragments = new ArrayList<>();
        if (usrVideoIdGroups == null) {
            return;
        }
        
//        for (int i = 0, size = usrVideoIdGroups.size(); i < size; i++) {
//            fragments.add(JokeyTestFragment.instance(usrVideoIdGroups.get(i),i));
//        }
        pageCount =  usrVideoIdGroups.size();
        setFragmentList(fragments);
    }
    private void setFragmentList(ArrayList<JokerLazyFragment> fragmentList) {
//        if (fList != null) {
//            fList.clear();
//        } else {
//            fList = new ArrayList<>();
//        }
//        fList.addAll(fragmentList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    /**
     * @param usrVideoIds
     *         处理数据
     */
    
    private void notifyList(List<String> usrVideoIds) {
        if (usrVideoIdGroups != null) {
            usrVideoIdGroups.clear();
        }
        if (usrVideoIds == null) {
            return;
        }

        for (int i = 0; i < usrVideoIds.size(); i++) {
            if (i % 4 == 0) {
                usrVideoIdGroup = new ArrayList<>();
                usrVideoIdGroup.add(usrVideoIds.get(i));
                usrVideoIdGroups.add(usrVideoIdGroup);
            } else {
                usrVideoIdGroup.add(usrVideoIds.get(i));
            }
        }
    }


    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        JokerLazyFragment fragment  = (JokerLazyFragment) super.instantiateItem(container, position);
        fragment.updata(usrVideoIdGroups.get(position),position);
        DBLog.i("instantiateItem-->position-->"+position);
        return fragment;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
        DBLog.i("destroyItem-->"+position+ "container:"+container+ "    object  "+object);
    }
}
