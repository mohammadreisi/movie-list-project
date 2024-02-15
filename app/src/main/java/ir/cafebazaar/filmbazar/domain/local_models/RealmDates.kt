package ir.cafebazaar.filmbazar.domain.local_models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import ir.cafebazaar.filmbazar.domain.Dates

open class RealmDates : RealmObject() {
    @PrimaryKey
    var id: Long? = null
    var maximum: String? = null
    var minimum: String? = null
}

fun RealmDates.toDomain(): Dates {
    return Dates(
        this.maximum ?: "",
        this.minimum ?: ""
    )
}
