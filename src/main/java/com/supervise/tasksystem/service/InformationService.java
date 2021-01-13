package com.supervise.tasksystem.service;

import com.supervise.tasksystem.dao.ExpertDao;
import com.supervise.tasksystem.dao.MarketDao;
import com.supervise.tasksystem.model.Expert;
import com.supervise.tasksystem.model.Market;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InformationService {
    @Autowired
    ExpertDao expertDao;
    @Autowired
    MarketDao marketDao;

    public List<Market> getAllMarkets(){       //获得所有市场
        return marketDao.findAll();
    }
    public List<Expert> getAllExperts(){           //获得所有专家
        return expertDao.findAll();
    }
}
