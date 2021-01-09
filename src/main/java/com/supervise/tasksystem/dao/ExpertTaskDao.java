package com.supervise.tasksystem.dao;

import com.supervise.tasksystem.model.ExpertTask;
import com.supervise.tasksystem.model.ExpertTaskGroup;
import com.supervise.tasksystem.model.MarketTask;
import com.supervise.tasksystem.model.MarketTaskGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpertTaskDao extends JpaRepository<ExpertTask,Integer> {
    List<ExpertTask> findByExpertTaskGroupAndIsFinishedFalse(ExpertTaskGroup expertTaskGroup);
    List<ExpertTask> findByExpertTaskGroup(ExpertTaskGroup expertTaskGroup);
}
