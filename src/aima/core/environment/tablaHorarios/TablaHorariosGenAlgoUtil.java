package aima.core.environment.tablaHorarios;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import aima.core.search.framework.problem.GoalTest;
import aima.core.search.local.FitnessFunction;
import aima.core.search.local.Individual;

public class TablaHorariosGenAlgoUtil {
	
	private static ArrayList<Profesor> profs;
	private static int numTurnos;
	
	public static void setProfesores(ArrayList<Profesor> profs, int numTurnos) {
		TablaHorariosGenAlgoUtil.profs = profs;
		TablaHorariosGenAlgoUtil.numTurnos = numTurnos;
	}
	
	public static FitnessFunction<Integer> getFitnessFunction() {
		return new HorariosFitnessFunction();
	}
	
	public static GoalTest getGoalTest(){
		return new TablaHorariosAlgoGoalTest();
	}
	
	public static Individual<Integer> generateRandomIndividual(){
		List<Integer> individual = new ArrayList<Integer>();
		for(int i = 0; i < 16; i++) {
			individual.add(-1);
		}
		int turnosCubiertos = 0, randProf, randPos;
		while(turnosCubiertos < numTurnos) {
			randPos = new Random().nextInt(16);
			randProf = new Random().nextInt(profs.size());
			if(individual.get(randPos) == -1) {
				individual.set(randPos, randProf);
				turnosCubiertos++;
			}
		}
		return new Individual<>(individual);
	}
	
	public static Collection<Integer> getFiniteAlphabetForBoardOfSize(){
		Collection<Integer> alph = new ArrayList<Integer>();
		
		for(int i = -1; i < profs.size(); i++) {
			alph.add(i);
		}
		
		return alph;
	}
	
	public static class HorariosFitnessFunction implements FitnessFunction<Integer>{

		public double apply(Individual<Integer> individual) {
			double fitness = 1;
			
			//Suma +10 al fitness por cada asignacion de turno que coincida en un dia preferido.
			//Tambien guarda en numTurnos el numero de turnos asignados a cada profesor.
			for(int i = 0; i < 16; i++) {
				if(individual.getRepresentation().get(i) != -1 && profs.get(individual.getRepresentation().get(i)).isPreferido(i + 1))
					fitness += 10;
			}
			
			//Se calcula el numero de turnos que tiene cada profesor y elnumero total de turnos asignados
			int totTurn = 0;
			int[] turnos = new int[profs.size()];
			
			for(int i = 0; i < 16; i++) {
				if(individual.getRepresentation().get(i) != -1) {
					turnos[individual.getRepresentation().get(i)]++;
					totTurn++;
				}
			}
			
			//Suma 20*numturnos al fitness si todos los turnos estan asignados
			//La cantidad decrece hasta 0 si no hay ningun turno asignado
			//No suma nada si hay mas turnos asignados que necesarios
			double asignados = 20 * numTurnos;
			if(totTurn <= numTurnos) {
				asignados = (totTurn/numTurnos) * asignados;
			}
			else {
				asignados = (numTurnos/totTurn) * asignados;
			}
			
			//Se ordena el array de numTurnos de mayor a menor
			sort(turnos);
			
			//Se suma al fitness un bonus por buena asignacion de turnos.
			//Si todos los profesores tienen solo 1 turno se suma (10*numero de turnos que se asignan).
			//Si un solo profesor tiene todos los turnos no se suma nada
			double repetidos = numTurnos * 10;
			for(int i = 0; i < profs.size(); i++) {
				if(turnos[i] > 1) {
					repetidos = repetidos - (repetidos * (turnos[i]/profs.size()));
				}
			}
			fitness = fitness + repetidos + asignados;
			
			return fitness;
		}
		
		private void sort(int[] arr) {
			int tmp;
			int i = 0;
			boolean cambio = true;
			while(cambio && i < profs.size()) {
				cambio = false;
				for(int j = 0; j < profs.size() - i - 1; j++) {
					tmp = 0;
					if(arr[j] < arr[j + 1]) {
						cambio = true;
						tmp = arr[j + 1];
						arr[j + 1] = arr[j];
						arr[j] = tmp;
					}
				}
				i++;
			}
		}
		
	}
	
	public static class TablaHorariosAlgoGoalTest implements GoalTest{
		
		private final TablaHorariosGoalTest goalTest = new TablaHorariosGoalTest(profs, numTurnos);
		
		@Override
		public boolean isGoalState(Object state) {
			return goalTest.isGoalState(state);
		}
		
	}
	
	public static String representTabla(Individual<Integer> ind) {
		String tabla = new String();
		int turno = 0;
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++) {
				if(ind.getRepresentation().get(turno) != -1) {
					tabla = tabla + profs.get(ind.getRepresentation().get(turno)).getName() + "     \t";
				}
				else {
					tabla = tabla + "\t\t";
				}
				tabla = tabla + "|";
				turno++;
			}
			tabla = tabla + "\n";
		}
		return tabla;
	}	
}
