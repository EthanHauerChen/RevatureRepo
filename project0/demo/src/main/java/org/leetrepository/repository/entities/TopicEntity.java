package org.leetrepository.repository.entities;

import java.util.Objects;

public class TopicEntity {
    private int id;
    private String name;

    public TopicEntity() {
    }

    public TopicEntity(String name) {
        this.name = name;
    }

    public TopicEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TopicEntity topic = (TopicEntity) o;

        if (getId() != topic.getId()) return false;
        return getName().equals(topic.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "TopicEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
