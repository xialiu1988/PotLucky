package com.hot2019pot.seattled41.xl.potluck.planner.Map;

import java.util.List;

public class MyPojo {
    private List<Results> results;

    private String status;

    public void setResults(List<Results> results){
        this.results = results;
    }
    public List<Results> getResults(){
        return this.results;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return this.status;
    }

}
