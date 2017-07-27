package samples;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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

            GeneralClassLoader classLoader = new GeneralClassLoader("E:\\temp\\");
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


//        // 热部署（hotswap）
//        // "我有一个问题。hotswap能够保证新创建的类采用新的版本。如何使正在运行的线程类的实例或者在集合中的类的实例替换为最新的？”
//        // John， 这篇文章讲的在线升级与热替换无法对正在运行的类的实例进行升级，属于“半”热替换。
//
//        // https://www.ibm.com/developerworks/cn/java/j-lo-hotswapcls/index.html
//        // https://www.ibm.com/developerworks/cn/java/j-lo-hotdeploy/index.html
//        // https://www.ibm.com/developerworks/cn/java/l-multithreading/
//        // https://www.ibm.com/developerworks/cn/java/l-protectjava/index.html
//        // JDK 和CGLIB,Javassist,ASM
//        // https://www.ibm.com/developerworks/cn/java/j-dyn0302/index.html
//        try {
//            samples.FileSystemClassLoader cl = new samples.FileSystemClassLoader("../swap", new String[]{"Foo"});
//            Class cls = cl.loadClass("Foo");
//            IFoo foo = (IFoo)cls.newInstance();
//            foo.sayHello();
//        } catch(Exception ex) {
//            ex.printStackTrace();
//        }
//
//
//        // 升级控制实体关键代码
//        class UpgradeController extends ActiveObject{
//            int nready  = 0;
//            int nfinished = 0;
//            Worker[] workers;
//    ......
//            // 收到外部升级命令消息时，会触发该方法被调用
//            public void askForUpgrade() {
//                for(int i=0; i<workers.length; i++)
//                    workers[i].getTaskQueue().enqueue(new PrepareUpgradeCmd(workers[i]));
//            }
//
//            // 收到工作实体回应的准备就绪命令消息时，会触发该方法被调用
//            public void readyForUpgrade(String worker_name) {
//                nready++;
//                if(nready == workers.length){
//                    for(int i=0; i<workers.length; i++)
//                        workers[i].getControlQueue().enqueue(new
//                            StartUpgradeCmd(workers[i]));
//                }
//            }
//
//            // 收到工作实体回应的升级完毕命令消息时，会触发该方法被调用
//            public void finishUpgrade(String worker_name) {
//                nfinished++;
//                if(nfinished == workers.length){
//                    for(int i=0; i<workers.length; i++)
//                        workers[i].getControlQueue().enqueue(new
//                            ContineWorkCmd(workers[i]));
//
//                }
//            }
//
//    ......
//
//        }
//
//        // 工作实体关键代码
//        class Worker extends ActiveObject{
//            UpgradeController ugc;
//            HotswapCL hscl;
//            IFoo foo;
//            String state = "hello world!";
//
//    ......
//
//            // 收到升级控制实体的准备升级命令消息时，会触发该方法被调用
//            public void prepareUpgrade() {
//                switchToControlQueue();
//                ugc.getMsgQueue().enqueue(new ReadyForUpdateCMD(ugc,this));
//            }
//
//            // 收到升级控制实体的开始升级命令消息时，会触发该方法被调用
//            public void startUpgrade(String worker_name) {
//                doUpgrade();
//                ugc.getMsgQueue().enqueue(new FinishUpgradeCMD(ugc,this));
//            }
//
//            // 收到升级控制实体的继续工作命令消息时，会触发该方法被调用
//            public void continueWork(String worker_name) {
//                switchToTaskQueue();
//            }
//
//            // 收到定时命令消息时，会触发该方法被调用
//            public void doWork() {
//                foo.sayHello();
//            }
//
//            // 实际升级动作
//            private void doUpgrade() {
//                hscl = new HowswapCL("../swap", new String[]{"Foo"});
//                Class cls = hscl.loadClass("Foo");
//                foo = (IFoo)cls.newInstance();
//                foo.SetState(state);
//            }
//        }
//
//        // IFoo 接口定义
//        interface IFoo {
//            void SetState(String);
//            void sayHello();
//        }

    }


    public void testClassIdentity() {
        String classDataRootPath = "C:\\workspace\\Classloader\\classData";
        FileSystemClassLoader fscl1 = new FileSystemClassLoader(classDataRootPath);
        FileSystemClassLoader fscl2 = new FileSystemClassLoader(classDataRootPath);
        String className = "com.example.Sample";
        try {
            Class<?> class1 = fscl1.loadClass(className);
            Object obj1 = class1.newInstance();
            Class<?> class2 = fscl2.loadClass(className);
            Object obj2 = class2.newInstance();
            Method setSampleMethod = class1.getMethod("setSample", Object.class);
            setSampleMethod.invoke(obj1, obj2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

