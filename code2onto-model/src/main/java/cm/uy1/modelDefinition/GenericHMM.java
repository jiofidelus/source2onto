//PB avec les autres HMM: les états sont fixés à l'avance. On utilise les métadonnées pour identifier 
//les éléments pertinents or on peut imaginer un HMM dans lequel on ajoute les etiquettes dans tout le 
//code se sorte que lorsque l'on extrait, on sait qui est concept, attribut, etc. Un exemple: 
//class - PRE, Patient - CONCEPT, age - ATTRIBUT, etc.

package cm.uy1.modelDefinition;


import java.util.ArrayList;
import java.util.List;


public class GenericHMM {
	
	//The list of states
	private List<State> listStates = new ArrayList<>();

	//Initial transition
	private List<Transition> initialTransition;

	public List<State> getListStates() {
		return listStates;
	}

	public void setListStates(List<State> listStates) {
		this.listStates = listStates;
	}

	public List<Transition> getInitialTransition() {
		return initialTransition;
	}

	public void setInitialTransition(List<Transition> initialTransition) {
		this.initialTransition = initialTransition;
	}

	@Override
	public String toString() {
		String toBeReturn="\n-------------Initial transition-----------------------\n";
		for (Transition transition : initialTransition) {
			toBeReturn+=transition.getEndState().getLabel()+
					"->"+transition.getTransitionValue()+"\n";
		}
		for (State state : listStates) {
			toBeReturn+=state.toString();
		}
		return toBeReturn;
	}
}







