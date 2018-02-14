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
            totest.add(i);  normHash.add(i);
        }
    }

    @Test //has next
    public void test1(){
        Iterator<Integer> testiter =  totest.iterator();
        Iterator<Integer> javaiter =  normHash.iterator();
        assertEquals("Something went wrong",javaiter.hasNext(),testiter.hasNext());
    }
    @Test //next
    public void test2() {
        Iterator<Integer> testiter =  totest.iterator();
        Iterator<Integer> javaiter =  normHash.iterator();
        assertEquals("IteratorNext Fail!", javaiter.next(), testiter.next());
    }

    @Test //next
    public void test3() {
        Iterator<Integer> testiter =  totest.iterator();
        Iterator<Integer> javaiter =  normHash.iterator();
        //go to next element
        javaiter.next();  testiter.next();
        //delete it
        javaiter.remove();  testiter.remove();
        assertEquals("IteratorRemove Fail!", javaiter.next(), testiter.next());
    }

    @Test //next -> isempty
    public void test4() {
        Iterator<Integer> testiter =  totest.iterator();
        Iterator<Integer> javaiter =  normHash.iterator();
        //go to next element
        javaiter.next();  testiter.next();
        //delete it
        javaiter.remove();  testiter.remove();
        assertEquals("IsEmpty Fail!",normHash.isEmpty(), totest.isEmpty());
    }

    @Test //next ->contains number 5
    public void test5() {
        Iterator<Integer> testiter =  totest.iterator();
        Iterator<Integer> javaiter =  normHash.iterator();
        //go to next element
        javaiter.next();  testiter.next();
        //delete it
        javaiter.remove();  testiter.remove();
        assertEquals("Contains Fail!",normHash.contains(5), totest.contains(5));
    }


}
