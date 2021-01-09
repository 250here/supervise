package com.supervise.tasksystem;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

public class TaskSystemCommandLineApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(TasksystemApplication.class, args);
    }
    public void run(String... args) throws Exception {
        System.out.println("adki----------------------");
    }
}
