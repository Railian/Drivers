package ua.drivers.mobile.android.feature.authorization.contract

interface Validator<T,R> {
    fun validate(value :T): R
}