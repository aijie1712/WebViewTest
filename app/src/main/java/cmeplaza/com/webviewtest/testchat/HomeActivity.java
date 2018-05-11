package cmeplaza.com.webviewtest.testchat;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import cmeplaza.com.webviewtest.R;

public class HomeActivity extends AppCompatActivity {
    private ViewPager viewPager;

    private BFragment bFragment;

    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initView();
    }

    private void initView() {
        frameLayout = findViewById(R.id.frameLayout);
        viewPager = findViewById(R.id.viewPager);
        List<AFragment> fragmentList = new ArrayList<>();
        fragmentList.add(AFragment.newInstance("第一"));
        fragmentList.add(AFragment.newInstance("第二"));
        fragmentList.add(AFragment.newInstance("第三"));
        fragmentList.add(AFragment.newInstance("第四"));
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(adapter);

        for (final AFragment aFragment : fragmentList) {
            aFragment.setOnButtonClickListener(new AFragment.OnButtonClickListener() {
                @Override
                public void onButtonClick() {
                    frameLayout.setVisibility(View.VISIBLE);
                    if (bFragment == null) {
                        bFragment = BFragment.newInstance(aFragment.getData());
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, bFragment).commit();
                    } else {
                        bFragment.reSetData(aFragment.getData());
                    }
                }
            });
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_1:
                viewPager.setCurrentItem(0);
                break;
            case R.id.btn_2:
                viewPager.setCurrentItem(1);
                break;
            case R.id.btn_3:
                viewPager.setCurrentItem(2);
                break;
            case R.id.btn_4:
                viewPager.setCurrentItem(3);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (frameLayout.getVisibility() == View.VISIBLE) {
            frameLayout.setVisibility(View.GONE);
            bFragment.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }
}
