package neuralnetworkconnections

import neuralnetwork.Perceptron

trait INeuronConnection 
{
	def setInput( perceptron : Perceptron ) : Unit
	def setOutput( perceptron : Perceptron ) : Unit
	def getName() : String
	def isValueReady() : Boolean
	
	def setInput( input : Double ) : Unit
	def getInput() : Double
	
	def clearInput() : Unit
	
	def setError( error : Double ) : Unit
	def getError() : Double
}