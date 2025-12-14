package org.leetrepository.repository.entities;

import java.util.Objects;

public class ProblemTopicEntity {
    private int problemId;
    private int topicId;

    public ProblemTopicEntity() {
    }

    public ProblemTopicEntity(int problemId, int topicId) {
        this.problemId = problemId;
        this.topicId = topicId;
    }

    public int getProblemId() {
        return problemId;
    }

    public void setProblemId(int problemId) {
        this.problemId = problemId;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProblemTopicEntity that = (ProblemTopicEntity) o;

        if (getProblemId() != that.getProblemId()) return false;
        return getTopicId() == that.getTopicId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(problemId, topicId);
    }

    @Override
    public String toString() {
        return "ProblemTopicEntity{" +
                "problemId=" + problemId +
                ", topicId=" + topicId +
                '}';
    }
}
