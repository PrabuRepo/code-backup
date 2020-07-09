package com.practice.basics.garbage.collection;

/*
 * Garbage collector frees the memory occupied by the unreachable objects during the java program by deleting these unreachable 
 * objects. It ensures that the available memory will be used efficiently, but does not guarantee that there will be sufficient 
 * memory for the program to run.
 * 
 * Garbage collections happened in Heap memory.
 * 
 * GC is a daemon thread. A daemon thread runs behind the application. It is started by JVM. The thread stops when all non-daemon 
 * threads stop.
 * 
 * The Garbage Collection can not be forced, though there are few ways by which it can be requested there is no guarantee that
 * these requests will be taken care of by JVM.
 * 
 * Different ways to call garbage collector? 
 *   Garbage collection can be invoked using System.gc() or Runtime.getRuntime().gc().
 *   
 * What is the purpose of overriding finalize() method? 
 * The finalize() method should be overridden for an object to include the clean up code or to dispose of the system resources
 * that should to be done before the object is garbage collected.
 */
public class GarbageCollection {

}
