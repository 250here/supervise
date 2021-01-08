package com.supervise.tasksystem.service;

import com.supervise.tasksystem.dao.MarketTaskDao;
import com.supervise.tasksystem.dao.MarketTaskItemDao;
import com.supervise.tasksystem.model.MarketTask;
import com.supervise.tasksystem.model.MarketTaskItem;
import com.supervise.tasksystem.model.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarketTaskItemService {
    @Autowired
    MarketTaskDao marketTaskDao;
    @Autowired
    MarketTaskItemDao marketTaskItemDao;





}
