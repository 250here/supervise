package com.supervise.tasksystem.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="EXPERT_TASK_GROUP")
@Getter
@Setter
public class ExpertTaskGroup {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int expertTaskGroupId;
    Date deadline;
    @Column(length = 50)
    String taskDescription;
    @OneToMany(mappedBy = "expertTaskGroup")
    List<ExpertTask> expertTasks;
}
