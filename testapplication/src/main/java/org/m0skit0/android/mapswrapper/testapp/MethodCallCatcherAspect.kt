package org.m0skit0.android.mapswrapper.testapp

import android.util.Log
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect

@Aspect
class MethodCallCatcherAspect {

    private val TAG = "[ASPECTJ]"

    @Around("call(* org.m0skit0.android.mapswrapper..*(..))")
    fun anySupportMapFragmentCall(joinPoint: ProceedingJoinPoint) {
        Log.d(TAG, "Method called: ${joinPoint.toLongString()}")
        joinPoint.proceed()
    }
}
