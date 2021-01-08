package com.supervise.tasksystem.dao;

import com.supervise.tasksystem.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarketTaskDao extends JpaRepository<MarketTask,Integer> {
    List<MarketTask> findByMarketTaskGroupAndIsFinishedFalse(MarketTaskGroup marketTaskGroup);
    List<MarketTask> findByMarketTaskGroup(MarketTaskGroup marketTaskGroup);
}
