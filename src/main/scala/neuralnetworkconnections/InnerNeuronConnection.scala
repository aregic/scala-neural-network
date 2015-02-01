package neuralnetworkconnections

import neuralnetwork.Perceptron
import scala.collection.mutable.Map

object InnerNeuronConnection
{
    var id = 1
    
    def genId() : Int =
    {
        id += 1
        return id
    }
}

class InnerNeuronConnection
(
    var name				: String = "InnerConnection" + InnerNeuronConnection.genId(),
    var inputPerceptron 	: Perceptron = null,
    var outputPerceptron	: Perceptron = new Perceptron()
)
extends INeuronConnection
{
    var value = 0.0d
    var isValueReady = false
    var error = 0.0d
    
	def setInput( perceptron : Perceptron ) : Unit =
		inputPerceptron = perceptron
			
	def setOutput( perceptron : Perceptron ) : Unit =
	{
	    if ( inputPerceptron == perceptron )
	        throw new CircleInNeuralNetwork("Input and output is the same in InnerNeuronConnection.")
	    outputPerceptron = perceptron
	}
	
	def getName() : String = name
	    
	def setInput( input : Double ) : Unit =
	{
	    value = input
	    isValueReady = true
	    
	    outputPerceptron.inputEvent()
	}
	
	def getInput() : Double =
	    value
	    
	def clearInput() : Unit =
	    isValueReady = false
	    
    def setError( error : Double ) : Unit = 
    {
	    this.error = error
    	inputPerceptron.setError( error )
    }
	
	def getError() : Double =
	    error
}