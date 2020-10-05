@ECHO OFF
cd %~dp0
mvn exec:java -Dexec.mainClass="jenkins.Rough"
echo ---- COPY FOLDERS ----
cd %projectDir%
IF NOT EXIST TEST_RESULTS MD TEST_RESULTS
xcopy /E "%projectDir%/target/" "TEST_RESULTS/MultipleConcepts/PipeLine/%datetime%"
echo Done