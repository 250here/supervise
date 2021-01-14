package com.supervise.tasksystem.service;

import com.supervise.tasksystem.dao.ExpertTaskDao;
import com.supervise.tasksystem.dao.ExpertTaskItemDao;
import com.supervise.tasksystem.model.ExpertTask;
import com.supervise.tasksystem.model.ExpertTaskItem;
import com.supervise.tasksystem.util.VirtualTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ExpertTaskItemService {
    @Autowired
    ExpertTaskDao expertTaskDao;
    @Autowired
    ExpertTaskItemDao expertTaskItemDao;
    @Autowired
    ExpertTaskService expertTaskService;

    public ExpertTaskItem completeExpertTaskItem(int expertId, int expertTaskItemId, int unqualifiedNumber){        //完成检测项

        Date date = VirtualTime.getDate();
        Optional<ExpertTaskItem> expertTaskItemOptional = expertTaskItemDao.findById(expertTaskItemId);
        ExpertTaskItem expertTaskItem = expertTaskItemOptional.isPresent()?expertTaskItemOptional.get() : null;

        if(expertTaskItem == null){
            return expertTaskItem;
        }
        if(expertTaskItem.getExpertTask().getExpert().getExpertId() != expertId){
            return null;
        }

        expertTaskItem.setFinished(true);
        expertTaskItem.setUnqualifiedNumber(unqualifiedNumber);
        expertTaskItem.setFinishDate(date);
        expertTaskItemDao.save(expertTaskItem);

        ExpertTask expertTask = expertTaskItem.getExpertTask();
        if(expertTaskService.hasUnfinishedItem(expertTask.getExpertTaskId())==false){
            expertTask.setFinished(true);
            expertTaskDao.save(expertTask);
        }
        return expertTaskItem;
    }

}
