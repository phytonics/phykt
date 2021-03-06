package com.thepyprogrammer.linkt

import com.thepyprogrammer.ktlib.array.DoubleArray2D
import com.thepyprogrammer.ktlib.array.zeros

typealias VectorArray = Array<Vector>

fun vectorOf(vararg vals: Double) = Vector(*vals)
inline fun <reified T: Number> vectorOf(vararg vals: T) = Vector(*vals.map { it.toDouble() }.toDoubleArray())

fun columnVectorOf(vararg vals: Double) = vals.toTypedArray().toColumnVector()
inline fun <reified T: Number> columnVectorOf(vararg vals: T) = vals.map { it.toDouble() }.toColumnVector()
fun columnVectorOf(vec: Vector) = vec.toTypedArray().toColumnVector()

fun vectorArrayOf(arr: DoubleArray2D): VectorArray = arr.vectorize()

fun matrixOf(vararg rows: Vector) = Matrix(*rows)
fun matrixOf(rows: List<Vector>) = Matrix(*rows.toTypedArray())
fun matrixOf(array: DoubleArray2D) = array.toMatrix()

fun zeroVectorOf(size: Int) = vectorOf(*zeros(size))
fun zeroMatrixOf(m: Int = 2, n: Int = 2) = Matrix(*vectorArrayOf(zeros(m, n)))
fun zeroMatrixOf(size: Int) = matrixOf((0..size).map { zeroVectorOf(it) })

fun unitVectorOf(dim: Int) = vectorOf(*(0..dim).map { 0.0 }.toTypedArray().apply { this[dim] = 1.0 })
fun identityMatrixOf(size: Int) = zeroMatrixOf(size).apply { (0..size).forEach { this[it, it] = 1.0 } }

fun DoubleArray.toVector(): Vector = vectorOf(*this)
inline fun <reified T: Number> Array<T>.toVector(): Vector = map { it.toDouble() }.toDoubleArray().toVector()
inline fun <reified T: Number> Collection<T>.toVector(): Vector = toTypedArray().toVector()

fun DoubleArray.toColumnVector(): Matrix = toTypedArray().toColumnVector()
inline fun <reified T: Number> Array<T>.toColumnVector(): Matrix = matrixOf(*map { vectorOf(it) }.toTypedArray())
inline fun <reified T: Number> Collection<T>.toColumnVector(): Matrix = toTypedArray().toColumnVector()

fun DoubleArray2D.vectorize(): Array<Vector> = map { it.toVector() }.toTypedArray()
fun DoubleArray2D.toMatrix() = matrixOf(*vectorize())