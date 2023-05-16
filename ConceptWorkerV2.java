/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gcod_algo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Triphil
 */
public class ConceptWorkerV2 extends Thread {
    
    final int[][] context;
    Set<List<Integer>> extentSet;
    Set<List<Integer>> intentSet;
    List<List<Integer>> concepts;
    List<int[]> cncptMatrix;
//    List<Integer> cncpt_no_obj;
    int type;
    int start_index;
    int end_index;
    
    
    
    public ConceptWorkerV2(int[][] context, Set<List<Integer>> intentSet, Set<List<Integer>> extentSet, int type, int st, int end){
        this.context = context;
        this.extentSet = extentSet;
        this.intentSet = intentSet;
        
        this.type = type;
        this.start_index = st;
        this.end_index = end; 
        
    }
    
    
    @Override
    public void run(){
        if(type == 1)
            granuleConceptRow(start_index, end_index);
        else
            granuleConceptcolom(start_index, end_index);
    }
    
    public void granuleConceptRow(int start_index_r, int end_index_r) {
        Utility.printStamp("Row Concept Started");
        concepts = new ArrayList<>();
        cncptMatrix = new ArrayList<>();
//        cncpt_no_obj = new ArrayList<>();
        // find row concepts
        for (int in = start_index_r; in <= end_index_r; in++) {
//            Utility.showCompletionPercentage("Row Concept completed : ", context.length, in);
            int[] context1 = context[in];
            List<Integer> intent = new ArrayList<>();
            for (int col = 0; col < context1.length; col++) {
                if (context1[col] == 1) {
                    intent.add(col);
                }
            }

            if (intent.isEmpty()) {
                continue;
            }
            
            synchronized(intent){
                if( intentSet.contains(intent) )
                    continue;
                intentSet.add(intent);
            }

            List<Integer> extent = new ArrayList<>();
            int[] isPresent = new int[context.length];
            for (int r = 0; r < context.length; r++) {
                boolean inExtent = true;
                for (int i : intent) {
                    if (context[r][i] != 1) {
                        inExtent = false;
                        break;
                    }
                }
                if (inExtent) {
                    extent.add(r);
                    isPresent[r] = 1;
                }
            }

            cncptMatrix.add(isPresent);
            concepts.add(extent);
        }
        Utility.printStamp("Row concepts completed");
    }

    public void granuleConceptcolom( int start_index_c, int end_index_c) {
        Utility.printStamp("Colom Concepts Started");
        concepts = new ArrayList<>();
        cncptMatrix = new ArrayList<>();
        // find column concepts
        for (int col = start_index_c; col <= end_index_c; col++) {
            List<Integer> extent = new ArrayList<>();
            int[] isPresent = new int[context.length];
            for (int row = 0; row < context.length; row++) {
                if (context[row][col] == 1) {
                    extent.add(row);
                    isPresent[row] = 1;
                }
            }
            
            if (extent.isEmpty()) {
                continue;
            }
            
            synchronized(extent){
                if(extentSet.contains(extent))
                    continue;
                extentSet.add(extent);
            }

            cncptMatrix.add(isPresent);
            concepts.add(extent);
        }

        Utility.printStamp("Colom concepts completed");
    }

    
}
