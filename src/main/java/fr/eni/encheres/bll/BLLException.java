package fr.eni.encheres.bll;

import java.util.ArrayList;
import java.util.List;

public class BLLException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Exception> bllExceptions ;
	
	public BLLException() {
		super();
		this.bllExceptions = new ArrayList<Exception>();
	}
	
	public void addException(Exception e) {
	    if (bllExceptions == null) {  
	    bllExceptions = new  ArrayList<Exception>();
	    		}
	    bllExceptions.add(e) ;
	}
	
	public boolean isEmpty() {
		return bllExceptions.size() > 0? false:true;
	}

	public List<Exception> getBllExceptions() {
		return bllExceptions;
	}
}
