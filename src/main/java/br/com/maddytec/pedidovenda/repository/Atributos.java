package br.com.maddytec.pedidovenda.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.maddytec.pedidovenda.model.Atributo;
import br.com.maddytec.pedidovenda.repository.filter.AtributoFilter;
import br.com.maddytec.pedidovenda.util.jpa.Transactional;

public class Atributos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Atributo guardar(Atributo atributo) {
		return manager.merge(atributo);
	}

	@Transactional
	public void remover(Atributo atributo) {
		atributo = porId(atributo.getId());
		manager.remove(atributo);
		manager.flush();
	}

	public Atributo porNome(String nome) {
		try {
			return manager
					.createQuery("from Atributo where upper(nome) = :nome",
							Atributo.class)
					.setParameter("nome", nome.toUpperCase()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Atributo porId(Long id) {
		return manager.find(Atributo.class, id);
	}

	public List<Atributo> lista() {
		return manager.createQuery("from Atributo", Atributo.class)
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Atributo> filtrados(AtributoFilter filtro) {

		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Atributo.class);

		// MatchMode.ANYWHERE determina o like '%%'
		if (StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(),
					MatchMode.ANYWHERE));
		}

		// MatchMode.ANYWHERE determina o like '%%'
		if (StringUtils.isNotBlank(filtro.getDescricao())) {
			criteria.add(Restrictions.ilike("descricao", filtro.getDescricao(),
					MatchMode.ANYWHERE));
		}

		return criteria.addOrder(Order.asc("nome")).list();
	}

}
