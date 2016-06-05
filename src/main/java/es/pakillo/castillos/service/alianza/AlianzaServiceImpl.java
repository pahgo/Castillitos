package es.pakillo.castillos.service.alianza;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.pakillo.castillos.dao.alianza.AlianzaDao;
import es.pakillo.castillos.model.Alianza;

@Service("alianzaService")
@Transactional
public class AlianzaServiceImpl implements AlianzaService {

	@Autowired
	private AlianzaDao dao;

	public void saveAlianza(Alianza alianza) {
		dao.saveAlianza(alianza);
	}

	public List<Alianza> findAllAlianzas() {
		return dao.findAllAlianzas();
	}

	public void deleteAlianzaByNombre(String nombre) {
		dao.deleteAlianzaByNombre(nombre);
	}

	public Alianza findByNombre(String nombre) {
		return dao.findByNombre(nombre);
	}

	public void updateAlianza(Alianza alianza) {
		dao.updateAlianza(alianza);
	}

	@Override
	public Alianza findById(Long idAlianza) {
		return dao.findById(idAlianza);
	}
}
