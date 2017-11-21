package br.com.apss.fazendaweb.validadors;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class DtPartoValidation implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		Object dt = component.getAttributes().get("dtCobertura");
		Date dataCobertura = (Date) dt;
		
		if (dataCobertura == null) {
			return;
		}
		
		Date dataParto = (Date) value;
		if (dataCobertura.after(dataParto)) {
			FacesMessage msg = new FacesMessage("Informe outra data para o parto.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
	}
}