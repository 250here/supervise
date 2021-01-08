package com.supervise.tasksystem.dao;
import com.supervise.tasksystem.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeDao  extends JpaRepository<ProductType,Integer> {
}
