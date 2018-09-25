package cm.uy1.training;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cm.uy1.helper.Helper;
import cm.uy1.modelDefinition.HMM;
import cm.uy1.modelDefinition.Observation;
import cm.uy1.modelDefinition.State;
import cm.uy1.modelDefinition.Transition;

/**
 * In this training, we suppose that the PRE, POST, TARGET, and OTHER sets are
 * disjoint
 * 
 * @author azanzi
 *
 */
public class HMMTrainingData {

	/**
	 * Retreives the list of observations emitted by each Hidden state and calculate
	 * the initial transition vector
	 * 
	 * @param hmm
	 * @param souceCodes
	 * @param preObservation
	 * @param targetObservation
	 * @param postObservation
	 * @param otherObservation
	 * @return
	 */
	public static HMM hmmInit(HMM hmm, List<String> souceCodes,
			List<String> preObservation, List<String> targetObservation, 
			List<String> postObservation, List<String> otherObservation) {
		// Get the initial transition from the HMM in prameter
		// Using the data furnish by the user, calculate and set
		// the real values of this vector
		// Write all the codes which calculte the initial transition vector

		String[] tmp;
		// The number of files of the training data
		int nbFile = souceCodes.size()+4;
		int nbPre = 1, nbTarget = 1, nbPost = 1, nbOther = 1;
		double preProba = 0.0, targetProba = 0.0, postProba = 0.0, otherProba = 0;
		// The set are disjoints
		for (String sourceFile : souceCodes) {
			tmp = sourceFile.split(" ");
			// Counting the number of PRE at the begining of each file
			if (Helper.belongs2List(tmp[0], preObservation)) {
				nbPre++;
			} 
			if (Helper.belongs2List(tmp[0], targetObservation)) {
				nbTarget++;
			} 
			if (!Helper.belongs2List(tmp[0], preObservation) && 
					!Helper.belongs2List(tmp[0], targetObservation)) {
				nbOther++;
			}
		}
		// Transitions
		List<Transition> initialTransition = new ArrayList<Transition>();
		initialTransition.add(new Transition(new State(), hmm.getPre(), (double)nbPre/(double)nbFile));
		initialTransition.add(new Transition(new State(), hmm.getTarget(), (double)nbTarget/(double)nbFile));
		initialTransition.add(new Transition(new State(), hmm.getPost(), (double)nbPost/(double)nbFile));
		initialTransition.add(new Transition(new State(), hmm.getOther(), (double)nbOther/(double)nbFile));
		
		hmm.setInitialTransition(initialTransition);

		return hmm;
	}

	// Calculation of observation model
	
	/**
	 * Pre observation vector
	 * @param hmm
	 * @param preObservation
	 * @param sourceCodes
	 * @return
	 */
	public static HMM preObservation(HMM hmm, List<String> preObservation, 
			List<String> sourceCodes) {

		// List of frequencies calculated
		List<Integer> frequencies = new ArrayList<>();

		List<Observation> observations = new ArrayList<>();

		String[] tmp;
		// To avoid zero probability, we have add a smoothing term (beta =
		// nbTPM/nbTotal)
		int nbTMP = 1;
		int nbTotal = preObservation.size();
		// Browse the list of preObservation and count the number of each element in the
		// training data
		for (String preObse : preObservation) {
			// Browse each file of the source code
			for (String sourceFile : sourceCodes) {
				tmp = sourceFile.split(" ");
				// Counting the number of times I see the element in the file
				nbTMP += nbObservFile(tmp, preObse);
			}
			// Add the frequency of the first element in the list
			frequencies.add(nbTMP);
			// Initialise the temporaries variables to 1 to avoid zero probabilities
			nbTMP = 1;
		}
		// Calculate the sum of all the frequencies oberved
		nbTotal = nbTotal + sumFrequencies(frequencies);
		// Calculate the Observation states
		for (int i = 0; i < frequencies.size(); i++) {
			observations.add(new Observation(preObservation.get(i),
					(double) (frequencies.get(i) + 1) / (double) nbTotal, hmm.getPre()));
		}
		// Add the observations to the PRE state of the HMM
		hmm.getPre().setObservations(observations);
		return hmm;

	}

	// Calculte the TARGET observation
	/**
	 * Target observation vector
	 * Because TARGET are not know, we call all the TARGET elements data
	 * @param hmm
	 * @param targetObservations
	 * @param preObservations
	 * @param postObservations
	 * @param sourceCodes
	 * @return
	 */
	public static  HMM targetObservation(HMM hmm,
			List<String> targetObservations,
			List<String>preObservations,
			List<String> postObservations,
			List<String>sourceCodes) {
		
		List<Observation> observations=new ArrayList<>();
		
		//We suppose that all the TARGET are unknown and are called data
		observations.add(new Observation("data", 1, hmm.getTarget()));
		hmm.getTarget().setObservations(observations);
		return hmm;
		
	}
	
	/**
	 * Post observation vector
	 * @param hmm
	 * @param postObservations
	 * @param preObservations
	 * @param sourceCodes
	 * @return
	 */
	
	public static HMM postObservation(HMM hmm, 
			List<String> postObservations,
			List<String> preObservations,
			List<String> sourceCodes) {

		List<Observation> observations = new ArrayList<>();
		
		Map<String, Integer> mapProbabilities = new HashMap<>();
		//Map initilization with the value 1 for the smoothing term (avoid 0 probability)
		for (String postOb : postObservations) {
			mapProbabilities.put(postOb, 1);
		}

		String[] tmp;
		// To avoid zero probability, we have add a smoothing term (beta =
		// nbTPM/nbTotal)
		int nbTMP = 1;
		int nbTotal = postObservations.size();
		//Browse the list of postObservation and count the number of each element in the 
		//training data
			//Browse each file of the source code
			for (String sourceFile : sourceCodes) {
				tmp = sourceFile.split(" ");
				
				//Counting the number of time I see the element in the file
				//If the element at i position is a PRE, then, there will be a list 
				//of targets finishing with a POST state
				for (int i = 0; i < tmp.length; i++) {
					if(Helper.belongs2List(tmp[i].trim(), preObservations)) {
						//Loop as we see the post and count this post element
						while(i<tmp.length && 
								!Helper.belongs2List(tmp[i].trim(), postObservations)) {
							i++;
						}
						if(i<tmp.length && 
								Helper.belongs2List(tmp[i].trim(), postObservations)) {
							mapProbabilities.put(tmp[i].trim(), 
									mapProbabilities.get(tmp[i].trim())+1);
						}
					}
				}
			}
		
		//Number of POST element
			for (String postObserv : postObservations) {
				nbTotal = nbTotal + mapProbabilities.get(postObserv);
			}
			//Calculating the observations states
			for (String postObsev : postObservations) {
				Observation observation = new Observation
						(postObsev, (double)mapProbabilities.get(postObsev)/(double)nbTotal, 
								hmm.getPost());
				observations.add(observation);
			}
			hmm.getPost().setObservations(observations);
			return hmm;

	}

	/**
	 * Other observation vector
	 * Because all the elements in OTHER set are unknown, we call them other
	 * @param hmm
	 * @param otherObservation
	 * @return
	 */
	public static HMM otherObservation(HMM hmm, List<String> otherObservation) {

		//We suppose that all others elements are unknown and are determine by pre and post states
		List<Observation> observations = new ArrayList<>();
		observations.add(new Observation("other", 1, hmm.getOther()));
		hmm.getOther().setObservations(observations);
		
		return hmm;

	}

	/**
	 * ********* The transitions vectors
	 */
	/**
	 * The PRE element is always follow by a TARGET or another PRE element
	 * 
	 * @param hmm
	 * @param preTransitions
	 * @param sourceCodes
	 * @param preObserved
	 * @return
	 */
	// Calculation of transition model
	public static HMM preTransition(HMM hmm, List<String> sourceCodes, List<String> preObserved) {
		// To avoid zero probability from transitions, we have add a smoothing
		// term beta
		int nbPre = 4;
		int nbPrePre = 1, nbPreTarget = 1, nbPrePost = 1, nbPreOther = 1;
		List<Transition> preTransitions = new ArrayList<>();
		String[] tmp;

		for (String sourceFile : sourceCodes) {
			tmp = sourceFile.split(" ");
			for (int i = 0; i < tmp.length; i++) {
				// The number of PRE in all the document
				if (Helper.belongs2List(tmp[i], preObserved))
					nbPre++;
				// Calculate the prePre value when a word belongs to the PRE vector
				// is follows by another word € PRE
				if (i+1 < tmp.length && Helper.belongs2List(tmp[i], preObserved)
						&& Helper.belongs2List(tmp[i + 1], preObserved)) {
					nbPrePre++;
				}
				// Calculate the preTARGET value when a word belonging to the PRE vector
				// is follows by another word € TARGET
				if (Helper.belongs2List(tmp[i], preObserved) && 
						!Helper.belongs2List(tmp[i + 1], preObserved)) {
					// The element is a target and a pre is follow by a target
					nbPreTarget++;
				}
			}
		}
		// Ajusting the probabilities
		nbPreTarget = nbPreTarget + (nbPre - nbPrePre - nbPreTarget - nbPrePost - nbPreOther);
		// Calculate the PRE transition
		preTransitions.add(new Transition(hmm.getPre(), hmm.getPre(), (double) nbPrePre / (double) nbPre));
		preTransitions.add(new Transition(hmm.getPre(), hmm.getTarget(), (double) nbPreTarget / (double) nbPre));
		preTransitions.add(new Transition(hmm.getPre(), hmm.getPost(), (double) nbPrePost / (double) nbPre));
		preTransitions.add(new Transition(hmm.getPre(), hmm.getOther(), (double) nbPreOther / (double) nbPre));
		// Set the preTransition vector to the HMM
		hmm.getPre().setTransitions(preTransitions);
		return hmm;
	}
/**
 * Calculating the TARGET transition vector
 * @param hmm
 * @param sourceCodes
 * @param preObserved
 * @param postObserved
 * @return
 */
	public static HMM targetTransition(HMM hmm, List<String> sourceCodes, 
			List<String> preObserved, List<String> postObserved) {
		//To avoid zero probability from transitions, we have add a smoothing term beta
		int nbTarget = 4;
		int nbTargetPre=1, nbTargetTarget=1, nbTargetPost=1, nbTargetOther=1;
		
		List<Transition> targetTransitions = new ArrayList<>();
		
		String[] tmp;
		
		for (String sourceFile : sourceCodes) {
			tmp = sourceFile.split(" ");
			for (int i = 0; i < tmp.length; i++) {
				//If the current element is a PRE and the following one is not a PRE
				//It is a TARGET
				if(i+1<tmp.length && Helper.belongs2List(tmp[i], preObserved) && 
						!Helper.belongs2List(tmp[i+1], preObserved)) {
					nbTarget++;
					i++;
					//Iterate over the array as I see a POST
					while(i<tmp.length && !Helper.belongs2List(tmp[i], postObserved)) {
						nbTarget++;
						nbTargetTarget++;
						i++;
					}
					if(i<tmp.length && 
							Helper.belongs2List(tmp[i], postObserved))
						nbTargetPost++;
				}
			}
		}
		//Ajusting the probabilities - the most probable observations are target->post
		nbTargetTarget = nbTargetTarget+(nbTarget-nbTargetPre-nbTargetPost-nbTargetTarget-nbTargetOther);

		//Calculate the TARGET transition
		targetTransitions.add(new Transition(hmm.getTarget(), 
				hmm.getPre(), (double)nbTargetPre/(double)nbTarget));
		targetTransitions.add(new Transition(hmm.getTarget(), 
				hmm.getTarget(), (double)nbTargetTarget/(double)nbTarget));
		targetTransitions.add(new Transition(hmm.getTarget(), 
				hmm.getPost(), (double)nbTargetPost/(double)nbTarget));
		targetTransitions.add(new Transition(hmm.getTarget(), 
				hmm.getOther(), (double)nbTargetOther/(double)nbTarget));
		
		//Set the TargetTransion vector to the HMM
		hmm.getTarget().setTransitions(targetTransitions);

		return hmm;
	}
/**
 * Calculating the POST transition vector
 * @param hmm
 * @param sourceCodes
 * @param postObserved
 * @param preObserved
 * @return
 */
	public static HMM postTransition(HMM hmm, List<String> sourceCodes,
			List<String> postObserved, List<String> preObserved) {

		//To avoid zero probability from transitions, we have add a smoothing term beta
		int nbPost=4;
		int nbPostPre=1, nbPostTarget=1, nbPostPost=1, nbPostOther=1;
		List<Transition> postTransitions = new ArrayList<>();
		
		String[] tmp;
		
		for (String sourceFile : sourceCodes) {
			tmp = sourceFile.split(" ");
			for (int i = 0; i < tmp.length; i++) {
				if(Helper.belongs2List(tmp[i], preObserved)) {
					//Loop until I see a post
					while(i<tmp.length && !Helper.belongs2List(tmp[i], postObserved)) {
						i++;
					}
					if(i<tmp.length && 
							Helper.belongs2List(tmp[i], postObserved)) {
						nbPost++;
						//The next element is it a PRE ?
						if(i+1<tmp.length && Helper.belongs2List(tmp[i+1], preObserved))
							nbPostPre++;
						//The next element is it a POST ?
						if(i+1<tmp.length && Helper.belongs2List(tmp[i+1], postObserved))
							nbPostPost++;
						//Or the next element is an OTHER ? from POST -> OTHER
						if(i+1<tmp.length && 
								!Helper.belongs2List(tmp[i+1], preObserved)&&
								!Helper.belongs2List(tmp[i+1], postObserved))
							nbPostOther++;
					}
				}
			}
		}
		//Ajusting the probability: the most probable post -> pre
		nbPostPre = nbPostPre + (nbPost - nbPostPre - nbPostTarget - nbPostPost - nbPostOther);
		//Calculte the POST transision
		postTransitions.add(new Transition(hmm.getPost(), hmm.getPre(), (double)nbPostPre/(double)nbPost));
		postTransitions.add(new Transition(hmm.getPost(), hmm.getTarget(), (double)nbPostTarget/(double)nbPost));
		postTransitions.add(new Transition(hmm.getPost(), hmm.getPost(), (double)nbPostPost/(double)nbPost));
		postTransitions.add(new Transition(hmm.getPost(), hmm.getOther(), (double)nbPostOther/(double)nbPost));
		// Set the preTransition vector to the HMM
		hmm.getPost().setTransitions(postTransitions);
//		
		return hmm;
	}
/**
 * Calculating the OTHER transition vector
 * @param hmm
 * @param sourceCodes
 * @param preObserved
 * @param targetObserved
 * @param postObserved
 * @return
 */
	public static HMM otherTransition(HMM hmm, 
			List<String> sourceCodes, List<String> preObserved, 
			List<String>targetObserved, List<String> postObserved) {
		
		//To avoid zero probabilities from transitions, we have add a smoothing term beta
		int nbOther = 4;
		int nbOtherPre = 1, nbOtherTarget=1, nbOtherPost=1, nbOtherOther=1;

		List<Transition> otherTransition = new ArrayList<>();
		String[] tmp;
		for (String sourceFile : sourceCodes) {
			tmp = sourceFile.split(" ");
			for (int i = 0; i < tmp.length; i++) {
				//If the element at index i is a PRE, then find the POST (index i+k) state and test
				//the element at i+k+1 -> can be OTHER or PRE
				if(Helper.belongs2List(tmp[i], preObserved)) {
					while(i<tmp.length && !Helper.belongs2List(tmp[i], postObserved)) {
						i++;
					}//the element at i+k is a post
					if(i<tmp.length && 
							Helper.belongs2List(tmp[i], postObserved)) {
						if(i+1<tmp.length && !Helper.belongs2List(tmp[i+1], preObserved)) {
							//It is in the set OTHER
							i++;
							nbOther++;
							while(i<tmp.length) {
								i++;
								if(i<tmp.length && Helper.belongs2List(tmp[i], preObserved)) {
									nbOtherPre++;
									break;
								}
								if(i<tmp.length && Helper.belongs2List(tmp[i], targetObserved)) {
									nbOtherTarget++;
									break;
								}
								if(i<tmp.length 
										&& !Helper.belongs2List(tmp[i], preObserved)
										&& !Helper.belongs2List(tmp[i], targetObserved)) {
									nbOtherOther++;
								}
							}
						}
					}
				}
				//If the element at index i is a TARGET, then find the POST (index i+k) state and test
				//the element at i+k+1 -> can be OTHER or PRE
				if(i<tmp.length 
						&& Helper.belongs2List(tmp[i], targetObserved)) {
					while(i<tmp.length && !Helper.belongs2List(tmp[i], postObserved)) {
						i++;
					}//The element at the position i+k is a post
					//Is the next one is not a PRE, it is in the set OTHER
					if(i+1<tmp.length && !Helper.belongs2List(tmp[i+1], preObserved)) {
						//It is in the set OTHER
						i++;
						nbOther++;
						while(i<tmp.length) {
							i++;
							if(i<tmp.length && Helper.belongs2List(tmp[i], preObserved)) {
								nbOtherPre++;
								break;
							}
							if(i<tmp.length && Helper.belongs2List(tmp[i], targetObserved)) {
								nbOtherTarget++;
								break;
							}
							if(i<tmp.length 
									&& !Helper.belongs2List(tmp[i], preObserved)
									&& !Helper.belongs2List(tmp[i], targetObserved)) {
								nbOtherOther++;
							}
						}
					}
				}
				//If the element at index i is in the set OTHER, then find the PRE (index i+k)
				//state and test the element at i+k+1 -> can be OTHER or PRE
				if(i<tmp.length 
						&& !Helper.belongs2List(tmp[i], preObserved) && 
						!Helper.belongs2List(tmp[i], targetObserved)) {
					//The element is in the set OTHER
					nbOther++;
					while(i<tmp.length) {
						i++;
						if(i<tmp.length && Helper.belongs2List(tmp[i], preObserved)) {
							nbOtherPre++;
							break;
						}
						if(i<tmp.length && Helper.belongs2List(tmp[i], targetObserved)) {
							nbOtherTarget++;
							break;
						}
						if(i<tmp.length 
								&& !Helper.belongs2List(tmp[i], preObserved)
								&& !Helper.belongs2List(tmp[i], targetObserved)) {
							nbOtherOther++;
						}
					}
				}
			}
		}
		//Ajusting the probabilities
		nbOtherOther = nbOtherOther + (nbOther-nbOtherPre-nbOtherTarget-nbOtherPost-nbOtherOther);
		//Calculate the OTHER transitions
		otherTransition.add(new Transition(hmm.getOther(), hmm.getPre(), (double)nbOtherPre/(double)nbOther));
		otherTransition.add(new Transition(hmm.getOther(), hmm.getTarget(), (double)nbOtherTarget/(double)nbOther));
		otherTransition.add(new Transition(hmm.getOther(), hmm.getPost(), (double)nbOtherPost/(double)nbOther));
		otherTransition.add(new Transition(hmm.getOther(), hmm.getOther(), (double)nbOtherOther/(double)nbOther));
		//Set the otherTransition vector to the HMM
		hmm.getOther().setTransitions(otherTransition);
		return hmm;
	}
	
	
	/**
	 * Counting the number of time a word belongs to a list of string
	 * 
	 * @param tmp
	 * @param preObse
	 * @return
	 */
	private static int nbObservFile(String[] tmp, String preObse) {
		int nbTimes = 0;
		for (int i = 0; i < tmp.length; i++)
			if (tmp[i].trim().equals(preObse))
				nbTimes++;
		return nbTimes;
	}

	/**
	 * Calculate the sum of all the elements in the list
	 * 
	 * @param frequencies
	 * @return
	 */
	private static int sumFrequencies(List<Integer> frequencies) {
		int nbTotal = 0;
		for (Integer nb : frequencies) {
			nbTotal += nb;
		}
		return nbTotal;
	}

}
