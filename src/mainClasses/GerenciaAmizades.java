package mainclasses;

import java.util.ArrayList;
import java.util.List;

public class GerenciaAmizades {
	
	private List<SolicitacaoAmizade> listSolicitacoes;

	public GerenciaAmizades(){
		this.listSolicitacoes = new ArrayList<SolicitacaoAmizade>();
	}
	public String enviarSolicitacaoAmizade(Usuario solicitante,Usuario solicitado){
		String idSolicitacao = "solicitacao"+this.listSolicitacoes.size()+"ID";
		SolicitacaoAmizade solicitacao = new SolicitacaoAmizade(idSolicitacao, solicitante,solicitado);
		this.listSolicitacoes.add(solicitacao);
		return idSolicitacao;
	}

	public void aceitarSolicitacaoAmizade(String loginSolicitado, String idsolicitacao){
		int sizeList =this.listSolicitacoes.size();
		for(int i=0;i<sizeList;i++){
			if(this.listSolicitacoes.get(i).getIdSolicitacao().equals(idsolicitacao)){
				if(this.listSolicitacoes.get(i).getSolicitado().getLogin().equals(loginSolicitado)){
					this.listSolicitacoes.get(i).setFoiAceita(true);
					break;
				}
			}
		}
	}
	
	public void removeSolicitacao(String idsolicitacao){
		int sizeList =this.listSolicitacoes.size();
		for(int i=0;i<sizeList;i++){
			if(this.listSolicitacoes.get(i).getIdSolicitacao().equals(idsolicitacao)){
				this.listSolicitacoes.remove(i);
				break;
			}
		}
	}
	
	public Usuario getSolicitante(String idsolicitacao) {
		int sizeList =this.listSolicitacoes.size();
		for(int i=0;i<sizeList;i++){
			if(this.listSolicitacoes.get(i).getIdSolicitacao().equals(idsolicitacao)){
				return this.listSolicitacoes.get(i).getSolicitante();
			}
		} return null;
	}

}
