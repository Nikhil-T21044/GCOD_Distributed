/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gcod_algo;

public class MatrixRedis {

    final int m;
    final int n;
    private final String initials;

    public MatrixRedis(int m, int n, String initials) {
        this.m = m;
        this.n = n;
        this.initials = initials;
    }

    private String buildKey(int i, int j) {
        return initials + "_" + i + "_" + j;
    }

    public void set(int i, int j, String value) {
        RedisCon.set(buildKey(i, j), value);
    }

    public String get(int i, int j) {
        return RedisCon.get(buildKey(i, j));
    }

}
