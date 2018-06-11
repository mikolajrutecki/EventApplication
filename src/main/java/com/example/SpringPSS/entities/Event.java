package com.example.SpringPSS.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter@Setter
@Entity
@Table(name = "events")
public class Event {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false, unique = true, length = 40)
    private String title;

    @Column(nullable = false, length = 40)
    private String date;

    @Column(nullable = false, length = 200)
    private String agenda;

    @Column(nullable = false, length = 40)
    private String type;

    @Column(nullable = false, length = 40)
    private String feeding;

}
