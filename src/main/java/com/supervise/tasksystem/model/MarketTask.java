package com.supervise.tasksystem.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="MARKET_TASK")
@Getter
@Setter
public class MarketTask {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "MARKET_TASK_ID")
    int marketTaskId;
    boolean isFinished;
    @ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name = "MARKET_TASK_GROUP_ID")
    MarketTaskGroup marketTaskGroup;
    @ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name = "MARKET_ID")
    Market market;
    @OneToMany(mappedBy = "marketTaskItemId")
    List<MarketTaskItem> marketTaskItems;
}
