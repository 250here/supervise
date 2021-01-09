package com.supervise.tasksystem.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="EXPERT_TASK_ITEM")
@Getter
@Setter
public class ExpertTaskItem {
     @Id
     @GeneratedValue(strategy= GenerationType.IDENTITY)
     int expertTaskItemId;
     int unqualifiedNumber;
     boolean isFinished;
     Date finishDate;
     @ManyToOne(cascade=CascadeType.REFRESH)
     @JoinColumn(name = "EXPERT_TASK_ID")
     ExpertTask expertTask;
}

