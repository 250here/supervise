package com.supervise.tasksystem.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="EXPERT_TASK")
@Getter
@Setter
public class ExpertTask {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int ExpertTaskId;
    boolean isFinished;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "EXPERT_TASK_GROUP_ID")
    ExpertTaskGroup expertTaskGroup;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "EXPERT_ID")
    Expert expert;
    @OneToMany(mappedBy = "expertTaskItemId")
    List<ExpertTaskItem> expertTaskItems;
}
