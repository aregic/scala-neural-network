package vectormath.vectornorms

import neuralnetwork.WeightSet

object EuclideanNorm 
extends VectorNorm 
{
	def apply( weightSet : WeightSet ) : Double = 
	{
        var sum = 0.0d
        weightSet.foreach( p => sum+= (p._2*p._2) )
        return math.sqrt(sum) / weightSet.size
	}
}