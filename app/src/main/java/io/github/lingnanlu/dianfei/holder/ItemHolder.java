package io.github.lingnanlu.dianfei.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.github.johnkil.print.PrintView;
import com.unnamed.b.atv.model.TreeNode;

import io.github.lingnanlu.dianfei.DeviceAbstract;
import io.github.lingnanlu.dianfei.R;

/**
 * Created by rabbit on 5/23/2016.
 */
public class ItemHolder extends TreeNode.BaseNodeViewHolder<DeviceAbstract>{

    public ItemHolder(Context context) {
        super(context);
    }

    @Override
    public View createNodeView(TreeNode node, DeviceAbstract value) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_node, null, false);
        TextView name = (TextView) view.findViewById(R.id.node_value);
        name.setText(value.name);

        final PrintView icon = (PrintView) view.findViewById(R.id.icon);
        icon.setIconText(context.getResources().getString(value.icon));

        return view;
    }

}
