package com.supervise.tasksystem.dao;

import com.supervise.tasksystem.model.MarketTask;
import com.supervise.tasksystem.model.MarketTaskGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarketTaskGroupDao extends JpaRepository<MarketTaskGroup,Integer> {

}
