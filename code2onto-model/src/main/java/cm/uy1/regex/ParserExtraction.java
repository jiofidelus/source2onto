//package cm.uy1.regex;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import cm.uy1.helper.Helper;
//
//public class ParserExtraction {
//	
//
//	static String termsRegex = "int|long|String|float|double|"
//			+ "package|public|private|protected";
////	static String termsRegex = "package|public|private|protected";
//	
//	static String conditionRegex = "if|else|switch";
//	
//	private static Pattern pattern;
//	private static Matcher matcher;
//	
//	
//	public static final String TRAININDATA = "/home/azanzi/Documents/workspace/"
//			+ "knowExtracFromSC/experiments/DataSources/1-TrainDS/1-raw_data";
//
//	public static final String EPICAMDATA = "/home/azanzi/Documents/workspace"
//			+ "/AdditionalMaterials/DataSources/2-TestDS/1-raw_data";
////			"/home/azanzi/Documents/workspace/knowExtracFromSC/"
////			+ "experiments/DataSources/2-TestDS";
//
//	public static final String TERMSREGEXEXTRACTION = 
//			"/home/azanzi/Documents/workspace/knowExtracFromSC/"
//			+ "experiments/termExtractedWithRegex";
//	
//	public static final String CONDITIONREGEXEXTRACTION = 
//			"/home/azanzi/Documents/workspace/knowExtracFromSC/"
//			+ "experiments/conditionExtractedWithRegex";
//
//	private static final String GEOSERVERRAWDATA = "/home/azanzi/Documents"
//			+ "/workspace/AdditionalMaterials/DataSources/3-Geoserver/1-raw_data/";
//	
//	static int nbTargetTerms=0;
//	static int nbTargetCondition=0;
//	
//	public static String collectTermsFromFiles(String filePath) {
//		String data = "";
//		
//		pattern = Pattern.compile(termsRegex);
//		FileReader fr;
//		BufferedReader br;
//		try {
//		fr = new FileReader(filePath);
//		br = new BufferedReader(fr);
//		
//		for (String line; (line=br.readLine())!=null;) {
////			System.out.println("++++The line readed : "+line);
//			//get the data
//			matcher = pattern.matcher(line);
//			if(matcher.find()) {
//				data+=line+"\n";
//				if(line.contains("package")) {
//					nbTargetTerms+=2;
//				}
//				if(line.contains("abstract")&&
//						!line.contains("extends")&&
//						!line.contains("implements")) {
//					nbTargetTerms+=3;
//				}
//				if(line.contains("abstract")&&
//						line.contains("extends")&&
//						!line.contains("implements")) {
//					nbTargetTerms+=5;
//				}
//				if(line.contains("abstract")&&
//						!line.contains("extends")&&
//						line.contains("implements")) {
//					nbTargetTerms+=5;
//				}
//				if(line.contains("abstract")&&
//						line.contains("extends")&&
//						line.contains("implements")) {
//					nbTargetTerms+=7;
//				}
//				if(line.contains("extends")) {
//					nbTargetTerms+=4;
//				}
//				if(line.contains("implements")) {
//					nbTargetTerms+=4;
//				}
//				else {
//					if(line.contains("class")&& !line.contains("extends")
//							&& !line.contains("implements") && !line.contains("abstract")) {
//					nbTargetTerms+=2;
//					}
//					else {
//						if(line.contains("interface")&& !line.contains("extends")
//								&& !line.contains("implements") && !line.contains("abstract")) {
//							nbTargetTerms+=2;
//						}
//						else {
//							if(line.contains("enum")) {
//								nbTargetTerms+=2;
//							}
//							else if(!line.contains("abstract")) nbTargetTerms+=2;
//						}
//					}
//			}
//				
//		}}
//		}catch (IOException e) {
//			// TODO: handle exception
//		}
//		
//		return data;
//	}
//	
//	public static String collectConditionFromSourceFiles(String filePath) {
//		String data = "";
//
//		pattern = Pattern.compile(conditionRegex);
//		FileReader fr;
//		BufferedReader br;
//		try {
//		fr = new FileReader(filePath);
//		br = new BufferedReader(fr);
//		
//		for (String line; (line=br.readLine())!=null;) {
////			System.out.println("++++The line readed : "+line);
//			//get the data
//			matcher = pattern.matcher(line);
//			if(matcher.find()) {
//				nbTargetCondition++;
//				data+=line+"\n";
//				while(line!=null && 
//						line.contains("}")){
//					line=br.readLine();
//					data=data+line+" ";
//				}
//			}
//		}
//		}catch (IOException e) {
//			// TODO: handle exception
//		}
//		
//		
//		return data;
//	}
//	
//	/**
//	 * Extract the names of classes and methods
//	 * @param projectPath
//	 * @return
//	 */
//	public static String getTerms(String projectPath) {
//		String terms = "";
//		
//		List<String> listSourceFiles 
//		= Helper.getFiles(projectPath);
//		
//		for (String sourceFiles : listSourceFiles) {
//			terms = terms + collectTermsFromFiles(sourceFiles);
//		}
////		System.out.println(terms);
//		return terms;
//	}
//	
//	public static String getConditions(String projectPath) {
//		String conditions="";
//		List<String> listSourceFiles
//			=Helper.getFiles(projectPath);
//		for (String sourceFile : listSourceFiles) {
//			conditions = conditions + collectConditionFromSourceFiles(sourceFile);
//		}
//		
//		return conditions;
//	}
//	
//	public static void main(String[] args) {
//		
//		/**
//		 * Counting the number of terms and 
//		 * conditions that may normaly be extracted
//		 */
//		/**
//		 * For EPICAM
//		 */
////		System.out.println(getTerms(EPICAMDATA));
//		getTerms(EPICAMDATA);
//		getConditions(EPICAMDATA);
//		System.out.println("The number of target terms: "+nbTargetTerms);
//		System.out.println("The number of target conditions: "+nbTargetCondition);
////		Helper.writeDataToFile(TERMSREGEXEXTRACTION, getTerms(EPICAMDATA));
////		Helper.writeDataToFile(CONDITIONREGEXEXTRACTION, getConditions(EPICAMDATA));
////		getTerms(TRAININDATA);
////		System.out.println(getConditions(EPICAMDATA));
//		/**
//		 * For Geoserver
//		 */
//
////		getTerms(GEOSERVERRAWDATA);
////		getConditions(GEOSERVERRAWDATA);
////		System.out.println("The number of target terms: "+nbTargetTerms);
////		System.out.println("The number of target conditions: "+nbTargetCondition);
//	}
//	
//}
