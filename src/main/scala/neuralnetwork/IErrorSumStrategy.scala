package neuralnetwork

import neuralnetworkconnections.INeuronConnection
import scala.collection.mutable.Map

trait IErrorSumStrategy 
{
	def getSum( connections : Map[String,_<:INeuronConnection] ) : Double
}