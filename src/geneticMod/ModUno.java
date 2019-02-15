package geneticMod;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import aima.core.search.local.FitnessFunction;
import aima.core.search.local.GeneticAlgorithm;
import aima.core.search.local.Individual;

public class ModUno<A> extends GeneticAlgorithm<A>{
	
	protected double prob;
	
	public ModUno(int individualLength, Collection<A> finiteAlphabet, double mutationProbability, double prob) {
		super(individualLength, finiteAlphabet, mutationProbability, new Random());
		this.prob = prob;
	}
	
	
	
	@Override
	protected List<Individual<A>> nextGeneration(List<Individual<A>> population, FitnessFunction<A> fitnessFn) {
		// new_population <- empty set
		List<Individual<A>> newPopulation = new ArrayList<Individual<A>>(population.size());
		// for i = 1 to SIZE(population) do
		for (int i = 0; i < population.size(); i++) {
			// x <- RANDOM-SELECTION(population, FITNESS-FN)
			Individual<A> x = randomSelection(population, fitnessFn);
			// y <- RANDOM-SELECTION(population, FITNESS-FN)
			Individual<A> y = randomSelection(population, fitnessFn);
			// child <- REPRODUCE(x, y)
			Individual<A> child;
			if (random.nextDouble() <= prob){
				child = reproduce(x, y);
			}
			else {
				List<Individual<A>> selFather = new ArrayList<Individual<A>>();
				selFather.add(x);
				selFather.add(y);
				child = randomSelection(selFather, fitnessFn);
			}
			if (random.nextDouble() <= mutationProbability) {
				child = mutate(child);
			}
			newPopulation.add(child);
		}
		notifyProgressTracers(getIterations(), population);
		return newPopulation;
	}
}
