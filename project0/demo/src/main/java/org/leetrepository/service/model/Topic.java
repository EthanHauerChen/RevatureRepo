package org.leetrepository.service.model;

import java.util.HashSet;

public class Topic {
    private int id;
    private String name;
    private HashSet<Problem> associatedProblems;

    public Topic() {
    }

    public Topic(int id, String name, HashSet<Problem> associatedProblems) {
        this.id = id;
        this.name = name;
        this.associatedProblems = associatedProblems;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashSet<Problem> getAssociatedProblems() {
        return associatedProblems;
    }

    public void setAssociatedProblems(HashSet<Problem> associatedProblems) {
        this.associatedProblems = associatedProblems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Topic topic = (Topic) o;

        if (getId() != topic.getId()) return false;
        return getName().equals(topic.getName());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getName().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
