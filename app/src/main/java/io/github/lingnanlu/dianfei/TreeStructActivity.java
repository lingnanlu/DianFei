package io.github.lingnanlu.dianfei;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.lingnanlu.dianfei.Provider.DataProvider;
import io.github.lingnanlu.dianfei.holder.ItemHolder;

public class TreeStructActivity extends AppCompatActivity {

    public static final int LEVEL_0 = 0;
    public static final int LEVEL_1 = 1;
    public static final int LEVEL_2 = 2;
    public static final int LEVEL_3 = 3;
    public static final int LEVEL_4 = 4;
    public static final int LEVEL_5 = 5;
    public static final int LEVEL_6 = 6;
    public static final int LEVEL_7 = 7;
    public static final int DETAIL = -1;


    @Bind(R.id.tree_containter)
    FrameLayout treeContainer;

    @Bind(R.id.detail_container)
    LinearLayout detailContainer;

    Handler mHander = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == DETAIL) {
                //show detail info in detail container;
            } else {
                showLevel(msg.what);
            }
        }
    };

    private void showLevel(int level) {
        TreeNode root = DataProvider.getTreeByLevel(level);
        AndroidTreeView treeView = new AndroidTreeView(this, root);
        treeContainer.addView(treeView.getView());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_struct);
        ButterKnife.bind(this);

        showLevel(0);

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
