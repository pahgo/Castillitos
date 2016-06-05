package es.pakillo.castillos.service.jugador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.pakillo.castillos.dao.jugador.JugadorDao;
import es.pakillo.castillos.model.Jugador;
import es.pakillo.castillos.service.ingreso.IngresoService;
import es.pakillo.castillos.service.jugador.JugadorService;

@Service("jugadorService")
@Transactional
public class JugadorServiceImpl implements JugadorService {

	@Autowired
	private JugadorDao dao;
	
	@Autowired
	private IngresoService ingresoService;

	public void saveJugador(Jugador jugador) {
		dao.saveJugador(jugador);
	}

	public List<Jugador> findAllJugadores() {
		return dao.findAllJugadores();
	}

	public void deleteJugadorByNombre(String nombre) {
		dao.deleteJugadorByNombre(nombre);
	}

	public Jugador findByNombre(String nombre) {
		return dao.findByNombre(nombre);
	}

	public void updateJugador(Jugador jugador) {
		dao.updateJugador(jugador);
	}

	@Override
	public List<Jugador> findByIdAlianza(Long idAlianza) {
		return dao.findByIdAlianza(idAlianza);
	}

	@Override
	public Jugador findById(Long idJugador) {
		return dao.findById(idJugador);
	}

	@Override
	public Jugador findFullJugadorById(Long idJugador, boolean orderAsc) {
		Jugador jugador = dao.findById(idJugador);
		jugador.setIngresos(ingresoService.findByIdJugador(idJugador, orderAsc));
		return jugador;
	}
}
