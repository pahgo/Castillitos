package es.pakillo.castillos.dao.ingreso;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import es.pakillo.castillos.dao.AbstractDao;
import es.pakillo.castillos.model.Ingreso;

@Transactional
@Repository("ingresoDao")
public class IngresoDaoImpl extends AbstractDao implements IngresoDao {

	public void saveIngreso(Ingreso ingreso) {
		persist(ingreso);
	}

	@SuppressWarnings("unchecked")
	public List<Ingreso> findAllIngresos() {
		Criteria criteria = getSession().createCriteria(Ingreso.class);
		return (List<Ingreso>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ingreso> findByIdJugador(Long idJugador, boolean orderAsc) {
		Criteria criteria = getSession().createCriteria(Ingreso.class);
		criteria.add(Restrictions.eq("idJugador", idJugador));
		if (orderAsc) 
			criteria.addOrder(Order.asc("fecha"));
		else
			criteria.addOrder(Order.desc("fecha"));
		return (List<Ingreso>) criteria.list();
	}

	@Override
	public void update(Ingreso ingreso) {
		persist(ingreso);
	}

}
