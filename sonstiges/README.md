# Maven

### TheNexus (Team, welches Nexus entwickelt) stellt weitergehende Infos online zur Verfügung, insbesondere:
- Das Standardwerk "Maven: The Complete Reference" (vieles ist nach wie vor aktuell):
  https://books.sonatype.com/mvnref-book/reference/index.html

  

# JNI

Information about the Java Native Interface (JNI).

### Oracle:

- [Java Native Interface Specification](https://docs.oracle.com/en/java/javase/17/docs/specs/jni/)


### Relevant Articles bei Baeldung: 

- [Guide to JNI (Java Native Interface)](https://www.baeldung.com/jni)
- [Using JNA to Access Native Dynamic Libraries](https://www.baeldung.com/java-jna-dynamic-libraries)
- [Check if a Java Program Is Running in 64-Bit or 32-Bit JVM](https://www.baeldung.com/java-detect-jvm-64-or-32-bit)
- [How to use JNI’s RegisterNatives() method?](https://www.baeldung.com/jni-registernatives)


### Sonstige:

- [JNI Cookbook](https://github.com/mkowsiak/jnicookbook)


# Debugging:

-  Debugger von der Kommandozeile für Java Code:
   Das JDK liefert mit "jdb" im "lib"-Verzeichnissogar einen mit.
   Details siehe https://docs.oracle.com/en/java/javase/17/docs/specs/man/jdb.html

2) Debuggen von Java-Code und JNI-C-Code:
   Es gibt wohl nicht den einen Debugger der beides kann, aber man kann wohl den Eclipse-Java-Debugger und den C-Debugger gleichzeitig im Projekt nutzen.
   Siehe z.B. https://developers.redhat.com/blog/2016/11/03/eclipse-for-jni-development-and-debugging-on-linux-java-and-c#checking_native_code_with__xcheck_jni
   und
   https://stackoverflow.com/questions/13133022/java-jni-c-debugger

Der erste Link verweist im Übrigen auf Spezialliteratur zum JNI.


