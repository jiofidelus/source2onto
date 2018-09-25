package cm.uy1.source2onto.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.primefaces.component.fileupload.FileUpload;
import org.primefaces.model.UploadedFile;

import cm.uy1.helper.Helper;
import cm.uy1.modelDefinition.HMM;
import cm.uy1.modelDefinition.Transition;
import cm.uy1.training.HMMTrainingData;

import javax.annotation.PostConstruct;

@ManagedBean(name="hMMDefinitionController")
public class HMMDefinitionController {
	
	private static String programmingLang;
	private static String pre, target, post, other;
	private static String modelDescription;
	private static String modelSelected;
	private static List<String> trainingMethods;
	private static String methodSelected;

	private static String trainingData;
	//The results of the model training
	private static  String initialVector;
	private static  String transitionVector;
	private static  String observationVector;
	
	//The parameters to be show on UI
	private static String hmmParams;
	
	private String parameterPath;
	
	private static HMM hmm;
	
	//Training path
//    private UploadedFile file;
	
	public String getMethodSelected() {
		return methodSelected;
	}


	public HMMDefinitionController() {
		super();
	}

	public void setMethodSelected(String methodSelected) {
		this.methodSelected = methodSelected;
	}
	
	@PostConstruct
	public void init() {
		trainingMethods = new ArrayList<>();
		trainingMethods.add("Training on data");
		trainingMethods.add("Baum Welch algorithm");
		trainingMethods.add("Viterbi algorithm");
		trainingMethods.add("K-mean algorithm");
	}
	
	public HMMDefinitionController(String programmingLang, String pre, 
			String target, String post, String other,
			String modelDescription) {
		super();
		this.programmingLang = programmingLang;
		this.pre = pre;
		this.target = target;
		this.post = post;
		this.other = other;
		this.modelDescription = modelDescription;
	}


	public String getProgrammingLang() {
		return programmingLang;
	}


	public void setProgrammingLang(String programmingLang) {
		this.programmingLang = programmingLang;
	}


	public String getPre() {
		return pre;
	}


	public void setPre(String pre) {
		this.pre = pre;
	}


	public String getTarget() {
		return target;
	}


	public void setTarget(String target) {
		this.target = target;
	}


	public String getPost() {
		return post;
	}


	public void setPost(String post) {
		this.post = post;
	}


	public String getOther() {
		return other;
	}


	public void setOther(String other) {
		this.other = other;
	}


	public String getModelDescription() {
		return modelDescription;
	}


	public void setModelDescription(String modelDescription) {
		this.modelDescription = modelDescription;
	}


	public String getModelSelected() {
		return modelSelected;
	}


	public void setModelSelected(String modelSelected) {
		this.modelSelected = modelSelected;
	}


	public List<String> getTrainingMethods() {
		return trainingMethods;
	}


	public void setTrainingMethods(List<String> trainingMethods) {
		this.trainingMethods = trainingMethods;
	}


	public String getTrainingData() {
		return trainingData;
	}


	public void setTrainingData(String trainingData) {
		this.trainingData = trainingData;
	}


	public String getInitialVector() {
		return initialVector;
	}


	public void setInitialVector(String initialVector) {
		this.initialVector = initialVector;
	}


	public String getTransitionVector() {
		return transitionVector;
	}


	public void setTransitionVector(String transitionVector) {
		this.transitionVector = transitionVector;
	}


	public String getObservationVector() {
		return observationVector;
	}


	public void setObservationVector(String observationVector) {
		this.observationVector = observationVector;
	}
	
//	public UploadedFile getFile() {
//		return file;
//	}
//
//
//	public void setFile(UploadedFile file) {
//		this.file = file;
//	}


//	public void uploadTrainingData() {
//		System.out.println("*********************Uploading the file*************************");
//	}

//		@ManagedBean
//		public class FileUploadView {
//		     
//		    private UploadedFile file;
//		 
//		    public UploadedFile getFile() {
//		        return file;
//		    }
//		 
//		    public void setFile(UploadedFile file) {
//		        this.file = file;
//		    }
//		     
//		    public void upload() {
//		        if(file != null) {
//		            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
//		            FacesContext.getCurrentInstance().addMessage(null, message);
//		        }
//		    }
//		}
//	}
	
	public String getHmmParams() {
		return hmmParams;
	}


	public void setHmmParams(String hmmParams) {
		this.hmmParams = hmmParams;
	}


	public HMM getHmm() {
		return hmm;
	}


	public void setHmm(HMM hmm) {
		this.hmm = hmm;
	}


	public String getParameterPath() {
		return parameterPath;
	}


	public void setParameterPath(String parameterPath) {
		this.parameterPath = parameterPath;
	}


	////
	//The method which train the model
	public static void trainedModel() {
		
		//Get the data that will be used for the training step
		List<String> sourceCodes = Helper.getAllDataFromFolder(trainingData);
		System.out.println("********Geting the training data:");
		System.out.println(Helper.getAllDataFromFolder(trainingData));
		String[] tmp;
		String chaineShow="";
		
		hmm = new HMM();
		hmm.init();
		//The PRE elements enter
		List<String> preObservation=new ArrayList<String>();
		tmp=pre.split(" ");
		for (int i = 0; i < tmp.length; i++) {
			preObservation.add(new String(tmp[i]));
		}
		//The TARGET set
		tmp=target.split(" ");
		List<String> targetObservation=new ArrayList<String>();
		for (int i = 0; i < tmp.length; i++) {
			targetObservation.add(new String(tmp[i]));
		}
		//The POST set
		tmp=post.split(" ");
		List<String> postObservation=new ArrayList<String>();
		for (int i = 0; i < tmp.length; i++) {
			postObservation.add(new String(tmp[i]));
		}
		//The OTHER set
		tmp=other.split(" ");
		List<String> otherObservation=new ArrayList<String>();
		for (int i = 0; i < tmp.length; i++) {
			otherObservation.add(new String(tmp[i]));
		}
		
		System.out.println("**************************"
				+ "Initilizing the HMM*********************************");
		
		//HMM initialization
		HMMTrainingData.hmmInit(hmm, sourceCodes, preObservation, 
				targetObservation, postObservation, otherObservation);
		System.out.println(hmm);
		
		for (Transition transition : hmm.getInitialTransition()) {
			chaineShow = chaineShow + transition.getEndState().getLabel()+":\t";
			chaineShow = chaineShow + transition.getTransitionValue()+"\n";
		}
		
		initialVector = chaineShow;
		
		System.out.println("**************************"
				+ "Calculating the HMM transitions*********************************");
		chaineShow="";
		//HMM transition calculation
		HMMTrainingData.preTransition(hmm, sourceCodes, preObservation);
		HMMTrainingData.targetTransition(hmm, sourceCodes, preObservation, 
				postObservation);
		HMMTrainingData.postTransition(hmm, sourceCodes, postObservation, 
				preObservation);
		HMMTrainingData.otherTransition(hmm, sourceCodes, preObservation, 
				targetObservation, postObservation);
		System.out.println(hmm);
		
		System.out.println("**************************"
				+ "Calculating the HMM observations*********************************");
		chaineShow="";
		//HMM observation calculation
		HMMTrainingData.preObservation(hmm, preObservation, sourceCodes);
		HMMTrainingData.targetObservation(hmm, targetObservation, 
				preObservation, postObservation, sourceCodes);
		HMMTrainingData.postObservation(hmm, postObservation, 
				preObservation, sourceCodes);
		HMMTrainingData.otherObservation(hmm, otherObservation);
		
		//Show data on the screen
		hmmParams=hmm.toString();
		
//		chaineShow=chaineShow+"PRE\n";
//		for (Transition transition : hmm.getPre().getTransitions()) {
//			chaineShow=chaineShow+transition.getStartState().getLabel()+
//					"-->"+transition.getEndState().getLabel()+": "
//					+transition.getTransitionValue()+"\n";
//		}
//		chaineShow=chaineShow+"TARGET\n";
//
//		for (Transition transition : hmm.getTarget().getTransitions()) {
//			chaineShow=chaineShow+transition.getStartState().getLabel()+
//					"-->"+transition.getEndState().getLabel()+": "+transition.getTransitionValue()+"\n";			
//		}
//		chaineShow=chaineShow+"POST\n";
//
//		for (Transition transition : hmm.getPost().getTransitions()) {
//			chaineShow=chaineShow+transition.getStartState().getLabel()+
//					"-->"+transition.getEndState().getLabel()+": "+transition.getTransitionValue()+"\n";			
//		}
//		chaineShow=chaineShow+"OTHER\n";
//
//		for (Transition transition : hmm.getOther().getTransitions()) {
//			chaineShow=chaineShow+transition.getStartState().getLabel()+
//					"-->"+transition.getEndState().getLabel()+": "+transition.getTransitionValue()+"\n";			
//		}
//		transitionVector = chaineShow;
		
//		System.out.println("**************************"
//				+ "Calculating the HMM observations*********************************");
//		chaineShow="";
//		
//		chaineShow=chaineShow+"PRE\n";
//		for (Observation observation : hmm.getPre().getObservations()) {
//			chaineShow=chaineShow+observation.getLabel()+
//					": "+observation.getEmissionValue();
//		}
//		chaineShow=chaineShow+"TARGET\n";
//		for (Observation observation : hmm.getTarget().getObservations()) {
//			chaineShow=chaineShow+observation.getLabel()+
//					": "+observation.getEmissionValue();			
//		}
//		chaineShow=chaineShow+"POST\n";
//		for (Observation observation : hmm.getPost().getObservations()) {
//			chaineShow=chaineShow+observation.getLabel()+
//					": "+observation.getEmissionValue();			
//		}
//		chaineShow=chaineShow+"OTHER\n";
//		for (Observation observation : hmm.getOther().getObservations()) {
//			chaineShow=chaineShow+observation.getLabel()+
//					": "+observation.getEmissionValue();			
//		}
//		
//		observationVector = chaineShow;
		System.out.println("**************************"
				+ "The HMM trained*********************************");
		
		System.out.println(hmm);

	}
	
	public void saveModel() {

		System.out.println("**************************"
				+ "Saving the HMM*********************************");
		System.out.println("************The path to the parameter: "+parameterPath);
		System.out.println("************************The HMM to be save***********************");
		System.out.println(hmm);
		Helper.saveHMM(hmm, parameterPath);
		
		
		
	}
}
