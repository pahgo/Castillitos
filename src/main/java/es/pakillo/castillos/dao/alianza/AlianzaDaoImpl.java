package es.pakillo.castillos.dao.alianza;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import es.pakillo.castillos.dao.AbstractDao;
import es.pakillo.castillos.model.Alianza;

@Transactional
@Repository("alianzaDao")
public class AlianzaDaoImpl extends AbstractDao implements AlianzaDao {

	public void saveAlianza(Alianza alianza) {
		persist(alianza);
	}

	@SuppressWarnings("unchecked")
	public List<Alianza> findAllAlianzas() {
		Criteria criteria = getSession().createCriteria(Alianza.class);
		return (List<Alianza>) criteria.list();
	}

	public void deleteAlianzaByNombre(String nombre) {
		Query query = getSession().createSQLQuery("delete from Alianza where nombre = :nombre");
		query.setString("nombre", nombre);
		query.executeUpdate();
	}

	public Alianza findByNombre(String nombre) {
		Criteria criteria = getSession().createCriteria(Alianza.class);
		criteria.add(Restrictions.eq("nombre", nombre));
		return (Alianza) criteria.uniqueResult();
	}

	public void updateAlianza(Alianza alianza) {
		getSession().update(alianza);
	}

}
