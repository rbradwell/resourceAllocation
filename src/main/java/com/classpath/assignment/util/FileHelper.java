package com.classpath.assignment.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {

    public List<String> readFile(String filename) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader() ;
        InputStream inputStream = classLoader.getResourceAsStream(filename);
        return readFromInputStream(inputStream);
    }

    private List<String> readFromInputStream(InputStream inputStream) throws IOException {
        List<String> lines = new ArrayList<>() ;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line) ;
            }
        }
        return lines;
    }

}
