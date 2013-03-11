package mainclasses;

public class SolicitacaoAmizade {

	private String idSolicitacao;
	private Usuario solicitante,solicitado;
	private boolean foiAceita;
	
	public SolicitacaoAmizade(String id,Usuario solicitante, Usuario solicitado){
		setFoiAceita(false);
		setSolicitante(solicitante);
		setSolicitado(solicitado);
		setIdSolicitacao(id);
	}

	public boolean isFoiAceita() {
		return foiAceita;
	}

	public void setFoiAceita(boolean foiAceita) {
		this.foiAceita = foiAceita;
	}

	public String getIdSolicitacao() {
		return idSolicitacao;
	}

	private void setIdSolicitacao(String idSolicitacao) {
		this.idSolicitacao = idSolicitacao;
	}

	public Usuario getSolicitante() {
		return this.solicitante;
	}

	private void setSolicitante(Usuario userSolicitante) {
		this.solicitante = userSolicitante;
	}

	public Usuario getSolicitado() {
		return this.solicitado;
	}

	private void setSolicitado(Usuario userSolicitado) {
		this.solicitado = userSolicitado;
	}
}