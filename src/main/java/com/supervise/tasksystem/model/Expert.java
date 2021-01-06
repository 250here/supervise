package com.supervise.tasksystem.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="EXPERT")
@Getter
@Setter
public class Expert {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int expertId;
    @Column(length = 50)
    String expertName;
    @Column(length = 101)
    String expertPassword;
}
