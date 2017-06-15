import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class ClassLoaderTest {

    public static void main(String[] args) {
        String jarName = "C.jar";
        try {
            File file = new File(jarName);
            URL url = file.toURI().toURL();
            URLClassLoader loader = new URLClassLoader(new URL[]{url});

            Class loadClass = loader.loadClass("C");

            // TypeObject typeObject = (TypeObject) loadClass.newInstance();
            // typeObject.execute();

            String className = "Example";
            Class c = Class.forName(className);
            // factory = (TypeObject) c.newInstance();

        } catch (Exception e) {
            e.printStackTrace();
        }


        // TODO

        URL[] urls = new URL[]{};
        MyClassLoader classLoader = new MyClassLoader(urls, null);
        String path = "E:/libs/commons-codec-1.6.jar";
        try {
            classLoader.addJar(new File(path).toURI().toURL());
            Class<?> clazz = classLoader.loadClass("org.apache.commons.codec.digest.DigestUtils");
            Method md5Method = clazz.getDeclaredMethod("md5Hex", byte[].class);
            Object returnValue = md5Method.invoke(null, "hello world".getBytes("utf-8"));
            System.out.println("length=" + returnValue.toString().length() + ",result=" + returnValue.toString());
            classLoader.close();

            /**
             ClassLoader cl = new URLClassLoader(new URL[]{new File(path).toURI().toURL()});
             Class<?> clazz2 = classLoader.loadClass("org.apache.commons.codec.digest.DigestUtils");
             Method md5Method2 = clazz.getDeclaredMethod("md5Hex", byte[].class);
             Object returnValue2 = md5Method.invoke(clazz2.newInstance(), "hello world".getBytes("utf-8"));
             System.out.println("length=" + returnValue2.toString().length() + ",result=" + returnValue2.toString());
             **/
        } catch (Exception e) {
            e.printStackTrace();
        }


        // TODO javassist cglib ASM
        // http://www.blogjava.net/hello-yun/archive/2014/09/28/418365.html
    }

    static class MyClassLoader extends URLClassLoader {

        public MyClassLoader(URL[] urls) {
            super(urls);
        }

        public MyClassLoader(URL[] urls, ClassLoader parent) {
            super(urls, parent);
        }

        public void addJar(URL url) {
            this.addURL(url);
        }

    }
}
