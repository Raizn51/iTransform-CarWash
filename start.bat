@echo off
setlocal enabledelayedexpansion

:: ===============================
:: Start service if port is free or after killing it

call :killAndStart 8761 "service-registry"
call :killAndStart 8081 "api-gateway"
call :killAndStart 8082 "authenticate-service"
call :killAndStart 8083 "customer-service"
call :killAndStart 8085 "car-service"
call :killAndStart 8084 "washer-service"
call :killAndStart 8086 "order-service"
call :killAndStart 8087 "payment-service"
call :killAndStart 8088 "admin-service"
call :killAndStart 8089 "notification-service"

echo All services started fresh.
pause
exit /b

:: ===============================
:: Function to kill Java process on port and start service
:killAndStart
set "targetPort=%~1"
set "serviceDir=%~2"

echo.
echo === Handling %serviceDir% on port %targetPort% ===

set "pidFound="

for /f "tokens=5" %%A in ('netstat -aon ^| findstr :%targetPort% ^| findstr LISTENING') do (
    set "pidFound=%%A"
)

if defined pidFound (
    echo Port %targetPort% is in use by PID !pidFound!. Killing it...
    taskkill /PID !pidFound! /F >nul 2>&1
    timeout /t 2 >nul
) else (
    echo Port %targetPort% is free.
)

echo Starting %serviceDir%...
start "%serviceDir%" cmd /k "cd %serviceDir% && mvnw spring-boot:run"
timeout /t 5 >nul
exit /b
