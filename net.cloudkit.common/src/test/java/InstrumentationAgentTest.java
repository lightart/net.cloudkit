import java.lang.instrument.ClassDefinition;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

/**
 * 监测器 类定义动态改变和操作
 * https://www.ibm.com/developerworks/cn/java/j-lo-jse61/index.html
 * <p>
 * manifest
 * Manifest-Version: 1.0
 * Premain-Class: Premain
 * <p>
 * java -javaagent:jar 文件的位置 [= 传入 premain 的参数 ]
 * java –javaagent:InstrumentationAgent.jar –cp InstrumentationAgent.jar TestMain
 */
public class InstrumentationAgentTest {


    /**
     * This method is called before the application’s main-method is called,
     * when this agent is specified to the Java VM.
     * <p>
     * public static void premain(String agentArgs, Instrumentation inst);
     * public static void premain(String agentArgs);
     **/
    public static void premain(String agentArgs, Instrumentation inst)
        throws ClassNotFoundException, UnmodifiableClassException {
        System.out.println("InstrumentationAgentTest.premain() was called.");

        /*
        // Set up the class-file transformer.
        ClassFileTransformer instrumentationTransformer = new InstrumentationTransformer();

        System.out.println("Adding a InstrumentationTransformer instance to the JVM.");
        inst.addTransformer(instrumentationTransformer);

        // agent 包之中的 Manifest 所设定的特性 Can-Set-Native-Method-Prefix 设置成true
        if (!inst.isNativeMethodPrefixSupported()) {
            return; // 如果无法设置，则返回
        }
        // 设置 native 函数的 prefix，注意这个下划线必须由用户自己规定
        inst.setNativeMethodPrefix(instrumentationTransformer, "prefix_");
        */

        // 可以批量转换类定义
        ClassDefinition def = new ClassDefinition(
            TransClass.class,
            InstrumentationTransformer.getBytesFromFile(InstrumentationTransformer.CLASS_NUMBER_RETURNS_SEC)
        );
        inst.redefineClasses(new ClassDefinition[]{def});
        System.out.println("success");
    }

    /**
     * 虚拟机启动后的动态 instrument
     * <p>
     * public static void agentmain (String agentArgs, Instrumentation inst);
     * public static void agentmain (String agentArgs);
     *
     * @param agentArgs
     * @param inst
     */
    public static void agentmain(String agentArgs, Instrumentation inst) throws ClassNotFoundException, UnmodifiableClassException,
        InterruptedException {
        inst.addTransformer(new InstrumentationTransformer(), true);

        // 可以批量转换类定义
        // inst.retransformClasses(TransClass.class);
        inst.retransformClasses(new Class[]{TransClass.class});

        // BootClassPath / SystemClassPath 的动态增补
        // 在 agent 的 manifest 里加入 Boot-Class-Path 可以在动态地载入 agent 的同时加入自己的 boot class 路径
        // inst.appendToBootstrapClassLoaderSearch();
        // inst.appendToSystemClassLoaderSearch();
        System.out.println("Agent Main Done");
    }
}
