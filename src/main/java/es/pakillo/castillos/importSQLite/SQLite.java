package es.pakillo.castillos.importSQLite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import es.pakillo.castillos.model.Jugador;

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


	public Jugador getJugadorById(final long idJugador) throws SQLException {
		final QueryBuilder qb = new QueryBuilder();

		qb.select().field(Jugadores.allFields()).from().valuesFrom(Tablas.JUGADORES.name()).where()
				.field(Jugadores.ID_JUGADOR.name()).equal().value(idJugador);

		final ResultSet rs = execute(qb.toString());
		rs.next();
		return Jugadores.mapper(rs);

	}
}
