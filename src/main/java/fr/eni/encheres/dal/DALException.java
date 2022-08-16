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
        return "Accès aux données : " +  super.getMessage() + "\nNote technique : \n" + this.getCause().getMessage();
    }

}
