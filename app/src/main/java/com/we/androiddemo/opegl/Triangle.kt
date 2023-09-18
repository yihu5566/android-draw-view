package com.we.androiddemo.opegl

import android.opengl.GLES20
import com.we.androiddemo.opegl.MyRender
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer

/**
 * @Author : dongfang
 * @Created Time : 2022-07-19  10:53
 * @Description:
 */
class Triangle {

    val COORDS_PER_VERTEX = 3

    private var triangleCoords = floatArrayOf(
        // 前面 正方形的4个顶点
        0.0f, 0.5f, 0.0f,
        -0.5f, -0.5f, 0.0f,
        0.5f, -0.5f, 0.0f,
    )

//    var color = floatArrayOf(255f, 0f, 0f, 1.0f)

    var color = floatArrayOf(
        1.0f, 0f, 0f, 1.0f,
        0f, 1.0f, 0f, 1.0f,
        0f, 0f, 1.0f, 1.0f
    )
    val mProgram: Int
    var mPositionHandle: Int = 0
    val vertexCount = triangleCoords.size / COORDS_PER_VERTEX
    val vertexStride = COORDS_PER_VERTEX * 4
    var vertexBuffer: FloatBuffer
    var colorBuffer: FloatBuffer
    var mColorHandle: Int = 0
    var mMVPMatrixHandle: Int = 0


    val vertexShaderCode =
        "uniform mat4 uMVPMatrix;" +
                "attribute vec4 vPosition;" +
                "varying  vec4 vColor;" +
                "attribute vec4 aColor;" +
                "void main() {" +
                // the matrix must be included as a modifier of gl_Position
                // Note that the uMVPMatrix factor *must be first* in order
                // for the matrix multiplication product to be correct.
                "  gl_Position = uMVPMatrix * vPosition;" +
                "  vColor=aColor;" +
                "}"

    val fragmentShaderCode =
        "precision mediump float;" +
                "varying vec4 vColor;" +
                "void main() {" +
                "  gl_FragColor = vColor;" +
                "}"

    init {
        val buffer = ByteBuffer.allocateDirect(triangleCoords.size * 4)
        buffer.order(ByteOrder.nativeOrder())
        vertexBuffer = buffer.asFloatBuffer()
        vertexBuffer.put(triangleCoords)
        vertexBuffer.position(0)


        val byteBuffer = ByteBuffer.allocateDirect(color.size * 4)
        byteBuffer.order(ByteOrder.nativeOrder())
        colorBuffer = byteBuffer.asFloatBuffer()
        colorBuffer.put(color)
        colorBuffer.position(0)


        val vertexShader = MyRender.loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode)
        val fragmentShader = MyRender.loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode)

        mProgram = GLES20.glCreateProgram()

        //顶点着色器
        GLES20.glAttachShader(mProgram, vertexShader)
        //片段着色器
        GLES20.glAttachShader(mProgram, fragmentShader)
        GLES20.glLinkProgram(mProgram)
    }

    fun draw(mMVPMatrix: FloatArray) {

        GLES20.glUseProgram(mProgram)
//      位置好相关参数设置
        mMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix")
        GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mMVPMatrix, 0)
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition")
        // 启用通用顶点属性数组
        GLES20.glEnableVertexAttribArray(mPositionHandle)
        // 指定了渲染时索引值为 index 的顶点属性数组的数据格式和位置
        GLES20.glVertexAttribPointer(
            mPositionHandle, COORDS_PER_VERTEX,
            GLES20.GL_FLOAT, false,
            vertexStride, vertexBuffer
        )
//  获取片段着色器的vColor 成员句柄
//        mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor")
//        GLES20.glUniform4fv(mColorHandle, 1, color, 0)

        //获取片元着色器的vColor成员的句柄
        mColorHandle = GLES20.glGetAttribLocation(mProgram, "aColor");
        //设置绘制三角形的颜色
        GLES20.glEnableVertexAttribArray(mColorHandle)

        GLES20.glVertexAttribPointer(
            mColorHandle,
            4,
            GLES20.GL_FLOAT,
            false,
            0,
            colorBuffer
        )

//       画三角
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount)
//       禁用顶点数组
        GLES20.glDisableVertexAttribArray(mPositionHandle)
    }

}