package es.pakillo.castillos.service.jugador;

import java.util.List;

import es.pakillo.castillos.model.Jugador;

public interface JugadorService {

	void saveJugador(Jugador jugador);

	List<Jugador> findAllJugadores();

	void deleteJugadorByNombre(String nombre);

	Jugador findByNombre(String nombre);

	void updateJugador(Jugador jugador);
	
	List<Jugador> findByIdAlianza(Long idAlianza);
}
