package cm.uy1.modelDefinition;


import java.util.ArrayList;
import java.util.List;


public class HMM_ {
	
	//The states
	private State pre=new State();
	private State target=new State();
	private State post=new State();
	private State other=new State();
	
	//List of transitions
	private List<Transition> preTransitions = new ArrayList<>();
	private List<Transition> targetTransitions = new ArrayList<>();
	private List<Transition> postTransitions = new ArrayList<>();
	private List<Transition> otherTransitions = new ArrayList<>();
	
	//Initial transition
	private List<Transition> initialTransition;
	
	//List of the observations
	private List<Observation> preObservation = new ArrayList<>();
	private List<Observation> targetObservation = new ArrayList<>();
	private List<Observation> postObservation = new ArrayList<>();
	private List<Observation> otherObservation = new ArrayList<>();
	
	/**
	 * This method is used to build the states and the 
	 * observation
	 */
	public void init() {
		//Initial vector
		initialTransition.add(new Transition(new State(), pre, 0));
		initialTransition.add(new Transition(new State(), target, 0));
		initialTransition.add(new Transition(new State(), post, 0));
		initialTransition.add(new Transition(new State(), other, 0));
		
		//Initialize PRE transition vector with 0 probabilities
		List<Transition> preTransition=new ArrayList<>();
		preTransition.add(new Transition(pre, pre, 0));
		preTransition.add(new Transition(pre, target, 0));
		preTransition.add(new Transition(pre, post, 0));
		preTransition.add(new Transition(pre, other, 0));

		//Initialize the PRE observation vector
		List<Observation> preObserved=new ArrayList<>();
//		for (String label : preObservation) {
//			preObserved.add(new Observation(label, 0, pre));
//		}
		//Set the transition and the emission of the HMM
		pre.setLabel("PRE");
		pre.setObservations(preObserved);
		pre.setTransitions(preTransition);

		//Initialize TARGET transition vector with 0 probabilities
		List<Transition> targetTransition=new ArrayList<>(); //{{
		targetTransition.add(new Transition(target, pre, 0));
		targetTransition.add(new Transition(target, target, 0));
		targetTransition.add(new Transition(target, post, 0));
		targetTransition.add(new Transition(target, other, 0));
//		}};

		//Initialize the TARGET emission vector with 0 probabilities
		List<Observation> targetObserved=new ArrayList<>();
//		for (String label : targetObservation) {
//			targetObserved.add(new Observation(label, 0, target));
//		}
		//Set the transition and the emission of the HMM
		target.setLabel("TARGET");
		target.setObservations(targetObserved);
		target.setTransitions(targetTransition);

		//Initialize POST transition vector with 0 probabilities
		List<Transition> postTransition=new ArrayList<>();// {{
		postTransition.add(new Transition(post, pre, 0));
		postTransition.add(new Transition(post, target, 0));
		postTransition.add(new Transition(post, post, 0));
		postTransition.add(new Transition(post, other, 0));
//		}};

		//Initialize the POST emission vector with 0 probabilities
		List<Observation> postObserved=new ArrayList<>();
//		for (String label : postObservation) {
//			postObserved.add(new Observation(label, 0, post));
//		}
		//Set the transition and the emission of the HMM
		post.setLabel("POST");
		post.setObservations(postObserved);
		post.setTransitions(postTransition);
		
		//Initialize OTHER transition vector with 0 probabilities
		List<Transition> otherTransition=new ArrayList<>(); //{{
		otherTransition.add(new Transition(other, pre, 0));
		otherTransition.add(new Transition(other, target, 0));
		otherTransition.add(new Transition(other, post, 0));
		otherTransition.add(new Transition(other, other, 0));
//		}};
		

		//Initialize the POST emission vector with 0 probabilities
		List<Observation> otherObserved=new ArrayList<>();
//		for (String label : otherObservation) {
//			otherObserved.add(new Observation(label, 0, other));
//		}
		//Set the transition and the emission of the HMM
		other.setLabel("OTHER");
		other.setObservations(otherObserved);
		other.setTransitions(otherTransition);
	}

}







