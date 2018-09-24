package com.factory.andre.factoryinmobile.utils

import android.view.View
import io.realm.Realm
import io.realm.RealmObject
import java.lang.reflect.Array
import java.util.*

fun <T : RealmObject> Realm.generateId(clazz: Class<T>): Long {
    return ((this.where(clazz).max("id") as Long?) ?: 0L) + 1L
}

fun View.dpToPx(dp: Int): Int {
    val density = this.getResources()
            .getDisplayMetrics()
            .density
    return Math.round(dp.toFloat() * density)
}


inline fun <T, outT> Iterable<T>.forEachConvert(action: (T) -> outT): List<outT> {
    val outList = ArrayList<outT>()
    for (element in this)
        outList.add(action(element))
    return outList
}