@echo off
setlocal enabledelayedexpansion

:: ===============================
:: Kill Java processes running on specified ports

call :killPort 8761 "service-registry"
call :killPort 8081 "api-gateway"
call :killPort 8082 "authenticate-service"
call :killPort 8083 "customer-service"
call :killPort 8085 "car-service"
call :killPort 8084 "washer-service"
call :killPort 8086 "order-service"
call :killPort 8087 "payment-service"
call :killPort 8088 "admin-service"
call :killPort 8089 "notification-service"

echo.
echo All matching services terminated.
pause
exit /b

:: ===============================
:: Function to kill Java process on port
:killPort
set "targetPort=%~1"
set "serviceName=%~2"

echo.
echo === Checking port %targetPort% for %serviceName% ===

set "pidFound="

for /f "tokens=5" %%A in ('netstat -aon ^| findstr :%targetPort% ^| findstr LISTENING') do (
    set "pidFound=%%A"
)

if defined pidFound (
    echo Port %targetPort% is in use by PID !pidFound!. Killing %serviceName%...
    taskkill /PID !pidFound! /F >nul 2>&1
    echo %serviceName% on port %targetPort% killed.
) else (
    echo Port %targetPort% is already free.
)

exit /b
