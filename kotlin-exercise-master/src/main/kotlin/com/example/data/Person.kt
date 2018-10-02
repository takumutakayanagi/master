package com.example.data

import com.example.type.Blood
import java.time.LocalDate

data class Person(val name: String, val age: Int, val blood: Blood) : Comparable<Person> {

    var birthDay: LocalDate? = null

    constructor(name: String, age: Int, blood: Blood, birthDay: LocalDate) : this(name, age, blood) {
        this.birthDay = birthDay
    }

    override fun toString(): String {
        return "Person(name='$name', age=$age, blood=$blood, birthDay=$birthDay)"
    }

    override fun compareTo(other: Person): Int {
        if (this.blood != other.blood) return this.blood.compareTo(other.blood)
        if (this.birthDay != null && other.birthDay == null) return -1
        if (this.birthDay == null && other.birthDay != null) return 1
        return this.birthDay!!.compareTo(other.birthDay!!)
    }

}