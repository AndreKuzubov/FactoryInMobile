package com.factory.andre.factoryinmobile.utils

import io.realm.Realm
import io.realm.RealmObject

fun <T : RealmObject> Realm.generateId(clazz: Class<T>): Long {
    return ((this.where(clazz).max("id") as Long?) ?: 0L) + 1L
}