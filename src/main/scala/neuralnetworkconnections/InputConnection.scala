package neuralnetworkconnections

import neuralnetwork.Perceptron
import scala.collection.mutable.Map

class InputConnection
(
    var name 			: String,
    var autoPropagation : Boolean	= true
)
extends INeuronConnection
{
    var isValueReady 	: Boolean 	= false
    var value			: Double 	= 0.0d
        
    val outputPerceptrons : Map[String, Perceptron] = Map()
    
	def setInput( perceptron : Perceptron ) : Unit =
		throw new RuntimeException("InputConnection cannot have input perceptron")
	
	def addOutput( perceptron : Perceptron ) : Unit =
	    outputPerceptrons( perceptron.name ) = perceptron
	
	def getName() : String = name
	
	def setInput( input : Double ) : Unit =
	{
	    value = input
	    isValueReady = true
	    
	    if ( autoPropagation )
	        outputPerceptrons.foreach( p => 
	           p._2.inputEvent()
	        )
	}
    
    def getInput() : Double = value        
	    
	def clearInput() : Unit =
	    isValueReady = false
	    
    def setError( error : Double ) : Unit = {}
}