package com.example.btl_nhom_7;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import me.relex.circleindicator.CircleIndicator;
import com.example.btl_nhom_7.R;

import com.example.btl_nhom_7.Photo.model.Photo;
import com.example.btl_nhom_7.Photo.adapter.PhotoViewPagerAdapter;
public class Home extends AppCompatActivity {

    private ViewPager mViewPager;
    private CircleIndicator mCircleIndicator;
    private List<Photo> mListPhoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mViewPager = findViewById(R.id.view_page);
        mCircleIndicator = findViewById(R.id.circle_indicator);
        mListPhoto = getListPhoto();

        PhotoViewPagerAdapter adapter = new PhotoViewPagerAdapter(mListPhoto);
        mViewPager.setAdapter(adapter);
        mCircleIndicator.setViewPager(mViewPager);
    }
    private List<Photo> getListPhoto(){
        List<Photo> list = new ArrayList<>();
        list.add(new Photo(R.drawable.bgimg_1));
        list.add(new Photo(R.drawable.bgimg_2));
        list.add(new Photo(R.drawable.bgimg_3));
        list.add(new Photo(R.drawable.bgimg_4));

        return list;
    }
}