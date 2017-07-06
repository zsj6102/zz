package com.colpencil.redwood.view.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.colpencil.redwood.R;
import com.colpencil.redwood.bean.PostsComment;
import com.colpencil.redwood.function.tools.ImageLoaderUtils;
import com.colpencil.redwood.view.activity.mine.WebViewActivity;
import com.colpencil.redwood.view.base.CommonAdapter;
import com.colpencil.redwood.view.base.CommonViewHolder;
import com.property.colpencil.colpencilandroidlibrary.Function.Tools.TimeUtil;
import com.property.colpencil.colpencilandroidlibrary.Ui.SelectableRoundedImageView;

import java.util.List;

/**
 * @author 陈宝
 * @Description:回复的适配器
 * @Email DramaScript@outlook.com
 * @date 2016/7/29
 */
public class AnswerAdapter extends CommonAdapter<PostsComment> {

    private int fromwhere;  //0表示帖子，1表示百科
    private Context context;

    public AnswerAdapter(Context context, List<PostsComment> mDatas, int itemLayoutId, int fromwhere) {
        super(context, mDatas, itemLayoutId);
        this.context = context;
        this.fromwhere = fromwhere;
    }

    @Override
    public void convert(CommonViewHolder holder, PostsComment item, int position) {
        if (fromwhere == 0) {
            holder.getView(R.id.ll_content).setBackgroundColor(context.getResources().getColor(R.color.color_fffbfa));
        } else {
            holder.getView(R.id.ll_content).setBackgroundColor(context.getResources().getColor(R.color.main_background));
        }
        holder.setText(R.id.answer_nickname, item.getNickname());
        holder.setText(R.id.answer_content, item.getRe_content());
        if (item.getCreatetime() == 0) {
            holder.setText(R.id.answer_time, TimeUtil.getTimeDiffDay(item.getTime(), System.currentTimeMillis()));
        } else {
            holder.setText(R.id.answer_time, TimeUtil.getTimeDiffDay(item.getCreatetime(), System.currentTimeMillis()));
        }
        ImageLoaderUtils.loadImage(mContext, item.getFace(), (SelectableRoundedImageView) holder.getView(R.id.answer_header));
        ImageLoaderUtils.loadImage(mContext, item.getMember_photo(), (ImageView) holder.getView(R.id.answer_level));
        holder.getView(R.id.ll_condition).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, WebViewActivity.class);
                intent.putExtra("key", "info");
                mContext.startActivity(intent);
            }
        });
    }
}
