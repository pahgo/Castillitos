package es.pakillo.castillos.importSQLite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.pakillo.castillos.model.Fragmentos;
import es.pakillo.castillos.model.Jugador;
import es.pakillo.castillos.model.Puntos;

public enum SQLite {
	DB;

	private Connection con;

	/**
	 * Crea la conexión con la BBDD.
	 */
	private SQLite() {
		con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection(
					"jdbc:sqlite:" + this.getClass().getResource("/").getPath() + "/resources/castillo.db");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	/**
	 * Recupera la conexión de la página.
	 * 
	 * @return Connection.
	 */
	public Connection getConnection() {
		return con;
	}

	public ResultSet execute(final String sql) throws SQLException {
		ResultSet rs = null;

		if (sql != null && !"".equals(sql.trim())) {
			final PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.execute();
			rs = pstmt.getResultSet();
		}
		return rs;
	}

	public List<Puntos> getPuntuacionesById(final int id) throws SQLException {
		final List<Puntos> puntuaciones = new ArrayList<Puntos>();
		final QueryBuilder qb = new QueryBuilder();

		qb.select().field(PuntosJugador.allFields()).from().valuesFrom(Tablas.PUNTOS_JUGADOR.nombre()).where()
				.field(PuntosJugador.ID_JUGADOR.nombre()).equal().value(id).order().field(PuntosJugador.FECHA.nombre())
				.desc();

		final ResultSet rs = SQLite.DB.execute(qb.toString());
		while (rs.next()) {
			puntuaciones.add(PuntosJugador.mapper(rs));
		}

		return puntuaciones;
	}

	public List<Fragmentos> getFragmentosById(final int id) throws SQLException {
		final List<Fragmentos> fragmentos = new ArrayList<Fragmentos>();
		final QueryBuilder qb = new QueryBuilder();

		qb.select().field(FragmentosJugador.allFields()).from().valuesFrom(Tablas.FRAGMENTOS_JUGADOR.nombre()).where()
				.field(PuntosJugador.ID_JUGADOR.nombre()).equal().value(id).order().field(PuntosJugador.FECHA.nombre())
				.desc();

		final ResultSet rs = SQLite.DB.execute(qb.toString());
		while (rs.next()) {
			fragmentos.add(FragmentosJugador.mapper(rs));
		}

		return fragmentos;
	}

	public Jugador getJugadorById(final long idJugador) throws SQLException {
		final QueryBuilder qb = new QueryBuilder();

		qb.select().field(Jugadores.allFields()).from().valuesFrom(Tablas.JUGADORES.name()).where()
				.field(Jugadores.ID_JUGADOR.name()).equal().value(idJugador);

		final ResultSet rs = execute(qb.toString());
		rs.next();
		return Jugadores.mapper(rs);

	}
}
