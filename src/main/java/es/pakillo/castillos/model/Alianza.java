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
@Table(name="ALIANZAS")
public class Alianza {

	@Id
	@GenericGenerator(name="gen",strategy="increment")
	@GeneratedValue(generator="gen")
	@Column(name = "ID", unique = true, nullable = false, precision = 15, scale = 0)
	private Long id;

	@Column(name = "nombre", nullable = false)
	private String nombre;

	@Column(name = "fecha_insercion", nullable = false)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate fechaInsercion;

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

	public LocalDate getFechaInsercion() {
		return fechaInsercion;
	}

	public void setFechaInsercion(LocalDate joiningDate) {
		this.fechaInsercion = joiningDate;
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
		if (!(obj instanceof Alianza))
			return false;
		Alianza other = (Alianza) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", nombre=" + nombre + ", fechaInsercion="
				+ fechaInsercion + "]";
	}
	
	
	

}
