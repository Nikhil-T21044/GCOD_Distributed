/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.gcod_algo;

import java.io.IOException;
import java.util.Arrays;
//import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

//import org.roaringbitmap.RoaringBitmap;

/**
 *
 * @author Triphil
 */
public class GCOD_Algo {

    public static void main(String[] args) {
        for(var i : args)
            System.out.print(i + " ");
        System.out.println("Input_File_Absolute_Address : " + args[0]);

        if (args.length != 4) { // NOT Correct Args
            System.out.println("INPUT FORMAT: Input_File_Absolute_Address no_of_lines no_of_worker no_of_outliers");
            return;
        }
        long beforeUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        int[][] dt = getData(args[0], Integer.parseInt(args[1]));
        double[] all_gcof;
        if(Integer.parseInt(args[2]) < 2)
            all_gcof = inmemoryProcessedv2(args, dt);
        else
            all_gcof = inmemoryProcessedv4(args, dt, Integer.parseInt(args[2]));
        long afterUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        
        System.out.println("Memory Consumed : " + (afterUsedMem-beforeUsedMem) + "bytes");
        
        printResult(all_gcof, Integer.parseInt(args[3]));
        
        
    }

    static void printResult(double[] all_gcof, int outliers) {

//            for (double i : all_gcof) {
//                System.out.println(i);
//            }
            Integer[] indices = new Integer[all_gcof.length];
            for (int i = 0; i < all_gcof.length; i++) {
                indices[i] = i;
            }

            Arrays.sort(indices, (a, b) -> Double.compare(all_gcof[b], all_gcof[a]));
            for (int i = 0; i < all_gcof.length && i < outliers ; i++) {
                System.out.print(all_gcof[indices[i]]);
                System.out.print(" :  ");
                System.out.println(indices[i]);
            }
    }

    static int[][] getData(String fileAddress, int n) {
        if (n == 0){
            int[][] d = {{1, 0, 0, 0, 1, 0},{1, 0, 0, 0, 0, 1},{0, 1, 0, 0, 1, 0},{1, 0, 0, 0, 0, 1},{1, 0, 0, 0, 1, 0},{0, 0, 1, 1, 0, 0}};
            return d;
        }
        int[][] dt = null;
        try {
            dt = CsvReader.readCsv(fileAddress, n);
        } catch (IOException ex) {
            Logger.getLogger(GCOD_Algo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dt;
    }

    static double[] inmemory(String[] args, int[][] dt) {
        List<Set<Integer>[]> cncpt = GranuleConcept1.granuleConceptv2(dt);
//            for (Set<Integer>[] cncptr : cncpt) {
//                System.out.println(cncptr[0] + "  :  " + cncptr [1]);
//            }
        int[][] gcd = GCD.gcdMatrix(dt.length, cncpt);
        System.out.println(gcd.length);

        double[][] e8_matrix = EQ8.eq8MatrixMethod(gcd, cncpt);

        double[] all_gcof = GCOF.allGcof(cncpt, dt.length, e8_matrix);
        return all_gcof;
    }

    static void withRedis(String[] args, int[][] dt) {
//      Step 1 -> Initialize Services
        RedisCon.init(args[1], args[2]);

        List<Set<Integer>[]> cncpt = GranuleConcept1.granuleConcept1(dt);
//            for (Set<Integer>[] cncptr : cncpt) {
//                System.out.println(cncptr[0] + "  :  " + cncptr [1]);
//            }
        intMatrix gcd = GCD.gcdMatrix(dt.length, cncpt, "gcd");
        System.out.println(gcd.length);

        doubleMatrix e8_matrix = EQ8.eq8MatrixMethod(gcd, cncpt, "e8");

        doubleMatrix all_gcof = GCOF.allGcof(cncpt, dt.length, e8_matrix);

//            for (double i : all_gcof) {
        System.out.println(all_gcof.length);
//            }
//            Integer[] indices = new Integer[all_gcof.length];
        for (int i = 0; i < all_gcof.length; i++) {
//                indices[i] = i;
            System.out.println(all_gcof.get(0, i));
        }
//
//            Arrays.sort(indices, (a, b) -> Double.compare(all_gcof[b], all_gcof[a]));
//            for (int i = 0; i < 100; i++) {
//                System.out.print(all_gcof[indices[i]]);
//                System.out.print(" :  ");
//                System.out.println(indices[i]);
//            }

        RedisCon.shutdown();
    }

    static double[] inmemoryProcessed(String[] args, int[][] dt) {
        List<Set<Integer>[]> cncpt = GranuleConcept1.granuleConceptv2(dt);
        int[][] gcd = GCD.gcdMatrix(dt.length, cncpt);
        System.out.println(gcd.length);
        double[] e8_matrix = EQ8.eq8ArrMethod(gcd, cncpt);
        double[] all_gcof = ProcessedGCOF.allGcof(cncpt, dt.length, e8_matrix);
        return all_gcof;
    }

    static double[] inmemoryProcessedv2(String[] args, int[][] dt) {
        long st = System.currentTimeMillis();
        Utility.printStamp("Concept Started");
        Concept cncpt = GranuleConcept1.granuleConceptv3(dt);
        Utility.printStamp("Concept Completed");
        int[][] gcd = GCD.gcdMatrixv2(dt.length, cncpt.cncptMatrix);
        System.out.println(gcd.length);
        Utility.printStamp("GCD Completed");
        double[] e8_matrix = EQ8.eq8ArrMethod(gcd, cncpt.concepts);
        Utility.printStamp("E8 Completed");
        double[] all_gcof = ProcessedGCOF.allGcof(cncpt.concepts, dt.length, e8_matrix);
        Utility.printStamp("GCOF Completed");
        long end = System.currentTimeMillis();
        System.out.println( (end-st) + " milliseconds " );
        return all_gcof;
    }

    // Thread Based
    static double[] inmemoryProcessedv3(String[] args, int[][] dt, int no_of_worker) {
        long st = System.currentTimeMillis();
        Utility.printStamp("Concept Started");
        Concept cncpt = GranuleConcept1.granuleConceptv4(dt,no_of_worker);
        Utility.printStamp("Concept Completed");
        int[][] gcd = GCD.gcdMatrixv3(dt.length, cncpt.cncptMatrix, no_of_worker);
//        printGCD(gcd);
        Utility.printStamp("GCD Completed");
        System.out.println(gcd.length);
        double[] e8_matrix = EQ8.eq8ArrMethodv2(gcd, cncpt.concepts, no_of_worker);
//        printE8(e8_matrix);
        Utility.printStamp("E8 Completed");
        double[] gcof = ProcessedGCOF.allGcofv3(cncpt.concepts, dt.length, e8_matrix, no_of_worker);
        Utility.printStamp("GCOF Completed");
        long end = System.currentTimeMillis();
        System.out.println( (end-st) + " milliseconds " );
        return gcof;
    }
    
    // Thread Based
    static double[] inmemoryProcessedv4(String[] args, int[][] dt, int no_of_worker) {
        long st = System.currentTimeMillis();
        Utility.printStamp("Concept Started");
        Concept cncpt = GranuleConcept1.granuleConceptv5(dt,no_of_worker);
        Utility.printStamp("Concept Completed");
        int[][] gcd = GCD.gcdMatrixv3(dt.length, cncpt.cncptMatrix, no_of_worker);
//        printGCD(gcd);
        Utility.printStamp("GCD Completed");
        System.out.println(gcd.length);
        double[] e8_matrix = EQ8.eq8ArrMethodv3(gcd, cncpt.concept_ext, no_of_worker);
//        printE8(e8_matrix);
        Utility.printStamp("E8 Completed");
        double[] gcof = ProcessedGCOF.allGcofv4(cncpt.concept_ext, dt.length, e8_matrix, no_of_worker);
        Utility.printStamp("GCOF Completed");
        long end = System.currentTimeMillis();
        System.out.println( (end-st) + " milliseconds " );
        return gcof;
    }
    
    // Thread Based compressed
//    static double[] inmemoryProcessedv5(String[] args, int[][] dt, int no_of_worker) {
//        long st = System.currentTimeMillis();
//        Utility.printStamp("Concept Started");
//        Concept cncpt = GranuleConcept1.granuleConceptv6(dt,no_of_worker);
//        Utility.printStamp("Concept Completed");
//        int[][] gcd = GCD.gcdMatrixv4(dt.length, cncpt.cncptMatrixCompressed, no_of_worker);
////        printGCD(gcd);
//        Utility.printStamp("GCD Completed");
//        System.out.println(gcd.length);
//        double[] e8_matrix = EQ8.eq8ArrMethodv3(gcd, cncpt.concept_ext, no_of_worker);
////        printE8(e8_matrix);
//        Utility.printStamp("E8 Completed");
//        double[] gcof = ProcessedGCOF.allGcofv4(cncpt.concept_ext, dt.length, e8_matrix, no_of_worker);
//        Utility.printStamp("GCOF Completed");
//        long end = System.currentTimeMillis();
//        System.out.println( (end-st) + " milliseconds " );
//        return gcof;
//    }
//    
    static void printGCD(int[][] gcd){
        for(int[] i : gcd){
            System.out.println(Arrays.toString(i));
        }
    }
    
    static void printE8(double[] gcd){
         System.out.println(Arrays.toString(gcd));
    }
    
    /*
[0, 3, 3, 3, 0, 4]
[3, 0, 4, 0, 3, 3]
[3, 4, 0, 4, 3, 3]
[3, 0, 4, 0, 3, 3]
[0, 3, 3, 3, 0, 4]
[4, 3, 3, 3, 4, 0]
    
[12.5, 14.833333333333334, 15.5, 17.166666666666668, 13.666666666666666, 13.5]
    */
}
