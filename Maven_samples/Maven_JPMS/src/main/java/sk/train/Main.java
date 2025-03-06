package sk.train;


public class Main {

    public static void main(String[] args) {

    	//problemfrei
        System.out.println("Hello World!");

        //interne API nutzen
		//com.sun.awt..AWTUtilities: deprecated in Java10, removed in Java11!!
        //System.out.println(com.sun.awt.AWTUtilities.isWindowShapingSupported());
        System.out.println(java.util.Arrays.toString(jdk.internal.misc.VM.getRuntimeArguments()));
		//System.out.println(java.util.Arrays.toString(jdk.internal.misc.VM.getRuntimeArguments()));
		//Abhilfe für Compiler: --add-exports java.base/jdk.internal.misc=ALL-UNNAMED
		//für VM ebenfalls notwendig



    }
}

