package aima.core.environment.tablaHorarios;

import java.util.ArrayList;

public class Profesor {
	private int id;
	private String nombre;
	private ArrayList<Integer> turnosProhibidos;
	private ArrayList<Integer> turnosPreferidos;
	
	public Profesor(String nombre, int id) {
		this.nombre = nombre;
		this.id = id;
		this.turnosProhibidos = new ArrayList<Integer>();
		this.turnosPreferidos = new ArrayList<Integer>();
	}
	
	public boolean isProhibido(int turno) {
		return turnosProhibidos.contains(turno);
	}
	
	public boolean isPreferido(int turno) {
		return turnosPreferidos.contains(turno);
	}
	
	public void setProhibidos(ArrayList<Integer> prohibidos) {
		this.turnosProhibidos = new ArrayList<Integer>(prohibidos);
	}
	
	public void setPreferidos(ArrayList<Integer> preferidos) {
		this.turnosPreferidos = new ArrayList<Integer>(preferidos);
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.nombre;
	}
	
	public ArrayList<Integer> getProhibidos(){
		return this.turnosProhibidos;
	}
	
	public ArrayList<Integer> getPreferidos(){
		return this.turnosPreferidos;
	}
}
