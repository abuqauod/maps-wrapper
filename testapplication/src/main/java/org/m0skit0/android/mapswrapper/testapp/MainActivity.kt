package org.m0skit0.android.mapswrapper.testapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import net.bytebuddy.ByteBuddy
import net.bytebuddy.android.AndroidClassLoadingStrategy
import net.bytebuddy.implementation.FixedValue
import net.bytebuddy.matcher.ElementMatchers


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        var file = getDir("temp", Context.MODE_PRIVATE)

        ByteBuddy()
            .subclass(Any::class.java)
            .method(ElementMatchers.named("toString"))
            .intercept(FixedValue.value("Hello world!"))
            .make()
            .load(MainActivity::class.java.classLoader, AndroidClassLoadingStrategy.Injecting(file))
            .loaded
            .newInstance()
            .toString().let {
                Log.d(javaClass.simpleName, it)
            }
    }
}
