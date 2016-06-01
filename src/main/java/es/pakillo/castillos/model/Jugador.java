package es.pakillo.castillos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

@Entity
@Table(name="jugador")
public class Jugador {

	@Id
	@GenericGenerator(name="gen",strategy="increment")
	@GeneratedValue(generator="gen")
	@Column(name = "ID", unique = true, nullable = false, precision = 15, scale = 0)
	private Long id;

	@Column(name = "nombre", nullable = false)
	private String nombre;

	@Column(name = "aka")
	private String alias;

	@Column(name = "fecha_registro", nullable = false)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate fechaRegistro;

	@Column(name = "id_alianza", nullable = false)
	private Long idAlianza;

	/**
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * @param alias the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * @return the idAlianza
	 */
	public Long getIdAlianza() {
		return idAlianza;
	}

	/**
	 * @param idAlianza the idAlianza to set
	 */
	public void setIdAlianza(Long idAlianza) {
		this.idAlianza = idAlianza;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDate getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDate joiningDate) {
		this.fechaRegistro = joiningDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id.intValue();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Jugador))
			return false;
		Jugador other = (Jugador) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Jugador [id=" + id + ", nombre=" + nombre + ", fechaRegistro="
				+ fechaRegistro + "]";
	}
	
	
	

}
