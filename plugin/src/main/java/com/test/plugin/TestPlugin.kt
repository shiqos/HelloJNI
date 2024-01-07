package com.test.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class TestPlugin : Plugin<Project> {
    companion object {
        private const val TAG = "TestPlugin"
    }

    override fun apply(project: Project) {
        println("$TAG apply")
    }
}