package com.uservotes.model;

import java.util.Objects;

public class Idea {
    private String title;
    private String creator;

    public Idea(String title, String creator) {
        this.title = title;
        this.creator = creator;
    }

    public String getTitle() {
        return title;
    }

    public String getCreator() {
        return creator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Idea idea = (Idea) o;
        return Objects.equals(title, idea.title) && Objects.equals(creator, idea.creator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, creator);
    }
}
