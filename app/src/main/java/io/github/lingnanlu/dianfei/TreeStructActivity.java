package io.github.lingnanlu.dianfei;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

public class TreeStructActivity extends AppCompatActivity {


    View container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_struct);

        container = findViewById(R.id.containter);


        TreeNode root = TreeNode.root();

        TreeNode parent = new TreeNode("MyParentNode");
        TreeNode child0 = new TreeNode("ChildNode0");
        TreeNode child1 = new TreeNode("ChildNode1");

        parent.addChildren(child0, child1);
        root.addChild(parent);
        AndroidTreeView tView = new AndroidTreeView(this, root);

    }
}
