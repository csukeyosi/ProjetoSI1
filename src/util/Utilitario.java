package util;

public abstract class Utilitario {
	
	/**
	 * Metodo que auxilia na verificação de variaveis na forma de String.
	 * 
	 * @param String
	 *            element
	 * @return true se elemento nao for null ou vazio, false caso contrario.
	 */
	public static boolean elementIsValid(String element) {
		if (element == null || element.isEmpty()) {
			return false;
		}
		return true;
	}

}
