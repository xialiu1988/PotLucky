package com.hot2019pot.seattled41.xl.potluck.planner.Map;

public class Bounds {
    private Northeast northeast;

    private Southwest southwest;

    public void setNortheast(Northeast northeast){
        this.northeast = northeast;
    }
    public Northeast getNortheast(){
        return this.northeast;
    }
    public void setSouthwest(Southwest southwest){
        this.southwest = southwest;
    }
    public Southwest getSouthwest(){
        return this.southwest;
    }
}
