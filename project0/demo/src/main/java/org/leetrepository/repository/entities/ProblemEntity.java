package org.leetrepository.repository.entities;

import java.util.Objects;

public class ProblemEntity {
    private int id;
    private String name;
    private String description;
    private String difficulty;
    private String url;

    public ProblemEntity() {}

    public ProblemEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ProblemEntity(int id, String name, String description, String difficulty, String url) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.difficulty = difficulty;
        this.url = url;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProblemEntity that = (ProblemEntity) o;

        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ProblemEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
