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
        
    var outputPerceptron : Perceptron = new Perceptron()
    
	def setInput( perceptron : Perceptron ) : Unit =
		throw new RuntimeException("InputConnection cannot have input perceptron")
	
	def setOutput( perceptron : Perceptron ) : Unit =
	    outputPerceptron = perceptron
	
	def getName() : String = name
	
	def setInput( input : Double ) : Unit =
	{
	    value = input
	    isValueReady = true
	    
	    if ( autoPropagation )
	        outputPerceptron.inputEvent()
	}
    
    def getInput() : Double = value        
	    
	def clearInput() : Unit =
	    isValueReady = false
	    
    def setError( error : Double ) : Unit = {}
    
    //Error propagated to input connection is n.a.
    def getError() : Double = 0.0d
}