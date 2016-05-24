package io.github.lingnanlu.dianfei.Provider;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.unnamed.b.atv.model.TreeNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.github.lingnanlu.dianfei.Constants;
import io.github.lingnanlu.dianfei.Item;
import io.github.lingnanlu.dianfei.R;

/**
 * Created by rabbit on 5/24/2016.
 */
public class DataProvider {

    private static final String TAG = "DataProvider";
    static SQLiteDatabase db = SQLiteDatabase.openDatabase("/sdcard/swordDB/DLXT", null, SQLiteDatabase.OPEN_READWRITE);

    //用做缓存
    private static Map<Integer, TreeNode> treeCache = new HashMap<>();

    private static TreeNode wholeTree;

    //查询数据库并生成相应的树
    public static TreeNode getTreeByLevel(int level) {

        if (treeCache.get(level) != null) {
            return treeCache.get(level);
        } else {
            TreeNode root = buildTreeFromLevel(level);
            treeCache.put(level, root);
            return root;
        }
    }

    //生成整个树， 之后由该树得到其它层的树
    public static void generateWholeTree(){

        wholeTree = new TreeNode(null);

        walk(wholeTree);
        
    }
    private static TreeNode buildTreeFromLevel(int level) {

        return null;
    }

    private static void walk(TreeNode node) {

        if (node == null) return;
        addSubNode(node);
        for (TreeNode child : node.getChildren()) {
            walk(child);
        }
    }


    private static void innerAddSubNode(List<Integer> tableNums, TreeNode node) {
        String query = null;
        Item object = (Item) node.getValue();
        for (Integer tableNum : tableNums) {
            if (tableNum == Constants.TABLE_1) {
                query = "select Name,ID,UpNodeID,UpNodeType from Table1 order by ID";
            } else {
                query = "select Name,ID,UpNodeID,UpNodeType from" +  "Table" + tableNum + "where UpNodeID='"+object.id +"' and UpNodeType='" + tableNum + "' order by UpNodePort";
            }
            Log.d(TAG, "innerAddSubNode: " + query);
            Cursor cursor = db.rawQuery(query, null);
            for(int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                Item item = new Item();
                item.id = cursor.getString(1);
                item.name = cursor.getString(0);
                item.tableNum = tableNum;
                item.icon = R.string.ic_account_circle;
                TreeNode child = new TreeNode(item);
                node.addChild(child);
            }
        }

    }
    //从其它表中查询对象并做为node的子结点， 具体查询哪些表需要根据node对应的对象所在的表
    private static void addSubNode(TreeNode node) {

        Item object = (Item) node.getValue();
        String query = null;
        if (object == null) {
            //说明node为根结点，只为作树根
            innerAddSubNode(Arrays.asList(1), node);
        } else {
            switch (object.tableNum) {
                case Constants.TABLE_2:
                    innerAddSubNode(Arrays.asList(3, 6), node);
                    break;
                case Constants.TABLE_3:
                    innerAddSubNode(Arrays.asList(4, 5, 6), node);
                    break;
                case Constants.TABLE_6:
                    innerAddSubNode(Arrays.asList(7), node);
                    break;
                default:
                    break;
            }
        }


    }

}
