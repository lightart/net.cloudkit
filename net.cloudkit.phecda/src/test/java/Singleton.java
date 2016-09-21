public class Singleton {
    // ...
    private static class LazyHolder {
        static final Singleton INSTANCE = new Singleton();
    }
    public static Singleton getInstance() {
        return LazyHolder.INSTANCE;
    }
}
