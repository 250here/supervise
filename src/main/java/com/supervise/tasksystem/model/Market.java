package com.supervise.tasksystem.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="MARKET")
@Getter
@Setter
public class Market {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int marketId;
    @Column(length = 50)
    String marketName;
    @Column(length = 50)
    String principal;
    @OneToMany(mappedBy = "MARKET_TASK_ID")
    List<MarketTask> marketTasks;
}
