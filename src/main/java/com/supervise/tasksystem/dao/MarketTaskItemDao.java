package com.supervise.tasksystem.dao;

import com.supervise.tasksystem.model.MarketTask;
import com.supervise.tasksystem.model.MarketTaskItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface MarketTaskItemDao extends JpaRepository<MarketTaskItem,Integer> {
    List<MarketTaskItem> findByMarketTaskAndIsFinishedFalse(MarketTask marketTask);
    List<MarketTaskItem> findByMarketTask(MarketTask marketTask);
}
