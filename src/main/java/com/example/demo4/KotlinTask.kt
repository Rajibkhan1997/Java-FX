// KotlinTask.kt
package com.example.demo4

import java.time.LocalDate

data class KotlinTask(
        var name: String,
        var description: String,
        var startDate: LocalDate,
        var endDate: LocalDate,
        var duration: Int,
        var successorTasks: List<KotlinTask> = mutableListOf()
)
