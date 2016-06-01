package es.pakillo.castillos.dao.jugador;

import java.util.List;

import es.pakillo.castillos.model.Jugador;

public interface JugadorDao {

	void saveJugador(Jugador jugador);
	
	List<Jugador> findAllJugadores();
	
	void deleteJugadorByNombre(String nombre);
	
	Jugador findByNombre(String nombre);
	
	void updateJugador(Jugador jugador);
	
	List<Jugador> findByIdAlianza(Long idAlianza);
}
