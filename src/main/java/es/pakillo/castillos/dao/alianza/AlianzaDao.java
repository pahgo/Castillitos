package es.pakillo.castillos.dao.alianza;

import java.util.List;

import es.pakillo.castillos.model.Alianza;

public interface AlianzaDao {

	void saveAlianza(Alianza alianza);
	
	List<Alianza> findAllAlianzas();
	
	void deleteAlianzaByNombre(String nombre);
	
	Alianza findByNombre(String nombre);
	
	void updateAlianza(Alianza alianza);

	Alianza findById(Long idAlianza);
}
