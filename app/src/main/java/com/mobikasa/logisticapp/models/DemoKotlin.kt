package com.mobikasa.logisticapp.models

import java.util.*

class DemoKotlin(name: String, pwd: String, age: Int) {
    var mName = name
        get() {
            return field.toLowerCase(Locale.ROOT)
        }
    var password = pwd
        set(value) {
            field =
                if (value.length >= 10) value else throw IllegalArgumentException("Password Should Be !10 characters ")
        }
    var mAge = age
        set(value) {
            field = if (value >= 18) value else throw IllegalArgumentException("Age Must be 18+")
        }

}