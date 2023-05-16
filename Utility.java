/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gcod_algo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Triphil
 */
public class Utility {
    
    public static final void showCompletionPercentage(String message, int n, int i){
        if(  ( (i*1000)/n )%100 == 0  )
                System.out.println( message + (double) i*100/n + "%    "  + System.currentTimeMillis() );
    }
    
    public static final void printStamp(String message){
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Format the current date and time to a desired string format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        
        System.out.println(message + " " + formattedDateTime);
    }
     
}
