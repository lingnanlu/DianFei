package io.github.lingnanlu.dianfei.holder;

import android.content.Context;
import android.view.View;

import com.unnamed.b.atv.model.TreeNode;

import io.github.lingnanlu.dianfei.Item;

/**
 * Created by rabbit on 5/23/2016.
 */
public class ItemHolder extends TreeNode.BaseNodeViewHolder<Item>{

    public ItemHolder(Context context) {
        super(context);
    }

    @Override
    public View createNodeView(TreeNode node, Item value) {
        return null;
    }

}
