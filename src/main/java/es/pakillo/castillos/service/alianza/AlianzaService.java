package es.pakillo.castillos.service.alianza;

import java.util.List;

import es.pakillo.castillos.model.Alianza;

public interface AlianzaService {

	void saveAlianza(Alianza alianza);

	List<Alianza> findAllAlianzas();

	void deleteAlianzaByNombre(String nombre);

	Alianza findByNombre(String nombre);

	void updateAlianza(Alianza alianza);
	
}
