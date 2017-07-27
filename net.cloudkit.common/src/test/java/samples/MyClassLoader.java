package samples;

/**
 * 自定义一个类加载器，用于将字节码转换为class对象
 */
public class MyClassLoader extends ClassLoader {

    public Class<?> defineMyClass(String name, byte[] b, int off, int len) {
        return super.defineClass(name, b, off, len);
    }

}
