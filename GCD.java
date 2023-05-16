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
import org.roaringbitmap.RoaringBitmap;
//import static Utility.
/**
 *
 * @author Triphil
 */
public class GCD {

    public static int[][] gcdMatrix(int no_of_obj, List<Set<Integer>[]> concepts) {
        int n = no_of_obj;
        int[][] gcd = new int[n][n];

        for (int i = 0; i < n; i++) {
            Utility.showCompletionPercentage("GCD Completed : ", n, i);
            for (int j = i + 1; j < n; j++) {
                int d = 0;
                for (Set<Integer>[] c : concepts) {
                    boolean iInConcept = false, jInConcept = false;
                    Set<Integer> extent = c[1];
                    if (extent.contains(i)) {
                        iInConcept = true;
                    }
                    if (extent.contains(j)) {
                        jInConcept = true;
                    }

                    if (iInConcept != jInConcept) {
                        d += 1;
                    }
                }
                gcd[i][j] = d;
                gcd[j][i] = d;
            }
        }

        return gcd;
    }
    
    
    public static int[][] gcdMatrixv2(int no_of_obj, List<int[]> concepts) {
        int n = no_of_obj;
        int[][] gcd = new int[n][n];

        for (int i = 0; i < n; i++) {
            Utility.showCompletionPercentage("GCD Completed : ", n, i);
            for (int j = i + 1; j < n; j++) {
                int d = 0;
                for (int[] c : concepts) {
                    boolean iInConcept = false, jInConcept = false;
                    if (c[i] == 1) {
                        iInConcept = true;
                    }
                    if (c[j] == 1) {
                        jInConcept = true;
                    }

                    if (iInConcept != jInConcept) {
                        d += 1;
                    }
                }
                gcd[i][j] = d;
                gcd[j][i] = d;
            }
        }

        return gcd;
    }
    public static intMatrix gcdMatrix(int no_of_obj, List<Set<Integer>[]> concepts, String initials) {
        int n = no_of_obj;
        intMatrix gcd = new intMatrix(n, n, initials);
        for (int i = 0; i < n; i++) {
            gcd.set(i, i, 0);
            Utility.showCompletionPercentage("GCD Completed : ", n, i);
            for (int j = i + 1; j < n; j++) {
                int d = 0;
                for (Set<Integer>[] c : concepts) {
                    boolean iInConcept = false, jInConcept = false;
                    Set<Integer> extent = c[1];
                    if (extent.contains(i)) {
                        iInConcept = true;
                    }
                    if (extent.contains(j)) {
                        jInConcept = true;
                    }

                    if (iInConcept != jInConcept) {
                        d += 1;
                    }
                }
                gcd.set(i, j, d);
                gcd.set(j, i, d);
            }
        }

        return gcd;
    }

    public static int[][] gcdMatrixv3(int no_of_obj, List<int[]> concepts, int no_of_worker){
        int n = no_of_obj;
        int[][] gcd = new int[n][n];
        
        GCDWorker[] workers =  new GCDWorker[no_of_worker];
        
        int range = no_of_obj/no_of_worker;
        
        int start_index = 0;
        int end_index = range-1;
        
        for(int i = 0; i < no_of_worker; i++){
            GCDWorker w = new GCDWorker(no_of_obj, concepts, start_index, end_index, gcd);
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
        
        return gcd;
    }

    public static int[][] gcdMatrixv4(int no_of_obj, ArrayList<RoaringBitmap> concepts, int no_of_worker){
        int n = no_of_obj;
        int[][] gcd = new int[n][n];
        
        GCDWorker[] workers =  new GCDWorker[no_of_worker];
        
        int range = no_of_obj/no_of_worker;
        
        int start_index = 0;
        int end_index = range-1;
        
        for(int i = 0; i < no_of_worker; i++){
            GCDWorker w = new GCDWorker(no_of_obj, concepts, start_index, end_index, gcd);
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
        
        return gcd;
    }

}
