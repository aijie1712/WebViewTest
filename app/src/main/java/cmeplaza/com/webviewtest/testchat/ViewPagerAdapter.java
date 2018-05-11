package cmeplaza.com.webviewtest.testchat;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2016-11-30
 *
 * @desc 主页的FragmentAdapter
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<AFragment> fragmentList;

    private Fragment fragment;

    public ViewPagerAdapter(FragmentManager fm, List<AFragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        fragment = (Fragment) object;
    }

    public Fragment getCurrentFragment() {
        return fragment;
    }

    @Override
    public Fragment getItem(int index) {
        return fragmentList.get(index);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

}
