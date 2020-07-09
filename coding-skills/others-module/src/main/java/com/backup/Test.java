package com.backup;

public class Test {

	public static void main(String[] args) {
		
		syso
	}
	
	public void static merge(ArrayList<Integer> a, ArrayList<Integer> b) {
	    if(a.size() == 0 && b.size() == 0) return;
	    int i=0, j=0;
	    while(i < a.size() && j < b.size()){
	        if(a.get(i) >= b.get(j)){ 
	            a.add(i, b.get(j));
	            j++;
	        }
	        i++;
	    }
	    
	    while(j < b.size()){
	        a.add(b.get(i));
	    }
	    
	}

}
