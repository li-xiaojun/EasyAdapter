package com.lxj.easyadapter;

import java.util.List;


/**
 *
 * @param <T>
 */
public abstract class CommonAdapter<T> extends MultiItemTypeAdapter<T>
{
    protected int mLayoutId;
    protected List<T> mDatas;

    public CommonAdapter(final int layoutId, List<T> datas)
    {
        super(datas);
        mLayoutId = layoutId;
        mDatas = datas;

        addItemViewDelegate(new ItemViewDelegate<T>()
        {
            @Override
            public int getItemViewLayoutId()
            {
                return layoutId;
            }

            @Override
            public boolean isForViewType( T item, int position)
            {
                return true;
            }

            @Override
            public void convert(ViewHolder holder, T t, int position)
            {
                CommonAdapter.this.convert(holder, t, position);
            }
        });
    }

    protected abstract void convert(ViewHolder holder, T t, int position);


}
