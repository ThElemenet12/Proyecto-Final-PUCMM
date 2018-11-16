package Logico;

import java.util.ArrayList;
import java.util.Date;

public class Evento {
	private String id;
	private String nombre;
	private String area;
	private Date fechaIni;
	private Date fechaFin;
	private ArrayList<Persona> misMiembros;
	private ArrayList<Recurso> misRecursos;
	private ArrayList <Trabajo> misTrabajos;
	
	public Evento(String id,String nombre, String area, Date fechaIni, Date fechaFin) {
		this.id = id;
		this.nombre = nombre;
		this.area = area;
		this.fechaIni = fechaIni;
		this.fechaFin = fechaFin;
		misMiembros = new ArrayList<>();
		misRecursos = new ArrayList<>();
		misTrabajos = new ArrayList<>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getId() {
		return id;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Date getFechaIni() {
		return fechaIni;
	}

	public void setFechaIni(Date fechaIni) {
		this.fechaIni = fechaIni;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public void agregarJuez(Persona juez) {
		misMiembros.add(juez);
	}
	public void agregarParticipante(Persona participante) {
		misMiembros.add(participante);
	}
	public void agregarRecurso(Recurso recurso) {
		misRecursos.add(recurso);
	}
	public void agregarTrabajoEvento(Trabajo trabajo) {
		misTrabajos.add(trabajo);
	}
	
	public ArrayList<Persona> getMisMiembros() {
		return misMiembros;
	}

	public ArrayList<Trabajo> getMisTrabajos() {
		return misTrabajos;
	}

	public ArrayList<Recurso> getMisRecursos() {
		return misRecursos;
	}

	public Persona buscarParticipantePorCedula(String cedula) {
		Persona aux = null;
		int i = 0;
		boolean encontrado = false;
		
		while(encontrado != true && i < misMiembros.size()) {
			
			if(misMiembros.get(i) instanceof Participante) {
				if(misMiembros.get(i).getCedula().equalsIgnoreCase(cedula)) {
					aux = misMiembros.get(i);
					encontrado = true;
				}
			}
			
		}	
		return aux;
	}
	public Persona buscarJuezporCedula(String cedula) {
		Persona aux = null;
		int i = 0;
		boolean encontrado = false;
		
		while(encontrado != true && i < misMiembros.size()) {
			
			if(misMiembros.get(i) instanceof Juez) {
				if(misMiembros.get(i).getCedula().equalsIgnoreCase(cedula)) {
					aux = misMiembros.get(i);
					encontrado = true;
				}
			}
		}	
		return aux;
	}
	public Recurso buscarRecursosPorId(String id) {
		Recurso aux = null;
		int i = 0;
		boolean encontrado = false;
		while(encontrado != true && i < misRecursos.size()) {
			if(misRecursos.get(i).getId().equalsIgnoreCase(id)) {
				aux = misRecursos.get(i);
				encontrado = true;
			}		
		}
		return aux;
	}
	public int cantTrabajoDispPorArea(String area) {
		int aux = 0;
		
		for(int i = 0; i < misTrabajos.size(); i++) {
			if(misTrabajos.get(i).getArea().equalsIgnoreCase(area)) {
				if(misTrabajos.get(i).isDisponible()) {
					aux++;
				}
			}
		}
		return aux;
	}
	
}
