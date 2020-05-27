package org.m0skit0.android.mapswrapper.testapp

import android.util.Log
import java.io.InputStream
import java.net.URL
import java.util.*

class TestClassloader(private val classLoader: ClassLoader) : ClassLoader() {

    private val TAG = javaClass.simpleName

    override fun getResource(name: String?): URL {
        return classLoader.getResource(name)
    }

    override fun setPackageAssertionStatus(packageName: String?, enabled: Boolean) {
        classLoader.setPackageAssertionStatus(packageName, enabled)
    }

    override fun setClassAssertionStatus(className: String?, enabled: Boolean) {
        classLoader.setClassAssertionStatus(className, enabled)
    }

    override fun loadClass(name: String?): Class<*> {
        Log.d(TAG, "loadClass $name")
        return classLoader.loadClass(name)
    }

    override fun getResourceAsStream(name: String?): InputStream {
        return classLoader.getResourceAsStream(name)
    }

    override fun clearAssertionStatus() {
        classLoader.clearAssertionStatus()
    }

    override fun getResources(name: String?): Enumeration<URL> {
        return classLoader.getResources(name)
    }

    override fun setDefaultAssertionStatus(enabled: Boolean) {
        classLoader.setDefaultAssertionStatus(enabled)
    }
}
