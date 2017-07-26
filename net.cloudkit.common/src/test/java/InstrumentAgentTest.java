import java.lang.instrument.Instrumentation;
import java.lang.instrument.ClassFileTransformer;

public class InstrumentAgentTest {

    private static Instrumentation instrumentation = null;

    /**
     * This method is called before the application’s main-method is called,
     * when this agent is specified to the Java VM.
     **/
    public static void premain(String agentArgs, Instrumentation instrumentation) {
        System.out.println("PerfMonAgent.premain() was called.");
        // Initialize the static variables we use to track information.
        InstrumentAgentTest.instrumentation = instrumentation;
        // Set up the class-file transformer.
        ClassFileTransformer trans = new MyTransformer();
        System.out.println("Adding a PerfMonXformer instance to the JVM.");
        InstrumentAgentTest.instrumentation.addTransformer(trans);
    }
}
