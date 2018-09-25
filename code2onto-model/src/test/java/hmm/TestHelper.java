package hmm;

import java.util.ArrayList;
import java.util.List;

import cm.uy1.helper.Helper;
import cm.uy1.modelDefinition.HMM;
import cm.uy1.modelDefinition.Observation;
import cm.uy1.modelDefinition.State;
import cm.uy1.modelDefinition.Transition;
import cm.uy1.modelUse.Column;
import cm.uy1.modelUse.Frame;

public class TestHelper {
	
	public static final String TRAININDATA = "/home/azanzi/Documents/workspace/"
			+ "knowExtracFromSC/experiments/DataSources/1-TrainDS/1-raw_data";

	static String fileTestPath = "/home/azanzi/Documents/workspace/"
			+ "knowExtracFromSC/experiments/DataSources/1-TrainDS/"
			+ "1-raw_data/StudentGpaComparator.java"; 

	static String knowledgeExtractionPath = "/home/azanzi/"
			+ "Documents/workspace/AdditionalMaterials/conceptsExtracted";
	
	public static void main(String[] args) {
		//The data list for the test
		List<String> dataList = new ArrayList<String>() {{
			add("public");
			add("private");
			add("protected");
			add("static");
			add("final");
		}};

		State preState = new State("PRE");
		State targetState = new State("TARGET");
		State postState = new State("POST");
		State otherState = new State("OTHER");
		
		//Initial transition
		List<Transition> initialTransitions = new ArrayList<>();
		initialTransitions.add(new Transition(new State(), preState, 0.112));
		initialTransitions.add(new Transition(new State(), targetState, 0.452));
		initialTransitions.add(new Transition(new State(), postState, 0.654));
		initialTransitions.add(new Transition(new State(), otherState, 0.02445));
		
		//Some observations for the tests
		Observation obs1 = new Observation("a", 0.524, preState);
		Observation obs2 = new Observation("b", 0.114, preState);
		Observation obs3 = new Observation("c", 0.0544, preState);
		Observation obs4 = new Observation("d", 0.784, preState);
		List<Observation> preObservations = new ArrayList<>();
		
		preObservations.add(obs1);
		preObservations.add(obs2);
		preObservations.add(obs3);
		preObservations.add(obs4);
		
		//PRE transisions
		List<Transition> preTransitions = new ArrayList<>();
		preTransitions.add(new Transition(preState, preState, 0.1154));
		preTransitions.add(new Transition(preState, targetState, 0.0245));
		preTransitions.add(new Transition(preState, postState, 0.01455));
		preTransitions.add(new Transition(preState, otherState, 0.557));
		
		preState.setTransitions(preTransitions);
		preState.setObservations(preObservations);
		
		//TARGET state------------------
		List<Transition> targetTransition = new ArrayList<>();
		targetTransition.add(new Transition(targetState, preState, 0.1254));
		targetTransition.add(new Transition(targetState, targetState, 0.2544));
		targetTransition.add(new Transition(targetState, postState, 0.365));
		targetTransition.add(new Transition(targetState, otherState, 0.785));
		
		List<Observation> targetObservations = new ArrayList<>();
		targetObservations.add(new Observation("data", 1, targetState));
		
		targetState.setTransitions(targetTransition);
		targetState.setObservations(targetObservations);
		
		//Post state-----------------
		List<Transition> postTransition = new ArrayList<>();
		postTransition.add(new Transition(postState, preState, 0.1575));
		postTransition.add(new Transition(postState, targetState, 0.1575));
		postTransition.add(new Transition(postState, postState, 0.1575));
		postTransition.add(new Transition(postState, otherState, 0.1575));
		
		//Observations
		List<Observation> postObservations = new ArrayList<>();
		postObservations.add(new Observation("e", 0.4484, postState));
		postObservations.add(new Observation("f", 0.784, postState));
		postObservations.add(new Observation("g", 0.02325, postState));
		postObservations.add(new Observation("h", 0.658, postState));
		
		postState.setTransitions(postTransition);
		postState.setObservations(postObservations);
		
		//OTHER state---------------------
		List<Transition> otherTransitions = new ArrayList<>();
		otherTransitions.add(new Transition(otherState, preState, 0.145));
		otherTransitions.add(new Transition(otherState, targetState, 0.7844));
		otherTransitions.add(new Transition(otherState, postState, 0.668));
		otherTransitions.add(new Transition(otherState, otherState, 0.0245));
		
		otherState.setTransitions(otherTransitions);
		
		List<Observation> otherObservations = new ArrayList<>();
		otherObservations.add(new Observation("other", 1, otherState));
		
		otherState.setObservations(otherObservations);
		
		/**
		 * Create and initialise the HMM
		 */
		HMM hmm = new HMM();
		hmm.setInitialTransition(initialTransitions);
		hmm.setPre(preState);
		hmm.setTarget(targetState);
		hmm.setPost(postState);
		hmm.setOther(otherState);
		
		/**
		 * the tmp[] tab for the tests
		 */
		String[] tmp = {"a", "k", "p", "x", "e", "c", 
				"5", "q", "f", "21", "sd", "v", "g", "s", "v",
				"5", "m", "8", "0"};
		//Create the column
		Column column = new Column();
		//Create the frames
		Frame framePre = new Frame("package", "PRE", 2*10E-201);
		Frame frameTarget = new Frame("fidelus", "TARGET", 3*10E-209);
		Frame framePost = new Frame(";", "POST", 4*10E-209);
		Frame frameOTHER = new Frame("other", "OTHER", 2*10E-209);
		column.setPreFrame(framePre);
		column.setTargetFrame(frameTarget);
		column.setPostFrame(framePost);
		column.setOtherFrame(frameOTHER);
		/**
		 * Testing the HMM
		 */
//		System.out.println("Show the HMM that will be used for the tests");
//		System.out.println(hmm.toString());
		
		/**
		 * Testing belong2list method:
		 */
//		System.out.println("**********************************************************");
//		System.out.println("Testing if the string belongs to a list");
//		System.out.println(Helper.belongs2List("public", dataList)+" belongs to the list");
//		System.out.println(Helper.belongs2List("sddsf", dataList)+" don't belongs to the list");
//
//		/**
//		 * Testing the getFile method to get all the files in a folder
//		 */
//		System.out.println("**********************************************************");
//		System.out.println("Testing the getFile method that returns all the file of a folder");
//		List<String> listFiles = Helper.getFiles(TRAININDATA);
//		for (String doc : listFiles) {
//			System.out.println(doc);
//		}
//		/**
//		 * Testing the method that count the number of files in a folder
//		 */
//		System.out.println("**********************************************************");
//		System.out.println("Counting the number of files in a folder");
//		System.out.println(Helper.getNBFiles(TRAININDATA));
//		
//		/*
//		 * Testing the method that count the number of statements in a folder
//		 */
//		System.out.println("**********************************************************");
//		System.out.println("Counting the number of statements in a folder");
//		System.out.println(Helper.getNBInstructions(TRAININDATA));
//		
//		/**
//		 * Testing the method that return a string corresponding to all the statements
//		 * get in a source file
//		 */
//		System.out.println("**********************************************************");
//		System.out.println("Testing the method to get data from file");
//		System.out.println(Helper.getDataFromFiles(fileTestPath));
//
//		/**
//		 * Testing the method that return all the statements of a software
//		 */
//		System.out.println("**********************************************************");
//		System.out.println("Testing the method to get all data from file");
//		List<String> allDataFromFolder = Helper.getAllDataFromFolder(TRAININDATA);
//		for (String data : allDataFromFolder) {
//			System.out.println(data);
//		}
		/**
		 * Testing the getMaxFrame method that return the frame of a column which has the 
		 * greatest probability for the underline label to be observed
		 */
//		System.out.println("From this column, the frame that will be used by the viterbi algorithm:");
//		System.out.println(Helper.getMaxFrame(framePre, frameTarget, framePost, frameOTHER, 
//				2, 4, 7, 10));
		
		/**
		 * Testing the getObservedLabel which returns the label of a state 
		 */
//		System.out.println("The label observed are:");
//		System.out.println(Helper.getObservedLabels(preState));

		/**
		 * Testing the methods that return the value emitted by a state (pre, target, post, other)
		 * given a string. The string is at position i in the string tmp
		 */
		//PRE state
//		PRE: a, b, c, d
//		POST: e, f, g, h
//		String[] tmp = {"a", "k", "p", "x", "e", "c", 
//				"5", "q", "f", "21", "sd", "v", "g", "s", "v"};		
//		System.out.println("****************Testing the PRE state****************");
//		System.out.println("The prevalue is : "
//				 + Helper.preEmittedValue(tmp, 0, hmm));
//		System.out.println("The prevalue is : "
//				 + Helper.preEmittedValue(tmp, 1, hmm));
//		System.out.println("The prevalue is : "
//				 + Helper.preEmittedValue(tmp, 5, hmm));
//		System.out.println("The prevalue is : "
//				 + Helper.preEmittedValue(tmp, 6, hmm));
//		System.out.println("The prevalue is : "
//				 + Helper.preEmittedValue(tmp, 8, hmm));
//		//TARGET state
//		/**
//		 * Testing the methods that return the value emitted by a state (pre, target, post, other)
//		 * given a string. The string is at position i in the string tmp
//		 */
////		PRE: a, b, c, d
////		POST: e, f, g, h
////		String[] tmp = {"a", "k", "p", "x", "e", "c", 
////				"5", "q", "f", "21", "sd", "v", "g", "s", "v"};
//		System.out.println("****************Testing the TARGET state****************");
//		System.out.println("The target is : "
//				 + Helper.targetEmittedValue(tmp, 0, hmm));
//		System.out.println("The target is : "
//				 + Helper.targetEmittedValue(tmp, 1, hmm));
//		System.out.println("The target is : "
//				 + Helper.targetEmittedValue(tmp, 5, hmm));
//		System.out.println("The target is : "
//				 + Helper.targetEmittedValue(tmp, 6, hmm));
//		System.out.println("The target is : "
//				 + Helper.targetEmittedValue(tmp, 8, hmm));
//		
//		System.out.println("****************Testing the POST state****************");
////		PRE: a, b, c, d
////		POST: e, f, g, h
////		String[] tmp = {"a", "k", "p", "x", "e", "c", 
////				"5", "q", "f", "21", "sd", "v", "g", 
////		"s", "v", "5", "m", "8", "0"};
//		System.out.println("The post is : "
//				 + Helper.postEmittedValue(tmp, 0, hmm));
//		System.out.println("The post is : "
//				 + Helper.postEmittedValue(tmp, 1, hmm));
//		System.out.println("The post is : "
//				 + Helper.postEmittedValue(tmp, 4, hmm));
//		System.out.println("The post is : "
//				 + Helper.postEmittedValue(tmp, 6, hmm));
//		System.out.println("The post is : "
//				 + Helper.postEmittedValue(tmp, 8, hmm));
//
//		System.out.println("****************Testing the OTHER state****************");
////		PRE: a, b, c, d
////		POST: e, f, g, h
//		String[] tmp1 = {"a", "k", "p", "x", "e", "c", 
//				"5", "q", "f", "21", "sd", "v", "g", "s", "v",
//				"x", "a", "m", "8", "f", "51", "pio", "nhb"};
//		System.out.println("The other is : "
//				 + Helper.otherEmittedValue(tmp1, 0, hmm));
//		System.out.println("The other is : "
//				 + Helper.otherEmittedValue(tmp1, 14, hmm));
//		System.out.println("The other is : "
//				 + Helper.otherEmittedValue(tmp1, 18, hmm));
//		System.out.println("The other is : "
//				 + Helper.otherEmittedValue(tmp1, 15, hmm));
//		System.out.println("The other is : "
//				 + Helper.otherEmittedValue(tmp1, 21, hmm));

		/**
		 * Testing to add column to alpha table 
		 */
//		System.out.println("Before the method:");
//		System.out.println(column);
//		System.out.println(Helper.addColumnToAlphaTable(column));
//		System.out.println("After the method");
//		System.out.println(column);
		
		/**
		 * Testing the most greatest frame probability
		 * It returns the frame of the column that have the most greatest
		 * probability value
		 */
//		System.out.println("The most greatest frame proba of the column:"+column);
//		System.out.println(Helper.mostGreatestFrameProba(column));
//		System.out.println(hek);
		
		/**
		 * Testing how to get knowledge from file
		 */
//		System.out.println(Helper.getClass
//				(knowledgeExtractionPath, "class"));
	}
	
}











