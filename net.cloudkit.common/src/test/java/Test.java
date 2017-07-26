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
            // ClassLoader classLoader = new URLClassLoader(new URL[]{new File("E:\\temp\\MergePDF.class").toURI().toURL()});
            // Class loadClass = classLoader.loadClass("MergePDF");

            MyClassLoader classLoader = new MyClassLoader("E:\\temp\\");
            Class loadClass = classLoader.findClass("MergePDF");

            Object loadObject = loadClass.newInstance();

            Class[] args1 = new Class[1];
            args1[0] = String[].class;

            // getDeclaredMethod 返回一个 Method 对象，该对象反映此 Class 对象所表示的类或接口的指定已声明方法。
            // getMethod 返回一个 Method 对象，它反映此 Class 对象所表示的类或接口的指定公共成员方法。
            Method method = loadClass.getDeclaredMethod("main", args1);
            method.setAccessible(true);
            String[][] arguments = new String[1][];
            arguments[0] = new String[]{"Hello,world"};
            method.invoke(loadObject, arguments);

//        } catch (MalformedURLException e) {
//            e.printStackTrace();
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

