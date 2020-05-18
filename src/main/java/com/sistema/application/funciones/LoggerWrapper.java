package com.sistema.application.funciones;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerWrapper {
    public static Logger logger = null;
    public static Logger loggerHibernate = null;
    private static LoggerWrapper instance = null;
    
    public static LoggerWrapper getInstance(String className) {
        if(instance == null) {
            prepareLogger(className, false);
            instance = new LoggerWrapper ();
        }
        return instance;
    }
    
    public static LoggerWrapper getInstance(String className, boolean tunOffHibernateLog) {
        if(instance == null) {
            prepareLogger(className, tunOffHibernateLog);
            instance = new LoggerWrapper ();
        }
        return instance;
    }
    
    private static void prepareLogger(String className, boolean tunOffHibernateLog) {
        logger = Logger.getLogger(className);
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new LogFormatter());
        logger.addHandler(handler);
        logger.setUseParentHandlers(false);
        logger.setLevel(Level.FINEST);
        if(tunOffHibernateLog)
            Logger.getLogger("org.hibernate").setLevel(Level.OFF); // Para no mostrar los logs de Hibernate
    }

    // Mensaje genérico
    public void log(Level level, String msg) {
        logger.log(level, msg);
    }

    public void error(String msg) {
        logger.log(Level.SEVERE, msg);
    }

    public void info(String msg) {
        logger.log(Level.INFO, msg);
    }

    public void warning(String msg) {
        logger.log(Level.WARNING, msg);
    }
}
