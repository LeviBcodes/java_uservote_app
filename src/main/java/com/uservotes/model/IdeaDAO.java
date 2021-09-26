package com.uservotes.model;
import java.util.List;

public interface IdeaDAO {
    boolean add(Idea idea);

    List<Idea> findAll();
}
