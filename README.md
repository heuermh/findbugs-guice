findbugs-guice
==============
findbugs-guice is a detector for [Findbugs](http://code.google.com/p/findbugs/), a popular static
analysis tool, that detects errors in the use of [Guice](http://code.google.com/p/google-guice/).

findbugs-guice detects:

  * scope annotations on interfaces (which Guice does not support)
  * installation of submodules via Module.configure() (which doesn't install @Provides methods) rather than Module.install()
  * static field injection (which is recommended against)
  * final field injection (which is recommended against and error-prone)

How to install
--------------
Install findbugs-guice like any other Findbugs detector:

1. Put [the JAR](https://github.com/downloads/tomfitzhenry/findbugs-guice/findbugs-guice-0.3.jar) in `FINDBUGS_HOME/plugin`.
2. Test your installation against [code which exhibits the issues that findbugs-guice detects](https://github.com/tomfitzhenry/findbugs-guice/tree/master/src/test/benchmarks).

How to build
------------
`mvn package`
