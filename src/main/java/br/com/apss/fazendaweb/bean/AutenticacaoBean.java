package br.com.apss.fazendaweb.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.Enumeration;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.flywaydb.core.Flyway;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.postgresql.ds.PGPoolingDataSource;

import br.com.apss.fazendaweb.model.Usuario;
import br.com.apss.fazendaweb.service.UsuarioService;
import br.com.apss.fazendaweb.util.FlywayInit;

@Named
@SessionScoped
public class AutenticacaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	private Usuario usuarioLogado;

	@Inject
	UsuarioService usuarioService;
	
	FlywayInit f = new FlywayInit();
	

	@PostConstruct
	public void inicializarBean() {
		usuario = new Usuario();
		usuario.setNome("ADMIN");
		f.iniFlywa();
	}

	public void validar() {
		try {
			usuarioLogado = usuarioService.autenticar(usuario.getNome(), usuario.getSenha());
			if (usuarioLogado == null) {
				Messages.addGlobalError("Login ou senha não correspondem");
				return;
			}
			Faces.redirect("./autenticacao.xhtml");
		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError(erro.getMessage());
		}
	}

	public void autenticar() {
		try {
			System.out.println("metodo autenticar na class: AutenticacaoBean");
			usuarioLogado = usuarioService.autenticar(usuario.getNome(), usuario.getSenha());
			if (usuarioLogado == null) {
				Messages.addGlobalError("Login ou senha não correspondem");
				return;
			}
			HttpSession session;
			FacesContext ctx = FacesContext.getCurrentInstance();
			session = (HttpSession) ctx.getExternalContext().getSession(false);
			session.setAttribute("usuarioAutenticado", usuarioLogado);
			Faces.redirect("./home.xhtml");
		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError(erro.getMessage());
		}

	}

	public String logout() {
		HttpSession session;
		FacesContext ctx = FacesContext.getCurrentInstance();
		session = (HttpSession) ctx.getExternalContext().getSession(false);
		session.setAttribute("usuarioAutenticado", null);
		Enumeration<String> vals = session.getAttributeNames();
		while (vals.hasMoreElements()) {
			session.removeAttribute(vals.nextElement());
		}
		return "autenticacao?faces-redirect=true";

	}
	public String logout2() {
		FacesContext.getCurrentInstance().getExternalContext()
		.invalidateSession();
		return "/autenticacao?faces-redirect=true";
		}

	/****************************** Getters e Setters *************************/

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

}
