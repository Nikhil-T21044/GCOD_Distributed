/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gcod_algo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Triphil
 */
public class E8Worker extends Thread {
    
    final int[][] gcdMatrix;
    final List<Set<Integer>[]> concept;
    final ArrayList<List<Integer>> concept_ext;
    final GCDArrObj gcdArr; 
    double[] eq8Matrix;
    int start_index;
    int end_index;
    
    public E8Worker(int[][] gcdMatrix, List<Set<Integer>[]> concept, int start_index, int end_index, double[] eq8Matrix){
        this.gcdMatrix = gcdMatrix;
        this.concept = concept;
        this.eq8Matrix = eq8Matrix;
        this.start_index = start_index;
        this.end_index = end_index;
        this.concept_ext = null;
        this.gcdArr = null;
    }
    
    public E8Worker(int[][] gcdMatrix, ArrayList<List<Integer>> concept, int start_index, int end_index, double[] eq8Matrix){
        this.gcdMatrix = gcdMatrix;
        this.concept = null;
        this.eq8Matrix = eq8Matrix;
        this.start_index = start_index;
        this.end_index = end_index;
        this.concept_ext = concept;
        this.gcdArr = null;
    }
    
    public E8Worker(GCDArrObj gcdArr, ArrayList<List<Integer>> concept, int start_index, int end_index, double[] eq8Matrix){
        this.gcdMatrix = null;
        this.concept = null;
        this.eq8Matrix = eq8Matrix;
        this.start_index = start_index;
        this.end_index = end_index;
        this.concept_ext = concept;
        this.gcdArr = gcdArr;
    }

    @Override
    public void run() {
        if(concept_ext == null)
            e8Runner();
        else if(gcdArr != null)
            e8RunnerLiteArr();
        else
            e8RunnerLite();
    
    }
    
    private void e8Runner(){
        System.out.println("E8 Worker Running");
        for (int i = this.start_index; i <= this.end_index; i++) {
            for (int j = 0; j < i; j++) {
                double val = eq8Value(i, j);
                eq8Matrix[i] += val;
                eq8Matrix[j] += val;
            }
            eq8Matrix[i] += eq8Value(i, i);
        }
    }
    
    private void e8RunnerLite(){
        System.out.println("E8 Worker Running");
        for (int i = this.start_index; i <= this.end_index; i++) {
            for (int j = 0; j < i; j++) {
                double val = eq8ValueLite(i, j);
                eq8Matrix[i] += val;
                eq8Matrix[j] += val;
            }
            eq8Matrix[i] += eq8ValueLite(i, i);
        }
    }
    
    private void e8RunnerLiteArr(){
        System.out.println("E8 Worker Running");
        for (int i = this.start_index; i <= this.end_index; i++) {
            for (int j = 0; j < i; j++) {
                double val = eq8ValueLiteArr(i, j);
                eq8Matrix[i] += val;
                eq8Matrix[j] += val;
            }
            eq8Matrix[i] += eq8ValueLiteArr(i, i);
        }
    }

    private double eq8Value(int concept1Index, int concept2Index) {
        // Initialize the sum to zero
        double sumValue = 0.0;

        // Get the two concepts for which we want to calculate the Eq. 8 value
        Set<Integer> concept1 = concept.get(concept1Index)[1];
        Set<Integer> concept2 = concept.get(concept2Index)[1];

        // Iterate through all the combination objects in the concepts
        for (int i : concept1) {
            for (int j : concept2) {
                sumValue += gcdMatrix[i][j];
            }
        }

        // Return the Eq. 8 value for the two concepts
        return sumValue / (concept1.size() * concept2.size());
    }

    private double eq8ValueLite(int concept1Index, int concept2Index) {
        // Initialize the sum to zero
        double sumValue = 0.0;

        // Get the two concepts for which we want to calculate the Eq. 8 value
        List<Integer> concept1 = concept_ext.get(concept1Index);
        List<Integer> concept2 = concept_ext.get(concept2Index);

        // Iterate through all the combination objects in the concepts
        for (int i : concept1) {
            for (int j : concept2) {
                sumValue += gcdMatrix[i][j];
            }
        }

        // Return the Eq. 8 value for the two concepts
        return sumValue / (concept1.size() * concept2.size());
    }
    
    private double eq8ValueLiteArr(int concept1Index, int concept2Index) {
        // Initialize the sum to zero
        double sumValue = 0.0;

        // Get the two concepts for which we want to calculate the Eq. 8 value
        List<Integer> concept1 = concept_ext.get(concept1Index);
        List<Integer> concept2 = concept_ext.get(concept2Index);

        // Iterate through all the combination objects in the concepts
        for (int i : concept1) {
            for (int j : concept2) {
                sumValue += this.gcdArr.get(i, j);
            }
        }

        // Return the Eq. 8 value for the two concepts
        return sumValue / (concept1.size() * concept2.size());
    }

}
