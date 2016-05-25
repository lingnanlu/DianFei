package io.github.lingnanlu.dianfei;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.lingnanlu.dianfei.holder.ItemHolder;

public class TreeGraphActivity extends AppCompatActivity implements TreeNode.TreeNodeClickListener{


    AndroidTreeView deviceTree;

    @Bind(R.id.tree_containter) FrameLayout treeContainer;
    @Bind(R.id.tv_device_name) TextView tvDeviceName;
    @Bind(R.id.tv_device_location) TextView tvDeviceLocation;
    @Bind(R.id.tv_up_device_type) TextView tvUpDeviceType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_graph);
        ButterKnife.bind(this);

        DeviceTree.generateWholeTree(new CallBack<TreeNode>() {
            @Override
            public void onPre() {
                Toast.makeText(TreeGraphActivity.this, "正在生成树", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResult(TreeNode root) {

                deviceTree = new AndroidTreeView(TreeGraphActivity.this, root);
                deviceTree.setDefaultAnimation(true);
                deviceTree.setDefaultViewHolder(ItemHolder.class);
                deviceTree.setDefaultContainerStyle(R.style.TreeNodeStyleCustom);
                deviceTree.setDefaultNodeClickListener(TreeGraphActivity.this);
                treeContainer.addView(deviceTree.getView());
                Map<Integer, List<TreeNode>> map = DeviceTree.devicesOfType;
                Toast.makeText(TreeGraphActivity.this, "generate tree success", Toast
                        .LENGTH_LONG).show();
            }

            @Override
            public void onError(Exception e) {

            }
        });

    }

    @Override
    public void onClick(TreeNode node, Object value) {
        DeviceAbstract device = (DeviceAbstract) value;
        DeviceTree.getDeviceDetail(device.id, device.type, new CallBack<DeviceDetail>() {
            @Override
            public void onPre() {

            }

            @Override
            public void onResult(DeviceDetail result) {
                tvDeviceLocation.setText(result.location);
                tvDeviceName.setText(result.name);
                tvUpDeviceType.setText(result.upNodeType);
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }
}
