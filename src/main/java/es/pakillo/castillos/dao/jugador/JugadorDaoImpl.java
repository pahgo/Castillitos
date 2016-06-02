package es.pakillo.castillos.dao.jugador;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import es.pakillo.castillos.dao.AbstractDao;
import es.pakillo.castillos.model.Jugador;

@Transactional
@Repository("jugadorDao")
public class JugadorDaoImpl extends AbstractDao implements JugadorDao {

	public void saveJugador(Jugador jugador) {
		persist(jugador);
	}

	@SuppressWarnings("unchecked")
	public List<Jugador> findAllJugadores() {
		Criteria criteria = getSession().createCriteria(Jugador.class);
		return (List<Jugador>) criteria.list();
	}

	public void deleteJugadorByNombre(String nombre) {
		Query query = getSession().createSQLQuery("delete from Jugador where nombre = :nombre");
		query.setString("nombre", nombre);
		query.executeUpdate();
	}

	public Jugador findByNombre(String nombre) {
		Criteria criteria = getSession().createCriteria(Jugador.class);
		criteria.add(Restrictions.eq("nombre", nombre));
		return (Jugador) criteria.uniqueResult();
	}

	public void updateJugador(Jugador jugador) {
		getSession().update(jugador);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Jugador> findByIdAlianza(Long idAlianza) {
		Criteria criteria = getSession().createCriteria(Jugador.class);
		criteria.add(Restrictions.eq("idAlianza", idAlianza));
		criteria.addOrder(Order.desc("puntos"));
		return (List<Jugador>) criteria.list();
	}

	@Override
	public Jugador findById(Long idJugador) {
		Criteria criteria = getSession().createCriteria(Jugador.class);
		criteria.add(Restrictions.eq("id", idJugador));
		return (Jugador) criteria.uniqueResult();
	}

}
