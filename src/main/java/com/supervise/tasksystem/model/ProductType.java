package com.supervise.tasksystem.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="PRODUCT_TYPE")
@Getter
@Setter
public class ProductType {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int productTypeId;
    @Column(length = 50)
    String productTypeName;
}
