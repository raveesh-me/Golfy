package `in`.cookytech.golfy

val mHoles = Array<Hole>(18,{ Hole("Hole ${it+1} :", 0) })

data class Hole(
        val mHoleLabel:String,
        var mHoleStrokeCount: Int)