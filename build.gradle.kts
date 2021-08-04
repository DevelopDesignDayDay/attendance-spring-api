import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val kotlinVersion = "1.5.20"
    id("org.springframework.boot") version "2.5.3"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.jpa") version kotlinVersion
    kotlin("plugin.allopen") version kotlinVersion
    kotlin("plugin.noarg") version kotlinVersion
    id("com.ewerk.gradle.plugins.querydsl") version "1.0.10"
    kotlin("kapt") version kotlinVersion
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

noArg {
    annotation("javax.persistence.Entity")
}

group = "com.example"
//version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

extra["springCloudVersion"] = "2020.0.3"

dependencies {
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    implementation("io.github.openfeign:feign-okhttp")
    implementation("io.github.openfeign:feign-jackson:9.3.1")

    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactive")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    val querydslVersion = "4.4.0"

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.querydsl:querydsl-core:$querydslVersion")
    implementation("com.querydsl:querydsl-jpa:$querydslVersion")
    implementation("com.querydsl:querydsl-apt:$querydslVersion")
    implementation("com.querydsl:querydsl-sql:$querydslVersion")
    implementation("com.querydsl:querydsl-sql-spring:$querydslVersion")
    kapt("com.querydsl:querydsl-apt:$querydslVersion:jpa")
    testImplementation ("com.querydsl:querydsl-jpa:$querydslVersion")
    testAnnotationProcessor ("com.querydsl:querydsl-apt:$querydslVersion:jpa")

    runtimeOnly("mysql:mysql-connector-java")

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    implementation("org.springdoc:springdoc-openapi-webflux-ui:1.5.9")
    implementation("org.springdoc:springdoc-openapi-data-rest:1.5.9")
    implementation("org.springdoc:springdoc-openapi-kotlin:1.5.9")

    implementation("io.github.microutils:kotlin-logging-jvm:2.0.6")

//    implementation("org.springframework.boot:spring-boot-starter-security")
//    testImplementation("org.springframework.security:spring-security-test")
    implementation(group= "io.jsonwebtoken", name= "jjwt-api", version= "0.11.2")
    runtimeOnly(group= "io.jsonwebtoken", name= "jjwt-impl", version= "0.11.2")
    runtimeOnly(group= "io.jsonwebtoken", name= "jjwt-jackson", version= "0.11.2")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

val generatedSourcesDir = file("${buildDir}/generated/querydsl")

configurations {
    querydsl.get().setExtendsFrom(arrayOf(compileClasspath.get()).asIterable())
}

tasks.compileQuerydsl {
    options.annotationProcessorPath = configurations.querydsl.get()
}

sourceSets.main {
    java.srcDirs.add(file(generatedSourcesDir))
    java.srcDirs.add(file("$buildDir/generated/source/kapt/main"))
}

querydsl {
    querydslSourcesDir = relativePath(generatedSourcesDir)
    library = "com.querydsl:querydsl-apt"
    jpa = true
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.bootJar {
    archiveFileName.set("app.jar")
}
