package neuralnetwork.sumstrategies

import neuralnetwork.IErrorSumStrategy
import neuralnetworkconnections.INeuronConnection
import scala.collection.mutable.Map

object SumStrategy
extends IErrorSumStrategy
{
	def getSum( connections : Map[String,_<:INeuronConnection] ) : Double =
	{
	    var sum = 0.0d
		connections.foreach( p => 
		    sum += p._2.getError() 
		)
		return sum
	}
}