package com.hot2019pot.seattled41.xl.potluck.planner;

import org.springframework.data.repository.CrudRepository;

public interface PotluckRepository extends CrudRepository<Potluck,Long> {
    public Potluck findByCode(String code);
}
