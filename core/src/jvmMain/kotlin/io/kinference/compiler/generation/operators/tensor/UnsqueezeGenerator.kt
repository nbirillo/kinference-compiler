package io.kinference.compiler.generation.operators.tensor

import io.kinference.compiler.generation.info.TensorInfo
import io.kinference.compiler.generation.operators.OperatorGenerationInfo
import io.kinference.compiler.generation.operators.common.TypeAndShapeAwareOperatorGenerator
import io.kinference.compiler.generation.utils.actualAxis
import io.kinference.ndarray.toIntArray
import io.kinference.operators.tensor.Unsqueeze
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class UnsqueezeGenerator(
    private val operator: Unsqueeze,
    info: OperatorGenerationInfo
) : TypeAndShapeAwareOperatorGenerator(operator, info) {

    private val axes: IntArray = operator.getAttribute<LongArray>("axes").toIntArray()

    override fun resultInfo(): Map<String, TensorInfo> {
        val input = inputInfo.first()
        val actualAxes = axes.map { input.shape.actualAxis(it) }.sorted()

        val newShape = input.shape.toMutableList()
        actualAxes.forEach { axis ->
            newShape.add(axis, 1)
        }
        return mapOf(
            operator.outputs.first() to TensorInfo(
                shape = newShape.toIntArray(),
                dataType = input.dataType
            )
        )
    }
}
