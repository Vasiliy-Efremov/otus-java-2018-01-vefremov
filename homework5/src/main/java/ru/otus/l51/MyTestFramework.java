package ru.otus.l51;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import ru.otus.l51.annotations.*;

import static ru.otus.l51.ReflectionHelper.*;

public class MyTestFramework {

    public static void run(Class<?> input) throws Exception {
        if (input != null) {
            List<Method> listWithTestAnnotation = new ArrayList<>();
            Method methodWithAfter = null;
            Method methodWithBefore = null;
            int countMethodsWithAfterAnnotation = 0;
            int countMethodsWithBeforeAnnotation = 0;

            for (Method method : input.getDeclaredMethods()) {
                Annotation[] annotations = method.getDeclaredAnnotations();
                for (Annotation annotation : annotations) {
                    if (annotation instanceof After) {
                        methodWithAfter = method;
                        countMethodsWithAfterAnnotation++;
                        if (countMethodsWithAfterAnnotation > 1) {
                            throw new Exception("Cannot be more then 1 method with After Annotation");
                        }
                    }
                    if (annotation instanceof Before) {
                        methodWithBefore = method;
                        countMethodsWithBeforeAnnotation++;
                        if (countMethodsWithBeforeAnnotation > 1) {
                            throw new Exception("Cannot be more then 1 method with Before Annotation");
                        }
                    }
                    if (annotation instanceof Test) {
                        listWithTestAnnotation.add(method);
                    }
                }
            }

            for (Method method : listWithTestAnnotation) {
                Object instance = instantiate(input);

                if (methodWithBefore != null) {
                    methodWithBefore.invoke(instance);
                }

                method.invoke(instance);

                if (methodWithAfter != null) {
                    methodWithAfter.invoke(instance);
                }
            }
        }
    }

    public static void run(String input) throws Exception {
        if (input != null) {
            List<Class> listWithClasses = getClassNamesFromPackage(input);
            for (int i = 0; i < listWithClasses.size(); i++) {
                run(listWithClasses.get(i));
            }
        }
    }

    private static List<Class> getClassNamesFromPackage(String packageName) throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        ClassPath classPath = ClassPath.from(classLoader);
        ImmutableSet<ClassPath.ClassInfo> classInfoSet = classPath.getTopLevelClasses(packageName);
        List<Class> listWithClassNames = new ArrayList<>();
        for (ClassPath.ClassInfo classInfo : classInfoSet) {
            listWithClassNames.add(Class.forName(classInfo.getName()));
        }
        return listWithClassNames;
    }
}
