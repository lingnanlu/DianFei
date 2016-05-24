package io.github.lingnanlu.dianfei;

/**
 * Created by rabbit on 5/24/2016.
 */
public class Item {

    public int tableNum;               //该Item所处的表
    public String id;
    public String name;
    public int icon;

    public Item() {
    }

    public Item(int level, int id, String name, int icon) {
        this.tableNum = level;
        this.id = id;
        this.name = name;
        this.icon = icon;
    }
}
