package org.leetrepository.repository.entities;

import java.nio.ByteBuffer;
import java.util.Objects;

public class SolutionEntity {
    private int problemId;
    private String name;
    private String description;
    private ByteBuffer solutionCode;

    public SolutionEntity() {
    }
    public SolutionEntity(int problemId, String name, String description) {
        this.problemId = problemId;
        this.name = name;
        this.description = description;
    }
    public SolutionEntity(int problemId, String name, String description, ByteBuffer solutionCode) {
        this.problemId = problemId;
        this.name = name;
        this.description = description;
        this.solutionCode = solutionCode;
    }

    public int getProblemId() {
        return problemId;
    }

    public void setProblemId(int problemId) {
        this.problemId = problemId;
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

    public ByteBuffer getSolutionCode() {
        return solutionCode;
    }

    public void setSolutionCode(ByteBuffer solutionCode) {
        this.solutionCode = solutionCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SolutionEntity that = (SolutionEntity) o;

        return getProblemId() == that.getProblemId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(problemId);
    }
}
