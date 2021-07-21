group = rootProject.group
version = rootProject.version

kotlin {
    jvm {}

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(kotlin("stdlib"))
                api("io.kinference:inference:0.1.2")
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        val jvmMain by getting {
            dependencies {
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-junit5"))

                runtimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.2")
            }
        }
    }
}
