package com.example.SpringPSS.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter@Setter
@Entity
@Table(name = "event")

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

//    @Column(nullable = false, length = 40)
//    private String type;
//
//    @Column(nullable = false, length = 40)
//    private String feeding;

    @ManyToMany(mappedBy = "events")
    private Set<User> users = new HashSet<>();

    public Event() {
        super();
    }

    public Event(final String title, final String date, final String agenda){
        super();
        this.title = title;
        this.date = date;
        this.agenda = agenda;
    }


}
