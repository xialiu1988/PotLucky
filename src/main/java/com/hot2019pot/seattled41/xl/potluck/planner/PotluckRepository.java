package com.hot2019pot.seattled41.xl.potluck.planner;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;


/**
 * DB interaction for Potluck
 */
public interface PotluckRepository extends CrudRepository<Potluck,Long> {
    /**
     * Find a Potluck object by its sharecode
     * @param code String, sharecode that is unique
     *             and associated to Potluck object
     * @return Potluck, Potluck object
     */
    public Potluck findByCode(String code);
}
