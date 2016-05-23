package io.github.lingnanlu.dianfei;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import io.github.lingnanlu.dianfei.holder.ItemHolder;

public class TreeStructActivity extends AppCompatActivity {


    FrameLayout container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_struct);

        container = (FrameLayout) findViewById(R.id.containter);

        TreeNode root = TreeNode.root();

        TreeNode myProfile = new TreeNode(new ItemHolder.IconTreeItem(R.string.ic_person, "My Profile"));
        TreeNode nan = new TreeNode(new ItemHolder.IconTreeItem(R.string.ic_person, "nan"));
        TreeNode lu = new TreeNode(new ItemHolder.IconTreeItem(R.string.ic_person, "lu"));
        TreeNode ling = new TreeNode(new ItemHolder.IconTreeItem(R.string.ic_person, "ling"));

        root.addChildren(myProfile, nan, lu, ling);

        AndroidTreeView treeView = new AndroidTreeView(this, root);
        treeView.setDefaultAnimation(true);
        treeView.setDefaultViewHolder(ItemHolder.class);
        treeView.setDefaultContainerStyle(R.style.TreeNodeStyleDivided, true);
        container.addView(treeView.getView());
    }
}
