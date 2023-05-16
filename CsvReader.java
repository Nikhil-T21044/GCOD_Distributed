/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gcod_algo;

/**
 *
 * @author Triphil
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CsvReader {
    public static int[][] readCsv(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            List<String> lines = br.lines().collect(Collectors.toList());
            br.close();
            int numRows = lines.size();
            int numCols = lines.get(0).split(",").length;
            int[][] matrix = new int[numRows][numCols];

            for (int i = 0; i < numRows; i++) {
                String[] values = lines.get(i).split(",");
                for (int j = 0; j < numCols; j++) {
                    matrix[i][j] = Integer.parseInt(values[j]);
                }
            }

            return matrix;
        }
    }
    
        public static int[][] readCsv(String filename, int n) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            List<String> lines = br.lines().collect(Collectors.toList());
            br.close();
            int numRows = ( lines.size() < n || n < 0 ) ? lines.size() : n;
            int numCols = lines.get(0).split(",").length;
            int[][] matrix = new int[numRows][numCols];

            for (int i = 0; i < numRows; i++) {
                String[] values = lines.get(i).split(",");
                for (int j = 0; j < numCols; j++) {
                    matrix[i][j] = Integer.parseInt(values[j]);
                }
            }

            return matrix;
        }
    }
    
    public static intMatrix readCsv(String filename, String intials) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            List<String> lines = br.lines().collect(Collectors.toList());
            br.close();
            int numRows = lines.size();
            int numCols = lines.get(0).split(",").length;
            
            intMatrix contxt = new intMatrix(numRows, numCols, intials);      
            for (int i = 0; i < numRows; i++) {
                String[] values = lines.get(i).split(",");
                for (int j = 0; j < numCols; j++) {
                    contxt.set(i, j, Integer.parseInt(values[j]));
                }
            }

            return contxt;
        }
    }
}
