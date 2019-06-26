package com.hot2019pot.seattled41.xl.potluck.planner;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PotluckItemRepository extends CrudRepository<PotluckItem, Long> {
  public void deleteByPotluck(Potluck potluck);
  public List<PotluckItem> findByPotluck(Potluck potluck);
}
