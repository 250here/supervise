package com.supervise.tasksystem.commandline.role;

import com.supervise.tasksystem.commandline.CommandLineInput;
import com.supervise.tasksystem.commandline.InfoSets;
import com.supervise.tasksystem.service.MarketTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MarketCommandLine {
    private int marketId=-1;
    @Autowired
    MarketTaskService marketTaskService;
    @Autowired
    InfoSets infoSets;
    public void run(){
        String outputStr="";
        outputStr+="请输入ID";
        System.out.println(outputStr);
        marketId= CommandLineInput.chooseNumber(infoSets.getMarketIds());
        outputStr="";
        outputStr+="---市场主页---\n" +
                "选择功能:\n" +
                "0 退出\n" +
                "1 查看待完成任务\n" +
                "2 完成一项任务\n";
        while (true){
            System.out.print(outputStr);
            int command= CommandLineInput.chooseNumber(new int[]{0,1,2,3,4,5,6});
            switch (command){
                case 0:return;
                case 1:showUnfinishedTasks();break;
                case 2:finishOneTask();break;
                default:throw new RuntimeException();
            }
        }
    }
    private void showUnfinishedTasks(){

    }
    private void finishOneTask(){

    }
}
