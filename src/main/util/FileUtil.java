package main.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    private FileUtil() {
        super();
    }

    public static List<List<Integer>> getObjectMatrix(String fileUrl) {
        List<List<Integer>> objectMatrix = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileUrl))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                List<Integer> objectMatrixRow = new ArrayList<>();
                String[] splitted = line.trim().split(" ");
                for (String s : splitted) {
                    objectMatrixRow.add(Integer.parseInt(s));
                }
                objectMatrix.add(objectMatrixRow);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return objectMatrix;
    }
}