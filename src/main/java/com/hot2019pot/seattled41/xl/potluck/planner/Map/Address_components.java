package com.hot2019pot.seattled41.xl.potluck.planner.Map;

import java.util.List;

public class Address_components {
    private String long_name;

    private String short_name;

    private List<String> types;

    public void setLong_name(String long_name){
        this.long_name = long_name;
    }
    public String getLong_name(){
        return this.long_name;
    }
    public void setShort_name(String short_name){
        this.short_name = short_name;
    }
    public String getShort_name(){
        return this.short_name;
    }
    public void setTypes(List<String> types){
        this.types = types;
    }
    public List<String> getTypes(){
        return this.types;
    }

}
