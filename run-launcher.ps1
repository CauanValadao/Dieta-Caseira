# PowerShell helper to run Launcher via Maven exec plugin
# Requires: JAVA_HOME pointing to a JDK (recommended: JDK 21), maven in PATH

if (-not $Env:JAVA_HOME) {
  Write-Host "Warning: JAVA_HOME is not set. It's recommended to use JDK 21 (Temurin 21)." -ForegroundColor Yellow
}

Write-Host "Running Launcher via Maven exec plugin..."

# Use quotes to avoid PowerShell parsing issues
mvn "-Dexec.mainClass=dietacaseira.Launcher" exec:java
