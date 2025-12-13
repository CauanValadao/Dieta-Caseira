@echo off
if "%JAVA_HOME%"=="" echo Warning: JAVA_HOME is not set. It's recommended to use JDK 21.

echo Running Launcher via Maven exec plugin...

mvn "-Dexec.mainClass=dietacaseira.Launcher" exec:java
