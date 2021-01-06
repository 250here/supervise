package com.supervise.tasksystem.dao;

import com.supervise.tasksystem.model.Expert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpertDao extends JpaRepository<Expert,Integer> {
}
