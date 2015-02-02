package unittests

import org.scalatest.FunSuite
import neuralnetwork.Perceptron
import neuralnetworkconnections.OutputConnection
import org.scalamock.scalatest.MockFactory
import neuralnetworkconnections.InnerNeuronConnection

class TestsForOutputConnection
extends FunSuite
with MockFactory
{
	test("Test perceptron->outputConnection error backpropagation") 
	{
	    val mPerceptron = mock[Perceptron]
	    val outputConnection = new InnerNeuronConnection()
	    outputConnection.setInput(mPerceptron)
	    
	    (mPerceptron.errorEvent _).expects()
	    
	    outputConnection.setError(1.0d)
	}
}