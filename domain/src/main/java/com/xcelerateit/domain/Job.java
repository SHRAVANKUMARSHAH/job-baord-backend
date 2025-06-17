package com.xcelerateit.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "jobs")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private Long id;
    private String title;
    private String description;
    private String skills;
    private String location;

    public Job() {}  // Default constructor

    public Job(String title, String description, String skills, String location) {

        this.title = title;
        this.description = description;
        this.skills = skills;
        this.location = location;
    }

    // Getters and Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; } // ← यह नई लाइन जोड़ी गई है

    public String getTitle() { return title; }
    public String getLocation() { return location; }
    public String getSkills() { return skills; }
    public String getDescription() { return description; }
}
