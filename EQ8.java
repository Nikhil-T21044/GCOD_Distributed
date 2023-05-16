/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gcod_algo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Triphil
 */
public class EQ8 {

    static double eq8Value(int[][] gcdMatrix, List<Set<Integer>[]> conceptList, int concept1Index, int concept2Index) {
        // Initialize the sum to zero
        double sumValue = 0.0;

        // Get the two concepts for which we want to calculate the Eq. 8 value
        Set<Integer> concept1 = conceptList.get(concept1Index)[1];
        Set<Integer> concept2 = conceptList.get(concept2Index)[1];

        // Iterate through all the combination objects in the concepts
        for (int i : concept1) {
            for (int j : concept2) {
                sumValue += gcdMatrix[i][j];
            }
        }

        // Return the Eq. 8 value for the two concepts
        return sumValue / (concept1.size() * concept2.size());
    }

    public static double[][] eq8MatrixMethod(int[][] gcdMatrix, List<Set<Integer>[]> concept) {
        int numConcepts = concept.size();
        double[][] eq8Matrix = new double[numConcepts][numConcepts];

        for (int i = 0; i < numConcepts; i++) {
            Utility.showCompletionPercentage("eq8Matrix Completed : ", numConcepts, i);
            for (int j = 0; j <= i; j++) {
                eq8Matrix[i][j] = eq8Value(gcdMatrix, concept, i, j);
                eq8Matrix[j][i] = eq8Matrix[i][j];
            }
        }
        return eq8Matrix;
    }

    public static double[] eq8ArrMethod(int[][] gcdMatrix, List<Set<Integer>[]> concept) {
        int numConcepts = concept.size();
        double[] eq8Matrix = new double[numConcepts];
        for (int i = 0; i < numConcepts; i++) {
            eq8Matrix[i] = 0.0;
        }
        for (int i = 0; i < numConcepts; i++) {
            Utility.showCompletionPercentage("eq8Matrix Completed : ", numConcepts, i);
            for (int j = 0; j < i; j++) {
                double val = eq8Value(gcdMatrix, concept, i, j);
                eq8Matrix[i] += val;
                eq8Matrix[j] += val;
            }
            eq8Matrix[i] += eq8Value(gcdMatrix, concept, i, i);
        }

        return eq8Matrix;
    }

    public static doubleMatrix eq8MatrixMethod(intMatrix gcdMatrix, List<Set<Integer>[]> concept, String initials) {
        int numConcepts = concept.size();
        doubleMatrix eq8Matrix = new doubleMatrix(numConcepts, numConcepts, initials);
        for (int i = 0; i < numConcepts; i++) {
            Utility.showCompletionPercentage("eq8Matrix Completed : ", numConcepts, i);
            for (int j = 0; j <= i; j++) {
                double val = eq8Value(gcdMatrix, concept, i, j);
                eq8Matrix.set(i, j, val);
                eq8Matrix.set(j, i, val);
            }
        }
        Utility.showCompletionPercentage("eq8Matrix Completed : ", numConcepts, numConcepts);
        return eq8Matrix;
    }

    static double eq8Value(intMatrix gcdMatrix, List<Set<Integer>[]> conceptList, int concept1Index, int concept2Index) {
        // Initialize the sum to zero
        double sumValue = 0.0;

        // Get the two concepts for which we want to calculate the Eq. 8 value
        Set<Integer> concept1 = conceptList.get(concept1Index)[1];
        Set<Integer> concept2 = conceptList.get(concept2Index)[1];

        // Iterate through all the combination objects in the concepts
        for (int i : concept1) {
            for (int j : concept2) {
                sumValue += gcdMatrix.get(i, j);
            }
        }

        // Return the Eq. 8 value for the two concepts
        return sumValue / (concept1.size() * concept2.size());
    }
    
    public static double[] eq8ArrMethodv2(int[][] gcdMatrix, List<Set<Integer>[]> concept, int no_of_worker){
        int numConcepts = concept.size();
        double[] e8Matrix = new double[numConcepts];
        
        E8Worker[] workers =  new E8Worker[no_of_worker];
        
        int range = numConcepts/no_of_worker;
        
        int start_index = 0;
        int end_index = range-1;
        
        for(int i = 0; i < no_of_worker; i++){
            E8Worker w = new E8Worker( gcdMatrix, concept, start_index, end_index, e8Matrix);
            w.start();
            workers[i] = w;
            start_index = end_index+1;
            end_index += range;
            end_index = numConcepts <= end_index ? numConcepts-1 : end_index;
        }
        
        for(int i = 0; i < no_of_worker; i++){
            try {
                workers[i].join();
            } catch (InterruptedException ex) {
                Logger.getLogger(GCD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return e8Matrix;
    }
    
    
    public static double[] eq8ArrMethodv3(int[][] gcdMatrix, ArrayList<List<Integer>> concept, int no_of_worker){
        int numConcepts = concept.size();
        double[] e8Matrix = new double[numConcepts];
        
        E8Worker[] workers =  new E8Worker[no_of_worker];
        
        int range = numConcepts/no_of_worker;
        
        int start_index = 0;
        int end_index = range-1;
        
        for(int i = 0; i < no_of_worker; i++){
            E8Worker w = new E8Worker( gcdMatrix, concept, start_index, end_index, e8Matrix);
            w.start();
            workers[i] = w;
            start_index = end_index+1;
            end_index += range;
            end_index = numConcepts <= end_index ? numConcepts-1 : end_index;
        }
        
        for(int i = 0; i < no_of_worker; i++){
            try {
                workers[i].join();
            } catch (InterruptedException ex) {
                Logger.getLogger(GCD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return e8Matrix;
    }

    public static double[] eq8ArrMethodv4(GCDArrObj gcdMatrix, ArrayList<List<Integer>> concept, int no_of_worker){
        int numConcepts = concept.size();
        double[] e8Matrix = new double[numConcepts];
        
        E8Worker[] workers =  new E8Worker[no_of_worker];
        
        int range = numConcepts/no_of_worker;
        
        int start_index = 0;
        int end_index = range-1;
        
        for(int i = 0; i < no_of_worker; i++){
            E8Worker w = new E8Worker( gcdMatrix, concept, start_index, end_index, e8Matrix);
            w.start();
            workers[i] = w;
            start_index = end_index+1;
            end_index += range;
            end_index = numConcepts <= end_index ? numConcepts-1 : end_index;
        }
        
        for(int i = 0; i < no_of_worker; i++){
            try {
                workers[i].join();
            } catch (InterruptedException ex) {
                Logger.getLogger(GCD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return e8Matrix;
    }

}
