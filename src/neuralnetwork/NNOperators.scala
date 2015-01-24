package neuralnetwork

import scala.collection.mutable.Map

object NNOperators 
{
	def sum( x : Map[String,Double] ) : Double = {
	    var sum = 0.0d
	    x.foreach( p => sum += p._2 )
	    return sum
	}
	
}