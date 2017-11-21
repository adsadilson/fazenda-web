package br.com.apss.fazendaweb.util;


import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

import br.com.apss.fazendaweb.model.Usuario;

@SuppressWarnings("serial")
public class AutenticacaoListener implements PhaseListener {

	  @Override
	    public void afterPhase(PhaseEvent event) {
	        FacesContext fc = event.getFacesContext();
	        ExternalContext ec = fc.getExternalContext();
	        System.out.println(fc.getViewRoot().getViewId());
	        if (!fc.getViewRoot().getViewId().contains("autenticacao.xhtml")) {
	            HttpSession session = (HttpSession) ec.getSession(true);
	            Usuario usuario = (Usuario) session.getAttribute("usuarioAutenticado");

	            if (usuario == null) {
	                try {
	                	System.out.println(ec.getRequestContextPath() + "/autenticacao.jsf");
	                    ec.redirect(ec.getRequestContextPath() + "/autenticacao.jsf");
	                } catch (IOException ex) {
	                    ex.printStackTrace();
	                }
	            }
	        }
	    }

	    @Override
	    public void beforePhase(PhaseEvent event) {
	    }

	    @Override
	    public PhaseId getPhaseId() {
	        return PhaseId.RESTORE_VIEW;
	    }

}