package com.supervise.tasksystem.dao;

import com.supervise.tasksystem.model.Market;
import com.supervise.tasksystem.model.MarketTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketDao extends JpaRepository<Market,Integer> {
}
