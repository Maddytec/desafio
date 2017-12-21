package br.com.maddytec.pedidovenda.converter;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.convert.ClientConverter;

import br.com.maddytec.pedidovenda.model.Atributo;
import br.com.maddytec.pedidovenda.repository.Atributos;


@FacesConverter(forClass = Atributo.class)
public class AtributoConverter implements Converter, ClientConverter {

	@Inject
	private Atributos atributos;

	@Override
	public Object getAsObject(FacesContext context, UIComponent componet,
			String value) {
		Atributo retorno = null;

		if (StringUtils.isNotEmpty(value)) {
			Long id = new Long(value);
			return retorno = atributos.porId(id);
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			Atributo atributo = (Atributo) value;
			return atributo.getId() == null ? null : atributo.getId()
					.toString();
		}

		return "";
	}

	@Override
	public String getConverterId() {
		return "br.com.maddytec.Atributo";
	}

	@Override
	public Map<String, Object> getMetadata() {
		return null;
	}

}
