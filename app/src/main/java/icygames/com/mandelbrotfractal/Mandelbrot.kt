package icygames.com.mandelbrotfractal

import android.content.Context

class Mandelbrot(context: Context, width: Int, height: Int, iterations: Int, palette: IntArray) {
    private val mContext: Context
    private var mWidth: Int
    private var mHeight: Int
    private var iter: Int
    private val mPalette: IntArray
    private var zoom = 1.0
    private var positionX = 0.0
    private var positionY = 0.0

    init {
        mContext = context
        mWidth = width
        mHeight = height
        iter = iterations
        mPalette = palette

        // Change those values to zoom in or zoom out of Mandelbrot set,
        // for example, set some of the values:   [or just uncomment if you are too lazy :-) ]

        // default value:
        zoom = 1.0 ;  positionX = 0.0 ;  positionY = 0.0 ;  iter = 100

//         zoom = 0.6 ;  positionX = 0.4; ;  positionY = 0.4 ;  iter = 100
//         zoom = 0.1 ;  positionX = 0.9 ;  positionY = 0.9 ;  iter = 100
//         zoom = 0.03 ;  positionX = 0.97 ;  positionY = 0.97 ;  iter = 150
//         zoom = 0.009 ;  positionX = 1.006 ;  positionY = 0.991 ;  iter = 200
//         zoom = 0.008 ;  positionX = 1.09 ;  positionY = 0.992 ;  iter = 200
//         zoom = 0.005 ;  positionX = 1.09 ;  positionY = 0.995 ;  iter = 200
//         zoom = 0.002 ;  positionX = 1.094 ;  positionY = 0.998 ;  iter = 200
//         zoom = 0.0006 ;  positionX = 1.098 ;  positionY = 0.9994 ;  iter = 300
//         zoom = 0.0001 ;  positionX = 1.0987 ;  positionY = 0.9999 ;  iter = 800

    }

    fun generate(bitmap: IntArray) {
        val xS = (MAXX - MINX) / mWidth
        val yS = (MAXY - MINY) / mHeight

        var y = 0
        while (y < mHeight) {
            val cY = MINY + y * yS * zoom
            var x = 0
            while (x < mWidth) {
                val cX = MINX + x * xS * zoom
                var zx = 0.0
                var zy = 0.0
                var i = 0
                while (i < iter && (zx*zx + zy*zy) < END) {
                    val zxtmp = zx*zx - zy*zy + cX + positionX
                    zy = START * zx * zy + cY + positionY
                    zx = zxtmp
                    i++
                }
                var colorPalette = i * (mPalette.size - 1) / iter
                if (colorPalette < 0) colorPalette = 0

                bitmap[y * mWidth + x] = mPalette[colorPalette]
                x++
            }
            y++
        }
    }

    fun setDimensions(width: Int, height: Int) {
        mWidth = width
        mHeight = height
    }

    companion object {
        private const val MINX = -2.5
        private const val MAXX = 1.0
        private const val MINY = -1.0
        private const val MAXY = 1.0
        private const val START = 2.0
        private const val END = START * START
    }
}
