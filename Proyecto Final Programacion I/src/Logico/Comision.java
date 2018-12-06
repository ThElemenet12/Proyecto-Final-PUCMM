package Logico;

import java.io.Serializable;
import java.util.ArrayList;

public class Comision implements Serializable{
	private int id;
	private String tema;
	private ArrayList<Persona> misMiembros;
	private String area;
	private static int cant = 1;
	private ArrayList<Trabajo> misTrabajos;
	
	public Comision(String area, String tema) {
		this.area = area;
		this.tema = tema;
		misMiembros = new ArrayList<>();
		setMisTrabajos(new ArrayList<>());
		id = cant;
		cant++;
	}
	public int getId() {
		return id;
	}

	public String getArea() {
		return area;
	}

	public ArrayList<Persona> getMisMiembros() {
		return misMiembros;
	}

	public void setArea(String area) {
		this.area = area;
	}
	public void insertarJuez(Juez miJuez) {
		misMiembros.add(miJuez);
	}
	public void insertarPrincipante(Participante miParticipante) {
		misMiembros.add(miParticipante);
	}
	
	public void createTrabajo() {
		String nombres[] = {"L\u00EDder", "Co-L\u00EDder", "Orador 1", "Orador 2", "Organizador 1", "Organizador 2"};
		for(int i = 0; i < 6; i ++) {
			Trabajo unTrabajo = new Trabajo(nombres[i]);
			getMisTrabajos().add(unTrabajo);
		}
	}
	
	public String getTema() {
		return tema;
	}


	public void setMisMiembros(ArrayList<Persona> misMiembros) {
		this.misMiembros = misMiembros;
	}


	public ArrayList<Trabajo> getMisTrabajos() {
		return misTrabajos;
	}

	public void setMisTrabajos(ArrayList<Trabajo> misTrabajos) {
		this.misTrabajos = misTrabajos;
	}
}
