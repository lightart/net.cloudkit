package net.cloudkit.integration;

import net.cloudkit.integration.services.AbstractServiceExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.tanukisoftware.wrapper.WrapperListener;
import org.tanukisoftware.wrapper.WrapperManager;

/**
 * 实现WrapperListener 由Java Service Wrapper执行
 * http://wrapper.tanukisoftware.com/doc/english/download.jsp
 *
 * @author hongquanli <hongquanli@qq.com>
 * @version 1.0 2015年08月26日 上午11:38:34
 */
public class Bootstrap implements WrapperListener {

    protected static Logger logger = LoggerFactory.getLogger(Bootstrap.class);

    private FileSystemXmlApplicationContext applicationContext = null;

    /*
    private final static String[] integration = {
            "/application-context.xml",
            "/backup/application-context-ftp.xml",
            "/application-context-task.xml"
    };
    */

    /**
     * @param args
     */
    @SuppressWarnings("resource")
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        // 打印参数
        for (String arg : args) {
            logger.debug(arg);
        }
        WrapperManager.start(new Bootstrap(), args);


    }

    @Override
    public void controlEvent(int event) {
        logger.debug("controlEvent(" + event + ")");

        boolean bool = (event == WrapperManager.WRAPPER_CTRL_LOGOFF_EVENT) && (WrapperManager.isLaunchedAsService() || WrapperManager.isIgnoreUserLogoffs());
        if (bool) {

        } else {
            WrapperManager.stop(0);
        }
    }

    @Override
    public Integer start(String[] args) {
        // 打印参数
        /*
        for (String arg : args)
            logger.debug(arg);
        */
        for(int i = 0; i < args.length; i++) {
            logger.debug("ARG[" + i + "]=" + args[i]);
        }

        // Resource resource = new ClassPathResource("application-context.xml");
        // BeanFactory factory = new XmlBeanFactory(resource);

        // 用classpath路径
        // ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:application-context.xml");
        // ApplicationContext factory = new ClassPathXmlApplicationContext("appcontext.xml");

        // ClassPathXmlApplicationContext使用了file前缀是可以使用绝对路径的
        // ApplicationContext factory = new ClassPathXmlApplicationContext("file:F:/workspace/example/src/application-context.xml");

        // 用文件系统的路径,默认指项目的根路径
        // ApplicationContext factory = new FileSystemXmlApplicationContext("src/application-context.xml");
        // ApplicationContext factory = new FileSystemXmlApplicationContext("webRoot/WEB-INF/application-context.xml");


        // 使用了classpath:前缀,这样,FileSystemXmlApplicationContext也能够读取classpath下的相对路径
        // ApplicationContext factory = new FileSystemXmlApplicationContext("classpath:application-context.xml");
        // ApplicationContext factory = new FileSystemXmlApplicationContext("file:F:/workspace/example/src/application-context.xml");

        // 不加file前缀
        // ApplicationContext factory = new FileSystemXmlApplicationContext("F:/workspace/example/src/application-context.xml");

        // new ClassPathXmlApplicationContext(integration, Bootstrap.class);
        // new ClassPathXmlApplicationContext("classpath:*.*");
        applicationContext = new FileSystemXmlApplicationContext("classpath:application-context*.xml");
        applicationContext.start();
        // new FileSystemXmlApplicationContext("application-context.xml");

        return null;
    }

    @Override
    public int stop(int exitCode) {
        logger.debug("stop(" + exitCode + ")");
        if(applicationContext != null) {
            applicationContext.stop();
        }
        return exitCode;
    }

}
