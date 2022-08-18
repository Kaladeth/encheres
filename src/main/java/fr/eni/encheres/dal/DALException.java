package fr.eni.encheres.dal;

	public class DALException extends Exception {
	
	    /**
	     * 
	     */
	    private static final long serialVersionUID = 1L;
	    public DALException(String message, Throwable exception) {
	        super(message, exception);
	    }
	    
	    @Override
	    public String getMessage() {
	        if (this.getCause().getMessage() != null) {	        		
	        		System.out.println("Note technique : \n" + this.getCause().getMessage());}
	        return "Erreur dans l'accès aux données : " +  super.getMessage();
	    }
	    
	    public DALException(String message) {
	    	super(message);
	    	}
	    }
	
	
