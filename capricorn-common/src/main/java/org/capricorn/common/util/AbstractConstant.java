package org.capricorn.common.util;

import java.util.concurrent.atomic.AtomicLong;

public abstract class AbstractConstant<T extends AbstractConstant<T>> implements Constant<T> {

    private static final AtomicLong uniqueIdGenerator = new AtomicLong();

    private final int id;
    private final String name;
    private final long uniqueId;


    protected AbstractConstant(int id,String name){
        this.id = id;
        this.name = name;
        this.uniqueId = uniqueIdGenerator.getAndIncrement();
    }

    @Override
    public final String name(){ return name;}

    @Override
    public final int id(){ return id;}

    @Override
    public final String toString(){ return name();}

    @Override
    public final int hashCode(){ return super.hashCode();}

    public final boolean equals(Object obj){ return super.equals(obj);}

    public final int compareTo(T o){
        if (this == o){
            return 0;
        }
        AbstractConstant<T> other = o;
        int returnCode ;
        returnCode = hashCode() - other.hashCode();
        if(returnCode != 0){
            return returnCode;
        }
        if(uniqueId < other.uniqueId){
            return -1;
        }
        if(uniqueId > other.uniqueId){
            return 1;
        }
        throw new Error("Failed to compare two different constants");
    }
}
