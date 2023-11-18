/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.twitter.utils;

import java.util.Base64;
import java.util.UUID;

/**
 *
 * @author sudarshan
 */
public class ShortUUID {
    public static String generateShortUUID(){
        UUID uuid = UUID.randomUUID();
        long mostSignificantBits = uuid.getMostSignificantBits();
        long leastSignificantBits = uuid.getLeastSignificantBits();

        // Combine the most and least significant bits into a single long value
        long combinedBits = mostSignificantBits ^ leastSignificantBits;

        // Encode the combined bits using Base64
        byte[] encodedBytes = Base64.getUrlEncoder().encode(toByteArray(combinedBits));

        // Convert the byte array to a String
        String shortUUID = new String(encodedBytes);

        // Remove padding characters from the end
        shortUUID = shortUUID.replaceAll("=", "");

        return shortUUID;

    }
    
    private static byte[] toByteArray(long value) {
        byte[] result = new byte[8];
        for (int i = 7; i >= 0; i--) {
            result[i] = (byte) (value & 0xFF);
            value >>= 8;
        }
        return result;
    }
    
}
