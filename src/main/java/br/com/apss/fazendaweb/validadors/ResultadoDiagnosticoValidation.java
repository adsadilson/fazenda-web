package br.com.apss.fazendaweb.validadors;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class ResultadoDiagnosticoValidation implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		Object atributo = component.getAttributes().get("dataDiagnostico");
		Date data  = (Date) atributo;
		String res = (String) value;
		if (data != null && res == null) {
			FacesMessage msg = new FacesMessage("Selecione o resultado diagnóstico.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
	}
}