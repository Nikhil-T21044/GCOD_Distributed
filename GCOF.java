/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gcod_algo;

import java.util.List;
import java.util.Set;

/**
 *
 * @author Triphil
 */
public class GCOF {

    public static double calcGod(double[][] e8Matrix, int numConcepts, int index) {
        double sum = 0;

        for (int i = 0; i < numConcepts; i++) {
            sum += e8Matrix[index][i];
        }
//        System.out.println(sum +"  "+ index);
        return sum / numConcepts;
    }

    public static double calcGcof(int objectIndex, List<Set<Integer>[]> concept, int no_of_obj, double[][] e8Matrix) {
        double mx = 0;
        double sum = 0;
        int numConcepts = concept.size();

        for (int i = 0; i < numConcepts; i++) {
            if (concept.get(i)[1].contains(objectIndex)) {
                mx += 1;
                sum += calcGod(e8Matrix, numConcepts, i) * (1 - Math.pow(concept.get(i)[1].size() / (double) no_of_obj, 1.0 / 3.0));
            }
        }

        return sum / (mx != 0 ? mx : 1);
    }

    public static double[] allGcof(List<Set<Integer>[]> concept, int no_of_obj, double[][] e8Matrix) {
        double[] gcof = new double[no_of_obj];

        for (int i = 0; i < no_of_obj; i++) {
            Utility.showCompletionPercentage("GCOF : ", no_of_obj, i);
            gcof[i] = calcGcof(i, concept, no_of_obj, e8Matrix);
        }

        return gcof;
    }
    
    
    public static doubleMatrix allGcof(List<Set<Integer>[]> concept, int no_of_obj, doubleMatrix e8Matrix) {
        doubleMatrix gcof = new doubleMatrix(1,no_of_obj,"gcof");

        for (int i = 0; i < no_of_obj; i++) {
            Utility.showCompletionPercentage("GCOF : ", no_of_obj, i);
            gcof.set(0, i, calcGcof(i, concept, no_of_obj, e8Matrix) );
        }

        return gcof;
    }
    
    public static double calcGcof(int objectIndex, List<Set<Integer>[]> concept, int no_of_obj, doubleMatrix e8Matrix) {
        double mx = 0;
        double sum = 0;

        for (int i = 0; i < concept.size(); i++) {
            if (concept.get(i)[1].contains(objectIndex)) {
                mx += 1;
                sum += calcGod(e8Matrix, concept, i) * (1 - Math.pow(concept.get(i)[1].size() / (double) no_of_obj, 1.0 / 3.0));
            }
        }

        return sum / (mx != 0 ? mx : 1);
    }
    
    public static double calcGod(doubleMatrix e8Matrix, List<Set<Integer>[]> concept, int index) {
        int numObjs = concept.size();
        double sum = 0;

        for (int i = 0; i < numObjs; i++) {
            sum += e8Matrix.get(index, i);
        }

        return sum / numObjs;
    }
}
