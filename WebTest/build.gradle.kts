plugins {
    id("java")
}

group = "dev.greben"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.testng:testng:7.1.0")
    testImplementation("org.seleniumhq.selenium:selenium-java:4.18.1")
    testImplementation("io.github.bonigarcia:webdrivermanager:5.7.0")
}

tasks.test {
    useTestNG()
}