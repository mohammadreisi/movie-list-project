package ir.cafebazaar.filmbazar.domain.local_models

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import ir.cafebazaar.filmbazar.domain.Dates

open class RealmDates : RealmObject {
    @PrimaryKey
    val id: Long? = null
    var maximum: String? = null
    var minimum: String? = null
}

fun RealmDates.toDomain(): Dates {
    return Dates(
        this.maximum ?: "",
        this.minimum ?: ""
    )
}
