package util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Menssagens {

	/**
	 * Adiciona uma mensagem de erro no contexto.
	 * 
	 * @param msg
	 *            Mensagem de erro desejada.
	 */
	public static void addMsgErro(String msg) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, ""));
	}

	/**
	 * Adiciona uma mensagem de sucesso no contexto.
	 * 
	 * @param msg
	 *            Mensagem de sucesso desejada.
	 */
	public static void addMsgSucesso(String msg) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, msg, ""));
	}
}
