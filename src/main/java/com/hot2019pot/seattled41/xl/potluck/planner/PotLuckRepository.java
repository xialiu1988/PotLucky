package com.hot2019pot.seattled41.xl.potluck.planner;

import org.springframework.data.repository.CrudRepository;

public interface PotLuckRepository extends CrudRepository<PotLuck,Long> {
    public  PotLuck findByCode(String code);
}
