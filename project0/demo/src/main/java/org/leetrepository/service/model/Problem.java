package org.leetrepository.service.model;

public class Problem {
    private int id;
    private String name;
    private String description;
    private String difficulty;
    private String url;
    private List<Solution> solutions;
    private List<Topic> topics;

    public Problem() {
    }

    public Problem(int id, String name, String description, String difficulty, String url, List<Solution> solutions, List<Topic> topics) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.difficulty = difficulty;
        this.url = url;
        this.solutions = solutions;
        this.topics = topics;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Solution> getSolutions() {
        return solutions;
    }

    public void setSolutions(List<Solution> solutions) {
        this.solutions = solutions;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Problem problem = (Problem) o;

        return getId() == problem.getId();
    }

    @Override
    public int hashCode() {
        return getId();
    }

    @Override
    public String toString() {
        return "Problem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", url='" + url + '\'' +
                ", solutions=" + solutions +
                ", topics=" + topics +
                '}';
    }
}
