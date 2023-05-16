/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gcod_algo;

/**
 *
 * @author Triphil
 */
public class intMatrix {
    
    final MatrixRedis dt;
    public final int length;
    public intMatrix(int m , int n, String initials){
        dt = new MatrixRedis(m, n, initials);
        length = m*n;
    }
    
    public void set(int i, int j, int val){
        dt.set(i, j, String.valueOf(val) );
    }
    
    public int get(int i, int j){
        return Integer.parseInt(dt.get(i, j) );
    }
}
