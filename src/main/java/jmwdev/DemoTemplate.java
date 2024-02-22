package jmwdev;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DemoTemplate {

    static Logger logger = LogManager.getLogger(Logger.class); // REPLACE

    public static void main(String[] args) {
        logger.info("started");
        // TODO
        logger.info("finished");
    }
}
