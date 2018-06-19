package com.example.SpringPSS.dtos;

import com.example.SpringPSS.entities.Event;

import java.util.ArrayList;

public class EventsWrapper {
    private ArrayList<Event> events;

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }
}
