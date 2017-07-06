package com.colpencil.redwood.view.activity.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.colpencil.redwood.R;
import com.colpencil.redwood.base.PhotoBaseActivity;
import com.colpencil.redwood.function.widgets.dialogs.SelectPlaceDialog;
import com.jph.takephoto.model.TResult;
import com.property.colpencil.colpencilandroidlibrary.ControlerBase.MVP.ColpencilPresenter;
import com.property.colpencil.colpencilandroidlibrary.Function.Annotation.ActivityFragmentInject;

import butterknife.Bind;

@ActivityFragmentInject(
        contentViewId = R.layout.activity_person_business
)
public class PersonApplyActivity extends PhotoBaseActivity implements View.OnClickListener {

    @Bind(R.id.tv_main_title)
    TextView tv_title;
    @Bind(R.id.iv_back)
    ImageView iv_back;
    @Bind(R.id.iv_positive)
    ImageView iv_positive;
    @Bind(R.id.iv_negative)
    ImageView iv_negative;
    @Bind(R.id.tv_city)
    TextView tv_city;

    private int type = 0;

    @Override
    protected void initViews(View view) {
        tv_title.setText("个人商家申请");
        iv_back.setOnClickListener(this);
        iv_positive.setOnClickListener(this);
        iv_negative.setOnClickListener(this);
        tv_city.setOnClickListener(this);
    }

    @Override
    public ColpencilPresenter getPresenter() {
        return null;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_back) {
            finish();
        } else if (id == R.id.iv_positive) {
            type = 0;
            openSelect(false, 1);
        } else if (id == R.id.iv_negative) {
            type = 1;
            openSelect(false, 1);
        } else if (id == R.id.tv_city) {
            new SelectPlaceDialog(this, tv_city).show();
        }
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        if (type == 0) {
            Glide.with(this).load(result.getImages().get(0).getCompressPath()).into(iv_positive);
        } else {
            Glide.with(this).load(result.getImages().get(0).getCompressPath()).into(iv_negative);
        }
    }
}
