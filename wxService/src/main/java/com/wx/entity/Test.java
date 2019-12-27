package com.wx.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "test")
@Entity
public class Test {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

}
