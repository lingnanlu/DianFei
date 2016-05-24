package io.github.lingnanlu.dianfei.Provider;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.util.Log;

import com.unnamed.b.atv.model.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.github.lingnanlu.dianfei.CallBack;
import io.github.lingnanlu.dianfei.Device;
import io.github.lingnanlu.dianfei.R;

/**
 * Created by rabbit on 5/24/2016.
 */
public class DeviceTree {

    private static final String TAG = "DeviceTree";
    private static SQLiteDatabase db;
    public static TreeNode tree;

    private static Handler handler = new Handler();
    //保存着每一种类型的所有设备, 在生成树时填充
    public static Map<Integer, List<TreeNode>> devicesOfType = new HashMap<>();

//    //用做缓存
//    private static Map<Integer, TreeNode> treeCache = new HashMap<>();
//
//    //查询数据库并生成相应的树
//    public static TreeNode getTreeByLevel(int level) {
//
//        if (treeCache.get(level) != null) {
//            return treeCache.get(level);
//        } else {
//            TreeNode root = buildTreeFromLevel(level);
//            treeCache.put(level, root);
//            return root;
//        }
//    }

//    private static TreeNode buildTreeFromLevel(int level) {
//
//        return null;
//    }

    //生成整个树， 之后由该树得到其它层的树
    public static void generateWholeTree(final CallBack<Void> cb){

        new Thread(new Runnable() {
            @Override
            public void run() {
                if (db == null) {
                    db = SQLiteDatabase.openDatabase("/sdcard/swordDB/DLXT", null, SQLiteDatabase.OPEN_READWRITE);
                }
                tree = TreeNode.root();
                walk(tree);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        cb.onResult(null);
                    }
                });

            }
        }).start();

    }


    private static void walk(TreeNode node) {

        if (node == null) return;

        addSubDevices(node);
        for (TreeNode child : node.getChildren()) {
            walk(child);
        }
        Device device = (Device) node.getValue();

        if (device == null) {
            devicesOfType.put(Device.TYPE_1, node.getChildren());
        } else{
            List<TreeNode> children = devicesOfType.get(device.type + 1);
            if (children == null) {
                children = new ArrayList<>(node.getChildren());
            } else {
                children.addAll(node.getChildren());
            }
            devicesOfType.put(device.type + 1, children);

        }

    }


    private static void innerAddDevices(TreeNode node, Integer... types) {
        String query = null;
        Device device = (Device) node.getValue();
        for (Integer type : types) {
            if (type == Device.TYPE_1) {
                query = "select Name,ID,UpNodeID,UpNodeType from Table1 order by ID";
            } else {
                query = "select Name,ID,UpNodeID,UpNodeType from " +  "Table" + type + " where " +
                        "UpNodeID='"+ device.id +"' and UpNodeType='" + device.type + "' order by " +
                        "UpNodePort";
            }
            Log.d(TAG, "innerAddDevices: " + query);
            Cursor cursor = db.rawQuery(query, null);

            int count = cursor.getCount();
            for(int i = 0; i < count; i++) {
                cursor.moveToNext();
                Device item = new Device();
                item.id = Integer.parseInt(cursor.getString(1));
                item.name = cursor.getString(0);
                item.type = type;

                //// TODO: 2016/5/24 根据设备类型来选择不同的图标
                item.icon = R.string.ic_account_circle;
                TreeNode child = new TreeNode(item);
                node.addChild(child);
            }
        }
    }

    //从其它表中查询对象并做为node的子结点， 具体查询哪些表需要根据node对应的对象所在的表
    private static void addSubDevices(TreeNode node) {

        Device object = (Device) node.getValue();
        String query = null;
        if (object == null) {
            //说明node为根结点
            innerAddDevices(node, Device.TYPE_1);
        } else {
            switch (object.type) {
                case Device.TYPE_1:
                    innerAddDevices(node, Device.TYPE_2);
                    break;
                case Device.TYPE_2:
                    innerAddDevices(node, Device.TYPE_3, Device.TYPE_6);
                    break;
                case Device.TYPE_3:
                    innerAddDevices(node, Device.TYPE_4, Device.TYPE_5, Device.TYPE_6);
                    break;
                case Device.TYPE_6:
                    innerAddDevices(node, Device.TYPE_7);
                    break;
                default:
                    break;
            }
        }


    }

}
