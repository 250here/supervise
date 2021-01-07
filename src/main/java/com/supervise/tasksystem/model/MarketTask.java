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
    int marketTaskId;
    boolean isFinished;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "MARKET_TASK_GROUP_ID")
    MarketTaskGroup marketTaskGroup;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "MARKET_ID")
    Market market;
    @OneToMany(mappedBy = "MARKET_TASK_ITEM_ID")
    List<MarketTaskItem> marketTaskItems;
}
