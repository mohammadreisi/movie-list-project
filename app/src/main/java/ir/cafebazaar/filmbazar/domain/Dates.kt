package ir.cafebazaar.filmbazar.domain

import ir.cafebazaar.filmbazar.domain.local_models.RealmDates

data class Dates(
    val maximum: String,
    val minimum: String
)

fun Dates.toRealmDates(): RealmDates {
    return RealmDates().also {
        it.maximum = maximum
        it.minimum = minimum
    }
}
