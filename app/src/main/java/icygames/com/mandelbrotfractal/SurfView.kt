package icygames.com.mandelbrotfractal

import android.content.Context
import android.graphics.Bitmap
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import java.util.concurrent.Executors

internal class SurfView(context: Context, attrs: AttributeSet?) : SurfaceView(context, attrs),
    SurfaceHolder.Callback {
    private var palette: Palette? = null
    private var mContext: Context? = null
    private var mandelbrot: Mandelbrot? = null
    private var mWidth = 0
    private var mHeight = 0
    private var mImage: IntArray? = null
    private var iterations = 100


    init {
        holder.addCallback(this)
        palette = Palette()
        mContext = context
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        if (mandelbrot == null) {
            mImage = IntArray(width * height)
            mWidth = width
            mHeight = height
        } else {
            if (mWidth != width || mHeight != height) {
                if (width * height > mImage!!.size) {
                    mImage = IntArray(width * height)
                }
            }
        }
        generate()
    }

    private fun generate() {
        mandelbrot = Mandelbrot(
            mContext!!,
            mWidth,
            mHeight,
            iterations,
            palette!!.getPalette()!!
        )
        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())

        executor.execute {
                mandelbrot!!.setDimensions(mWidth, mHeight)
                mandelbrot!!.generate(mImage!!)

            handler.post {
                createImage()
            }
        }
    }

    private fun createImage() {
                val can = holder.lockCanvas()
                val bitmap = Bitmap.createBitmap(mImage!!, mWidth, mHeight, Bitmap.Config.ARGB_8888)
                can.drawBitmap(bitmap, 0f, 0f, null)
                holder.unlockCanvasAndPost(can)
    }
}

