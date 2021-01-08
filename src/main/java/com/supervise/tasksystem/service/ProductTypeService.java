package com.supervise.tasksystem.service;

import com.supervise.tasksystem.dao.ProductTypeDao;
import com.supervise.tasksystem.model.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductTypeService {
    @Autowired
    ProductTypeDao productTypeDao;

}
