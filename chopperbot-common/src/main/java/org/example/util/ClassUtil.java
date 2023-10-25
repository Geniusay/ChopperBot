package org.example.util;

import org.example.bean.section.VideoSection;
import org.reflections.Reflections;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

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

    /**
     * 扫描某个注解
     */
    public static Set<Class<?>> getAnnotationClass(String packageName,Class annotationClass){
        //反射
        Reflections ref = new Reflections(packageName);
        // 获取扫描到的标记注解的集合
        Set<Class<?>> set = ref.getTypesAnnotatedWith(annotationClass);
//        for (Class<?> c : set) {
//            // 循环获取标记的注解
//            Plugin annotation = c.getAnnotation(Plugin.class);
//            if(annotation!=null){
//                // 打印注解中的内容
//                System.out.println("表名："+annotation.pluginName());
//            }
//
//        }
//        Class[] objects = (Class[]) set.toArray();
//        return List.of(objects);
        return set;
    }

    public static Map<String, Object> toMap(Object obj) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        Class<?> clazz = obj.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }

        return map;
    }

    public static Map<String, Object> toDeepMap(Object obj) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        Class<?> clazz = obj.getClass();

        // 获取当前类的字段
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }

        // 如果有父类且不是 Object 类，递归调用获取父类的字段
        Class<?> superClass = clazz.getSuperclass();
        if (superClass != null && !superClass.equals(Object.class)) {
            try {
                Object superObj = superClass.getDeclaredConstructor().newInstance();
                copyFields(superObj, obj, superClass);
                map.putAll(toDeepMap(superObj));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return map;
    }

    private static void copyFields(Object dest, Object source, Class<?> clazz) throws IllegalAccessException {
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            Field destField;
            try {
                destField = dest.getClass().getDeclaredField(field.getName());
                destField.setAccessible(true);
                destField.set(dest, field.get(source));
            } catch (NoSuchFieldException e) {
                // 忽略字段不存在的异常
            }
        }
    }
}
