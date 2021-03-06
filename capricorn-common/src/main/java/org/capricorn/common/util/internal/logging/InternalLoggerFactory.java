package org.capricorn.common.util.internal.logging;

public abstract class InternalLoggerFactory {

    private static volatile InternalLoggerFactory defaultFactory
            = newDefaultFactory(InternalLoggerFactory.class.getName());


    private static InternalLoggerFactory newDefaultFactory(String name){
        InternalLoggerFactory f;
        try{
            f = new Slf4JLoggerFactory(true);
            f.newInstance(name).debug("Using SLF4J as the default logging framework");
        }catch (Throwable t){
            f =new JdkLoggerFactory();
            f.newInstance(name).debug("Using java.util.logging as the deefault logging framework");
        }
        return f;
    }

    public static InternalLoggerFactory getDefaultFactory(){
        return defaultFactory;
    }

    public static void setDefaultFactory(InternalLoggerFactory defaultFactory){
        if(defaultFactory == null){
            throw new NullPointerException("defaultFactory");
        }
        InternalLoggerFactory.defaultFactory = defaultFactory;
    }

    public static InternalLogger getInstance(Class<?> clazz){
        return getInstance(clazz.getName());
    }

    public static InternalLogger getInstance(String name){
        return getDefaultFactory().newInstance(name);
    }
    /**
     * Creates a new logger instance with the specified name
     * @param name
     * @return
     */
    protected abstract InternalLogger newInstance(String name);
}
