package aima.gui.demo.tablaHorarios;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import aima.core.environment.tablaHorarios.TablaHorariosGenAlgoUtil;
import aima.core.environment.tablaHorarios.Profesor;
import aima.core.search.framework.problem.GoalTest;
import aima.core.search.local.FitnessFunction;
import aima.core.search.local.GeneticAlgorithm;
import aima.core.search.local.Individual;
import geneticMod.ModDos;
import geneticMod.ModTres;
import geneticMod.ModUno;

public class TablaHorariosDemo {
	public static void main(String[] args) {
		
		tablaHorariosGeneticAlgorithmSearch();
	}
	
	private static void tablaHorariosGeneticAlgorithmSearch() {
		System.out.println("TablaHorariosDemo GeneticAlgorithm  -->");
		try {
			ArrayList<Profesor> profs = new ArrayList<Profesor>();
			
			//Read config file
			int numTurnos = leerConfig(profs);
			
			TablaHorariosGenAlgoUtil.setProfesores(profs, numTurnos);
			FitnessFunction<Integer> fitnessFunction = TablaHorariosGenAlgoUtil.getFitnessFunction();
			GoalTest goalTest = TablaHorariosGenAlgoUtil.getGoalTest();
			
			//Generate an initial population
			Set<Individual<Integer>> population = new HashSet<>();
			
			for(int i = 0; i < 8; i++) {		//Aqui se cambia el tamaño de la población inicial
				population.add(TablaHorariosGenAlgoUtil.generateRandomIndividual());
			}
			
			GeneticAlgorithm<Integer> ga = new GeneticAlgorithm<Integer>(16,
					TablaHorariosGenAlgoUtil.getFiniteAlphabetForBoardOfSize(), 0.15);
		
			Individual<Integer> bestIndividual = ga.geneticAlgorithm(population, fitnessFunction, goalTest, 1000L);
			System.out.println("\nBase Code:");
			System.out.println("----------");
			System.out.println("\nMax Time (1 second) Best Individual=\n"
					+ TablaHorariosGenAlgoUtil.representTabla(bestIndividual));
			System.out.println("Turnos para asignar      = " + numTurnos);
			System.out.println("Fitness         = " + fitnessFunction.apply(bestIndividual));
			System.out.println("Is Goal         = " + goalTest.isGoalState(bestIndividual));
			System.out.println("Population Size = " + ga.getPopulationSize());
			System.out.println("Itertions       = " + ga.getIterations());
			System.out.println("Took            = " + ga.getTimeInMilliseconds() + "ms.");
			
			// Run till goal is achieved
			bestIndividual = ga.geneticAlgorithm(population, fitnessFunction, goalTest, 0L);
			System.out
			.println("\nGoal Test Best Individual=\n" + TablaHorariosGenAlgoUtil.representTabla(bestIndividual));
			System.out.println("Turnos para asignar      = " + numTurnos);
			System.out.println("Fitness         = " + fitnessFunction.apply(bestIndividual));
			System.out.println("Is Goal         = " + goalTest.isGoalState(bestIndividual));
			System.out.println("Population Size = " + ga.getPopulationSize());
			System.out.println("Itertions       = " + ga.getIterations());
			System.out.println("Took            = " + ga.getTimeInMilliseconds() + "ms.");
			
			//Mod 1
			
			ga = new ModUno<Integer>(16, TablaHorariosGenAlgoUtil.getFiniteAlphabetForBoardOfSize(), 0.15, 0.7);
		
			bestIndividual = ga.geneticAlgorithm(population, fitnessFunction, goalTest, 1000L);
			System.out.println("\nModification 1 (p = 0,7):");
			System.out.println("-------------------------");
			System.out.println("\nMax Time (1 second) Best Individual=\n"
					+ TablaHorariosGenAlgoUtil.representTabla(bestIndividual));
			System.out.println("Turnos para asignar      = " + numTurnos);
			System.out.println("Fitness         = " + fitnessFunction.apply(bestIndividual));
			System.out.println("Is Goal         = " + goalTest.isGoalState(bestIndividual));
			System.out.println("Population Size = " + ga.getPopulationSize());
			System.out.println("Itertions       = " + ga.getIterations());
			System.out.println("Took            = " + ga.getTimeInMilliseconds() + "ms.");
			
			// Run till goal is achieved
			bestIndividual = ga.geneticAlgorithm(population, fitnessFunction, goalTest, 0L);
			System.out
			.println("\nGoal Test Best Individual=\n" + TablaHorariosGenAlgoUtil.representTabla(bestIndividual));
			System.out.println("Turnos para asignar      = " + numTurnos);
			System.out.println("Fitness         = " + fitnessFunction.apply(bestIndividual));
			System.out.println("Is Goal         = " + goalTest.isGoalState(bestIndividual));
			System.out.println("Population Size = " + ga.getPopulationSize());
			System.out.println("Itertions       = " + ga.getIterations());
			System.out.println("Took            = " + ga.getTimeInMilliseconds() + "ms.");
			
			ga = new ModUno<Integer>(16, TablaHorariosGenAlgoUtil.getFiniteAlphabetForBoardOfSize(), 0.15, 0.8);
			
			bestIndividual = ga.geneticAlgorithm(population, fitnessFunction, goalTest, 1000L);
			System.out.println("\nModification 1 (p = 0,8):");
			System.out.println("-------------------------");
			System.out.println("\nMax Time (1 second) Best Individual=\n"
					+ TablaHorariosGenAlgoUtil.representTabla(bestIndividual));
			System.out.println("Turnos para asignar      = " + numTurnos);
			System.out.println("Fitness         = " + fitnessFunction.apply(bestIndividual));
			System.out.println("Is Goal         = " + goalTest.isGoalState(bestIndividual));
			System.out.println("Population Size = " + ga.getPopulationSize());
			System.out.println("Itertions       = " + ga.getIterations());
			System.out.println("Took            = " + ga.getTimeInMilliseconds() + "ms.");
			
			// Run till goal is achieved
			bestIndividual = ga.geneticAlgorithm(population, fitnessFunction, goalTest, 0L);
			System.out
			.println("\nGoal Test Best Individual=\n" + TablaHorariosGenAlgoUtil.representTabla(bestIndividual));
			System.out.println("Turnos para asignar      = " + numTurnos);
			System.out.println("Fitness         = " + fitnessFunction.apply(bestIndividual));
			System.out.println("Is Goal         = " + goalTest.isGoalState(bestIndividual));
			System.out.println("Population Size = " + ga.getPopulationSize());
			System.out.println("Itertions       = " + ga.getIterations());
			System.out.println("Took            = " + ga.getTimeInMilliseconds() + "ms.");
			
			ga = new ModUno<Integer>(16, TablaHorariosGenAlgoUtil.getFiniteAlphabetForBoardOfSize(), 0.15, 0.9);
			
			bestIndividual = ga.geneticAlgorithm(population, fitnessFunction, goalTest, 1000L);
			System.out.println("\nModification 1 (p = 0,9):");
			System.out.println("-------------------------");
			System.out.println("\nMax Time (1 second) Best Individual=\n"
					+ TablaHorariosGenAlgoUtil.representTabla(bestIndividual));
			System.out.println("Turnos para asignar      = " + numTurnos);
			System.out.println("Fitness         = " + fitnessFunction.apply(bestIndividual));
			System.out.println("Is Goal         = " + goalTest.isGoalState(bestIndividual));
			System.out.println("Population Size = " + ga.getPopulationSize());
			System.out.println("Itertions       = " + ga.getIterations());
			System.out.println("Took            = " + ga.getTimeInMilliseconds() + "ms.");
			
			// Run till goal is achieved
			bestIndividual = ga.geneticAlgorithm(population, fitnessFunction, goalTest, 0L);
			System.out
			.println("\nGoal Test Best Individual=\n" + TablaHorariosGenAlgoUtil.representTabla(bestIndividual));
			System.out.println("Turnos para asignar      = " + numTurnos);
			System.out.println("Fitness         = " + fitnessFunction.apply(bestIndividual));
			System.out.println("Is Goal         = " + goalTest.isGoalState(bestIndividual));
			System.out.println("Population Size = " + ga.getPopulationSize());
			System.out.println("Itertions       = " + ga.getIterations());
			System.out.println("Took            = " + ga.getTimeInMilliseconds() + "ms.");
			
			// Mod 2
			ga = new ModDos<Integer>(16, TablaHorariosGenAlgoUtil.getFiniteAlphabetForBoardOfSize(), 0.15, 0.7);
			
			bestIndividual = ga.geneticAlgorithm(population, fitnessFunction, goalTest, 1000L);
			System.out.println("\nModification 2:");
			System.out.println("---------------");
			System.out.println("\nMax Time (1 second) Best Individual=\n"
					+ TablaHorariosGenAlgoUtil.representTabla(bestIndividual));
			System.out.println("Turnos para asignar      = " + numTurnos);
			System.out.println("Fitness         = " + fitnessFunction.apply(bestIndividual));
			System.out.println("Is Goal         = " + goalTest.isGoalState(bestIndividual));
			System.out.println("Population Size = " + ga.getPopulationSize());
			System.out.println("Itertions       = " + ga.getIterations());
			System.out.println("Took            = " + ga.getTimeInMilliseconds() + "ms.");
			
			// Run till goal is achieved
			bestIndividual = ga.geneticAlgorithm(population, fitnessFunction, goalTest, 0L);
			System.out
			.println("\nGoal Test Best Individual=\n" + TablaHorariosGenAlgoUtil.representTabla(bestIndividual));
			System.out.println("Turnos para asignar      = " + numTurnos);
			System.out.println("Fitness         = " + fitnessFunction.apply(bestIndividual));
			System.out.println("Is Goal         = " + goalTest.isGoalState(bestIndividual));
			System.out.println("Population Size = " + ga.getPopulationSize());
			System.out.println("Itertions       = " + ga.getIterations());
			System.out.println("Took            = " + ga.getTimeInMilliseconds() + "ms.");

			// Mod 3
			ga = new ModTres<Integer>(16, TablaHorariosGenAlgoUtil.getFiniteAlphabetForBoardOfSize(), 0.15, 0.7);
			
			bestIndividual = ga.geneticAlgorithm(population, fitnessFunction, goalTest, 1000L);
			System.out.println("\nModification 3:");
			System.out.println("---------------");
			System.out.println("\nMax Time (1 second) Best Individual=\n"
					+ TablaHorariosGenAlgoUtil.representTabla(bestIndividual));
			System.out.println("Turnos para asignar      = " + numTurnos);
			System.out.println("Fitness         = " + fitnessFunction.apply(bestIndividual));
			System.out.println("Is Goal         = " + goalTest.isGoalState(bestIndividual));
			System.out.println("Population Size = " + ga.getPopulationSize());
			System.out.println("Itertions       = " + ga.getIterations());
			System.out.println("Took            = " + ga.getTimeInMilliseconds() + "ms.");
			
			// Run till goal is achieved
			bestIndividual = ga.geneticAlgorithm(population, fitnessFunction, goalTest, 0L);
			System.out
			.println("\nGoal Test Best Individual=\n" + TablaHorariosGenAlgoUtil.representTabla(bestIndividual));
			System.out.println("Turnos para asignar      = " + numTurnos);
			System.out.println("Fitness         = " + fitnessFunction.apply(bestIndividual));
			System.out.println("Is Goal         = " + goalTest.isGoalState(bestIndividual));
			System.out.println("Population Size = " + ga.getPopulationSize());
			System.out.println("Itertions       = " + ga.getIterations());
			System.out.println("Took            = " + ga.getTimeInMilliseconds() + "ms.");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int leerConfig(ArrayList<Profesor> profs) throws FileNotFoundException {
		System.out.println("Escribe el archivo de texto que quieres cargar: ");
		String line;
		Scanner console = new Scanner(System.in);
		line = console.nextLine();
			Scanner file = new Scanner(new File(line));
			line = file.nextLine();
			int numTurnos = Integer.parseInt(line);
			line = file.nextLine();
			line.trim();
			String[] words = line.split(", ");
			int numProfesores = words.length;
			
			for(int i = 0; i < numProfesores; i++) {
				profs.add(new Profesor(words[i], i));
			}
			
			for(int i = 0; i < numProfesores; i++) {
				line = file.nextLine();
				words = line.split(": ");
				if(words.length > 1) {
					words = words[1].split(",");
					ArrayList<Integer> diasProhibidos = new ArrayList<Integer>();
					for(int j = 0; j < words.length; j++) {
						diasProhibidos.add(Integer.parseInt(words[j] = words[j].trim()));						
					}
					profs.get(i).setProhibidos(diasProhibidos);
				}
			}
			
			for (int i = 0; i < numProfesores; i++) {
				line = file.nextLine();
				words = line.split(": ");
				if(words.length > 1) {
					words = words[1].split(",");
					ArrayList<Integer> diasPreferidos = new ArrayList<Integer>();
					for(int j = 0; j < words.length; j++) {
						diasPreferidos.add(Integer.parseInt(words[j] = words[j].trim()));						
					}
					profs.get(i).setPreferidos(diasPreferidos);
				}
			}
						
			file.close();
			console.close();
			return numTurnos;
	}
}
