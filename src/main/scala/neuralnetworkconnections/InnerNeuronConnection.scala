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
    val outputPerceptrons	: Map[String, Perceptron]	
    		= Map[String, Perceptron]()
)
extends INeuronConnection
{
    var value = 0.0d
    var isValueReady = false
    
	def setInput( perceptron : Perceptron ) : Unit =
		inputPerceptron = perceptron
			
	def addOutput( perceptron : Perceptron ) : Unit =
	{
	    if ( inputPerceptron == perceptron )
	        throw new CircleInNeuralNetwork("Input and output is the same in InnerNeuronConnection.")
	    outputPerceptrons += ( perceptron.name -> perceptron )
	}
	
	def getName() : String = name
	    
	def setInput( input : Double ) : Unit =
	{
	    value = input
	    isValueReady = true
	    
	    outputPerceptrons.foreach( p => 
	        p._2.inputEvent()
	    )
	}
	
	def getInput() : Double =
	    value
	    
	def clearInput() : Unit =
	    isValueReady = false
	    
    def setError( error : Double ) : Unit = 
    	inputPerceptron.setError( error )
}