package util;

import java.util.ArrayList;
import java.util.List;

public abstract class Utilitario {

	/**
	 * Verifica se element e null ou "".
>>>>>>> 1adf4227d54a7e14405aae516fdc02f521aecc42
	 * 
	 * @param String
	 *            String a ser testada.
	 * @return true se elemento nao for null ou vazio, false caso contrario.
	 */
	public static boolean elementIsValid(String element) {
		if (element == null || element.isEmpty()) {
			return false;
		}
		return true;
	}

	/**
<<<<<<< HEAD
	 * 
	 * @param dataParam
	 * @return
=======
	 * Verifica se a data e valida.
	 * @param dataParam
	 * 				Data na forma de String (dd/mm/aaaa).
	 * @return true caso a data seja valida, false caso contrario.
>>>>>>> 1adf4227d54a7e14405aae516fdc02f521aecc42
	 */
	public static boolean dataIsValida(String dataParam) {
		try {
			String[] datas = dataParam.split("/");
			Integer dia = Integer.parseInt(datas[0]);
			Integer mes = Integer.parseInt(datas[1]);
			Integer ano = Integer.parseInt(datas[2]);
			if (dia < 1 || dia > 31) {
				return false;
			} else if (ano < 2013) {
				return false;
			} else if (mes < 1 || mes > 12) {
				return false;
			} else {
				if (mes == 2) {
					if (dia > 29) {
						return false;
					}
					// se o ano for bisexto e dia for igual a 29, entao false.
					else if (!((ano % 4 == 0) && ((ano % 100 != 0) || (ano % 400 == 0)))
							&& dia == 29) {
						return false;
					}
				} else {
					List<Integer> meses30dias = new ArrayList<Integer>();
					meses30dias.add(4);
					meses30dias.add(6);
					meses30dias.add(9);
					meses30dias.add(11);
					if (meses30dias.contains(mes) && dia >= 31) {
						return false;
					}
				}
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
