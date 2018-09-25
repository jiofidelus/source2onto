package hmm;

import java.util.ArrayList;
import java.util.List;

import cm.uy1.helper.Helper;
import cm.uy1.modelDefinition.HMM;
import cm.uy1.modelUse.Column;
import cm.uy1.modelUse.KnowledgeExtractionHMM;
import cm.uy1.training.HMMTrainingData;

public class TestHMMPerl {
	public static final String TRAININDATA = "/home/azanzi/These/"
			+ "1-Journal_Web_Semantic/dataSources/4-Perl/1-TrainDS";
			
	public static final String EXPERIMENTINGDATA = "/home/azanzi/These/"
			+ "1-Journal_Web_Semantic/dataSources/4-Perl/2-geoip-api-perl";
	
	public static final String HMMPARAMETERS = "/home/azanzi/These/"
			+ "1-Journal_Web_Semantic/dataSources/4-Perl/parameters";
	
	public static final String KNOWLEDGEEXTRACTED = "/home/azanzi/These/"
			+ "1-Journal_Web_Semantic/dataSources/4-Perl/geoip-api-perl";
			
//			"/home/azanzi/Documents/workspace/"
//			+ "knowExtracFromSC/experiments/DataSources/3-Geoserver/1-raw_data";

//			"/home/azanzi/Documents/workspace"
//			+ "/knowExtracFromSC/experiments/DataSources/2-TestDS";

	//The string observed
		private static List<String> preObservation=new ArrayList<String>() {{
			add("use");
			add("sub");
			add("my");
		}};
		
		private static List<String> targetObservation=new ArrayList<String>() {{
		}};
		
		private static List<String> postObservation=new ArrayList<String>() {{
			add(";");
			add(":");
			add("{");
		}};
		private static List<String> otherObservation=new ArrayList<String>() {{
//			add("if");
//			add("else");
		}};
	
	public static void main(String[] args) {
		
		//Get the data that will be used for the training step
		List<String> sourceCodes = Helper.getAllDataFromFolder(TRAININDATA);
		
		//Get the experimental data
		List<String> dataSource = Helper.getAllDataFromFolder(EXPERIMENTINGDATA);
		
//		//Display the first element of the list
//		System.out.println("Training data");
//		System.out.println(sourceCodes);

		//Creating and initializing the HMM
//		System.out.println("Before the creation of the HMM");
		HMM hmm = new HMM();
		hmm.init();
		/**
		 * Empty HMM
		 */
//		System.out.println("***********************The empty HMM***********************");
//		System.out.println(hmm);
		/**
		 * Testing the initialization of the HMM
		 */
		//Initializing the HMM
		HMMTrainingData.hmmInit(hmm, sourceCodes, preObservation, 
				targetObservation, postObservation, otherObservation);
//		Displaying the HMM initialized
		System.out.println("********************The HMM initialized********************");
		System.out.println(hmm);
		
		/**
		 * Testing the observation model
		 */
//		//Running the methods that calculate the observation model
		HMMTrainingData.preObservation(hmm, preObservation, sourceCodes);
		HMMTrainingData.targetObservation(hmm, targetObservation, 
				preObservation, postObservation, sourceCodes);
		HMMTrainingData.postObservation(hmm, postObservation, preObservation, sourceCodes);
		HMMTrainingData.otherObservation(hmm, sourceCodes);
		
		//Display the new HMM initialized and the PRE, TARGET, POST and OTHER observations
		//calculated
		System.out.println("*************HMM observations calculated********************");
		System.out.println(hmm);
		
		/**
		 * Testing the transition model
		 */
//		//Running the methods that calculate the transitions model
		HMMTrainingData.preTransition(hmm, sourceCodes, preObservation);
		HMMTrainingData.targetTransition(hmm, sourceCodes, preObservation, 
				postObservation);
		HMMTrainingData.postTransition(hmm, sourceCodes, postObservation, 
				preObservation);
		HMMTrainingData.otherTransition(hmm, sourceCodes, preObservation, 
				targetObservation, postObservation);
		
		//Display the HMM trained
		System.out.println("**************The trained HMM*****************");
		System.out.println(hmm);
		
		/**
		 * Testing the saving and retreiving of HMM data from file
		 */
//		//Saving the HMM data in a file
//		System.out.println("***********Print HMM data*********************");
//		System.out.println(Helper.formatHMMParameters(hmm));
//		System.out.println("\n\n*************Saving HMM parameters*************");
//		System.out.println("                 ...                    ");
		Helper.saveHMM(hmm, HMMPARAMETERS);
//		Retreiving HMM data from a file
//		System.out.println("The HMM already trained:");
//		hmm = Helper.readHMMFromFile
//				("/home/azanzi/Documents/workspace/"
//				+ "knowExtracFromSC/experiments/hmmParams");
//		System.out.println(Helper.readHMMFromFile
//				("/home/azanzi/Documents/workspace/"
//				+ "knowExtracFromSC/experiments/hmmParams"));
		
		
		/**
		 * Testing the knowledge extraction module
		 */
		//the data to be used for the tests
//		System.out.println(dataSource.get(0));
//		System.out.println("***********The Viterbi table from the first file**********************");
		String knowledgeExtracted=""; 
		List<String> sourceCodeFiles = Helper.getFiles(EXPERIMENTINGDATA);
		int i=0;
		for (String file : dataSource) {
			List<Column> viterbiTable = KnowledgeExtractionHMM.viterbiTable(file, hmm);
			knowledgeExtracted = sourceCodeFiles.get(i).substring(78);
			knowledgeExtracted = knowledgeExtracted + 
					KnowledgeExtractionHMM.knowledgeExtraction(viterbiTable);
			Helper.writeOntoToFile(KNOWLEDGEEXTRACTED, knowledgeExtracted);
//			System.out.println("\n\n*********************************");
//			System.out.println("Knowledge extracted");
//			System.out.println(knowledgeExtracted);
		}
//		List<Column> viterbiTable = 
//				KnowledgeExtractionHMM.viterbiTable
//				(dataSource.get(1), hmm);
////		KnowledgeExtractionHMM.knowledgeExtraction(viterbiTable);
//////		System.out.println("\n\n*********************************");
//		System.out.println("Knowledge extracted");
//		System.out.println(KnowledgeExtractionHMM.knowledgeExtraction(viterbiTable));

	}

}
