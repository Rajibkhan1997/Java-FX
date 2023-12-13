// KotlinTaskGraph.kt
package com.example.demo4

class KotlinTaskGraph {
    private val projects: MutableList<KotlinProject> = mutableListOf()

    fun addProject(project: KotlinProject) {
        projects.add(project)
    }

    fun removeProject(project: KotlinProject) {
        projects.remove(project)
    }

    fun getAllProjects(): List<KotlinProject> {
        return projects.toList()
    }
}
