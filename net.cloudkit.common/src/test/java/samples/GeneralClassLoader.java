package samples;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * GeneralClassLoader
 */
public class GeneralClassLoader extends ClassLoader {
    private String fileName;

    public GeneralClassLoader(String fileName) {
        this.fileName = fileName;
    }

    protected Class<?> findClass(String className) throws ClassNotFoundException {
        Class<?> clazz = this.findLoadedClass(className);
        if (null == clazz) {
            try {

                String classFile = getClassFile(className);
                FileInputStream fis = new FileInputStream(classFile);
                FileChannel fileChannel = fis.getChannel();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                WritableByteChannel writableByteChannel = Channels.newChannel(baos);
                ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

                while (true) {
                    int i = fileChannel.read(buffer);
                    if (i == 0 || i == -1) {
                        break;
                    }
                    buffer.flip();
                    writableByteChannel.write(buffer);
                    buffer.clear();
                }

                fis.close();
                byte[] bytes = baos.toByteArray();

                clazz = defineClass(className, bytes, 0, bytes.length);
                // resolveClass(clazz);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return clazz;
    }

//    private byte[] loadClassBytes(String className) throws ClassNotFoundException {
//        try {
//            String classFile = getClassFile(className);
//            FileInputStream fis = new FileInputStream(classFile);
//            FileChannel fileChannel = fis.getChannel();
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            WritableByteChannel writableByteChannel = Channels.newChannel(baos);
//            ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
//            while (true) {
//                int i = fileChannel.read(buffer);
//                if (i == 0 || i == -1) {
//                    break;
//                }
//                buffer.flip();
//                writableByteChannel.write(buffer);
//                buffer.clear();
//            }
//            fis.close();
//            return baos.toByteArray();
//        } catch (IOException fnfe) {
//            throw new ClassNotFoundException(className);
//        }
//    }

    private String getClassFile(String name) {
        StringBuffer sb = new StringBuffer(fileName);
        name = name.replace('.', File.separatorChar) + ".class";
        sb.append(File.separator + name);
        return sb.toString();
    }
}
