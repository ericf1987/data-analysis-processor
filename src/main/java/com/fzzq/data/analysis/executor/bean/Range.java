package com.fzzq.data.analysis.executor.bean;

/**
 * 需要参与计算的主体维度信息
 * Created by fengye on 2017/8/7.
 */
public class Range {

    public static final String BROKER = "broker";

    public static final String ACCOUNT = "account";

    public static final String DEPARTMENT = "department";

    public static final String USER = "user";

    private String id;

    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Range(String id, String name){
        this.id = id;
        this.name = name;
    }

    public Range broker(String id){
        return new Range(id, Range.BROKER);
    }

    public Range account(String id){
        return new Range(id, Range.ACCOUNT);
    }

    public Range department(String id){
        return new Range(id, Range.DEPARTMENT);
    }

    public Range user(String id){
        return new Range(id, Range.USER);
    }

    @Override
    public String toString() {
        return "Range{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {

        if(obj == this) return true;
        if(obj == null || getClass() != (obj.getClass())) return false;

        Range range = (Range)obj;

        if(!id.equals(range.id)) return false;

        return name != null ? name.equals(range.name) : range.name == null;

    }
}
