package es.pakillo.castillos.service.ingreso;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.pakillo.castillos.dao.ingreso.IngresoDao;
import es.pakillo.castillos.model.Ingreso;

@Service("ingresoService")
@Transactional
public class IngresoServiceImpl implements IngresoService {

	@Autowired
	private IngresoDao dao;

	public void saveIngreso(Ingreso jugador) {
		dao.saveIngreso(jugador);
	}

	public List<Ingreso> findAllIngresos() {
		return dao.findAllIngresos();
	}


	@Override
	public List<Ingreso> findByIdJugador(Long idJugador, boolean orderAsc) {
		return dao.findByIdJugador(idJugador, orderAsc);
	}
}
