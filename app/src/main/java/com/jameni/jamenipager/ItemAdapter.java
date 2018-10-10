package com.jameni.jamenipager;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jameni.jamenilistlib.adapter.BrvahAdapter;

public class ItemAdapter extends BrvahAdapter<ItemModel> {
    public ItemAdapter() {
        super(R.layout.item_test);
    }

    @Override
    protected void convert(BaseViewHolder helper, ItemModel item) {
        helper.setText(R.id.tv, item.getValue());
    }
}
