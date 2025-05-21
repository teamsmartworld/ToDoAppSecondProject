package model;

import java.time.LocalDateTime;

public class Todo {
    private int id;
    private String title;
    private String description;
    private LocalDateTime deadline;
    private boolean done;
    private Person assignee;

    public Todo(int id, String title, String description, LocalDateTime deadline) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.done = false;
    }

    // Getters and Setters
    // (similar to Person class)
}
