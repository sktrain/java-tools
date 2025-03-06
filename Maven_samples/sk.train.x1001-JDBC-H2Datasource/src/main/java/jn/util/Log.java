package jn.util;

public class Log {

	public static void startMethod() {
		final StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		final String methodName = elements[2].getMethodName();
		hlog(methodName);
	}

	public static void hlog(String text) {
		final String LINE = "+------------------------------------------------";
		System.out.println(LINE);
		System.out.println("| " + text);
		System.out.println(LINE);
	}

	public static void tlog(String text) {
		synchronized (System.out) {
			System.out.printf("[ %2d ] ", Thread.currentThread().getId());
			System.out.println(text);
		}
	}

	public static void log(Object... params) {
		// getStackTrace ist der aktuellste Stackframe
		// wird aufgerufen durch log-Methode, wird aufgerufen durch eigentliche Methode
		// deshalb Index 2!
		final StackTraceElement element = Thread.currentThread().getStackTrace()[2];
//		System.err.println(Thread.currentThread().getStackTrace()[0]);
//		System.err.println(Thread.currentThread().getStackTrace()[1]);
//		System.err.println(Thread.currentThread().getStackTrace()[2]);
		final String className = element.getClassName();
		final String methodName = element.getMethodName();
		System.out.print(className + "." + methodName + "(");
		for (int i = 0; i < params.length; i++) {
			if (i > 0)
				System.out.print(", ");
			System.out.print(params[i]);
		}
		System.out.println(")");
	}

}
