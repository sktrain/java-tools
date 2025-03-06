package sk.train;


import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.FileAppender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SampleSL4JWithLogBack {

    private static Logger logger = LoggerFactory.getLogger("sk.train");

    public static void main(String argv[]) {
        //loggerconfGlobal();
        //loggerconfLocal();

        logger.debug("App start");
        for (int i=0; i <=15 ; ++i) {
            callWithLogging(i);
        }
        logger.debug("App done");
    }

    private static void callWithLogging(final int i) {
        try {
            //logger.trace("fak for value = " + i);
            logger.trace("fak for value {}", i);  //better perfomance
            Fakultaet.fak(i);
        } catch (Exception ex) {
            // Log the exception
            logger.warn("Exception with value {}", i, ex);

        }
    }

    //configuration for Logging-Provider LogBack
    private static void loggerconfLocal() {
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        PatternLayoutEncoder ple = new PatternLayoutEncoder();
        ple.setPattern("%date %level [%thread] %logger{10} [%file:%line] %msg%n");
        ple.setContext(lc);
        ple.start();

        ConsoleAppender<ILoggingEvent> cp = new ConsoleAppender<>();
        cp.setTarget("System.out");
        cp.setEncoder(ple);
        cp.setContext(lc);
        cp.start();

        ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("sk.train");
        logger.addAppender(cp);
        System.out.println(cp.getContext().getName());
        logger.setLevel(Level.TRACE);
        //logger.setAdditive(false); /* set to true if root should log too */
    }

    private static void loggerconfGlobal() {
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        PatternLayoutEncoder ple = new PatternLayoutEncoder();
        ple.setPattern("%date %level [%thread] %logger{10} [%file:%line] %msg%n");
        ple.setContext(lc);
        ple.start();

        FileAppender<ILoggingEvent> fp = new FileAppender<>();
        fp.setFile("Logback.log");
        fp.setEncoder(ple);
        fp.setContext(lc);
        fp.start();

        ch.qos.logback.classic.Logger logger =
                (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
        logger.addAppender(fp);
        System.out.println(fp.getContext().getName());
        logger.setLevel(Level.TRACE);
        logger.setAdditive(false); /* set to true if root should log too */
    }

}
