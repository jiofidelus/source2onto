package cm.uy1.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import cm.uy1.modelDefinition.HMM;
import cm.uy1.modelDefinition.Observation;
import cm.uy1.modelDefinition.State;
import cm.uy1.modelDefinition.Transition;
import cm.uy1.modelUse.Column;
import cm.uy1.modelUse.Frame;

public class Helper {

	/**
	 * //Test if a String belong to an array
	 * 
	 * @param chaineAtester
	 * @param tableau
	 * @return
	 */
	public static boolean belongs2List(String chaineAtester, List<String> dataList) {
		boolean belongs = false;
		for (String chaine : dataList) {
			if (chaineAtester.equals(chaine)) {
				belongs = true;
			}
		}
		return belongs;
	}

	/**
	 * Get all the files of a projects in form of a list of string
	 * 
	 * @param PROJECTPATH
	 * @return
	 */
	public static List<String> getFiles(String PROJECTPATH) {
		List<String> listFiles = new ArrayList<String>();
		try (Stream<Path> filePathStream = Files.walk(Paths.get(PROJECTPATH))) {
			filePathStream.forEach(filePath -> {
				if (Files.isRegularFile(filePath)) {
					// Count the number of files
					listFiles.add(filePath.toString());
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listFiles;
	}

	/**
	 * //Calculate the number of files
	 * 
	 * @param PROJECTPATH
	 * @return
	 */
	public static int getNBFiles(String PROJECTPATH) {
		return getFiles(PROJECTPATH).size();
	}

	/**
	 * //Calculate the number of instructions of a project
	 * 
	 * @param PROJECTPATH
	 * @return
	 */
	public static int getNBInstructions(String PROJECTPATH) {
		List<String> listDocs = getFiles(PROJECTPATH);
		LineNumberReader lnr;
		int nbInstr = 0;
		for (String doc : listDocs) {
			try {
				lnr = new LineNumberReader(new FileReader(new File(doc.trim())));
				lnr.skip(Long.MAX_VALUE);
				nbInstr = nbInstr + lnr.getLineNumber() + 1;
				lnr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return nbInstr;
	}

	/**
	 * get the data from a file Very usefull when training after the data collection
	 * step without collect information again
	 * 
	 * @param filePath
	 * @return
	 */
	public static String getDataFromFiles(String filePath) {
		String data = "";
		FileReader fr;
		BufferedReader br;
		try {
			fr = new FileReader(filePath);
			br = new BufferedReader(fr);

			System.out.println("***********"
					+ "Enter the getDataFromFile method for loop");
			for (String line; (line = br.readLine()) != null;) {
				// line = line.replace("[^\\s]+", "");
				// Removes the blank space
				line = line.replaceAll("\\s+", " ");
				line = line.replaceAll(";", "\\ ;\\ ");
				line = line.replaceAll("\\s+}", "\\ }\\ ");
				data += line.toString();
				// data += line.replace("[\\n|\\t|\\r]", "");
				System.out.println("The file readed: "+filePath);
				System.out.println("++++++++++++++++++++++++"
						+ "The line readed "+br.readLine());
			}
			System.out.println("***********Exit the getDataFromFile method for loop");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return data;
	}


	public static String getKnowledgeFromFiles(String filePath) {
		String data = "";
		FileReader fr;
		BufferedReader br;
		try {
			fr = new FileReader(filePath);
			br = new BufferedReader(fr);
			for (String line; (line = br.readLine()) != null;) {
				data += line.toString()+" ";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return data;
	}

	
	/**
	 * get the data from a file
	 * 
	 * @param folderPath
	 * @return
	 */
	public static List<String> getAllDataFromFolder(String folderPath) {
		List<String> filesList = Helper.getFiles(folderPath);
		List<String> data = new ArrayList<>();
		System.out.println("*********************"
				+ "Enter the for loop to loop on the file list: "+filesList.size());
		for (String filePath : filesList) {
			data.add(getDataFromFiles(filePath));
			System.out.println("The file : "+filePath);
		}
		System.out.println("*********************"
				+ "Exit the for loop to loop on the file list");

		return data;
	}

	public static void writeOntoToFile(String filePATH, String data) {
		FileWriter fw = null;
		PrintWriter pw = null;
		try {
			fw = new FileWriter(filePATH, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		pw = new PrintWriter(fw);
		pw.write(data);
		pw.close();
	}

	/**
	 * Return the argMax value usable to calculate the probability
	 * alpha(i,t+1)=P(Wt+1=wt+1|Ht+1=i) x argMax[(P(Ht+1=i|Ht=j) x alpha(j,t)] The
	 * method return argMax[(P(Ht+1=i|Ht=j) x alpha(j,t)] For example alpha(PRE, 2)
	 * = P(W2=public|H2=PRE) x argMax[P(H2=PRE|H1=j) x alpha(j,1)] In the previous
	 * case, j takes the values: j=PRE, POST, TARGET ou OTHER and t = 1
	 * 
	 * @param frameOne
	 * @param frameTwo
	 * @param frameFour
	 * @param oneOne
	 * @param oneTwo
	 * @param oneFour
	 * @return
	 */

	public static Frame getMaxFrame(Frame frameOne, Frame frameTwo, 
			Frame frameThree, Frame frameFour, double oneOne,
			double oneTwo, double oneThree, double oneFour) {
		Frame frameToBeReturn = null;

		double preValue = oneOne * frameOne.getProbabilityValue();
		double targetValue = oneTwo * frameTwo.getProbabilityValue();
		double postValue = oneThree * frameThree.getProbabilityValue();
		double otherValue = oneFour * frameFour.getProbabilityValue();

		if (preValue == Math.max(Math.max(preValue, targetValue), Math.max(postValue, otherValue)))
			frameToBeReturn = frameOne;

		if (targetValue == Math.max(Math.max(preValue, targetValue), Math.max(postValue, otherValue)))
			frameToBeReturn = frameTwo;

		if (postValue == Math.max(Math.max(preValue, targetValue), Math.max(postValue, otherValue)))
			frameToBeReturn = frameThree;

		if (otherValue == Math.max(Math.max(preValue, targetValue), Math.max(postValue, otherValue)))
			frameToBeReturn = frameFour;

		return frameToBeReturn;
	}

	/**
	 * This method return all the labels of a state
	 * 
	 * @param state
	 * @return
	 */
	public static List<String> getObservedLabels(State state) {
		List<String> labels = new ArrayList<>();
		for (Observation observations : state.getObservations()) {
			labels.add(observations.getLabel());
		}
		return labels;
	}

	/**
	 * Get the probability value of the element observed When the element belongs to
	 * the set PRE, get the probability value observed
	 * 
	 * @param chaine
	 * @param state
	 * @return
	 */
	public static double preEmittedValue(String tmp[], int i, HMM hmm) {
		double emitted = 0.0;
		String chaine = tmp[i];
		if (hmm.getPre().getLabel().equals("PRE")) {
			for (Observation observation : hmm.getPre().getObservations()) {
				if (observation.getLabel().equals(chaine))
					emitted = observation.getEmissionValue();
			}

		}
		return emitted;
	}

	/**
	 * Get the probability value of the element observed When the element belongs to
	 * the set POST, get the probability value of this element
	 * 
	 * @param tmp
	 * @param i
	 * @param hmm
	 * @return
	 */
	public static double postEmittedValue(String tmp[], int i, HMM hmm) {
		double emitted = 0.0;
		String chaine = tmp[i];

		if (hmm.getPost().getLabel().equals("POST")) {
			for (Observation observation : hmm.getPost().getObservations()) {
				if (observation.getLabel().equals(chaine))
					emitted = observation.getEmissionValue();
			}
		}

		return emitted;
	}

	/**
	 * Get the probability value of the element observed In general, a TARGET
	 * element is always follows by a POST. Then, loop over the array until I see a
	 * POST or a PRE. Is the element I see is a PRE, the element at the i position
	 * in the string is not a TARGET element. If I see an element belonging to the
	 * set POST, the element at position i is the TARGET.
	 * 
	 * @param tmp
	 * @param i
	 * @param hmm
	 * @return
	 */

	public static double targetEmittedValue(String[] tmp, int i, HMM hmm) {
		double emitted = 0.0;
		String chaine = tmp[i];
		// A TARGET element is always follows by another TARGET or a POST element
		if (!Helper.belongs2List(tmp[i], getObservedLabels(hmm.getPre()))&&
				!Helper.belongs2List(tmp[i], getObservedLabels(hmm.getPost()))) {
//				System.out.println("The value at position i: " + tmp[i]);
			//Testing if in the seven next word we have a POST element. If not, the current
			//word is a TARGET
				for (int j = 1; i + j < tmp.length; j++) {
					if (belongs2List(tmp[i + j], getObservedLabels(hmm.getPost())))
						emitted = 1.0;
					else {
						if (belongs2List(tmp[i + j], getObservedLabels(hmm.getPre())))
							break;
					}
				}
			
		}
		return emitted;
	}

	/**
	 * Get the probability value of the element observed In general, a TARGET
	 * element is always follows by a POST. Then, loop over the array until I see a
	 * POST or a PRE. Is the element I see is a PRE, the element at the i position
	 * in the string is not a TARGET element. If I see an element belonging to the
	 * set POST, the element at position i is the TARGET.
	 * 
	 * @param tmp
	 * @param i
	 * @param hmm
	 * @return
	 */
	public static double otherEmittedValue(String[] tmp, int i, HMM hmm) {
		double emitted = 0;
		String chaine = tmp[i];

		// An OTHER element is always follows by another OTHER or a PRE element
		if(!Helper.belongs2List(tmp[i], getObservedLabels(hmm.getPre()))&&
				!Helper.belongs2List(tmp[i], getObservedLabels(hmm.getPost()))){
		for (int j = 1; i+j < tmp.length; j++) {
//			System.out.println("************The caracter**************");
//			System.out.println(tmp[i]);
			if (belongs2List(tmp[i+j], getObservedLabels(hmm.getPre()))||
					belongs2List(tmp[i+j], getObservedLabels(hmm.getTarget()))||
					i+j==tmp.length-1)
				emitted = 1.0;
			else {
				if (belongs2List(tmp[i+j], getObservedLabels(hmm.getPost()))) {
					break;
				}else emitted=1.0; //if it is not a POST, it is a last case of OTHER
			}
		}
		}
		return emitted;
	}

	/**
	 * To avoid zero probability, this method test the values of the probabilities.
	 * If these values are too close to zero, it multiply by a big number
	 * 
	 * @param columnTMP
	 * @param alphaTable
	 */
	public static Column addColumnToAlphaTable(Column columnTMP) {
		double preProba = columnTMP.getPreFrame().getProbabilityValue();
		double targetProba = columnTMP.getTargetFrame().getProbabilityValue();
		double postProba = columnTMP.getPostFrame().getProbabilityValue();
		double otherProba = columnTMP.getOtherFrame().getProbabilityValue();

		if (Math.max(Math.max(preProba, targetProba), 
				Math.max(postProba, otherProba)) < 10E-200) {

			preProba = preProba * 10E200;
			targetProba = targetProba * 10E200;
			postProba = otherProba * 10E200;
			otherProba = otherProba * 10E200;

			columnTMP.getPreFrame().setProbabilityValue(preProba);
			columnTMP.getTargetFrame().setProbabilityValue(targetProba);
			columnTMP.getPostFrame().setProbabilityValue(postProba);
			columnTMP.getOtherFrame().setProbabilityValue(otherProba);
		}
		return columnTMP;
	}

	/***
	 * This method is used to get the Frame which has the greatest probability
	 * amongst others
	 * 
	 * @param colum
	 * @return
	 */

	public static Frame mostGreatestFrameProba(Column colum) {

		Frame frame = new Frame();

		double preProba = colum.getPreFrame().getProbabilityValue();
		double targetProba = colum.getTargetFrame().getProbabilityValue();
		double postProba = colum.getPostFrame().getProbabilityValue();
		double otherProba = colum.getOtherFrame().getProbabilityValue();

		if(Math.max(Math.max(preProba, targetProba), Math.max(postProba, otherProba))==preProba)
			frame = colum.getPreFrame();

		if(Math.max(Math.max(preProba, targetProba), Math.max(postProba, otherProba))==targetProba)
			frame = colum.getTargetFrame();

		if(Math.max(Math.max(preProba, targetProba), Math.max(postProba, otherProba))==postProba)
			frame = colum.getPostFrame();

		if(Math.max(Math.max(preProba, targetProba), Math.max(postProba, otherProba))==otherProba)
			frame = colum.getOtherFrame();

		return frame;
	}
	public static String formatHMMParameters(HMM hmm) {
		String hmmData = "";

		//Format the HMM parameters
		hmmData+="Init\n";
		for (Transition transition : hmm.getInitialTransition()) {
			hmmData+=transition.getTransitionValue()+"\n";
		}
		
		hmmData+="Transitions\n";

		hmmData+="PRE\n";
		for (Transition transition : hmm.getPre().getTransitions()) {
			//PRE -> PRE
			//PRE -> TARGET
			//PRE -> POST
			//PRE -> OTHER
			hmmData+=transition.getTransitionValue()+"\n";
		}
		
		hmmData+="TARGET\n";
		for (Transition transition : hmm.getTarget().getTransitions()) {
			hmmData+=transition.getTransitionValue()+"\n";
		}
		
		hmmData+="POST\n";
		for (Transition transition : hmm.getPost().getTransitions()) {
			hmmData+=transition.getTransitionValue()+"\n";
		}

		hmmData+="OTHER\n";
		for (Transition transition : hmm.getOther().getTransitions()) {
			hmmData+=transition.getTransitionValue()+"\n";
		}
		
		hmmData+="Observations\n";

		hmmData+="PRE\n";
		for (Observation observation : hmm.getPre().getObservations()) {
			hmmData+=observation.getLabel()+" "+observation.getEmissionValue()+"\n";
		}

		hmmData+="TARGET\n";
		for (Observation observation : hmm.getTarget().getObservations()) {
			hmmData+=observation.getLabel()+" "+observation.getEmissionValue()+"\n";
		}

		hmmData+="POST\n";
		for (Observation observation : hmm.getPost().getObservations()) {
			hmmData+=observation.getLabel()+" "+observation.getEmissionValue()+"\n";
		}

		hmmData+="OTHER\n";
		for (Observation observation : hmm.getOther().getObservations()) {
			hmmData+=observation.getLabel()+" "+observation.getEmissionValue()+"\n";
		}
		
		return hmmData;
	}
	/**
	 * This method save the HMM parameter in a file
	 * @param hmm
	 */
	//Saving the HMM parameters
	public static void saveHMM(HMM hmm, String parameterPath) {
//		FileOutputStream out;
		File file = new File(parameterPath);
		FileWriter fw = null;
		PrintWriter pw = null;
		String hmmData = "";
		
		//Creating the file if it does not exist
		try {
			file.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//		or
//		try {
//			out = new FileOutputStream(parameterPath);
//		} catch (FileNotFoundException e1) {
//			e1.printStackTrace();
//		}
		//Creating the output 
		try {
			fw = new FileWriter(parameterPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Get the HMM parameters
		hmmData=formatHMMParameters(hmm);
		pw = new PrintWriter(fw);
		pw.write(hmmData);
		pw.close();
		
	}
	
	public static HMM readHMMInitParam(String hmmParam, HMM hmm) {
		String tmp[] = hmmParam.split(" ");
		List<Transition> initTransition = new ArrayList<>();
		for (int i = 0; i < tmp.length; i++) {
			if(tmp[i].equals("Init")) {
				//The next four values are transitions values
				initTransition.add(new Transition(new State(), 
						new State("PRE"), Double.parseDouble(tmp[i+1])));
				
				initTransition.add(new Transition(new State(), 
						new State("TARGET"), Double.parseDouble(tmp[i+2])));

				initTransition.add(new Transition(new State(), 
						new State("POST"), Double.parseDouble(tmp[i+3])));

				initTransition.add(new Transition(new State(), 
						new State("OTHER"), Double.parseDouble(tmp[i+4])));
			}
		}
		hmm.setInitialTransition(initTransition);	
		
		return hmm;
	}
	
	public static HMM readHMMTransitionParam(String hmmParams, HMM hmm) {
		List<Transition> listTransitions = new ArrayList<>();
		String tmp[] = hmmParams.split(" ");
		
		//Creating and initializing the states
		State pre = new State("PRE");
		State target = new State("TARGET");
		State post = new State("POST");
		State other = new State("OTHER");

		List<Transition> preTransitions = new ArrayList<>();
		List<Transition> targetTransitions = new ArrayList<>();
		List<Transition> postTransitions = new ArrayList<>();
		List<Transition> otherTransitions = new ArrayList<>();
		
		hmm.setPre(pre);
		hmm.setTarget(target);
		hmm.setPost(post);
		hmm.setOther(other);
		
		for (int i = 0; i < tmp.length; i++) {
			if(tmp[i].equals("Transitions")) {
				//tmp[i+1] is the PRE
				preTransitions.add(new Transition(new State("PRE"), 
						new State("PRE"), Double.parseDouble(tmp[i+2])));
				
				preTransitions.add(new Transition(new State("PRE"), 
						new State("TARGET"), Double.parseDouble(tmp[i+3])));
				
				preTransitions.add(new Transition(new State("PRE"), 
						new State("POST"), Double.parseDouble(tmp[i+4])));

				preTransitions.add(new Transition(new State("PRE"), 
						new State("OTHER"), Double.parseDouble(tmp[i+5])));
				hmm.getPre().setTransitions(preTransitions);
				
				//Now, the TARGET
				targetTransitions.add(new Transition(new State("TARGET"), 
						new State("PRE"), Double.parseDouble(tmp[i+7])));
				
				targetTransitions.add(new Transition(new State("TARGET"), 
						new State("TARGET"), Double.parseDouble(tmp[i+8])));
				
				targetTransitions.add(new Transition(new State("TARGET"), 
						new State("POST"), Double.parseDouble(tmp[i+9])));

				targetTransitions.add(new Transition(new State("TARGET"), 
						new State("OTHER"), Double.parseDouble(tmp[i+10])));
				hmm.getTarget().setTransitions(targetTransitions);
				
				//Now, the POST
				postTransitions.add(new Transition(new State("POST"), 
						new State("PRE"), Double.parseDouble(tmp[i+12])));
				
				postTransitions.add(new Transition(new State("POST"), 
						new State("TARGET"), Double.parseDouble(tmp[i+13])));
				
				postTransitions.add(new Transition(new State("POST"), 
						new State("POST"), Double.parseDouble(tmp[i+14])));

				postTransitions.add(new Transition(new State("POST"), 
						new State("OTHER"), Double.parseDouble(tmp[i+15])));
				hmm.getPost().setTransitions(postTransitions);

				//Now, the OTHER
				otherTransitions.add(new Transition(new State("OTHER"), 
						new State("PRE"), Double.parseDouble(tmp[i+17])));
				
				otherTransitions.add(new Transition(new State("OTHER"), 
						new State("TARGET"), Double.parseDouble(tmp[i+18])));
				
				otherTransitions.add(new Transition(new State("OTHER"), 
						new State("POST"), Double.parseDouble(tmp[i+19])));

				otherTransitions.add(new Transition(new State("OTHER"), 
						new State("OTHER"), Double.parseDouble(tmp[i+20])));
				hmm.getOther().setTransitions(otherTransitions);
				
			}
		}
		
		
		return hmm;
	}

	public static HMM readHMMObservationParam(String hmmParams, HMM hmm) {
		String tmp[] = hmmParams.split(" ");

		List<Observation> preObservations = new ArrayList<>();
		List<Observation> targetObservations = new ArrayList<>();
		List<Observation> postObservations = new ArrayList<>();
		List<Observation> otherObservations = new ArrayList<>();

		for (int i = 0; i < tmp.length; i++) {
			System.out.println("-----------------------");
			if(tmp[i].equals("Observations")) {
				//getting the PRE observations
				if(tmp[i+1].equals("PRE")) {
					i+=2;
					while (!tmp[i].equals("TARGET")) {
						System.out.println("the value at position i: "+tmp[i]);
						System.out.println("the value at position i+1: "+tmp[i+1]);
						preObservations.add(new Observation(tmp[i], 
								Double.parseDouble(tmp[i+1]), hmm.getPre()));
						i+=2;
					}
				}
				//Getting the TARGET observations
				if(tmp[i].equals("TARGET")) {
					i+=1;
					while (!tmp[i].equals("POST")) {
						targetObservations.add(new Observation(tmp[i], 
								Double.parseDouble(tmp[i+1]), hmm.getPre()));
						i+=2;
					}
				}
				//Getting the POST observations
				if(tmp[i].equals("POST")) {
					i+=1;
					while (!tmp[i].equals("OTHER")) {
						postObservations.add(new Observation(tmp[i], 
								Double.parseDouble(tmp[i+1]), hmm.getPre()));
						i+=2;
					}
				}
				//Getting the OTHER observations
				if(tmp[i].equals("OTHER")) {
					i+=1;
					while (i<tmp.length) {
						otherObservations.add(new Observation(tmp[i], 
								Double.parseDouble(tmp[i+1]), hmm.getPre()));
						i+=2;
					}
				}
				
			}
		}
		hmm.getPre().setObservations(preObservations);
		hmm.getTarget().setObservations(targetObservations);
		hmm.getPost().setObservations(postObservations);
		hmm.getOther().setObservations(otherObservations);
		return hmm;
	}
	
	/**
	 * This method is used to get the parameters of the HMM from the file
	 * @param filePath
	 * @return
	 */
	//Getting the HMM parameters from file
	public static HMM readHMMFromFile(String filePath) {
		HMM hmm = new HMM();
		String hmmParam = "";
		FileReader fr;
		BufferedReader br;
		//Opening the file and reading the HMM parameters
		try {
			fr = new FileReader(filePath);
			br = new BufferedReader(fr);
			
			for (String line; (line = br.readLine()) != null;) {
				hmmParam+=line+" ";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Get the Initial vector
		readHMMInitParam(hmmParam, hmm);
		//Get the transition vectors
		readHMMTransitionParam(hmmParam, hmm);
		//Get the observation vectors
		readHMMObservationParam(hmmParam, hmm);
		return hmm;
	}
	/**
	 * This method takes has input a knowledge and a metadata and return all the knowledge 
	 * that may be identified by the metaData
	 * @param knowledgeExtracted
	 * @param metaData
	 * @return
	 */
	public static String getClass
	(String knowledgeExtractedFilePath, String metaData) {
		String classesExtracted="";
		String data [] = 
				Helper.getKnowledgeFromFiles(knowledgeExtractedFilePath).split(" ");
		for (int i = 0; i < data.length; i++) {
			if(data[i].equals(metaData))
				classesExtracted+=data[i+1]+" ";
		} 
		
		return classesExtracted;
	}
	
	public static String getAllTerms (String knowledgeExtractedFilePath) {
		
		String knowledgeExtracted="";
		
		String data [] = 
				Helper.getKnowledgeFromFiles(knowledgeExtractedFilePath).split(" ");
		for (int i = 0; i < data.length; i++) {
			knowledgeExtracted+=data[i]+" ";
		}
		return knowledgeExtracted;
	}
	
	
	
}
