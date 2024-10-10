// Top-level build file where you can add configuration options common to all sub-projects/modules.
//buildscript {
//    dependencies {
//        classpath ("org.jetbrains.kotlin:kotlin-serialization:1.6.10")
//    }
//}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false

    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.serialization) apply false  // equvalent to above commented buildScripts
}
/*
task clean(type: Delete){
    delete rootProject.buildDir
}*/
tasks.register<Delete>("clean") {
    delete(layout.buildDirectory)
}


