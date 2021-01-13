package com.supervise.tasksystem.commandline.role;

import com.supervise.tasksystem.commandline.CommandLineInput;
import com.supervise.tasksystem.service.ExpertTaskGroupService;
import com.supervise.tasksystem.service.MarketTaskGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SupervisorCommandLine {
    @Autowired
    ExpertTaskGroupService expertTaskGroupService;
    @Autowired
    MarketTaskGroupService marketTaskGroupService;
    public void run(){
        String outputStr="";
        outputStr+="---监管局主页---\n" +
                "选择功能:\n" +
                "0 退出" +
                "1 显示所有任务\n" +
                "2 向一组市场发起监管任务\n" +
                "3 向一组专家发起监管任务\n" +
                "4 查看某个农贸产品类别在某个时间范围内的总的不合格数\n" +
                "5 查看某个市场的评估\n" +
                "6 查看某个专家的评估\n" +
                "";
        while (true){
            System.out.print(outputStr);
            int command= CommandLineInput.chooseNumber(new int[]{0,1,2,3,4,5,6});
            switch (command){
                case 0:return;
                case 1:showAllTasks();break;
                case 2:createMarketTask();break;
                case 3:createExpertTask();break;
                case 4:showUnqualifIedNumberOfOneProductType();break;
                case 5:showGradeOfOneMarket();break;
                case 6:showGradeOfOneExpert();break;
                default:throw new RuntimeException();
            }
        }
    }
    private void showAllTasks(){
        
    }
    private void createMarketTask(){
        int id=CommandLineInput.nextInt();
    }
    private void createExpertTask(){

    }
    private void showUnqualifIedNumberOfOneProductType(){

    }
    private void showGradeOfOneMarket(){

    }
    private void showGradeOfOneExpert(){

    }
}
