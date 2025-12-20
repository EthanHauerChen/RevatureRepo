package org.leetrepository.service.model;

import java.nio.ByteBuffer;

public class Solution {
    private int id;
    private Problem problem;
    private String name;
    private String description;
    private ByteBuffer solutionCode;

    public Solution() {
    }

    public Solution(int id, Problem problem, String name, String description, ByteBuffer solutionCode) {
        this.id = id;
        this.problem = problem;
        this.name = name;
        this.description = description;
        this.solutionCode = solutionCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
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

        Solution solution = (Solution) o;

        return getId() == solution.getId();
    }

    @Override
    public int hashCode() {
        return getId();
    }

    @Override
    public String toString() {
        return "Solution{" +
                "id=" + id +
                ", problem=" + problem +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", solutionCode=" + solutionCode +
                '}';
    }
}
