package org.m0skit0.android.mapswrapper.testapp

import android.content.Context
import android.util.Log
import net.bytebuddy.ByteBuddy
import net.bytebuddy.android.AndroidClassLoadingStrategy
import net.bytebuddy.implementation.MethodDelegation
import net.bytebuddy.matcher.ElementMatchers
import org.m0skit0.android.mapswrapper.SupportMapFragment
import java.io.InputStream
import java.net.URL
import java.util.*

class TestClassloader(private val context: Context, private val classLoader: ClassLoader) : ClassLoader() {

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

    interface Forwarder<T, U> {
        fun to(target: U): T
    }

    class ForwarderInterceptor {

    }

    override fun loadClass(name: String?): Class<*> {
        Log.d(TAG, "loadClass $name")
        if (name == "org.m0skit0.android.mapswrapper.SupportMapFragment") {
            val file = context.getDir("temp", Context.MODE_PRIVATE)
            return ByteBuddy()
                .subclass(SupportMapFragment::class.java)
                .method(ElementMatchers.any())
                .intercept(MethodDelegation.to(com.google.android.gms.maps.SupportMapFragment.newInstance()))
                .make()
                .load(MainActivity::class.java.classLoader, AndroidClassLoadingStrategy.Injecting(file))
                .loaded
        }
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
