package ru.spbstu.telematics.java;

import java.util.Iterator;
import junit.framework.TestCase;

import org.junit.Test;
import static org.junit.Assert.*;


/**
 * Unit test for simple App.
 */
public class test{


    HashSet<Integer> totest;
    java.util.HashSet<Integer> normHash;


    public test(){
        totest = new HashSet<>();
        normHash = new java.util.HashSet<Integer>();
        for(int i=0;i<=15;i++){
            totest.add(i);
            normHash.add(i);
        }
    }

    @Test
    public void test(){
        Iterator<Integer> testiter =  totest.iterator();
        Iterator<Integer> javaiter =  normHash.iterator();


        assertEquals("IteratorHasNext Fail!",javaiter.hasNext(),testiter.hasNext());
        assertEquals("IteratorNext Fail!",javaiter.next(),testiter.next());

        //go to next element
        javaiter.next();
        testiter.next();

        //delete it
        javaiter.remove();
        testiter.remove();

        assertEquals("IteratorRemove Fail!",javaiter.next(), testiter.next());
        assertEquals("IsEmpty Fail!",normHash.isEmpty(), totest.isEmpty());
        assertEquals("Contains Fail!",normHash.contains(3), totest.contains(3));

    }
}