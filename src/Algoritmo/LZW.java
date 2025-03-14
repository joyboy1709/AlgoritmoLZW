/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Algoritmo;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author santu
 */
public class LZW {
    private Map<String, Integer> dictionary;
    private int nextCode;

    public LZW() {
        dictionary = new HashMap<>();
        // Inicializar el diccionario con los caracteres ASCII
        for (int i = 0; i < 256; i++) {
            dictionary.put("" + (char)i, i);
        }
        nextCode = 256;
    }

    public byte[] compress(String input) {
        StringBuilder buffer = new StringBuilder();
        byte[] output = new byte[input.length() * 2]; // Estimación aproximada del tamaño comprimido
        int length = 0;

        for (char c : input.toCharArray()) {
            String wc = buffer.toString() + c;
            if (dictionary.containsKey(wc)) {
                buffer.append(c);
            } else {
                output[length++] = (byte)(int)dictionary.get(buffer.toString());
                dictionary.put(wc, nextCode++);
                buffer.setLength(0);
                buffer.append(c);
            }
        }

        if (!buffer.toString().equals("")) {
            output[length++] = (byte)(int)dictionary.get(buffer.toString());
        }

        byte[] compressed = new byte[length];
        System.arraycopy(output, 0, compressed, 0, length);
        return compressed;
    }

    public String decompress(byte[] input) {
        StringBuilder result = new StringBuilder();
        Map<Integer, String> reverseDictionary = new HashMap<>();
        for (Map.Entry<String, Integer> entry : dictionary.entrySet()) {
            reverseDictionary.put(entry.getValue(), entry.getKey());
        }

        int oldCode = input[0] & 0xFF;
        String s = "" + (char)oldCode;
        result.append(s);

        int length = input.length;
        for (int i = 1; i < length; i++) {
            int newCode = input[i] & 0xFF;
            String entry;
            if (reverseDictionary.containsKey(newCode)) {
                entry = reverseDictionary.get(newCode);
            } else if (newCode == nextCode) {
                entry = s + s.charAt(0);
            } else {
                throw new IllegalArgumentException("Bad compressed k!");
            }

            result.append(entry);
            dictionary.put(nextCode++, s + entry.charAt(0));
            s = entry;
        }

        return result.toString();
    }
}