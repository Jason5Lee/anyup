package me.jason5lee.anyup

import kotlin.properties.PropertyDelegateProvider
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

object NullDelegate : ReadOnlyProperty<Any?, Nothing?>, PropertyDelegateProvider<Any?, ReadOnlyProperty<Any?, Nothing?>> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): Nothing?  = null
    override fun provideDelegate(
        thisRef: Any?,
        property: KProperty<*>
    ): ReadOnlyProperty<Any?, Nothing?> = this
}

inline fun <T, PT, RT, RD> T?.orNullDelegate(f: (T) -> PropertyDelegateProvider<PT, ReadOnlyProperty<RT, RD?>>):
        PropertyDelegateProvider<PT, ReadOnlyProperty<RT, RD?>> =
    if (this != null) {
        f(this)
    } else {
        NullDelegate
    }
