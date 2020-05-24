package org.m0skit0.android.mapswrapper.testapp

import android.util.Log
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before

@Aspect
class MethodCallCatcherAspect {

    private val TAG = "[ASPECTJ]"

    @Before("target(org.m0skit0.android.mapswrapper.SupportMapFragment)")
    fun anySupportMapFragmentCall(joinPoint: JoinPoint) {
        Log.d(TAG, "Method called: ${joinPoint.toLongString()}")
    }
}
