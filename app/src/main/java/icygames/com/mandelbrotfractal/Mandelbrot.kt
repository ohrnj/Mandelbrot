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

        // Change these values or uncomment the ones already defined to zoom into the Mandelbrot set,
        // for example, set some of the values:

        // default value:
        zoom = 1.0; positionX = 0.0; positionY = 0.0; iter = 100
//          zoom = 2.0 ;  positionX = 0.5; ;  positionY = 0.5 ;  iter = 100
//         zoom = 10.0 ;  positionX = 0.9 ;  positionY = 0.9 ;  iter = 100
//         zoom = 30.0 ;  positionX = 0.97 ;  positionY = 0.97 ;  iter = 150
//         zoom = 90.0 ;  positionX = 1.002 ;  positionY = 0.989 ;  iter = 200
//         zoom = 80.0 ;  positionX = 1.09 ;  positionY = 0.9875 ;  iter = 200
//         zoom = 180.0 ;  positionX = 1.092 ;  positionY = 0.9944 ;  iter = 200
//         zoom = 300.0 ;  positionX = 1.092 ;  positionY = 0.9964 ;  iter = 200
//         zoom = 600.0 ;  positionX = 1.0936 ;  positionY = 0.9984 ;  iter = 200
//         zoom = 1200.0 ;  positionX = 1.0938 ;  positionY = 0.9992 ;  iter = 200
//         zoom = 2000.0 ;  positionX = 1.0945 ;  positionY = 0.99954 ;  iter = 200
//         zoom = 3000.0 ;  positionX = 1.0948 ;  positionY = 0.99968 ;  iter = 600
//         zoom = 5000.0 ;  positionX = 1.09505 ;  positionY = 0.99980 ;  iter = 1600
    }

    fun generate(mImage: IntArray) {

        var y = 0
        while (y < mHeight) {
            val cY = -1.0 + y * (2.0/mHeight) / zoom
            var x = 0
            while (x < mWidth) {
                val cX = -2.5 + x * (3.5/mWidth) / zoom

                val colorIndex = calculateMandelbrot(cX, cY) * (mPalette.size - 1) / iter
                mImage[y * mWidth + x] = mPalette[colorIndex]
                x++
            }
            y++
        }
    }

    fun calculateMandelbrot(cX: Double, cY: Double): Int {
        var zx = 0.0
        var zy = 0.0
        var i = 0
        while (i < iter && (zx * zx + zy * zy) < 4) {
            val zxtmp = zx * zx - zy * zy + cX + positionX
            zy = 2 * zx * zy + cY + positionY
            zx = zxtmp
            i++
        }
        return i
    }



    fun setDimensions(width: Int, height: Int) {
        mWidth = width
        mHeight = height
    }
}
