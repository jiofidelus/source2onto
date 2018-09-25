package cm.uy1.modelUse;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import cm.uy1.helper.Helper;
import cm.uy1.modelDefinition.HMM;
import cm.uy1.modelDefinition.Transition;

/**
 * This class use the Viterbi algorithm to calculate
 * the most likely explanation in the HMM.
 * 
 * Then, the Viterbi PATH is identified and the 
 * elements lablled TARGET are extracted
 * 
 * @author azanzi
 *
 */
public class KnowledgeExtractionHMM {
	
	/**
	 * The knowledge exctraction method takes as input the viterbiTable and calculte the Viterbi PATH
	 * To do this, it browse the source code (frames) from the end to the begining and get the frames 
	 * that contains the most likely explanation. This this source code, the elements labelled TARGET 
	 * are retreive
	 */
	
	public static String knowledgeExtraction (List<Column> viterbiTable) {
		String knowledgeExtracted="";
		
		int tableLastIndex = viterbiTable.size()-1;
		Frame frame = new Frame();
		String label="";
		
		//Get the last column of the table
		Column lastColumn = viterbiTable.get(tableLastIndex);
		//Get the last frame which has the greatest probability
		frame = Helper.mostGreatestFrameProba(lastColumn);
//		System.out.println("0000000000000000000"+lastColumn);
//		System.out.println("0000000000000000000"+frame);
		
		//Get all the frames which permit to build the above frame
		List<Frame> mostLikelyFrames = new ArrayList<>();
		mostLikelyFrames.add(frame);
		//Browse the frame list to get the most likely frame
		for (int i = tableLastIndex; i >0; i--) {
			frame = frame.getFrameBuilder();
//			System.out.println("0000000000000000000 column"+viterbiTable.get(i));
//			System.out.println("0000000000000000000 Frame builder: "+frame);
			mostLikelyFrames.add(frame);
		}
		//Extract the words (our candidates terms to be knowledge) from the above frames which hidden 
		//state is TARGET
		for (int i = mostLikelyFrames.size()-1; i >0; i--) {
			if(mostLikelyFrames.get(i).getStateLabel().equals("TARGET")) {
				label = mostLikelyFrames.get(i).getObservedLabel();
				knowledgeExtracted=knowledgeExtracted + label + "\n";
			}
		}
		return knowledgeExtracted;
	}
	
	
	/**
	 * Build a viterbi table for a source file
	 * This method will be very helpful when building for all the source code
	 * Then, for any file, the knowledge sought will be extracted
	 * @param sourceFile
	 * @param hmm
	 * @return
	 */
	
	public static List<Column> viterbiTable(String sourceFile, HMM hmm){
		
		List<Column> viterbiTable = new ArrayList<>();
		//Get the initial transition vector
		List<Transition> initialTransition = hmm.getInitialTransition();
		//Get the transition vectors for states
		List<Transition> preTransition = hmm.getPre().getTransitions();
		List<Transition> targetTransition = hmm.getTarget().getTransitions();
		List<Transition> postTransition = hmm.getPost().getTransitions();
		List<Transition> otherTransition = hmm.getOther().getTransitions();
		
		sourceFile = sourceFile.replace("/", "/ ");
		
		String[] tmp = sourceFile.split(" ");
		
		//Table initialization
		//1- Creation of the first column
		Column column = new Column();
		//11-Create the frames of the first column
//		Frame framePRE0 = new Frame(tmp[0], "PRE", 
//				initialTransition.get(0).getTransitionValue()*1.0E100);
//		Frame frameTARGET0 = new Frame(tmp[0], "TARGET", 
//				initialTransition.get(1).getTransitionValue()*1.0E100);
//		Frame framePOST0 = new Frame(tmp[0], "POST", 
//				initialTransition.get(2).getTransitionValue()*1.0E100);
//		Frame frameOTHER0 = new Frame(tmp[0], "OTHER", 
//				initialTransition.get(3).getTransitionValue()*1.0E100);

		//11-Create the frames of the first column
		Frame framePRE0 = new Frame(tmp[0], "PRE", 
				initialTransition.get(0).getTransitionValue());
		Frame frameTARGET0 = new Frame(tmp[0], "TARGET", 
				initialTransition.get(1).getTransitionValue());
		Frame framePOST0 = new Frame(tmp[0], "POST", 
				initialTransition.get(2).getTransitionValue());
		Frame frameOTHER0 = new Frame(tmp[0], "OTHER", 
				initialTransition.get(3).getTransitionValue());
		//12-Add the first frames to the colums
		column.setPreFrame(framePRE0);
		column.setTargetFrame(frameTARGET0);
		column.setPostFrame(framePOST0);
		column.setOtherFrame(frameOTHER0);
		//2-Add the first column to the table
		viterbiTable.add(column);

		//3- Create the frames for the other states
		Frame framePRE;
		Frame frameTARGET;
		Frame framePOST;
		Frame frameOTHER;
		
		//31- Temporary column and frame
		Column columnTMP;
		Frame frameTMP;
		
		//4- Frame used to calculte the values of another frames
		Frame frameUsed;
		
		//5- The probability for any state to emit an element of the source file
		double preEmission=0, targetEmission=0, postEmission=0, otherEmission=0;
		
		//6- Value of probability of each column in the table
		double calculPre=0, calculTarget=0, calculPost=0, calculOTHER=0;
		
		//7- Calculate the viterbi table
		for (int i = 0; i < tmp.length; i++) {
			System.out.println("-----------------------"+tmp[i]+"-----------------------");
			preEmission=0; targetEmission=0; postEmission=0; otherEmission=0;
			calculPre=0; calculTarget=0; calculPost=0; calculOTHER=0;	
			
			tmp[i]=tmp[i].trim();
			columnTMP = new Column();
			frameTMP=null;
			
			//Get the last element of the table
			column = viterbiTable.get(viterbiTable.size()-1);
			//Get the frames of the last element 
			framePRE = column.getPreFrame();
			frameTARGET = column.getTargetFrame();
			framePOST = column.getPostFrame();
			frameOTHER = column.getOtherFrame();

			/**
			 * Now, we calculate the PRE state of the next column
			 */
			//Get the frame to be used to calculate the PRE frame
			frameUsed = Helper.getMaxFrame(framePRE, frameTARGET, framePOST, frameOTHER,
					preTransition.get(0).getTransitionValue(), preTransition.get(1).getTransitionValue(),
					preTransition.get(2).getTransitionValue(), preTransition.get(3).getTransitionValue());
			
			//Calculate the values of the probabilities
			calculPre = Math.max(Math.max(preTransition.get(0).getTransitionValue()*framePRE.getProbabilityValue(), 
					preTransition.get(1).getTransitionValue()*frameTARGET.getProbabilityValue()), 
					Math.max(preTransition.get(2).getTransitionValue()*framePOST.getProbabilityValue(),
					preTransition.get(3).getTransitionValue()*frameOTHER.getProbabilityValue()));

				//Get the emission probability
				//Calculte the probability of the observed string 
				preEmission = Helper.preEmittedValue(tmp, i, hmm);

				System.out.println("................Pre state Frame used : "+frameUsed.toString());
				System.out.println("+++++++++++++++++++++++ calcul pre :"+calculPre);
				System.out.println("+++++++++++++++++++++++ pre emission :"+preEmission);
				
				//Create the new frame for PRE state and add to the column
				frameTMP = new Frame(tmp[i], "PRE", calculPre*preEmission, frameUsed);
				columnTMP.setPreFrame(frameTMP);
			
			/**
			 * Now, we calculate the TARGET state of the next column
			 */
			frameUsed = Helper.getMaxFrame(framePRE, frameTARGET, framePOST, frameOTHER, 
					targetTransition.get(0).getTransitionValue(), 
					targetTransition.get(1).getTransitionValue(), 
					targetTransition.get(2).getTransitionValue(),
					targetTransition.get(3).getTransitionValue());
			//The value of the probability which is calculated
			calculTarget = Math.max(
					Math.max(targetTransition.get(0).getTransitionValue()*framePRE.getProbabilityValue(), 
							targetTransition.get(1).getTransitionValue()*frameTARGET.getProbabilityValue()), 
					Math.max(targetTransition.get(2).getTransitionValue()*framePOST.getProbabilityValue(),
							targetTransition.get(3).getTransitionValue())*frameOTHER.getProbabilityValue());
			//Get the emission probability
			//Get the probability of the observed string
			//In our case, it is always 1.0 because we suppose that all the words 
			//emitted by TARGET are data
			targetEmission=Helper.targetEmittedValue(tmp, i, hmm);
			//Create the new frame for TARGET state and add to the column
			//alpha(target, i)=P(s=public|h=target=.argmax(P(h2=target|h1=pre)*aplha(pre,1)
			frameTMP = new Frame(tmp[i], "TARGET", calculTarget*targetEmission, frameUsed);
			columnTMP.setTargetFrame(frameTMP);

			System.out.println("................Target state Frame used : "+frameUsed.toString());
			System.out.println("+++++++++++++++++++++++ calcul target :"+calculTarget);
			System.out.println("+++++++++++++++++++++++ target emission :"+targetEmission);
			
			/**
			 * Now, we calculate the POST state of the next column
			 */
			//Get the frame to used to calculte the POST frame
			frameUsed = Helper.getMaxFrame(framePRE, frameTARGET, framePOST, frameOTHER, 
					postTransition.get(0).getTransitionValue(),
					postTransition.get(1).getTransitionValue(),
					postTransition.get(2).getTransitionValue(),
					postTransition.get(3).getTransitionValue());
			//Calculate the values of the probabilities
			calculPost = Math.max(
					Math.max(postTransition.get(0).getTransitionValue()*framePRE.getProbabilityValue(), 
					postTransition.get(1).getTransitionValue()*frameTARGET.getProbabilityValue()), 
					Math.max(postTransition.get(2).getTransitionValue()*framePOST.getProbabilityValue(), 
					postTransition.get(3).getTransitionValue()*frameOTHER.getProbabilityValue()));
//			System.out.println("000000000000000");
//			System.out.println("POST-PRE "+ postTransition.get(0).getTransitionValue());
//			System.out.println("Frame PRE : "+framePRE.getProbabilityValue());
//
//			System.out.println("POST-TARGET "+ postTransition.get(1).getTransitionValue());
//			System.out.println("Frame TARGET : "+frameTARGET.getProbabilityValue());
//
//			System.out.println("POST-POST "+ postTransition.get(2).getTransitionValue());
//			System.out.println("Frame POST : "+framePOST.getProbabilityValue());
//
//			System.out.println("POST-OTHER "+ postTransition.get(3).getTransitionValue());
//			System.out.println("Frame OTHER : "+frameOTHER.getProbabilityValue());
			
			//Get the emission probability
			//Calculate the probability of the observed string
			postEmission = Helper.postEmittedValue(tmp, i, hmm);
			//Create the new frame for POST state and add to the column
			frameTMP = new Frame(tmp[i], "POST", calculPost*postEmission, frameUsed);
			columnTMP.setPostFrame(frameTMP);

			System.out.println("................post state Frame used : "+frameUsed.toString());
			System.out.println("+++++++++++++++++++++++ calcul post :"+calculPost);
			System.out.println("+++++++++++++++++++++++ post  emission :"+postEmission);
			/**
			 * Now, we calculate the OTHER state of the next column
			 */
			//Get the frame to used to calculate the OTHER frame
			frameUsed = Helper.getMaxFrame(framePRE, frameTARGET, framePOST, frameOTHER, 
					otherTransition.get(0).getTransitionValue(),
					otherTransition.get(1).getTransitionValue(),
					otherTransition.get(2).getTransitionValue(),
					otherTransition.get(3).getTransitionValue());
			//Calculate the value of the probability
			calculOTHER = Math.max(
					Math.max(otherTransition.get(0).getTransitionValue()*framePRE.getProbabilityValue(), 
					otherTransition.get(1).getTransitionValue()*frameTARGET.getProbabilityValue()), 
					Math.max(otherTransition.get(2).getTransitionValue()*framePOST.getProbabilityValue(), 
					otherTransition.get(3).getTransitionValue()*frameOTHER.getProbabilityValue()));
			//Get the emission probability
			//Calculte the probability of the observed string
			otherEmission = Helper.otherEmittedValue(tmp, i, hmm);
			//Create the new frame OTHER for the state OTHER and add to the column
			frameTMP = new Frame(tmp[i], "OTHER", calculOTHER*otherEmission, frameUsed);
			columnTMP.setOtherFrame(frameTMP);

			System.out.println("................OTHER state Frame used : "+frameUsed.toString());
			System.out.println("+++++++++++++++++++++++ calcul other :"+calculOTHER);
			System.out.println("+++++++++++++++++++++++ other emission :"+otherEmission);

			System.out.println("*****************************************************");
			System.out.println("******************Column inserted********************");
			System.out.println("*****************************************************");
			System.out.println(columnTMP);
			/**
			 * Now, let us add the column to the table
			 */
			viterbiTable.add(Helper.addColumnToAlphaTable(columnTMP));
		}
		
		return viterbiTable;
	}

}
