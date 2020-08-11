package SQLConnections;

import Logico.Evento;
import Logico.PUCMM;
import Logico.Recurso;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RecursosServices {

	public static void setTipoRecurso(String nombre) throws SQLException{
		Connection myConnection = Conexion.getConnection();
		CallableStatement cstmt = null;
		
		cstmt = myConnection.prepareCall("{call RegistrarTipoRecurso(?)");
		cstmt.setString("Nombre",nombre);
		
		cstmt.executeUpdate();
		cstmt.close();
	}
	public static void setEstadoRecurso(String nombre) throws SQLException{
		Connection myConnection = Conexion.getConnection();
		CallableStatement cstmt = null;
		
		cstmt = myConnection.prepareCall("{call RegistrarEstadoRecurso(?)");
		cstmt.setString("Nombre",nombre);
		
		cstmt.executeUpdate();
		cstmt.close();
		
	}
	public static void SetModeloRecurso(String nombre, String id, int tipoRecurso, int estadoRecurso) throws SQLException {
		Connection myConnection = Conexion.getConnection();
		CallableStatement cstmt = null;
		
		cstmt = myConnection.prepareCall("{call RegistrarModeloRecurso(?)");
		cstmt.setString("MacRecurso",id);
		cstmt.setInt("IdTipoRecurso", tipoRecurso);
		cstmt.setInt("IdEstadoRecurso", estadoRecurso);
		cstmt.setString("Nombre",nombre);
		
		
		cstmt.executeUpdate();
		cstmt.close();
	}
	public static void setRecursoEvento(String id, int idevento, int idArea) throws SQLException{
		Connection myConnection = Conexion.getConnection();
		CallableStatement cstmt = null;
		
		cstmt = myConnection.prepareCall("{call RegistraRecurso(?)");
		cstmt.setString("MacRecurso",id);
		cstmt.setInt("IdEvento", idevento);
		cstmt.setInt("IdArea", idArea);
		
		cstmt.executeUpdate();
		cstmt.close();
	}
	public static ArrayList<String> getEstadoRecursos() throws SQLException{
		ArrayList<String> estados = new ArrayList<>();
		Connection myConnection = Conexion.getConnection();
		CallableStatement cstmt = null;
		ResultSet rs = null;

		cstmt = myConnection.prepareCall("{call ListarEstadoRecurso}");
		cstmt.execute();
		rs = cstmt.getResultSet();
		while(rs.next()){
			estados.add(rs.getString("Nombre"));
		}
		rs.close();
		cstmt.close();
		myConnection.close();

		return estados;
	}
	public static ArrayList<String> getModelos() throws SQLException{
		ArrayList<String> modelos = new ArrayList<>();
		Connection myConnection = Conexion.getConnection();
		CallableStatement cstmt = null;
		ResultSet rs = null;

		cstmt = myConnection.prepareCall("{call ListarModeloRecurso}");
		cstmt.execute();
		rs = cstmt.getResultSet();
		while(rs.next()){
			modelos.add(rs.getString("Nombre"));
		}
		rs.close();
		cstmt.close();
		myConnection.close();

		return modelos;
	}
	public static void getRecursos() throws SQLException{
		ArrayList<Recurso> recursos = new ArrayList<>();
		Connection myConnection = Conexion.getConnection();
		CallableStatement cstmt = null;
		ResultSet rs = null;

		cstmt = myConnection.prepareCall("{call Rpt_ListaDeRecursos}");
		cstmt.execute();
		rs = cstmt.getResultSet();
		while(rs.next()) {
			recursos.add(new Recurso(Integer.parseInt(rs.getString("MacRecurso")), rs.getString("ModeloRecurso"),
					rs.getString("TipoRecuros"),rs.getString("EstadoRecurso")));
		}
		PUCMM.pucmm().setMisRecursos(recursos);
		rs.close();
		cstmt.close();
		myConnection.close();

	}
	public static ArrayList<Recurso> getRecursoByFecha(Date date) throws SQLException{
		ArrayList<Recurso> recursos = new ArrayList<>();
 		ResultSet rs = null;
 		CallableStatement cstmt = null;
 		Connection myConnection = Conexion.getConnection();

 		cstmt = myConnection.prepareCall("{call ListarRecursosDisponibles (?)}",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
 		cstmt.setDate("Fecha", new java.sql.Date(date.getTime()));
 		boolean results = cstmt.execute();
 		int rowsAffected = 0;
 		while(results || rowsAffected != -1){
 			if(results) {
				rs = cstmt.getResultSet();
				break;
			}else{
 				rowsAffected = cstmt.getUpdateCount();
			}
 			results = cstmt.getMoreResults();
		}
 		while(rs.next()){
 			recursos.add(new Recurso(Integer.valueOf(rs.getString("MacRecurso")), rs.getString("NombreRecurso"), rs.getString("TipoRecurso"),
					rs.getString("EstadoRecurso")));
		}
		rs.close();
		cstmt.close();
		myConnection.close();
		return recursos;

	}
}
