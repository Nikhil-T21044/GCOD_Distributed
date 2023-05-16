/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gcod_algo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.roaringbitmap.RoaringBitmap;

/**
 *
 * @author Triphil
 */
public class Concept {
    public final List<Set<Integer>[]> concepts;
    public final List<int[]> cncptMatrix;
    public final List<Integer> cncpt_no_obj;
    public final ArrayList<List<Integer>> concept_ext;
    public ArrayList<RoaringBitmap> cncptMatrixCompressed;
    
    public Concept(List<Set<Integer>[]> concepts, List<int[]> cncptMatrix, List<Integer> cncpt_no_obj){
        this.concepts = concepts;
        this.cncptMatrix = cncptMatrix;
        this.cncpt_no_obj = cncpt_no_obj;
        this.concept_ext = null;
    }
    
    public Concept(List<Set<Integer>[]> concepts, List<int[]> cncptMatrix){
        this.concepts = concepts;
        this.cncptMatrix = cncptMatrix;
        this.cncpt_no_obj = null;
        this.concept_ext = null;
    }
    
    public Concept(ArrayList<List<Integer>> concepts, List<int[]> cncptMatrix){
        this.concept_ext = concepts;
        this.cncptMatrix = cncptMatrix;
        this.cncpt_no_obj = null;
        this.concepts = null;
    }
    
    public Concept(ArrayList<List<Integer>> concepts, ArrayList<RoaringBitmap> cncptMatrixCompressed){
        this.concept_ext = concepts;
        this.cncptMatrix = null;
        this.cncpt_no_obj = null;
        this.concepts = null;
        this.cncptMatrixCompressed = cncptMatrixCompressed;
    }
}
