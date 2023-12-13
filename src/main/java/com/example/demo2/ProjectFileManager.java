// ProjectFileManager.java
package com.example.demo2;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ProjectFileManager {
    private static final String FILE_BASE_PATH = "src/main/FileBase/";

    public static void saveProject(Project project, String fileName) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FILE_BASE_PATH + fileName))) {
            outputStream.writeObject(project);
            System.out.println("Project saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Project loadProject(String fileName) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_BASE_PATH + fileName))) {
            Object obj = inputStream.readObject();
            if (obj instanceof Project) {
                System.out.println("Project loaded successfully.");
                return (Project) obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
