package com.practice.concurrency;

/*
 * In general each thread has its own copy of a variable, such that one thread is not concerned with the value of the same variable
 * in the other thread. But sometimes this may not be the case. Consider a scenario in which the count variable is holding the
 * number of times a method is called for a given class irrespective of any thread calling, in this case, irrespective of thread 
 * access the count has to be increased and value needs to be consistent across all the threads. In this case the count variable 
 * is declared as volatile. 
 * The copy of a volatile variable is stored in the main memory, so every time a thread access the variable even for reading
 * purpose the local copy is updated each time from the main memory. The volatile variable may have performance issues.
 */
public class VolatileProperties {
}
