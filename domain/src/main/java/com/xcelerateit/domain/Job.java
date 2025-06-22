package com.xcelerateit.domain;

import com.xcelerateit.domain.model.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "jobs")
public class Job extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String skills;
    private String location;

    public Job() {
    }

    public Job(String title, String description, String skills, String location) {
        this.title = title;
        this.description = description;
        this.skills = skills;
        this.location = location;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public String getSkills() {
        return skills;
    }

    public String getDescription() {
        return description;
    }

    // ✅ Setters - Add these for update functionality
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
