/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gcod_algo;

/**
 *
 * @author Triphil
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Triphil
 */
import com.lambdaworks.redis.*;

public class RedisCon {

    private static RedisConnection<String, String> connection;
    private static RedisClient redisClient;

    private static void connect(String host, String port) {
        redisClient = new RedisClient(
                RedisURI.create("redis://" + host + ":" + port));
        connection = redisClient.connect();
        System.out.println("Connected to Redis");
    }

    public static void init(String host, String port) {
        connect(host, port);
        connection.flushdb();
    }

    public static void shutdown() {
        connection.flushdb();
        connection.close();
        redisClient.shutdown();
    }

    public static void main(String[] args) {
        connect("127.0.0.1", "6379");
        
        connection.set("testkey", "testvalue");
         System.out.println(connection.get("testkey"));
        connection.close();
        redisClient.shutdown();
    }

    public static void set(String key, String value) {
        connection.set(key, value);
//        System.out.println(connection.get(key) + "  :   " + key);
    }

    public static String get(String key) {
//        System.out.println(connection.get(key) +"  :  "+ key);
        return connection.get(key);
    }

}
