package com.supervise.tasksystem.dao;

import com.supervise.tasksystem.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpertTaskItemDao extends JpaRepository<ExpertTaskItem,Integer> {
    List<ExpertTaskItem> findByExpertTaskAndIsFinishedFalse(ExpertTask expertTask);
    List<ExpertTaskItem> findByExpertTask(ExpertTask expertTask);
}
