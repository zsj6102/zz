package com.colpencil.redwood.view.activity.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.colpencil.redwood.R;
import com.property.colpencil.colpencilandroidlibrary.ControlerBase.MVP.ColpencilActivity;
import com.property.colpencil.colpencilandroidlibrary.ControlerBase.MVP.ColpencilPresenter;
import com.property.colpencil.colpencilandroidlibrary.Function.Annotation.ActivityFragmentInject;

import butterknife.Bind;

@ActivityFragmentInject(
        contentViewId = R.layout.activity_shopmain
)
/**
 * @deprecated 已成为商家
 * @author QFZ
 */
public class ShopMainActivity extends ColpencilActivity {
    @Bind(R.id.tv_main_title)
    TextView tv_main_title;
    @Bind(R.id.tv_shoppingCartFinish)
    TextView tv_shoppingCartFinish;

    @Override
    protected void initViews(View view) {
        tv_main_title.setText("我的店铺");
        tv_shoppingCartFinish.setText("");

    }

    @Override
    public ColpencilPresenter getPresenter() {
        return null;
    }

    @Override
    public void bindView(Bundle savedInstanceState) {

    }
}
