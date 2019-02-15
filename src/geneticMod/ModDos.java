package geneticMod;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import aima.core.search.local.FitnessFunction;
import aima.core.search.local.GeneticAlgorithm;
import aima.core.search.local.Individual;

public class ModDos<A> extends GeneticAlgorithm<A>{
	
	public ModDos(int individualLength, Collection<A> finiteAlphabet, double mutationProbability, double prob) {
		super(individualLength, finiteAlphabet, mutationProbability, new Random());
	}
	
	@Override
	protected List<Individual<A>> nextGeneration(List<Individual<A>> population, FitnessFunction<A> fitnessFn) {
		// new_population <- empty set
		List<Individual<A>> newPopulation = new ArrayList<Individual<A>>(population.size());
		// for i = 1 to SIZE(population) do
		for (int i = 0; i < population.size()/2; i++) {
			// x <- RANDOM-SELECTION(population, FITNESS-FN)
			Individual<A> x = randomSelection(population, fitnessFn);
			// y <- RANDOM-SELECTION(population, FITNESS-FN)
			Individual<A> y = randomSelection(population, fitnessFn);
			// childs <- REPRODUCETWO(x, y)
			ArrayList<Individual<A>> childs = reproduceTwo(x, y);
			// if (small random probability) then child <- MUTATE(child)
			for(int j = 0; j < childs.size(); j++){
				if (random.nextDouble() <= mutationProbability) {
					Individual<A> child = mutate(childs.get(j));
					newPopulation.add(child);
				}
				else
					newPopulation.add(childs.get(j));
			}
			
		}
		notifyProgressTracers(getIterations(), population);
		return newPopulation;
	}
	
	private ArrayList<Individual<A>> reproduceTwo(Individual<A> x, Individual<A> y) {
		
		int c = randomOffset(individualLength);
		ArrayList<Individual<A>> childs = new ArrayList<Individual<A>>();
		
		// Hijo 1
		List<A> childRepresentation = new ArrayList<A>();
		childRepresentation.addAll(x.getRepresentation().subList(0, c));
		childRepresentation.addAll(y.getRepresentation().subList(c, individualLength));
		Individual<A> child = new Individual<A>(childRepresentation);
		childs.add(child);
		
		// Hijo 2
		
		childRepresentation = new ArrayList<A>();
		childRepresentation.addAll(y.getRepresentation().subList(0, c));
		childRepresentation.addAll(x.getRepresentation().subList(c, individualLength));
		child = new Individual<A>(childRepresentation);
		childs.add(child);
		
		return childs;
	}
}
