package com.we.androiddemo.opegl

import android.content.Context
import android.opengl.GLSurfaceView
import com.we.androiddemo.opegl.MyRender

/**
 * @Author : dongfang
 * @Created Time : 2022-07-19  10:24
 * @Description:
 */
class MySurfaceView(context:Context):GLSurfaceView(context) {
   init {
       setEGLContextClientVersion(2)
       setRenderer(MyRender())
   }

}