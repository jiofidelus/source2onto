package cm.uy1.modelDefinition;


import java.util.ArrayList;
import java.util.List;


public class HMM {
	
	//The states
	private State pre=new State();
	private State target=new State();
	private State post=new State();
	private State other=new State();
	
	//Observations
	List<Observation> preObserved;
	List<Observation> targetObserved;
	List<Observation> postObserved;
	List<Observation> otherObserved;
		
	//Initial transition
	private List<Transition> initialTransition;
	
	/**
	 * This method is used to build the states and the 
	 * observation
	 */
	
	public HMM() {
		//Create the states
		pre=new State();
		target=new State();
		post=new State();
		other=new State();
	}
	
	public void init() {
//		System.out.println("*********Entering the method init**************");
		//Initialize PRE transition vector with 0 probabilities
		List<Transition> preTransition=new ArrayList<Transition>();
		preTransition.add(new Transition(pre, pre, 0));
		preTransition.add(new Transition(pre, target, 0));
		preTransition.add(new Transition(pre, post, 0));
		preTransition.add(new Transition(pre, other, 0));

		//Initialize the PRE observation vector
		preObserved=new ArrayList<Observation>();

		//Set the transition and the emission of the HMM
		pre.setLabel("PRE");
		pre.setObservations(preObserved);
		pre.setTransitions(preTransition);
		
		//Initialize TARGET transition vector with 0 probabilities
		List<Transition> targetTransition=new ArrayList<Transition>();
		targetTransition.add(new Transition(target, pre, 0));
		targetTransition.add(new Transition(target, target, 0));
		targetTransition.add(new Transition(target, post, 0));
		targetTransition.add(new Transition(target, other, 0));

		//Initialize the TARGET emission vector with 0 probabilities
		targetObserved=new ArrayList<Observation>();

		//Set the transition and the emission of the HMM
		target.setLabel("TARGET");
		target.setObservations(targetObserved);
		target.setTransitions(targetTransition);

		//Initialize POST transition vector with 0 probabilities
		List<Transition> postTransition=new ArrayList<Transition>();
		postTransition.add(new Transition(post, pre, 0));
		postTransition.add(new Transition(post, target, 0));
		postTransition.add(new Transition(post, post, 0));
		postTransition.add(new Transition(post, other, 0));

		//Initialize the POST emission vector with 0 probabilities
		postObserved=new ArrayList<Observation>();

		//Set the transition and the emission of the HMM
		post.setLabel("POST");
		post.setObservations(postObserved);
		post.setTransitions(postTransition);

		//Initialize OTHER transition vector with 0 probabilities
		List<Transition> otherTransition=new ArrayList<Transition>();
		otherTransition.add(new Transition(other, pre, 0));
		otherTransition.add(new Transition(other, target, 0));
		otherTransition.add(new Transition(other, post, 0));
		otherTransition.add(new Transition(other, other, 0));		

		//Initialize the POST emission vector with 0 probabilities
		otherObserved=new ArrayList<Observation>();

		//Set the transition and the emission of the HMM
		other.setLabel("OTHER");
		other.setObservations(otherObserved);
		other.setTransitions(otherTransition);

//		//Initial vector
		initialTransition = new ArrayList<Transition>();
		initialTransition.add(new Transition(new State(), pre, 0));
		initialTransition.add(new Transition(new State(), target, 0));
		initialTransition.add(new Transition(new State(), post, 0));
		initialTransition.add(new Transition(new State(), other, 0));
//		
//		System.out.println("*****The PRE state"
//				+ "************"+pre.toString()+" \n target state: "+target.toString()
//				+"\n other state: "+post.toString()+"\n other state: "+other.toString());
//		System.out.println("************Exit the method init**********");
	}

	public State getPre() {
		return pre;
	}

	public void setPre(State pre) {
		this.pre = pre;
	}

	public State getTarget() {
		return target;
	}

	public void setTarget(State target) {
		this.target = target;
	}

	public State getPost() {
		return post;
	}

	public void setPost(State post) {
		this.post = post;
	}

	public State getOther() {
		return other;
	}

	public void setOther(State other) {
		this.other = other;
	}

	public List<Transition> getInitialTransition() {
		return initialTransition;
	}

	public void setInitialTransition(List<Transition> initialTransition) {
		this.initialTransition = initialTransition;
	}

	public List<Observation> getPreObserved() {
		return preObserved;
	}

	public void setPreObserved(List<Observation> preObserved) {
		this.preObserved = preObserved;
	}

	public List<Observation> getTargetObserved() {
		return targetObserved;
	}

	public void setTargetObserved(List<Observation> targetObserved) {
		this.targetObserved = targetObserved;
	}

	public List<Observation> getPostObserved() {
		return postObserved;
	}

	public void setPostObserved(List<Observation> postObserved) {
		this.postObserved = postObserved;
	}

	public List<Observation> getOtherObserved() {
		return otherObserved;
	}

	public void setOtherObserved(List<Observation> otherObserved) {
		this.otherObserved = otherObserved;
	}

	@Override
	public String toString() {
		String toBeReturn="\n-------------Initial transition-----------------------\n";
		for (Transition transition : initialTransition) {
			toBeReturn+=transition.getEndState().getLabel()+
					"->"+transition.getTransitionValue()+"\n";
		}
		toBeReturn+=pre.toString();
		toBeReturn+=target.toString();
		toBeReturn+=post.toString();
		toBeReturn+=other.toString();
		
		return toBeReturn;
	}
}







