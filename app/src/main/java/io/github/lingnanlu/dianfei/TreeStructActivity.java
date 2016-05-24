package io.github.lingnanlu.dianfei;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.lingnanlu.dianfei.Provider.DeviceTree;
import io.github.lingnanlu.dianfei.holder.ItemHolder;

public class TreeStructActivity extends AppCompatActivity{


    @Bind(R.id.tree_containter)
    FrameLayout treeContainer;

    @Bind(R.id.detail_container)
    LinearLayout detailContainer;

//    Handler mHander = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            if (msg.what == DETAIL) {
//                //show detail info in detail container;
//            } else {
//                showLevel(msg.what);
//            }
//        }
//    };
//
//    private void showLevel(int level) {
//        TreeNode root = DeviceTree.getTreeByLevel(level);
//        AndroidTreeView treeView = new AndroidTreeView(this, root);
//        treeContainer.addView(treeView.getView());
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_struct);
        ButterKnife.bind(this);

        DeviceTree.generateWholeTree(new CallBack<Void>() {
            @Override
            public void onResult(Void result) {
                TreeNode tree = DeviceTree.tree;
                AndroidTreeView treeView = new AndroidTreeView(TreeStructActivity.this, tree);
                treeView.setDefaultAnimation(true);
                treeView.setDefaultViewHolder(ItemHolder.class);
                treeView.setDefaultContainerStyle(R.style.TreeNodeStyleDivided, true);
                treeContainer.addView(treeView.getView());
                Map<Integer, List<TreeNode>> map = DeviceTree.devicesOfType;
                Toast.makeText(TreeStructActivity.this, "generate tree success", Toast
                        .LENGTH_LONG).show();
            }

            @Override
            public void onError(Exception e) {

            }
        });

//        TreeNode root = TreeNode.root();
//
//        TreeNode myProfile = new TreeNode(new ItemHolder.IconTreeItem(R.string.ic_person, "My Profile"));
//        TreeNode nan = new TreeNode(new ItemHolder.IconTreeItem(R.string.ic_person, "nan"));
//        TreeNode lu = new TreeNode(new ItemHolder.IconTreeItem(R.string.ic_person, "lu"));
//        TreeNode ling = new TreeNode(new ItemHolder.IconTreeItem(R.string.ic_person, "ling"));
//
//        root.addChildren(myProfile, nan, lu, ling);
//
//        AndroidTreeView treeView = new AndroidTreeView(this, root);
//        treeView.setDefaultAnimation(true);
//        treeView.setDefaultViewHolder(ItemHolder.class);
//        treeView.setDefaultContainerStyle(R.style.TreeNodeStyleDivided, true);
//        treeContainer.addView(treeView.getView());
    }
}
