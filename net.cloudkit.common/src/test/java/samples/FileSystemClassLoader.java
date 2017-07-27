package samples;

import java.io.*;

/**
 * 引导类加载器（bootstrap class loader）：
 * 它用来加载 Java 的核心库，是用原生代码来实现的，并不继承自 java.lang.ClassLoader。
 *
 * 扩展类加载器（extensions class loader）：
 * 它用来加载 Java 的扩展库。Java 虚拟机的实现会提供一个扩展库目录。该类加载器在此目录里面查找并加载 Java 类。
 *
 * 系统类加载器（system class loader）：
 * 它根据 Java 应用的类路径（CLASSPATH）来加载 Java 类。一般来说，Java 应用的类都是由它来完成加载的。可以通过 ClassLoader.getSystemClassLoader()来获取它。
 *
 * 加载器的树状组织结构
 * ClassLoader loader = ClassLoaderTree.class.getClassLoader();
 * while (loader != null) {
 * System.out.println(loader.toString());
 * loader = loader.getParent();
 }
 */
public class FileSystemClassLoader extends ClassLoader {

    private String rootDir;

    public FileSystemClassLoader(String rootDir) {
        this.rootDir = rootDir;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        byte[] classData = getClassData(name);

        if (classData == null) {
            throw new ClassNotFoundException();
        } else {
            return defineClass(name, classData, 0, classData.length);
        }
    }

    private byte[] getClassData(String className) {

        String path = classNameToPath(className);

        try {
            InputStream ins = new FileInputStream(path);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int bufferSize = 4096;
            byte[] buffer = new byte[bufferSize];
            int bytesNumRead = 0;
            while ((bytesNumRead = ins.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesNumRead);
            }

            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String classNameToPath(String className) {
        return rootDir + File.separatorChar + className.replace('.', File.separatorChar) + ".class";
    }

}
