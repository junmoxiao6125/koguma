import org.jlleitschuh.gradle.ktlint.KtlintPlugin

buildscript {
    dependencies {
        classpath(libs.google.services)
        classpath(libs.firebase.crashlytics.gradle)
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.ktlint)
}

allprojects {
    apply<KtlintPlugin>()
}
