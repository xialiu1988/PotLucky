package com.hot2019pot.seattled41.xl.potluck.planner;

import org.springframework.data.repository.CrudRepository;

//Ed liked the Postico App that showed the visualization of the Postgres tables and relationships
/**
 * DB interaction for PotluckUser
 */
public interface PotluckUserRepository extends CrudRepository<PotluckUser,Long> {
    /**
     * Retrieve PotluckUser by username
     * @param username String, username
     * @return PotluckUser, user with usernanme
     */
    public PotluckUser findByUsername(String username);
}
