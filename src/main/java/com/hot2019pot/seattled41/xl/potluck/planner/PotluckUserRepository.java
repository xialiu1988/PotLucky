package com.hot2019pot.seattled41.xl.potluck.planner;

import org.springframework.data.repository.CrudRepository;

public interface PotluckUserRepository extends CrudRepository<PotluckUser,Long> {
    public PotluckUser findByUsername(String username);
}
