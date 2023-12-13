// KotlinProject.kt
package com.example.demo4

data class KotlinProject(
        var name: String,
        var description: String,
        var tasks: MutableList<KotlinTask> = mutableListOf()
)
