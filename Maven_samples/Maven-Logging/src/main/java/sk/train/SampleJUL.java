package sk.train;

import java.io.IOException;
import java.util.logging.*;

public class SampleJUL {

    private static Logger logger = Logger.getLogger("sk.train");

    public static void main(String argv[]) {
        //loggerconfGlobal();
        loggerconfLocal();

        logger.fine("App start");
        for (int i=0; i <=15 ; ++i) {
            callWithLogging(i);
        }
        logger.fine("App done");
    }

    private static void callWithLogging(final int i) {
        try {
            logger.finer(() -> "fak for value = " + i);
            Fakultaet.fak(i);
        } catch (Exception ex) {
            // Log the exception
            //logger.log(Level.WARNING, "Exception with value = " + i, ex);   // with Stacktrace
            logger.log(Level.WARNING,  ex, () -> "Exception with value " + i);   // with Stacktrace + better performance
            //logger.warning("Exception with value = " + i);
        }
    }

    private static void loggerconfGlobal() {
        Handler fh = null;
        try {
            fh = new FileHandler("Application.log", true);
        } catch (IOException e) {
            logger.severe("Filehandler not initialized");
        }
        Logger.getLogger("").addHandler(fh);    //global configuration
        logger.setLevel(Level.FINEST);
    }

    private static void loggerconfLocal() {
        Handler fh = null;
        try {
            fh = new FileHandler("MyApplication.log", true);
        } catch (IOException e) {
            logger.severe("Filehandler not initialized");  //useless
        }
        Logger mylog =  Logger.getLogger("sk.train");
        mylog.setUseParentHandlers(false);  //no global configuration
        mylog.addHandler(fh);    //local configuration
        Logger.getLogger("sk.train").setLevel(Level.FINEST);
    }

}
