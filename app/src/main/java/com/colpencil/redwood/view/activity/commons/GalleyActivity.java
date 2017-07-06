package com.colpencil.redwood.view.activity.commons;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.colpencil.redwood.R;
import com.colpencil.redwood.function.tools.ImageLoaderUtils;
import com.colpencil.redwood.function.utils.ListUtils;
import com.colpencil.redwood.function.widgets.zoom.PhotoView;
import com.colpencil.redwood.function.widgets.zoom.ViewPagerFixed;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GalleyActivity extends AppCompatActivity {

    @Bind(R.id.gallery01)
    ViewPagerFixed vpf;

    private int position;
    private ArrayList<String> imglist = new ArrayList<>();
    private ArrayList<View> listViews = null;
    private MyPageAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(R.layout.plugin_camera_gallery, null);
        setContentView(view);
        ButterKnife.bind(this, view);
        getParams();
        initData();
        initView();
    }

    private void getParams() {
        position = getIntent().getIntExtra("position", 0);
        imglist = getIntent().getStringArrayListExtra("data");
    }

    private void initData() {
        if (!ListUtils.listIsNullOrEmpty(imglist)) {
            for (String str : imglist) {
                initListViews(str);
            }
        }
    }

    private void initView() {
        vpf.setOnPageChangeListener(pageChangeListener);
        adapter = new MyPageAdapter(listViews);
        vpf.setAdapter(adapter);
        vpf.setPageMargin(10);
        vpf.setCurrentItem(position);
    }

    private void initListViews(String str) {
        if (listViews == null)
            listViews = new ArrayList<>();
        PhotoView img = new PhotoView(this);
        img.setBackgroundColor(0xff000000);
        img.setImageBitmap(BitmapFactory.decodeFile(str));
        img.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        ImageLoaderUtils.loadImage(this, str, img);
        listViews.add(img);
    }

    @OnClick(R.id.iv_back)
    void backOnClick() {
        finish();
    }

    class MyPageAdapter extends PagerAdapter {

        private ArrayList<View> listViews;

        private int size;

        public MyPageAdapter(ArrayList<View> listViews) {
            this.listViews = listViews;
            size = listViews == null ? 0 : listViews.size();
        }

        public void setListViews(ArrayList<View> listViews) {
            this.listViews = listViews;
            size = listViews == null ? 0 : listViews.size();
        }

        @Override
        public int getCount() {
            return size;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPagerFixed) arg0).removeView(listViews.get(arg1 % size));
        }

        @Override
        public void finishUpdate(View arg0) {
        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {
            try {
                ((ViewPagerFixed) arg0).addView(listViews.get(arg1 % size), 0);

            } catch (Exception e) {
            }
            return listViews.get(arg1 % size);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent();
            intent.putStringArrayListExtra("data", imglist);
            GalleyActivity.this.setResult(3, intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
//            location = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
