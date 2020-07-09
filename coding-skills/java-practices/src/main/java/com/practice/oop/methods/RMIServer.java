package com.practice.oop.methods;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

/*
 * Remote Method Invocation:
 *   Remote Method Invocation (RMI) is an API which allows an object to invoke a method on an object that exists in another 
 *   address space, which could be on the same machine or on a remote machine. Through RMI, object running in a JVM present 
 *   on a computer (Client side) can invoke methods on an object present in another JVM (Server side). RMI creates a public 
 *   remote server object that enables client and server side communications through simple method calls on the server object.
 * Working of RMI: 
 *    The communication between client and server is handled by using two intermediate objects: Stub object (on client side)
 *     and Skeleton object (on server side).
 * Stub Object:
 * The stub object on the client machine builds an information block and sends this information to the server. The block 
 * consists of
 *  - An identifier of the remote object to be used
 *  - Method name which is to be invoked
 *  - Parameters to the remote JVM
 *  
 *  Skeleton Object:
 *    The skeleton object passes the request from the stub object to the remote object. It performs following tasks
 *       - It calls the desired method on the real object present on the server.
 *       - It forwards the parameters received from the stub object to the method.
 *       
 * Steps to implement Interface 
 *  - Defining a remote interface
 *  - Implementing the remote interface 
 *  - Creating Stub and Skeleton objects from the implementation class using rmic (rmi complier)
 *  - Start the rmiregistry
 *  - Create and execute the server application program
 *  - Create and execute the client application program.
 */

/*
 * Step 1: Defining the remote interface
 *    The first thing to do is to create an interface which will provide the description of the methods that can be invoked by
 *    remote clients. This interface should extend the Remote interface and the method prototype within the interface should
 *    throw the RemoteException.
 */
interface Search extends Remote {
	// Declaring the method prototype
	public String query(String search) throws RemoteException;
}

/*
 * Step 2: Implementing the remote interface 
 *    The next step is to implement the remote interface. To implement the remote interface, the class should extend to
 *    UnicastRemoteObject class of java.rmi package. Also, a default constructor needs to be created to throw the 
 *    java.rmi.RemoteException from its parent constructor in class.
 */
class SearchQuery extends UnicastRemoteObject implements Search {
	private static final long serialVersionUID = 3463463L;

	// Default constructor to throw RemoteException from its parent constructor
	SearchQuery() throws RemoteException {
		super();
	}

	// Implementation of the query interface
	public String query(String search) throws RemoteException {
		String result;
		if (search.equals("Reflection in Java"))
			result = "Found";
		else
			result = "Not Found";

		return result;
	}
}

/*
 * Step 3:  Creating Stub and Skeleton objects from the implementation class using rmic 
 *    The rmic tool is used to invoke the rmi compiler that creates the Stub and Skeleton objects. Its prototype is rmic 
 *    classname. For above program the following command need to be executed at the command prompt rmic SearchQuery 
 * 
 * STEP 4: Start the rmiregistry 
 *    Start the registry service by issuing the following command at the command prompt start rmiregistry
 * 
 * STEP 5: Create and execute the server application program
 *    The next step is to create the server application program and execute it on a separate command prompt.
 *    The server program uses createRegistry method of LocateRegistry class to create rmiregistry within the server JVM with 
 *    the port number passed as argument.
 *    The rebind method of Naming class is used to bind the remote object to the new name.
 */
public class RMIServer {
	public static void main(String args[]) {
		try {
			// Create an object of the interface implementation class
			Search obj = new SearchQuery();

			// rmiregistry within the server JVM with port number 1900
			LocateRegistry.createRegistry(1900);

			// Binds the remote object by the name geeksforgeeks
			Naming.rebind("rmi://localhost:1900" + "/geeksforgeeks", obj);
		} catch (Exception ae) {
			System.out.println(ae);
		}
	}
}
