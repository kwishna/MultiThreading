@ECHO OFF
::SET projectDir=%~dp0
::SET date=%dd%%mm%%yyyy%
::SET datetime=%date:~-4%%date:~4,2%%date:~7,2%_%time:~0,2%%time:~3,2%%time:~6,2%

::SET HOUR=%time:~0,2%
::SET dtStamp9=%date:~-4%%date:~4,2%%date:~7,2%_0%time:~1,1%%time:~3,2%%time:~6,2%
::SET dtStamp24=%date:~-4%%date:~4,2%%date:~7,2%_%time:~0,2%%time:~3,2%%time:~6,2%
::if "%HOUR:~0,1%" == " " (SET dtStamp=%dtStamp9%) else (SET dtStamp=%dtStamp24%)
::ECHO %dtStamp%

SET projectDir=%~dp0

set dt=%DATE:~6,4%_%DATE:~3,2%_%DATE:~0,2%_%TIME:~0,2%_%TIME:~3,2%_%TIME:~6,2%
set dt=%dt: =0%
echo %dt%

echo ---- COPY FOLDERS ----
cd %projectDir%
IF NOT EXIST RESULTS MD RESULTS
xcopy /E "%projectDir%\target\*.*" "RESULTS\PipeLine_%dt%\*.*"