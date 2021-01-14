package com.supervise.tasksystem.commandline;

import com.supervise.tasksystem.commandline.role.ExpertCommandLine;
import com.supervise.tasksystem.commandline.role.MarketCommandLine;
import com.supervise.tasksystem.commandline.role.SupervisorCommandLine;
import com.supervise.tasksystem.util.VirtualTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CommandLine {
    @Autowired
    MarketCommandLine marketCommandLine;
    @Autowired
    ExpertCommandLine expertCommandLine;
    @Autowired
    SupervisorCommandLine supervisorCommandLine;
    public  void run(){
        String outputStr="";
        outputStr+="---系统主页---\n" +
                "选择角色:\n" +
                "0 退出\n" +
                "1 市场\n" +
                "2 专家\n" +
                "3 监管局\n" +
                "4 修改系统时间\n";
        while (true){
            System.out.println(outputStr);
            int num=CommandLineInput.chooseNumber(new int[]{0,1,2,3,4});
            switch (num){
                case 0:return;
                case 1: marketCommandLine.run();break;
                case 2: expertCommandLine.run();break;
                case 3: supervisorCommandLine.run();break;
                case 4: changeTime();break;
                default: throw new RuntimeException();
            }
        }

    }
    private void changeTime(){
        System.out.println("请输入要修改的时间:");
        Date date=CommandLineInput.nextDate();
        VirtualTime.setDate(date);
    }
}
