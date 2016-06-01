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
@Table(name="fragmentos")
public class Fragmentos {
	
	@Id
	@GenericGenerator(name="gen",strategy="increment")
	@GeneratedValue(generator="gen")
	@Column(name = "ID", unique = true, nullable = false, precision = 15, scale = 0)
	private int id;
	
	@Column(name = "fragmentos", nullable = false)
	private long fragmentos;
	
	@Column(name = "idJugador", nullable = false)
	private int idJugador;
	
	@Column(name = "fecha", nullable = false)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate fecha;
		
	public long getFragmentos() {
		return fragmentos;
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param fragmentos the fragmentos to set
	 */
	public void setFragmentos(long fragmentos) {
		this.fragmentos = fragmentos;
	}

	/**
	 * @param idJugador the idJugador to set
	 */
	public void setIdJugador(int idJugador) {
		this.idJugador = idJugador;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public int getIdFragmentos() {
		return id;
	}
	
	public int getIdJugador() {
		return idJugador;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}

}