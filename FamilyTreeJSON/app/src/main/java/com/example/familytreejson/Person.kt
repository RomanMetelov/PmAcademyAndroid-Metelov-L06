package com.example.familytreejson

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Person(
    val name: String,
    val age: Int,
    var isAdult: Boolean = isAdult(age),
    var amountOfAllRelatives: Int = 0,
    var mother: Person? = null,
    var father: Person? = null){

    companion object {
        var listOfMyRelatives: MutableList<Person> = mutableListOf<Person>()
    }

    override fun toString(): String {
        return this.name
    }

    fun countAllRelativesOf(person: Person) {
        this.mother?.let {
            person.amountOfAllRelatives++
            it.countAllRelativesOf(person)
        }
        this.father?.let {
            person.amountOfAllRelatives++
            it.countAllRelativesOf(person)
        }
    }
}