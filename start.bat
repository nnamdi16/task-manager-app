@echo off
REM Define ports for services
set FRONTEND_PORT=8080
set TASK_MANAGER_SERVICE_PORT=8081


REM Define an array of port numbers you want to stop
REM Add your port numbers here
set ports=%FRONTEND_PORT% %TASK_MANAGER_SERVICE_PORT%

REM Loop through the array and attempt to stop processes on each port
for %%port in (%ports%) do (
    echo Stopping processes on port %%port...
    for /f "tokens=2" %%pid in ('netstat -aon ^| findstr /r "%%port"') do (
        taskkill /f /pid %%pid
    )
)

echo All specified ports have been stopped.

REM Start the frontend on port 8080
cd frontend || exit
npm install
start npm run dev -- --port %FRONTEND_PORT%
cd ..

REM Start Task Manager Service on port 8081
cd backend || exit
call mvnw spring-boot:run -Dserver.port=%TASK_MANAGER_SERVICE_PORT%
cd ..


