package com.teegarcs.amifunny.data

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import kotlinx.datetime.Clock
import org.mongodb.kbson.ObjectId

class SavedJoke() : RealmObject {
    var joke: String = ""
    var nouns: String = ""

    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var date: Long = Clock.System.now().toEpochMilliseconds()

    constructor(joke: String, nouns: String) : this() {
        this.joke = joke
        this.nouns = nouns
    }
}
