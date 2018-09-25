package cm.uy1;

import java.awt.Taskbar.State;

import cm.uy1.modelDefinition.HMM;


public class Test {

	public static void main(String[] args) {
		
		System.out.println("Hello World!!!!!!!!!!!!!!");
		HMM hmm = new HMM();
		hmm.init();
//		State pre = new State("PRE");
		System.out.println(hmm);
		
	}

}
