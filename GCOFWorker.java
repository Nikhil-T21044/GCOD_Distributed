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
public class GCOFWorker extends Thread {

    List<Set<Integer>[]> concept;
    int no_of_obj;
    double[] e8Matrix;
    double[] all_gcof;
    int start_index;
    int end_index;
    ArrayList<List<Integer>> concept_ext;

    public GCOFWorker(List<Set<Integer>[]> concept, int no_of_obj, double[] e8Matrix, int start_index, int end_index, double[] all_gcof) {
        this.concept = concept;
        this.e8Matrix = e8Matrix;
        this.no_of_obj = no_of_obj;
        this.all_gcof = all_gcof;
        this.start_index = start_index;
        this.end_index = end_index;
    }

    public GCOFWorker(ArrayList<List<Integer>> concept, int no_of_obj, double[] e8Matrix, int start_index, int end_index, double[] all_gcof) {
        this.concept_ext = concept;
        this.e8Matrix = e8Matrix;
        this.no_of_obj = no_of_obj;
        this.all_gcof = all_gcof;
        this.start_index = start_index;
        this.end_index = end_index;
    }

    @Override
    public void run() {
        System.out.println("GCOF Worker Running");

        if (concept_ext != null) {
            for (int i = this.start_index; i <= this.end_index; i++) {
                all_gcof[i] = calcGcofLite(i, no_of_obj);
            }
        } else {
            for (int i = this.start_index; i <= this.end_index; i++) {
                all_gcof[i] = calcGcof(i, no_of_obj);
            }
        }

    }

    public double calcGod(double[] e8Matrix, int concept_size, int index) {
        return e8Matrix[index] / concept_size;
    }

    public double calcGcof(int objectIndex, int no_of_obj) {
        double mx = 0;
        double sum = 0;
        int concept_size = concept.size();
        for (int i = 0; i < concept_size; i++) {
            if (concept.get(i)[1].contains(objectIndex)) {
                mx += 1;
                sum += calcGod(e8Matrix, concept_size, i) * (1 - Math.pow(concept.get(i)[1].size() / (double) no_of_obj, 1.0 / 3.0));
            }
        }
        return sum / (mx != 0 ? mx : 1);
    }

    public double calcGcofLite(int objectIndex, int no_of_obj) {
        double mx = 0;
        double sum = 0;
        int concept_size = concept_ext.size();
        for (int i = 0; i < concept_size; i++) {
            if (concept_ext.get(i).contains(objectIndex)) {
                mx += 1;
                sum += calcGod(e8Matrix, concept_size, i) * (1 - Math.pow(concept_ext.get(i).size() / (double) no_of_obj, 1.0 / 3.0));
            }
        }
        return sum / (mx != 0 ? mx : 1);
    }
}
