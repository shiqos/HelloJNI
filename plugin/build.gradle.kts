plugins {
    `kotlin-dsl`
    id("maven-publish")
}

gradlePlugin {
    plugins {
        create("com.test.plugin") {
            id = "com.test.plugin"
            implementationClass = "com.test.plugin.TestPlugin"
        }
    }
}

dependencies {
    implementation(libs.gradle.android)
    implementation(libs.gradle.kotlin)

//    implementation("com.android.tools.build:gradle-api:7.2.1")
//    implementation("com.android.tools.build:gradle:7.2.1")
//    implementation("com.android.tools:common:30.2.1")
}

group = "com.test.plugin"
version = "0.0.1-SNAPSHOT"

afterEvaluate {
    val sourceJar = task<Jar>("sourceJar") {
        val sourceSets = project.extensions.getByType<SourceSetContainer>()
        from(sourceSets.getByName("main").java.srcDirs)
        archiveClassifier.set("sources")
    }

    configure<PublishingExtension> {
        publications.getByName<MavenPublication>("pluginMaven") {
            artifact(sourceJar)
        }

        repositories {
            maven {
                val snapshot = (version as String).endsWith("-SNAPSHOT")
                setUrl(
                    if (snapshot) "http://172.24.17.168:8081/repository/thirddep/"
                    else "http://172.24.17.168:8081/repository/thirddep/"
                )
                isAllowInsecureProtocol = true
                credentials {
                    username = System.getenv("MAVEN_USER")
                    password = System.getenv("MAVEN_PASSWD")
                }
            }
        }
    }
}