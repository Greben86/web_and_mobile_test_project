plugins {
    id("java")
}

group = "dev.greben"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.projectlombok:lombok:1.18.38")

    testImplementation("org.testng:testng:7.1.0")
    testImplementation("io.appium:java-client:8.6.0")
}

tasks.test {
    useTestNG()
}