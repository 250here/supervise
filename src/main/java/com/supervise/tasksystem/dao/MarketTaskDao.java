package com.supervise.tasksystem.dao;

import com.supervise.tasksystem.model.Expert;
import com.supervise.tasksystem.model.Market;
import com.supervise.tasksystem.model.MarketTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarketTaskDao extends JpaRepository<MarketTask,Integer> {
    List<MarketTask> findByMarketTaskGroupAndIsFinishedIsFalse(int marketTaskGroupId);
}
