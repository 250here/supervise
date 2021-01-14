package com.supervise.tasksystem;

import com.supervise.tasksystem.commandline.CommandLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;


//命令行是权宜之计，因此没有进行设计
//@Component
public class TaskSystemCommandLineApplication implements CommandLineRunner {
    @Autowired
    CommandLine commandLine;
    public static void main(String[] args) {
        SpringApplication.run(TasksystemApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        System.out.println("检查系统开始运行");
        while (true){
            try{
                commandLine.run();
                break;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
