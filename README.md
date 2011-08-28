findbugs-guice
==============
findbugs-guice is a [Findbugs](http://code.google.com/p/findbugs/) detector for [Guice](http://code.google.com/p/google-guice/).

How to install
--------------
Install findbugs-guice like any other Findbugs detector:

1. Put [the JAR](https://github.com/downloads/tomfitzhenry/findbugs-guice/findbugs-guice-0.1-SNAPSHOT.jar) in `FINDBUGS\_HOME/plugin`.
2. Test your installation against [code which exhibits the issues that findbugs-guice detects](https://github.com/tomfitzhenry/findbugs-guice/tree/master/src/test/benchmarks).

How to build
------------
`mvn package`
