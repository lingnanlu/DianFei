package io.github.lingnanlu.dianfei;

/**
 * Created by rabbit on 5/24/2016.
 */
public class Device {

    /*
     * 系统中有8种类型设备, 对应着8个表, 每一个类型的设备可能管理着多种类型的其它设备, 设备之间的关系如下
     * 1 -> 2
     * 2 -> 3, 6
     * 3 -> 4, 5, 6
     * 6 -> 7
     */

  //  public static final int TYPE_0 = 0;             //占位设备, 只用来标识root结点
    public static final int TYPE_1 = 1;
    public static final int TYPE_2 = 2;
    public static final int TYPE_3 = 3;
    public static final int TYPE_4 = 4;
    public static final int TYPE_5 = 5;
    public static final int TYPE_6 = 6;
    public static final int TYPE_7 = 7;
    public static final int TYPE_8 = 8;

    public int type;
    public int id;
    public String name;
    public int icon;

    public Device() {
    }

    public Device(int level, int id, String name, int icon) {
        this.type = level;
        this.id = id;
        this.name = name;
        this.icon = icon;
    }
}
