/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gcod_algo;

import java.util.ArrayList;
import java.util.List;
import org.roaringbitmap.RoaringBitmap;

/**
 *
 * @author Triphil
 */
class GCDWorker extends Thread {
    
    final int no_of_obj;
    final List<int[]> concepts;
    final int start_index;
    final int end_index;
    int[][] gcd;
    int[] gcdArr;
    List<RoaringBitmap> cncptMatrixCompressed;

    public GCDWorker(int no_of_obj, List<int[]> concepts, int start_index, int end_index, int[][] gcd) {
        this.no_of_obj = no_of_obj;
        this.concepts = concepts;
        this.start_index = start_index;
        this.end_index = end_index;
        this.gcd = gcd;
        this.cncptMatrixCompressed = null;
    }
    
    public GCDWorker(int no_of_obj, ArrayList<RoaringBitmap> cncptMatrixCompressed, int start_index, int end_index, int[][] gcd) {
        this.no_of_obj = no_of_obj;
        this.cncptMatrixCompressed = cncptMatrixCompressed;
        this.start_index = start_index;
        this.end_index = end_index;
        this.gcd = gcd;
        this.concepts = null;
    }
    
        public GCDWorker(int no_of_obj, ArrayList<RoaringBitmap> cncptMatrixCompressed, int start_index, int end_index, int[] gcdArr) {
        this.no_of_obj = no_of_obj;
        this.cncptMatrixCompressed = cncptMatrixCompressed;
        this.start_index = start_index;
        this.end_index = end_index;
        this.gcdArr = gcdArr;
        this.concepts = null;
    }
    

    @Override
    public void run() {
        System.out.println("GCD Worker Running");
        if(cncptMatrixCompressed != null)
            if(gcdArr != null)
                gcdArrCompressed();
            else
                gcdCompressed();
        else
            gcd();
    }
    
    
    private void gcd(){
                for (int i = this.start_index; i <= this.end_index; i++) {
            Utility.showCompletionPercentage("GCD Completed " + this.getId() +" : ", this.end_index - this.start_index, i-this.start_index);
            for (int j = i + 1; j < this.no_of_obj; j++) {
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
    }
    
    //Roaring Bitmap
    public void gcdCompressed() {
        System.out.println("GCD Worker Running");
        for (int i = this.start_index; i <= this.end_index; i++) {
            Utility.showCompletionPercentage("GCD Completed " + this.getId() +" : ", this.end_index - this.start_index, i-this.start_index);
            for (int j = i + 1; j < this.no_of_obj; j++) {
                int d = 0;
                for (RoaringBitmap c : this.cncptMatrixCompressed) {
                    boolean iInConcept = false, jInConcept = false;
                    if (c.contains(i)) {
                        iInConcept = true;
                    }
                    if (c.contains(j)) {
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
    }

    public void gcdArrCompressed() {
        System.out.println("GCD Worker Running");
        
        int N = this.no_of_obj;
        
        int s_i = N - 1 - (int) Math.floor((-1 + Math.sqrt((2 * N + 1) * (2 * N + 1) - 8 * (this.start_index + 1))) / 2);
        int s_j = this.start_index - s_i * (2 * N - s_i - 1) / 2;
        if(s_i == s_j) s_j += 1;
        
        int e_i = N - 1 - (int) Math.floor((-1 + Math.sqrt((2 * N + 1) * (2 * N + 1) - 8 * (this.end_index + 1))) / 2);
        int e_j = this.start_index - e_i * (2 * N - e_i - 1) / 2;

        //first row
        for (int j = s_j; j < this.no_of_obj; j++) {
                int d = 0;
                for (RoaringBitmap c : this.cncptMatrixCompressed) {
                    boolean iInConcept = false, jInConcept = false;
                    if (c.contains(s_i)) {
                        iInConcept = true;
                    }
                    if (c.contains(j)) {
                        jInConcept = true;
                    }
                    
                    if (iInConcept != jInConcept) {
                        d += 1;
                    }
                }
                int index = s_i*this.no_of_obj - ( s_i * (s_i-1) / 2 ) + j - s_i;
                gcdArr[index] = d;
        }
        
        //mid Matrix
        for (int i = s_i+1; i <= e_i-1; i++) {
            Utility.showCompletionPercentage("GCD Completed " + this.getId() +" : ", e_i - s_i, i-s_i);
            for (int j = i + 1; j < this.no_of_obj; j++) {
                int d = 0;
                for (RoaringBitmap c : this.cncptMatrixCompressed) {
                    boolean iInConcept = false, jInConcept = false;
                    if (c.contains(i)) {
                        iInConcept = true;
                    }
                    if (c.contains(j)) {
                        jInConcept = true;
                    }
                    
                    if (iInConcept != jInConcept) {
                        d += 1;
                    }
                }
                int index = i*this.no_of_obj - ( i * (i-1) / 2 ) + j - i;
                gcdArr[index] = d;
            }
        }
        
        //last row
        for (int j = e_i+1; j <= e_j; j++) {
                int d = 0;
                for (RoaringBitmap c : this.cncptMatrixCompressed) {
                    boolean iInConcept = false, jInConcept = false;
                    if (c.contains(e_i)) {
                        iInConcept = true;
                    }
                    if (c.contains(j)) {
                        jInConcept = true;
                    }
                    
                    if (iInConcept != jInConcept) {
                        d += 1;
                    }
                }
                int index = e_i*this.no_of_obj - ( e_i * (e_i-1) / 2 ) + j - e_i;
                gcdArr[index] = d;
        }
         
    }

}

