package aima.core.environment.tablaHorarios;

import java.util.ArrayList;

import aima.core.search.local.Individual;

public class TablaHorariosGoalTest {
	private ArrayList<Profesor> profesores;
	private int numTurnos;
	
	public TablaHorariosGoalTest(ArrayList<Profesor> profesores, int numTurnos) {
		this.profesores = new ArrayList<Profesor>(profesores);
		this.numTurnos = numTurnos;
	}

	public boolean isGoalState(Object generic) {
		@SuppressWarnings("unchecked")
		Individual<Integer> individual = (Individual<Integer>) generic;
		boolean isGoal = true;
		int i = 0, turno = 1, asignados = 0;
		while (isGoal && i < 16) {
			if(individual.getRepresentation().get(i) != -1) {					
				if (profesores.get(individual.getRepresentation().get(i)).isProhibido(turno)) {
					isGoal= false;
				}
				else
					asignados++;
			}
			turno++;
			i++;
		}
		return isGoal && asignados == numTurnos;
	}

}
