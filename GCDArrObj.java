/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gcod_algo;

/**
 *
 * @author Triphil
 */
public class GCDArrObj {
    final int[] Arr;
    final int n;
    
    public GCDArrObj(int[] Arr, int n){
        this.Arr = Arr;
        this.n = n;
    }
    
    public int get(int i , int j){
        return Arr[i*this.n - ( i * (i-1) / 2 ) + j - i] ;
    }
    
}
