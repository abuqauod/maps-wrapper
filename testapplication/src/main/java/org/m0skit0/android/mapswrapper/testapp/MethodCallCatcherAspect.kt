package org.m0skit0.android.mapswrapper.testapp

import android.util.Log
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before

@Aspect
class MethodCallCatcherAspect {

    private val TAG = "[ASPECTJ]"

    @Before("execution(* onCreate(*)) || execution(* onCreate())")
    fun anySupportMapFragmentCall(joinPoint: JoinPoint) {
        Log.d(TAG, "Method called: ${joinPoint.toLongString()}")
    }
}
