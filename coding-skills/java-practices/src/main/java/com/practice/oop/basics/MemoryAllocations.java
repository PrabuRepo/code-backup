package com.practice.oop.basics;

/*
 * Heap:
 *   - Each time an object is created in Java it goes into the part of memory known as heap.
 *   - Class member variables(It may be Primitive types or user defined classes) are stored in Heap.
 *   - In a multi-threaded application each thread will have its own stack but will share the same heap. This is why care 
 *     should be taken in your code to avoid any concurrent access issues in the heap space. 
 *  
 * Stack:
 *   - Local method variables(It may be Primitive types or user defined classes) are stored in Stack.
 *   - In Java methods local variables are pushed into stack. When a method is invoked and stack pointer is decremented when a 
 *     method call is completed.
 *   - The stack is thread safe (each thread will have its own stack) but the heap is not thread safe unless guarded with
 *     synchronization through your code.
 */
public class MemoryAllocations {

}
