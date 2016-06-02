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
@Table(name = "INGRESOS")
public class Ingreso implements Comparable<Ingreso> {

	@Id
	@GenericGenerator(name = "gen", strategy = "increment")
	@GeneratedValue(generator = "gen")
	@Column(name = "ID", unique = true, nullable = false, precision = 15, scale = 0)
	private Long id;

	@Column(name = "id_jugador")
	private Long idJugador;

	@Column(name = "fecha")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate fecha;

	@Column(name = "puntos")
	private Integer puntos;

	@Column(name = "puntos_prev")
	private Integer puntosPrevios;

	@Column(name = "fragmentos")
	private Integer fragmentos;

	@Column(name = "fragmentos_prev")
	private Integer fragmentosPrevios;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the idJugador
	 */
	public Long getIdJugador() {
		return idJugador;
	}

	/**
	 * @param idJugador
	 *            the idJugador to set
	 */
	public void setIdJugador(Long idJugador) {
		this.idJugador = idJugador;
	}

	/**
	 * @return the fecha
	 */
	public LocalDate getFecha() {
		return fecha;
	}

	/**
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the puntos
	 */
	public Integer getPuntos() {
		return puntos;
	}

	/**
	 * @param puntos
	 *            the puntos to set
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
	 * @param puntosPrevios
	 *            the puntosPrevios to set
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
	 * @param fragmentos
	 *            the fragmentos to set
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
	 * @param fragmentosPrevios
	 *            the fragmentosPrevios to set
	 */
	public void setFragmentosPrevios(Integer fragmentosPrevios) {
		this.fragmentosPrevios = fragmentosPrevios;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj != null) {
			return false;
		}
		if (obj instanceof Ingreso) {
			if (this.id.equals(((Ingreso) obj).getId()) && this.fecha.equals(((Ingreso) obj).fecha))
				return true;
		}
		return false;
	}

	@Override
	public int compareTo(Ingreso paramT) {
		if (this.idJugador.compareTo(paramT.getIdJugador()) != 0) {
			return this.idJugador.compareTo(paramT.getIdJugador());
		}
		else {
			return this.getFecha().compareTo(paramT.getFecha());			
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Ingresos [id=" + id + ", idJugador=" + idJugador + ", fecha=" + fecha + ", puntos=" + puntos
				+ ", puntosPrevios=" + puntosPrevios + ", fragmentos=" + fragmentos + ", fragmentosPrevios="
				+ fragmentosPrevios + "]\n";
	}

}
