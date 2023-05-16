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
public class ProcessedGCOF {
    public static double calcGod(double[] e8Matrix, List<Set<Integer>[]> concept, int index) {
//        System.out.println(e8Matrix[index] + "   "+index);
        return e8Matrix[index] / concept.size();
    }

    public static double calcGcof(int objectIndex, List<Set<Integer>[]> concept, int no_of_obj, double[] e8Matrix) {
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

    public static double[] allGcof(List<Set<Integer>[]> concept, int no_of_obj, double[] e8Matrix) {
        double[] gcof = new double[no_of_obj];

        for (int i = 0; i < no_of_obj; i++) {
            Utility.showCompletionPercentage("GCOF : ", no_of_obj, i);
            gcof[i] = calcGcof(i, concept, no_of_obj, e8Matrix);
        }

        return gcof;
    }
    
    public static double[] allGcofv2(List<int[]> concept_matrix, List<Integer> concept_no_obj, int no_of_obj, double[] e8Matrix) {
        double[] gcof = new double[no_of_obj];

        for (int i = 0; i < no_of_obj; i++) {
            Utility.showCompletionPercentage("GCOF : ", no_of_obj, i);
            gcof[i] = calcGcofv2(i, concept_matrix, concept_no_obj, no_of_obj, e8Matrix);
        }

        return gcof;
    }
    public static double calcGcofv2(int objectIndex, List<int[]> concept, List<Integer> concept_no_obj, int no_of_obj, double[] e8Matrix) {
        double mx = 0;
        double sum = 0;

        for (int i = 0; i < concept.size(); i++) {
            if (concept.get(i)[objectIndex] == 1) {
                mx += 1;
                sum += calcGodv2(e8Matrix, concept.size(), i) * (1 - Math.pow(concept_no_obj.get(i) / (double) no_of_obj, 1.0 / 3.0));
            }
        }

        return sum / (mx != 0 ? mx : 1);
    }
    
    public static double calcGodv2(double[] e8Matrix, int concept_size, int index) {
        return e8Matrix[index] / concept_size;
    }
    
    public static double[] allGcofv3(List<Set<Integer>[]> concept, int no_of_obj, double[] e8Matrix, int no_of_worker){
        double[] gcof = new double[no_of_obj];
        
//        int no_of_worker = 4;
        
        GCOFWorker[] workers =  new GCOFWorker[no_of_worker];
        
        int range = no_of_obj/no_of_worker;
        
        int start_index = 0;
        int end_index = range-1;
        
        for(int i = 0; i < no_of_worker; i++){
            GCOFWorker w = new GCOFWorker( concept, no_of_obj, e8Matrix, start_index, end_index, gcof);
            w.start();
            workers[i] = w;
            start_index = end_index+1;
            end_index += range;
            end_index = no_of_obj <= end_index ? no_of_obj-1 : end_index;
        }
        
        for(int i = 0; i < no_of_worker; i++){
            try {
                workers[i].join();
            } catch (InterruptedException ex) {
                Logger.getLogger(GCD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return gcof;
    }
 
    public static double[] allGcofv4(ArrayList<List<Integer>> concept, int no_of_obj, double[] e8Matrix, int no_of_worker){
        double[] gcof = new double[no_of_obj];
        
//        int no_of_worker = 4;
        
        GCOFWorker[] workers =  new GCOFWorker[no_of_worker];
        
        int range = no_of_obj/no_of_worker;
        
        int start_index = 0;
        int end_index = range-1;
        
        for(int i = 0; i < no_of_worker; i++){
            GCOFWorker w = new GCOFWorker( concept, no_of_obj, e8Matrix, start_index, end_index, gcof);
            w.start();
            workers[i] = w;
            start_index = end_index+1;
            end_index += range;
            end_index = no_of_obj <= end_index ? no_of_obj-1 : end_index;
        }
        
        for(int i = 0; i < no_of_worker; i++){
            try {
                workers[i].join();
            } catch (InterruptedException ex) {
                Logger.getLogger(GCD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return gcof;
    }

}
