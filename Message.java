/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gcod_algo;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
/**
 *
 * @author Triphil
 */
public class Message {
    Object obj;
    public JSONObject jo ;
    public int type;
    
    public Message(String msg){
        try {
            this.obj = new JSONParser().parse(msg);
            this.jo= (JSONObject) obj;
            this.type = Integer.parseInt( (String) this.jo.get("type") );
        } catch (ParseException ex) {
            Logger.getLogger(Message.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
