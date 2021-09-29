package com.uservotes.model;

import com.github.slugify.Slugify;

import java.io.IOException;
import java.util.Objects;

public class Idea {
    private String slug;
    private String title;
    private String creator;

    public Idea(String title, String creator) {
        this.title = title;
        this.creator = creator;
        try {
            Slugify slugify = new Slugify();
            slug = slugify.slugify(title);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getTitle() {
        return title;
    }

    public String getCreator() {
        return creator;
    }

    public String getSlug() {
        return slug;
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
