package vectormath.vectornorms

import neuralnetwork.WeightSet

trait VectorNorm 
{
	def apply( weightSet : WeightSet ) : Double
    
	def normalize( weightSet : WeightSet ) : Unit =
    {
        val norm = this( weightSet )
        weightSet.foreach( p => weightSet(p._1) = p._2/norm )        
    }
}