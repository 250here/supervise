package com.supervise.tasksystem.service;

import com.supervise.tasksystem.dao.ExpertTaskGroupDao;
import com.supervise.tasksystem.dao.MarketTaskGroupDao;
import com.supervise.tasksystem.dao.ProductTypeDao;
import com.supervise.tasksystem.model.ExpertTaskGroup;
import com.supervise.tasksystem.model.MarketTask;
import com.supervise.tasksystem.model.MarketTaskGroup;
import com.supervise.tasksystem.model.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductTypeService {
    @Autowired
    ProductTypeDao productTypeDao;
    @Autowired
    InformationService informationService;
    @Autowired
    MarketTaskGroupDao marketTaskGroupDao;
    @Autowired
    ExpertTaskGroupDao expertTaskGroupDao;

    public int getUnqualityNumberOfType(Date startTime, Date endTime, int productId){
        List<MarketTaskGroup> marketTaskGroupList = marketTaskGroupDao.findAll();
        List<ExpertTaskGroup> expertTaskGroupList = expertTaskGroupDao.findAll();

        for (MarketTaskGroup group : marketTaskGroupList){

        }
        return 0;
    }

    public boolean duringDate(Date startTime, Date endTime, Date date){
return false;
    }
}
