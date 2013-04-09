package mainclasses;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import tiposordenacao.OrdenaFeedPrincipal;
import tiposordenacao.SonsMaisFavoritadosSistema;
import tiposordenacao.SonsMaisFavoritadosUsuario;
import tiposordenacao.SonsMaisRecentes;
import tiposordenacao.TipoFeedPrincipal;
import util.Utilitario;
import exception.AtributoInexistenteException;
import exception.AtributoInvalidoException;
import exception.DataInvalidaException;
import exception.EmailDuplicadoException;
import exception.EmailInvalidoException;
import exception.LoginDuplicadoException;
import exception.LoginInexistenteException;
import exception.LoginInvalidoException;
import exception.NomeInvalidoException;
import exception.RegraInexistenteException;
import exception.RegraInvalidaException;
import exception.SessaoInexistenteException;
import exception.SessaoInvalidaException;
import exception.SomInexistenteException;
import exception.SomInvalidoException;
import exception.UsuarioInexistenteException;

/**
 * Executa as acoes solicitadas pela {@link NetMusicLive}, gerencia as
 * instrucoes.
 */
public class Sistema implements Serializable {

	private static final long serialVersionUID = 1L;
	private GerenciaSessao gerenciaSessao;
	private GerenciaUsuarioSom gerenciador;
	private OrdenaFeedPrincipal feedPrincipal;

	public Sistema() {
		zerarSistema();
	}

	public void zerarSistema() {
		gerenciaSessao = new GerenciaSessao();
		gerenciador = new GerenciaUsuarioSom();
		feedPrincipal = new SonsMaisRecentes();
	}

	public void criarUsuario(String login, String senha, String nome,
			String email) throws Exception {
		if (!Utilitario.elementIsValid(login)) {
			throw new LoginInvalidoException();

		} else if (!Utilitario.elementIsValid(nome)) {
			throw new NomeInvalidoException();

		} else if (!Utilitario.elementIsValid(email)) {
			throw new EmailInvalidoException();

		} else {
			if (gerenciador.existeLoginUsuario(login)) {
				throw new LoginDuplicadoException();
			}
			if (gerenciador.existeEmailUsuario(email)) {
				throw new EmailDuplicadoException();
			}
			gerenciador.criarUsuario(login, senha, nome, email);
		}
	}

	public boolean verificaLoginESenha(String login, String senha) {
		return gerenciador.verificaLoginESenha(login, senha);
	}

	public String abrirSessao(String login, String senha) throws Exception {
		if (!Utilitario.elementIsValid(login)) {
			throw new LoginInvalidoException();

		} else if (gerenciador.existeLoginUsuario(login)) {
			if (!verificaLoginESenha(login, senha)) {
				throw new LoginInvalidoException();
			}
			return gerenciaSessao.abrirSessao(login, senha);
			
		} else {
			throw new UsuarioInexistenteException();
		}
	}

	public boolean existeSessao(String login) {
		String idsessao = "sessao" + login;
		return gerenciaSessao.existeSessao(idsessao);
	}

	public String getAtributoUsuario(String login, String atributo)
			throws Exception {
		if (!Utilitario.elementIsValid(atributo)) {
			throw new AtributoInvalidoException();
			
		} else if (!Utilitario.elementIsValid(login)) {
			throw new LoginInvalidoException();
			
		} else if (!this.gerenciador.existeLoginUsuario(login)) {
			throw new UsuarioInexistenteException();
			
		} else {
			String result = this.gerenciador.getAtributoUsuario(login,
					atributo);
			if (!result.isEmpty()) {
				return result;
			}
		}
		throw new AtributoInexistenteException();
	}

	public List<Som> getPerfilMusical(String idsessao) throws Exception {
		return gerenciador.getPerfilMusical(gerenciaSessao.getLogin(idsessao));
	}

	public String postarSom(String idSessao, String link, String dataCriacao)
			throws Exception {
		if (!Utilitario.elementIsValid(link)) {
			throw new SomInvalidoException();

		} else if (!Utilitario.dataIsValida(dataCriacao)) {
			throw new DataInvalidaException();
		}

		return gerenciador.postarSom(gerenciaSessao.getLogin(idSessao), link, dataCriacao);
	}

	public String getAtributoSom(String idSom, String atributo)
			throws Exception {
		if (!Utilitario.elementIsValid(idSom)) {
			throw new SomInvalidoException();
		} else if (!Utilitario.elementIsValid(atributo)) {
			throw new AtributoInvalidoException();
		}

		String valorAtributo = gerenciador.getAtributoSom(idSom, atributo);
		if (!valorAtributo.isEmpty()) {
			return valorAtributo;
		}
		throw new AtributoInexistenteException();
	}

	public void seguirUsuario(String idSessaoSeguidor, String loginSeguido)
			throws Exception {
		String loginSeguidor = gerenciaSessao.getLogin(idSessaoSeguidor);
		
		if (!Utilitario.elementIsValid(loginSeguido)) {
			throw new LoginInvalidoException();
			
		} else if (loginSeguidor.equals(loginSeguido)) {
			throw new LoginInvalidoException();
		}

		if (gerenciador.existeLoginUsuario(loginSeguido)) { 
			gerenciador.seguirUsuario(loginSeguidor, loginSeguido);
			
		} else {
			throw new LoginInexistenteException();
		}
	}

	public List<Usuario> getListaDeSeguidores(String idSessao) throws Exception {
		return gerenciador.getListaSeguidoresUsuario(gerenciaSessao.getLogin(idSessao));
	}

	public int getNumeroDeSeguidores(String idSessao) throws Exception {
		return getListaDeSeguidores(idSessao).size();
	}

	public List<Usuario> getFontesDeSons(String idSessao) throws Exception {
		return gerenciador.getFontesSomUsuario(gerenciaSessao.getLogin(idSessao));
	}

	public String getIDUsuario(String idsessao) throws Exception {
		return gerenciador.getAtributoUsuario(gerenciaSessao.getLogin(idsessao), "id");
	}

	public List<Som> getVisaoDosSons(String idSessao) throws Exception {
		return gerenciador.getVisaoSonsUsuario(gerenciaSessao.getLogin(idSessao));
	}

	public List<Som> getSonsFavoritos(String idSessao) throws Exception {
		return gerenciador.getSonsFavoritosUsuario(gerenciaSessao.getLogin(idSessao));
	}

	public List<Som> getFeedExtra(String idSessao) throws Exception {
		return gerenciador.getFeedExtraUsuario(gerenciaSessao.getLogin(idSessao));
	}
	
	public List<Som> getMainFeed(String idSessao) throws Exception {
		return feedPrincipal.ordena(getFontesDeSons(idSessao), getSonsFavoritos(idSessao));
	}

	public void favoritarSom(String idSessao, String idSom) throws Exception {
		if(!Utilitario.elementIsValid(idSom)){
			throw new SomInvalidoException();
		}
		
		if(!gerenciador.favoritarSom(gerenciaSessao.getLogin(idSessao), idSom)){
			throw new SomInexistenteException();
		}
	}

	public void setMainFeedRule(String idsessao, String rule) throws Exception {
		gerenciaSessao.verificaSessao(idsessao);
		if (!Utilitario.elementIsValid(rule)) {
			throw new RegraInvalidaException();
		}

		if (rule.equals(TipoFeedPrincipal.SONS_RECENTES.toString())) {
			feedPrincipal = new SonsMaisRecentes();
			
		} else if (rule.equals(TipoFeedPrincipal.SONS_FAVORITADOS_SISTEMA
				.toString())) {
			feedPrincipal = new SonsMaisFavoritadosSistema();
			
		} else if (rule.equals(TipoFeedPrincipal.SONS_FAVORITADOS_USUARIO
				.toString())) {
			feedPrincipal = new SonsMaisFavoritadosUsuario();
			
		} else {
			throw new RegraInexistenteException();
		}
	}

	public void encerrarSessao(String login) {
		gerenciaSessao.encerrarSessao("sessao" + login);
	}

	public void encerrarSistema() {

	}
}