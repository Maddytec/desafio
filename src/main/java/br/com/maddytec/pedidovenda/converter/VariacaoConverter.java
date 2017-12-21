package br.com.maddytec.pedidovenda.converter;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.convert.ClientConverter;

import br.com.maddytec.pedidovenda.model.Variacao;
import br.com.maddytec.pedidovenda.repository.Variacoes;


@FacesConverter(forClass = Variacao.class)
public class VariacaoConverter implements Converter, ClientConverter {

	@Inject
	private Variacoes variacoes;

	@Override
	public Object getAsObject(FacesContext context, UIComponent componet,
			String value) {
		Variacao retorno = null;

		if (StringUtils.isNotEmpty(value)) {
			Long id = new Long(value);
			return retorno = variacoes.porId(id);
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			Variacao variacao = (Variacao) value;
			return variacao.getId() == null ? null : variacao.getId()
					.toString();
		}

		return "";
	}

	@Override
	public String getConverterId() {
		return "br.com.maddytec.Variacao";
	}

	@Override
	public Map<String, Object> getMetadata() {
		return null;
	}

}
