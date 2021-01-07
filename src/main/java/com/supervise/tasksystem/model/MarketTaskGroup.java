package com.supervise.tasksystem.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="MARKET_TASK_GROUP")
@Getter
@Setter
public class MarketTaskGroup {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int marketTaskGroupId;
    Date deadline;
    @Column(length = 50)
    String taskDescription;
    @OneToMany(mappedBy = "MARKET_TASK_GROUP_ID")
    List<MarketTask> marketTasks;
}
