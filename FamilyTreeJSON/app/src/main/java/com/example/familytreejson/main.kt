package com.example.familytreejson

import com.google.gson.Gson
import java.io.FileReader
import java.io.FileWriter

fun main() {
    val me: Person = init()
    val jsonFilePath: String = "app/src/main/java/com/example/familytreejson/familytree.json"
    val reader = FileReader(jsonFilePath)


    //serialization
    val jsonString = Gson().toJson(me)
    FileWriter(jsonFilePath).use {
        it.write(jsonString)

    }
    println(jsonString)


    //deserialization
    val newMe: Person = Gson().fromJson(reader, Person::class.java)
    println("(me == newMe) -> ${me==newMe}")
}


fun init(): Person {
    val me: Person = Person("Roma", 23)

    me.mother = Person("Mother", 45)
    me.father = Person("Father", 47)

    me.mother!!.mother = Person("Tetiana", 72)
    me.mother!!.father = Person("Vladlen", 76)

    me.father!!.mother = Person("Tamara", 74)
    me.father!!.father = Person("Volodymyr", 73)

    me.father!!.mother!!.father = Person("Valentyn", 99)

    me.countAllRelativesOf(me)

    Person.listOfMyRelatives = mutableListOf<Person>() //static variable, must be reset before using
    createListRelativesOf(me)
    Person.listOfMyRelatives.forEach {
        it.amountOfAllRelatives = Person.listOfMyRelatives.size
    }
    Person.listOfMyRelatives = mutableListOf<Person>() //static variable, must be reset after using

    return me
}

fun createListRelativesOf(person: Person) {
    person.mother?.let {
        Person.listOfMyRelatives.add(it)
        createListRelativesOf(it)
    }
    person.father?.let {
        Person.listOfMyRelatives.add(it)
        createListRelativesOf(it)
    }
}

fun isAdult(age: Int): Boolean {
    if (age >= 18) return true
    return false
}