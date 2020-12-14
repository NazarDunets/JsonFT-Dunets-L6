package com.example.ftjson

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import java.io.File

data class Person(
    @SerializedName("name")
    val name: String,
    @SerializedName("age")
    val age: Int,
    @SerializedName("mother")
    val mother: Person?,
    @SerializedName("father")
    val father: Person?,
) {
    @SerializedName("nrelatives")
    var numOfRelatives: Int

    @SerializedName("adult")
    var isAdult: Boolean

    init {
        var result = 0
        if (mother != null) result += 1 + mother.numOfRelatives
        if (father != null) result += 1 + father.numOfRelatives
        numOfRelatives = result

        isAdult = age >= 18
    }
}

fun main(args: Array<String>) {
    val me = getMe()
    val gson = Gson()

    val jsonString = gson.toJson(me)
    println(jsonString)

    val jsonFromFile = File("ft.json").bufferedReader()
            .use { it.readText() }
    val meFromJson = gson.fromJson(jsonFromFile, Person::class.java)
    println(meFromJson)
}

fun getMe(): Person {
    val babushkaM = Person(
        "babushkaM",
        99,
        null,
        null,
    )
    val dedushkaM = Person(
        "dedushkaM",
        101,
        null,
        null,
    )
    val sisterM = Person(
        "sisterM",
        40,
        null,
        null,
    )
    val babushkaP = Person(
        "babushkaP",
        100,
        null,
        null,
    )
    val dedushkaP = Person(
        "dedushkaP",
        103,
        null,
        null,
    )
    val mama = Person(
        "mama",
        43,
        babushkaM,
        dedushkaM,
    )
    val papa = Person(
        "papa",
        45,
        babushkaP,
        dedushkaP,
    )
    return Person(
        "Nazar",
        16,
        mama,
        papa,
    )
}