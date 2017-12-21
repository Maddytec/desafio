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

import br.com.maddytec.pedidovenda.model.Variacao;
import br.com.maddytec.pedidovenda.repository.filter.VariacaoFilter;
import br.com.maddytec.pedidovenda.util.jpa.Transactional;

public class Variacoes implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Variacao guardar(Variacao variacao) {
		return manager.merge(variacao);
	}

	@Transactional
	public void remover(Variacao variacao) {
		variacao = porId(variacao.getId());
		manager.remove(variacao);
		manager.flush();
	}

	public Variacao porNome(String nome) {
		try {
			return manager
					.createQuery("from Variacao where upper(nome) = :nome",
							Variacao.class)
					.setParameter("nome", nome.toUpperCase()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Variacao porId(Long id) {
		return manager.find(Variacao.class, id);
	}

	public List<Variacao> lista() {
		return manager.createQuery("from Variacao", Variacao.class)
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Variacao> filtrados(VariacaoFilter filtro) {

		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Variacao.class);

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
