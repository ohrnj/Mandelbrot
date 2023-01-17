package icygames.com.mandelbrotfractal

import android.graphics.Color

class Palette {
    private val stp = 0x04
    private val rgb = (0xD8 - 0x60) / stp
    private val yellow = (0xFF - 0x90) / stp
    private val orange = (0x90 - 0x30) / stp
    private val violet = (0x60 - 0x00) / stp
    private var mPalette: IntArray? = null

    init {
        mPalette = IntArray(3*rgb + orange + yellow + violet + 1)
        var index = 0

        for (i in 0 until rgb)
            mPalette!![index + i] = Color.rgb(0, 0, 0x60 + (i * stp))

        index += rgb
        for (i in 0 until orange)
            mPalette!![index + i] = Color.rgb(0xFF,0x30 + (i * stp),0x00)

        index += orange
        for (i in 0 until yellow)
            mPalette!![index + i] = Color.rgb(0xFF,0x90 + (i * stp),0x00)

        index += yellow
        for (i in 0 until rgb)
            mPalette!![index + i] = Color.rgb(0, 0x60 + (i * stp), 0)

        index += rgb
        for (i in 0 until violet)
            mPalette!![index + i] = Color.rgb(0x60,0x00 + (i * stp),0xFF)

        index += 1
        mPalette!![index] = Color.rgb(0, 0, 0)
    }

    fun getPalette(): IntArray? {
        return mPalette
    }
}