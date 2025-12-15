package org.leetrepository;

import org.leetrepository.repository.DAO.ProblemDAO;
import org.leetrepository.repository.entities.ProblemEntity;
import org.leetrepository.util.ConnectionHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        ProblemDAO problemDAO = new ProblemDAO();

        //test findById
//        try {
//            Optional<ProblemEntity> entity = problemDAO.findById(1);
//            System.out.println(entity);
//            entity = problemDAO.findById(99999); //should return Optional.empty
//            System.out.println(entity);
//        }
//        catch (SQLException e) {
//            System.out.println("SQL Exception");
//            e.printStackTrace();
//        }

        //test create
//        try {
//            ProblemEntity problemEntity = new ProblemEntity();
//            problemEntity.setId(371);
//            problemEntity.setName("Sum of Two Integers");
//            problemEntity.setDescription("Given two integers a and b, return the sum of the two integers without using the operators + and -.");
//            problemEntity.setDifficulty("Medium");
//            problemEntity.setUrl("https://leetcode.com/problems/sum-of-two-integers");
//            int id = problemDAO.create(problemEntity);
//            int id = problemDAO.create(problemEntity); //should throw SQLException
//            System.out.println("created ProblemEntity with id: " + id);
//            System.out.println("find ProblemEntity by id " + id + ": " + problemDAO.findById(id));
//
//
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }

        //test findAll
//        try {
//            List<ProblemEntity> problemEntities = problemDAO.findAll();
//            for (ProblemEntity p : problemEntities) {
//                System.out.println(p);
//            }
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//        }

        //test updateById
//        try {
//            ProblemEntity p = new ProblemEntity();
//            p.setId(1);
//            p.setName("Two Sum");
//            p.setDescription("Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.\n" +
//                    "\n" +
//                    "You may assume that each input would have exactly one solution, and you may not use the same element twice.\n" +
//                    "\n" +
//                    "You can return the answer in any order."
//            );
//            p.setDifficulty("Unspecified");
//            p.setUrl("https://leetcode.com/problemset");
//            System.out.println(problemDAO.updateById(p));
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }

        //test deleteById
//        try {
//            //create the entity to be deleted
//            ProblemEntity p = new ProblemEntity(
//                    99999,
//                    "test problem",
//                    "description",
//                    "Unspecified",
//                    "blah"
//            );
//            System.out.println("Successfully created entity with id " + problemDAO.create(p));
//
//            //delete the entity, verify
//            System.out.println(problemDAO.deleteById(p.getId()));
//            System.out.println(problemDAO.findById(p.getId()));
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
