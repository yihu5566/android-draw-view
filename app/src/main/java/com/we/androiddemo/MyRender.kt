package com.we.androiddemo

import android.opengl.GLES20
import android.opengl.GLSurfaceView
import android.opengl.Matrix
import android.os.SystemClock
import android.util.Log
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

/**
 * @Author : dongfang
 * @Created Time : 2022-07-19  10:25
 * @Description: 自定义
 */
class MyRender : GLSurfaceView.Renderer {
    var triangle: Triangle? = null

    //
    val mMVPMatrix = FloatArray(16)
    val mProjectionMatrix = FloatArray(16)
    val mViewMatrix = FloatArray(16)
    val mRotationMatrix = FloatArray(16)
    override fun onSurfaceCreated(p0: GL10?, p1: EGLConfig?) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f)
        triangle = Triangle()

    }

    override fun onSurfaceChanged(p0: GL10?, width: Int, height: Int) {
        GLES20.glViewport(0, 0, width, height)
        Log.d("onSurfaceChanged--", "$width==$height")

        var ratio = 0.5f
        Log.d("onSurfaceChanged--", ratio.toString())
        Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1f, 1f, 3F, 7F)
    }

    override fun onDrawFrame(p0: GL10?) {
        val scratch = FloatArray(16)

        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)
        Matrix.setLookAtM(
            mViewMatrix, 0, 0f, 0f, -3f,
            0f, 0f, 0f, 0f, 1.0f, 0.0f
        )
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0)

        //创建旋转动画
        val time = SystemClock.uptimeMillis() % 4000L
        var angle: Float = 0.090f * time
        Matrix.setRotateM(mRotationMatrix, 0, angle, 0F, 0F, -1f)

        Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, mRotationMatrix, 0)


        triangle?.draw(scratch)
    }

    companion object {
        fun loadShader(type: Int, shaderCode: String?): Int {

            // 创造顶点着色器类型(GLES20.GL_VERTEX_SHADER)
            // 或者是片段着色器类型 (GLES20.GL_FRAGMENT_SHADER)
            val shader = GLES20.glCreateShader(type)
            // 添加上面编写的着色器代码并编译它
            GLES20.glShaderSource(shader, shaderCode)
            GLES20.glCompileShader(shader)
            return shader
        }
    }

}
