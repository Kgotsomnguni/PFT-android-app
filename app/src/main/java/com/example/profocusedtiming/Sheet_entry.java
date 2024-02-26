package com.example.profocusedtiming;

public class Sheet_entry {
    private String name, imageUrl, description, TaskDate;

    public Sheet_entry() {
    }

    public Sheet_entry(String name, String imageUrl, String description, String dateOfAcquisition) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
        this.TaskDate = dateOfAcquisition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTaskDate() {
        return TaskDate;
    }

    public void setTaskDate(String taskDate) {
        this.TaskDate = taskDate;
    }
}




