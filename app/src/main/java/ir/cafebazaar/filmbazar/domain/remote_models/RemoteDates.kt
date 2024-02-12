package ir.cafebazaar.filmbazar.domain.remote_models

import ir.cafebazaar.filmbazar.domain.Dates
import ir.cafebazaar.filmbazar.domain.local_models.RealmDates
import ir.cafebazaar.filmbazar.domain.local_models.toDomain

data class RemoteDates(
    val maximum: String,
    val minimum: String
)

fun RemoteDates.toDomain(): Dates{
    return Dates(
        this.maximum,
        this.minimum
    )
}