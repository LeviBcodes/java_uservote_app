package com.uservotes.model;
import java.util.ArrayList;
import java.util.List;

public class SimpleIdeaDAO implements IdeaDAO {
    private List<Idea> ideas;

    public SimpleIdeaDAO() {
        ideas = new ArrayList<>();
    }

    @Override
    public boolean add(Idea idea) {
        return false;
    }

    @Override
    public List<Idea> findAll() {
        return null;
    }
}
