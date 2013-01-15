package com.calcul.diabetif.commun.util;

import java.util.Locale;

/* 
 * Algorithm provided by this website 
 * http://www.developpez.net/forums/d429810/autres-langages/algorithmes/contribuez/calcul-crc-64-bits/
 */
public class CRCChecker {

    public static long crc64_slow(char[] sequence) {
        // x64 + x4 + x3 + x1 + 1
        // long POLY64 = 0x000000000000001BL;
        long POLY64Reverse = 0xd800000000000000L;

        long reminder = 0;
        for (int i = 0; i < sequence.length; i++) {

            reminder = reminder ^ sequence[i];

            for (int bit = 7; bit >= 0; bit--) {
                if ((reminder & 0x1) == 1) {
                    reminder = (reminder >>> 1) ^ POLY64Reverse;
                } else {
                    reminder = (reminder >>> 1);
                }
            }
        }
        return reminder;
    }

    private static long checkCRCOfString(String string) {
        long crc = 0;
        crc = crc64_slow(string.toUpperCase(Locale.US).toCharArray());
        return crc;
    }

    public static String getEncryptedString(String inputSrting) {
        return Long.toHexString(checkCRCOfString(inputSrting));
    }
    // For Tes those
    // public static void main(String[] args) {
    // long crc = 0;
    // String s = "12345678"; // CRC64 = 46A5A9388A5BEFFE
    // System.out.println("encrypted password: "+Long.toHexString(checkCRCOfString(s)));
    // }
}
