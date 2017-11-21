package br.com.apss.fazendaweb.validadors;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class DataDiagnosticoValidation implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		Object atributo = component.getAttributes().get("resulDiagnostico");
		String res  = (String) atributo;
		Date data = (Date) value;
		if (data == null && res != null) {
			FacesMessage msg = new FacesMessage("Informe uma data.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
	}
}