package es.pakillo.castillos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

@Entity
@Table(name="JUGADORES")
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

	@Column(name = "fecha_registro")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate fechaRegistro;

	@Column(name = "id_alianza", nullable = false)
	private Long idAlianza;
	
	@Column(name = "puntos")
	private Integer puntos;
	
	@Column(name = "puntos_prev")
	private Integer puntosPrevios;

	@Column(name = "fragmentos")
	private Integer fragmentos;

	@Column(name = "fragmentos_prev")
	private Integer fragmentosPrevios;

	@Transient
	private Double proporcion;
	/**
	 * @return the puntos
	 */
	public Integer getPuntos() {
		return puntos;
	}

	/**
	 * @param puntos the puntos to set
	 */
	public void setPuntos(Integer puntos) {
		this.puntos = puntos;
	}

	/**
	 * @return the puntosPrevios
	 */
	public Integer getPuntosPrevios() {
		return puntosPrevios;
	}

	/**
	 * @param puntosPrevios the puntosPrevios to set
	 */
	public void setPuntosPrevios(Integer puntosPrevios) {
		this.puntosPrevios = puntosPrevios;
	}

	/**
	 * @return the fragmentos
	 */
	public Integer getFragmentos() {
		return fragmentos;
	}

	/**
	 * @param fragmentos the fragmentos to set
	 */
	public void setFragmentos(Integer fragmentos) {
		this.fragmentos = fragmentos;
	}

	/**
	 * @return the fragmentosPrevios
	 */
	public Integer getFragmentosPrevios() {
		return fragmentosPrevios;
	}

	/**
	 * @param fragmentosPrevios the fragmentosPrevios to set
	 */
	public void setFragmentosPrevios(Integer fragmentosPrevios) {
		this.fragmentosPrevios = fragmentosPrevios;
	}

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

	/**
	 * @return the proporcion
	 */
	public Double getProporcion() {
		return proporcion;
	}

	/**
	 * @param proporcion the proporcion to set
	 */
	public void setProporcion(Double proporcion) {
		this.proporcion = proporcion;
	}
	
	
	

}
