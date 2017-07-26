import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class Test {

    /**
     * main
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            ClassLoader classLoader = new URLClassLoader(new URL[]{new File("E:\\temp\\MergePDF.class").toURI().toURL()});
            Class loadClass = classLoader.loadClass("MergePDF");
            Object loadObject = loadClass.newInstance();

            Class[] args1 = new Class[1];
            args1[0] = String[].class;

            Method method = loadClass.getDeclaredMethod("main", args1);
            method.setAccessible(true);
            String[][] arguments = new String[1][];
            arguments[0] = new String[]{"Hello,world"};
            method.invoke(loadObject, arguments);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

