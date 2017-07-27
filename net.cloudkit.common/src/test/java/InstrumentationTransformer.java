import javassist.*;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

import java.io.*;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * GeneralTransformer ClassFileTransformer
 *
 */
public class InstrumentationTransformer implements ClassFileTransformer {

    public static final String CLASS_NUMBER_RETURNS_SEC = "TransClass.class.2";

    /**
     * transform
     *
     * @param loader
     * @param className
     * @param classBeingRedefined
     * @param protectionDomain
     * @param classfileBuffer
     * @return
     * @throws IllegalClassFormatException
     */
    @Override
    public byte[] transform(
        ClassLoader loader, String className, Class<?> classBeingRedefined,
        ProtectionDomain protectionDomain, byte[] classfileBuffer
    ) throws IllegalClassFormatException {

        /*
        byte[] transformed = null;
        System.out.println("Transforming " + className);

        // javassist
        ClassPool pool = ClassPool.getDefault();
        CtClass cl = null;
        try {
            cl = pool.makeClass(new ByteArrayInputStream(classfileBuffer));
            if (cl.isInterface() == false) {
                CtBehavior[] methods = cl.getDeclaredBehaviors();
                for (int i = 0; i < methods.length; i++) {
                    if (methods[i].isEmpty() == false) {
                        doMethod(methods[i]);
                    }
                }
                transformed = cl.toBytecode();
            }
        } catch (Exception e) {
            System.err.println("Could not instrument  " + className + ",  exception : " + e.getMessage());
        } finally {
            if (cl != null) {
                cl.detach();
            }
        }
        return transformed;
        */

        if (!className.equals("TransClass")) {
            return null;
        }
        return getBytesFromFile(CLASS_NUMBER_RETURNS_SEC);
    }

//    /**
//     * Do method
//     *
//     * @param method
//     * @throws NotFoundException
//     * @throws CannotCompileException
//     */
//    private void doMethod(CtBehavior method) throws NotFoundException,
//        CannotCompileException {
//        // method.insertBefore("long stime = System.nanoTime();");
//        // method.insertAfter("System.out.println(\"leave "+method.getName()+" and time:\"+(System.nanoTime()-stime));");
//        method.instrument(new ExprEditor() {
//            public void edit(MethodCall m) throws CannotCompileException {
//                m.replace("{ long stime = System.nanoTime(); $_ = $proceed($$); System.out.println(\""
//                    + m.getClassName() + "." + m.getMethodName()
//                    + ":\"+(System.nanoTime()-stime));}");
//            }
//        });
//    }


    /**
     * Get bytes from file
     *
     * @param fileName
     * @return
     */
    public static byte[] getBytesFromFile(String fileName) {
        try {
            // precondition
            File file = new File(fileName);
            InputStream is = new FileInputStream(file);
            long length = file.length();
            byte[] bytes = new byte[(int) length];

            // Read in the bytes
            int offset = 0;
            int numRead = 0;
            while (offset <bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
                offset += numRead;
            }

            if (offset < bytes.length) {
                throw new IOException("Could not completely read file " + file.getName());
            }
            is.close();
            return bytes;
        } catch (Exception e) {
            System.out.println("error occurs in _ClassTransformer!" + e.getClass().getName());
            return null;
        }
    }
}
