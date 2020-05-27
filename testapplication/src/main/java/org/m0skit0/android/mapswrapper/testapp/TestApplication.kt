package org.m0skit0.android.mapswrapper.testapp

import android.app.Application
import android.util.Log
import org.m0skit0.android.mapswrapper.MapType
import org.m0skit0.android.mapswrapper.MapsConfiguration
import java.lang.reflect.Field


class TestApplication : Application() {

    private val TAG = javaClass.simpleName

    override fun onCreate() {
        Log.d(TAG, "onCreate")
        javaClass.getFieldValue<Any>(this, "mLoadedApk")?.let { loadedApk ->
            Log.d(TAG, "mLoadedApk value: $loadedApk")
            loadedApk.javaClass.getFieldValue<ClassLoader>(loadedApk, "mBaseClassLoader")?.let {
                Log.d(TAG, "mBaseClassLoader value: $it")
            }
            loadedApk.javaClass.getFieldValue<ClassLoader>(loadedApk, "mDefaultClassLoader")?.let {
                Log.d(TAG, "mDefaultClassLoader value: $it")
            }
            loadedApk.javaClass.getFieldValue<ClassLoader>(loadedApk, "mClassLoader")?.let {
                Log.d(TAG, "mClassLoader value: $it")
                loadedApk.setFieldValue("mClassLoader", TestClassloader(it))
                loadedApk.javaClass.getFieldValue<ClassLoader>(loadedApk, "mClassLoader")?.let {
                    Log.d(TAG, "mClassLoader value: $it")
                }
            }
        }

        super.onCreate()
        MapsConfiguration.type = MapType.GOOGLE
    }

    private fun <T> Class<*>.getFieldValue(instance: Any, name: String): T? =
        field(name)?.get(instance) as T?

    private fun Any.setFieldValue(name: String, value: Any) {
        javaClass.field(name)?.set(this, value)
    }

    fun <T: Any> Class<T>.field(name: String): Field? =
        fields.firstOrNull { it.name == name }?.apply { isAccessible = true } ?:
        declaredFields.firstOrNull { it.name == name }?.apply { isAccessible = true }
}
