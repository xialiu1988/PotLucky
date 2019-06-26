package com.hot2019pot.seattled41.xl.potluck.planner;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * DB interaction for PotluckItem
 */
public interface PotluckItemRepository extends CrudRepository<PotluckItem, Long> {
  /**
   * Deletes PotluckItem by Potluck
   * @param potluck Potluck, event the item is attached to
   */
  public void deleteByPotluck(Potluck potluck);

  /**
   * Finds all PotluckItems associated to a single Potluck object
   * @param potluck Potluck, event
   * @return List, PotluckItems
   */
  public List<PotluckItem> findByPotluck(Potluck potluck);
}
