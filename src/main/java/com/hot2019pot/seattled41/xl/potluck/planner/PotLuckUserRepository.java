package com.hot2019pot.seattled41.xl.potluck.planner;

import org.springframework.data.repository.CrudRepository;

public interface PotLuckUserRepository extends CrudRepository<PotLuckUser,Long> {
    public  PotLuckUser findByUsername(String username);
}
