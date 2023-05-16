/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gcod_algo;

/**
 *
 * @author Triphil
 */
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.roaringbitmap.RoaringBitmap;

public class GranuleConcept1 {

//    TODO Only extent is used we can remove intent from result
    public static List<Set<Integer>[]> granuleConcept1(int[][] context) {
        List<Set<Integer>[]> concepts = new ArrayList<>();
        Set<String> intentSet = new HashSet<>();
        Set<String> extentSet = new HashSet<>();

        // find row concepts
        for (int in = 0; in < context.length; in++) {
            Utility.showCompletionPercentage("Row Concept completed : ", context.length, in);
            int[] context1 = context[in];
            Set<Integer> intent = new HashSet<>();
            for (int col = 0; col < context1.length; col++) {
                if (context1[col] == 1) {
                    intent.add(col);
                }
            }

            if (intent.isEmpty() || intentSet.contains(Arrays.toString(intent.toArray()))) {
                continue;
            }

            Set<Integer> extent = new HashSet<>();
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
                }
            }

            extentSet.add(Arrays.toString(extent.toArray()));
            intentSet.add(Arrays.toString(intent.toArray()));

            Set<Integer>[] concept = new Set[2];
            concept[0] = intent;
            concept[1] = extent;
            concepts.add(concept);
        }

        System.out.println("Row concepts completed");
        // find column concepts
        for (int col = 0; col < context[0].length; col++) {
            Utility.showCompletionPercentage("Colom Concept Completed :", context[0].length, col);
            Set<Integer> extent = new HashSet<>();
            for (int row = 0; row < context.length; row++) {
                if (context[row][col] == 1) {
                    extent.add(row);
                }
            }

            if (extent.isEmpty() || extentSet.contains(Arrays.toString(extent.toArray()))) {
                continue;
            }

            Set<Integer> intent = new HashSet<>();
            for (int c = 0; c < context[0].length; c++) {
                boolean inIntent = true;
                for (int i : extent) {
                    if (context[i][c] != 1) {
                        inIntent = false;
                        break;
                    }
                }
                if (inIntent) {
                    intent.add(c);
                }
            }

            extentSet.add(Arrays.toString(extent.toArray()));

            Set<Integer>[] concept = new Set[2];
            concept[0] = intent;
            concept[1] = extent;
            concepts.add(concept);
        }

        System.out.println("Colom concepts completed");
        return concepts;
    }

    public static List<Set<Integer>[]> granuleConceptv2(int[][] context) {
        List<Set<Integer>[]> concepts = new ArrayList<>();
        Set<Set<Integer>> intentSet = new HashSet<>();
        Set<Set<Integer>> extentSet = new HashSet<>();

        // find row concepts
        for (int in = 0; in < context.length; in++) {
            Utility.showCompletionPercentage("Row Concept completed : ", context.length, in);
            int[] context1 = context[in];
            Set<Integer> intent = new HashSet<>();
            for (int col = 0; col < context1.length; col++) {
                if (context1[col] == 1) {
                    intent.add(col);
                }
            }

            if (intent.isEmpty() || intentSet.contains(intent)) {
                continue;
            }

            Set<Integer> extent = new HashSet<>();
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
                }
            }

            extentSet.add(extent);
            intentSet.add(intent);

            Set<Integer>[] concept = new Set[2];
            concept[0] = intent;
            concept[1] = extent;
            concepts.add(concept);
        }

        System.out.println("Row concepts completed");
        // find column concepts
        for (int col = 0; col < context[0].length; col++) {
            Utility.showCompletionPercentage("Colom Concept Completed :", context[0].length, col);
            Set<Integer> extent = new HashSet<>();
            for (int row = 0; row < context.length; row++) {
                if (context[row][col] == 1) {
                    extent.add(row);
                }
            }

            if (extent.isEmpty() || extentSet.contains(extent)) {
                continue;
            }

            Set<Integer> intent = new HashSet<>();
            for (int c = 0; c < context[0].length; c++) {
                boolean inIntent = true;
                for (int i : extent) {
                    if (context[i][c] != 1) {
                        inIntent = false;
                        break;
                    }
                }
                if (inIntent) {
                    intent.add(c);
                }
            }

            extentSet.add(extent);

            Set<Integer>[] concept = new Set[2];
            concept[0] = intent;
            concept[1] = extent;
            concepts.add(concept);
        }

        System.out.println("Colom concepts completed");
        return concepts;
    }

    public static Concept granuleConceptv3(int[][] context) {
        List<Set<Integer>[]> concepts = new ArrayList<>();
        Set<Set<Integer>> intentSet = new HashSet<>();
        Set<Set<Integer>> extentSet = new HashSet<>();

        List<int[]> cncptMatrix = new ArrayList<>();
        List<Integer> cncpt_no_obj = new ArrayList<>();
        // find row concepts
        for (int in = 0; in < context.length; in++) {
            Utility.showCompletionPercentage("Row Concept completed : ", context.length, in);
            int[] context1 = context[in];
            Set<Integer> intent = new HashSet<>();
            for (int col = 0; col < context1.length; col++) {
                if (context1[col] == 1) {
                    intent.add(col);
                }
            }

            if (intent.isEmpty() || intentSet.contains(intent)) {
                continue;
            }

            Set<Integer> extent = new HashSet<>();
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
            cncpt_no_obj.add(extent.size());
            extentSet.add(extent);
            intentSet.add(intent);

            Set<Integer>[] concept = new Set[2];
            concept[0] = intent;
            concept[1] = extent;
            concepts.add(concept);
        }

        System.out.println("Row concepts completed");
        // find column concepts
        for (int col = 0; col < context[0].length; col++) {
            Utility.showCompletionPercentage("Colom Concept Completed :", context[0].length, col);
            Set<Integer> extent = new HashSet<>();

            int[] isPresent = new int[context.length];

            for (int row = 0; row < context.length; row++) {
                if (context[row][col] == 1) {
                    extent.add(row);
                    isPresent[row] = 1;
                }
            }

            if (extent.isEmpty() || extentSet.contains(extent)) {
                continue;
            }

            Set<Integer> intent = new HashSet<>();
            for (int c = 0; c < context[0].length; c++) {
                boolean inIntent = true;
                for (int i : extent) {
                    if (context[i][c] != 1) {
                        inIntent = false;
                        break;
                    }
                }
                if (inIntent) {
                    intent.add(c);
                }
            }

            cncptMatrix.add(isPresent);
            cncpt_no_obj.add(extent.size());
            extentSet.add(extent);

            Set<Integer>[] concept = new Set[2];
            concept[0] = intent;
            concept[1] = extent;
            concepts.add(concept);
        }

        System.out.println("Colom concepts completed");
        return new Concept(concepts, cncptMatrix, cncpt_no_obj);
    }


    public static Concept granuleConceptv4(int[][] context, int no_of_worker){
        List<Set<Integer>[]> concepts = new ArrayList<>();
        Set<Set<Integer>> intentSet = new HashSet<>();
        Set<Set<Integer>> extentSet = new HashSet<>();
        
        List<int[]> cncptMatrix = new ArrayList<>();
//        List<Integer> cncpt_no_obj = new ArrayList<>();
        
        // find row concepts
        ConceptWorker[] workers_r =  new ConceptWorker[no_of_worker];
        
        int range = context.length/no_of_worker;
        int start_index = 0;
        int end_index = range-1;
        
        for(int i = 0; i < no_of_worker; i++){
            ConceptWorker w = new ConceptWorker( context, intentSet, extentSet,1,start_index, end_index);
            w.start();
            workers_r[i] = w;
            start_index = end_index+1;
            end_index += range;
            end_index = context.length <= end_index ? context.length-1 : end_index;
        }

        for(int i = 0; i < no_of_worker; i++){
            try {
                workers_r[i].join();
                List<Set<Integer>[]>  cncpt = workers_r[i].concepts;
                for(Set<Integer>[] c : cncpt ){
                    extentSet.add(c[1]);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(GCD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        // find col concepts
        ConceptWorker[] workers_c =  new ConceptWorker[no_of_worker];
        
        range = context[0].length/no_of_worker;
        start_index = 0;
        end_index = range-1;
        
        for(int i = 0; i < no_of_worker; i++){
            ConceptWorker w = new ConceptWorker( context, intentSet, extentSet,2,start_index, end_index);
            w.start();
            workers_c[i] = w;
            start_index = end_index+1;
            end_index += range;
            end_index = context[0].length <= end_index ? context[0].length-1 : end_index;
        }
        
        // add row mined concepts
        for(int i = 0; i < no_of_worker; i++){
            concepts.addAll(workers_r[i].concepts);
            cncptMatrix.addAll(workers_r[i].cncptMatrix);
//            cncpt_no_obj.addAll(workers_r[i].cncpt_no_obj);
        }
        
        for(int i = 0; i < no_of_worker; i++){
            try {
                workers_c[i].join();
            } catch (InterruptedException ex) {
                Logger.getLogger(GranuleConcept1.class.getName()).log(Level.SEVERE, null, ex);
            }
            concepts.addAll(workers_c[i].concepts);
            cncptMatrix.addAll(workers_c[i].cncptMatrix);
//            cncpt_no_obj.addAll(workers_c[i].cncpt_no_obj);
        }
        
        return new Concept(concepts, cncptMatrix);
    }

    public static Concept granuleConceptv5(int[][] context, int no_of_worker){
        ArrayList<List<Integer>> concepts = new ArrayList<>();
        Set<List<Integer>> intentSet = new HashSet<>();
        Set<List<Integer>> extentSet = new HashSet<>();
        
        List<int[]> cncptMatrix = new ArrayList<>();
        
        // find row concepts
        ConceptWorkerV2[] workers_r =  new ConceptWorkerV2[no_of_worker];
        
        int range = context.length/no_of_worker;
        int start_index = 0;
        int end_index = range-1;
        
        for(int i = 0; i < no_of_worker; i++){
            ConceptWorkerV2 w = new ConceptWorkerV2( context, intentSet, extentSet,1,start_index, end_index);
            w.start();
            workers_r[i] = w;
            start_index = end_index+1;
            end_index += range;
            end_index = context.length <= end_index ? context.length-1 : end_index;
        }

        for(int i = 0; i < no_of_worker; i++){
            try {
                workers_r[i].join();
                extentSet.addAll(workers_r[i].concepts);
            } catch (InterruptedException ex) {
                Logger.getLogger(GCD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        // find col concepts
        ConceptWorkerV2[] workers_c =  new ConceptWorkerV2[no_of_worker];
        
        range = context[0].length/no_of_worker;
        start_index = 0;
        end_index = range-1;
        
        for(int i = 0; i < no_of_worker; i++){
            ConceptWorkerV2 w = new ConceptWorkerV2( context, intentSet, extentSet,2,start_index, end_index);
            w.start();
            workers_c[i] = w;
            start_index = end_index+1;
            end_index += range;
            end_index = context[0].length <= end_index ? context[0].length-1 : end_index;
        }
        
        // add row mined concepts
        for(int i = 0; i < no_of_worker; i++){
            concepts.addAll(workers_r[i].concepts);
            cncptMatrix.addAll(workers_r[i].cncptMatrix);
        }
        
        for(int i = 0; i < no_of_worker; i++){
            try {
                workers_c[i].join();
            } catch (InterruptedException ex) {
                Logger.getLogger(GranuleConcept1.class.getName()).log(Level.SEVERE, null, ex);
            }
            concepts.addAll(workers_c[i].concepts);
            cncptMatrix.addAll(workers_c[i].cncptMatrix);
        }
        
        intentSet = null;
        extentSet = null;
        
        return new Concept(concepts, cncptMatrix);
    }

//    public static Concept granuleConceptv6(int[][] context, int no_of_worker){
//        ArrayList<List<Integer>> concepts = new ArrayList<>();
//        Set<List<Integer>> intentSet = new HashSet<>();
//        Set<List<Integer>> extentSet = new HashSet<>();
//        
//        ArrayList<RoaringBitmap> cncptMatrixCompressed = new ArrayList<>();
//        // find row concepts
//        ConceptWorkerV2[] workers_r =  new ConceptWorkerV2[no_of_worker];
//        
//        int range = context.length/no_of_worker;
//        int start_index = 0;
//        int end_index = range-1;
//        
//        for(int i = 0; i < no_of_worker; i++){
//            ConceptWorkerV2 w = new ConceptWorkerV2( context, intentSet, extentSet,11,start_index, end_index);
//            w.start();
//            workers_r[i] = w;
//            start_index = end_index+1;
//            end_index += range;
//            end_index = context.length <= end_index ? context.length-1 : end_index;
//        }
//
//        for(int i = 0; i < no_of_worker; i++){
//            try {
//                workers_r[i].join();
//                extentSet.addAll(workers_r[i].concepts);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(GCD.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        
//        // find col concepts
//        ConceptWorkerV2[] workers_c =  new ConceptWorkerV2[no_of_worker];
//        
//        range = context[0].length/no_of_worker;
//        start_index = 0;
//        end_index = range-1;
//        
//        for(int i = 0; i < no_of_worker; i++){
//            ConceptWorkerV2 w = new ConceptWorkerV2( context, intentSet, extentSet,22,start_index, end_index);
//            w.start();
//            workers_c[i] = w;
//            start_index = end_index+1;
//            end_index += range;
//            end_index = context[0].length <= end_index ? context[0].length-1 : end_index;
//        }
//        
//        // add row mined concepts
//        for(int i = 0; i < no_of_worker; i++){
//            concepts.addAll(workers_r[i].concepts);
//            cncptMatrixCompressed.addAll(workers_r[i].cncptMatrixCompressed);
//        }
//        
//        for(int i = 0; i < no_of_worker; i++){
//            try {
//                workers_c[i].join();
//            } catch (InterruptedException ex) {
//                Logger.getLogger(GranuleConcept1.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            concepts.addAll(workers_c[i].concepts);
//            cncptMatrixCompressed.addAll(workers_c[i].cncptMatrixCompressed);
//        }
//        
//        intentSet = null;
//        extentSet = null;
//        
//        return new Concept(concepts, cncptMatrixCompressed);
//    }

}
