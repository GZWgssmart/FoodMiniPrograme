package com.gzzy.foodManProgram.common;


public enum TypeEnum {

    EMPTY(0, ""),WORK(1, "必须输入"), PURCHASE(2, "只读"), REFUND(3, "编辑"),OTHER_IN(4,"单选"),OTHER_OUT(5,"多选");


    Integer sfmdrequiredflg;

    String sfmdrequiredname;

    TypeEnum(Integer sfmdrequiredflg, String sfmdrequiredname) {
        this.sfmdrequiredflg = sfmdrequiredflg;
        this.sfmdrequiredname = sfmdrequiredname;
    }

    public Integer getSfmdrequiredflg() {
        return sfmdrequiredflg;
    }

    public void setSfmdrequiredflg(Integer sfmdrequiredflg) {
        this.sfmdrequiredflg = sfmdrequiredflg;
    }

    public String getSfmdrequiredname() {
        return sfmdrequiredname;
    }

    public void setSfmdrequiredname(String sfmdrequiredname) {
        this.sfmdrequiredname = sfmdrequiredname;
    }


    public static TypeEnum getBusinessTypeById(Integer id) {
        for (TypeEnum type : TypeEnum.values()) {
            if (type.getSfmdrequiredflg() == id)
                return type;
        }
        return null;
    }

    public static void main(String arg[]) {

        TypeEnum.getBusinessTypeById(1).getSfmdrequiredname();
    }

}
