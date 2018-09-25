package hmm;

import java.util.ArrayList;
import java.util.List;

public class TestHMMInit {
	
	public static final String TRAININDATA = "/home/azanzi/Documents/workspace/"
			+ "knowExtracFromSC/experiments/DataSources/1-TrainDS/1-raw_data";

	//The string observed
		private static List<String> preObservation=new ArrayList<String>() {{
			add("public");
			add("private");
			add("protected");
			add("static");
			add("final");
		}};
		
		private List<String> targetObservation=new ArrayList<String>() {{
			add("package");
			add("class");
			add("interface");
			add("extends");
			add("implements");
			add("abstract");
			add("enum");
		}};
		
		private static List<String> postObservation=new ArrayList<String>() {{
			add(";");
			add("{");
			add("}");
		}};
		private static List<String> otherObservation=new ArrayList<String>() {{
			add("if");
			add("else");
		}};

		
		
		
}

//public static final String EPICAMDATA = 
//"/home/azanzi/Documents/workspace/knowExtracFromSC/experiments/DataSources/2-TestDS";
//
//private static final String GEOSERVERDATA=
//"/home/azanzi/Documents/workspace/hmm/AdditionalMaterials/DataSources/"
//+ "1-TrainDS/2-treated_data/";
//
//private static final String GEOSERVER = "/home/azanzi/Documents/workspace/AdditionalMaterials"
//+ "/DataSources/3-Geoserver/geoserver-master";
