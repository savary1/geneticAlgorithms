package geneticMod;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import aima.core.search.local.FitnessFunction;
import aima.core.search.local.GeneticAlgorithm;
import aima.core.search.local.Individual;

public class ModTres<A> extends GeneticAlgorithm<A>{
		
	public ModTres(int individualLength, Collection<A> finiteAlphabet, double mutationProbability, double prob) {
		super(individualLength, finiteAlphabet, mutationProbability, new Random());
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
			Individual<A> child = reproduce(x, y);
			if (fitnessFn.apply(x) > fitnessFn.apply(child) || fitnessFn.apply(y) > fitnessFn.apply(child)){
				if(fitnessFn.apply(x) > fitnessFn.apply(y))
					child = new Individual<A>(x.getRepresentation());
				else
					child = new Individual<A>(y.getRepresentation());
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