package com.colpencil.redwood.model.imples;

import com.colpencil.redwood.bean.ListResult;
import com.colpencil.redwood.bean.NewsInfoVo;
import com.property.colpencil.colpencilandroidlibrary.ControlerBase.MVP.ColpencilModel;

import rx.Observer;

/**
 * @Description:新闻列表的Model接口
 * @author 陈宝
 * @Email  DramaScript@outlook.com
 * @date 2016/7/29
 */
public interface INewsListModel extends ColpencilModel {

    void loadNews(String cat_id, int page, int pageSize);

    void sub(Observer<ListResult<NewsInfoVo>> observer);

}
