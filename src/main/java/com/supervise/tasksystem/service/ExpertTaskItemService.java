package com.supervise.tasksystem.service;

import com.supervise.tasksystem.dao.ExpertTaskDao;
import com.supervise.tasksystem.dao.ExpertTaskItemDao;
import com.supervise.tasksystem.model.ExpertTask;
import com.supervise.tasksystem.model.ExpertTaskItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ExpertTaskItemService {
    @Autowired
    ExpertTaskDao expertTaskDao;
    @Autowired
    ExpertTaskItemDao expertTaskItemDao;
    @Autowired
    ExpertTaskService expertTaskService;

    public void completeExpertTaskItem(int expertTaskItemId, Date date){        //完成检测项
        ExpertTaskItem expertTaskItem = expertTaskItemDao.findById(expertTaskItemId).get();
        expertTaskItem.setFinished(true);
        expertTaskItem.setFinishDate(date);
        expertTaskItemDao.save(expertTaskItem);

        ExpertTask expertTask = expertTaskItem.getExpertTask();
        if(expertTaskService.hasUnfinishedItem(expertTask.getExpertTaskId())==false){
            expertTask.setFinished(true);
            expertTaskDao.save(expertTask);
        }
    }
}
