package com.zhangyh.management.admin.excel.t;

/**
 * @author zhangyh
 * @Date 2023/4/21 12:28
 * @desc
 */
/**
 * 三级联动指定插入位置，eg: B为指定第二列插入，C为指定第三列插入
 */
public enum CharStatusEnum {

    A('A',1), B('B',2), C('C',3),
    D('D',4),E('E',5),F('F',6),
    G('G',7),H('H',8),I('I',9),J('J',10);
    private char character;
    private int value;
    //构造方法
    CharStatusEnum(char character, int value) {
        this.character=character;
        this.value=value;
    }

    //自定义方法
    public void setCharacter(char character){
        this.character=character;
    }
    public void setValue(int value){
        this.value=value;
    }

    public char getCharacter() {
        return character;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "CharStatusEnum{" +
                "character=" + character +
                ", value=" + value +
                '}';
    }
}
