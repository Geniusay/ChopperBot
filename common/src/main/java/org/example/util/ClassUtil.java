package org.example.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Genius
 * @date 2023/07/21 09:56
 **/
public class ClassUtil {

    /**
     * 获得某一个包下的所有类
     * @param packageName   //包名
     * @return
     * @throws IOException
     */
    public static List<String> getClassesInPackage(String packageName)throws IOException {
        List<String> classNames = new ArrayList<>();

        String packagePath = packageName.replace(".", "/");
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        var resources = classLoader.getResources(packagePath);
        while (resources.hasMoreElements()) {
            var resource = resources.nextElement();
            File file = new File(resource.getFile());
            if (file.isDirectory()) {
                scanClassesInDirectory(packageName, file, classNames);
            }
        }

        return classNames;
    }

    //对包名进行分割
    private static void scanClassesInDirectory(String packageName, File directory, List<String> classNames) {
        for (File file : directory.listFiles()) {
            if (file.isFile()) {
                String className = packageName + "." + file.getName().replace(".class", "");
                classNames.add(className);
            } else if (file.isDirectory()) {
                scanClassesInDirectory(packageName + "." + file.getName(), file, classNames);
            }
        }
    }
}
