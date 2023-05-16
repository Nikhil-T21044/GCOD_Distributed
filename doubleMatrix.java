/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gcod_algo;

/**
 *
 * @author Triphil
 */
public class doubleMatrix {
    
    private final MatrixRedis dt;
    public final int length;
    public doubleMatrix(int m , int n, String initials){
        dt = new MatrixRedis(m, n, initials);
        length = m*n;
    }
    
    public void set(int i, int j, double val){
        dt.set(i, j, String.valueOf(val) );
    }
    
    public double get(int i, int j){
        return Double.parseDouble(dt.get(i, j) );
    }
}
