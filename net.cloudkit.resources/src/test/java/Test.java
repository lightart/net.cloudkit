
public class Test {

    public static void  main(String[] args) {
        Package pkg = Test.class.getPackage();
        System.out.println((pkg != null ? pkg.getImplementationVersion() : null));
    }
}
