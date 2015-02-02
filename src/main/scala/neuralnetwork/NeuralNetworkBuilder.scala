package neuralnetwork

import neuralnetworkconnections.INeuronConnection
import neuralnetworkconnections.INeuronConnection

object NeuralNetworkBuilder 
{
    // perc1 -> conn -> perc2
	def connect( perc1 : Perceptron, perc2 : Perceptron, conn : INeuronConnection ) : Unit =
	{
	    perc1.addOutput(conn)
	    perc2.addInput(conn)
	    conn.setInput(perc1)
	    conn.setOutput(perc2)
	}
	
	// perceptron -> connection
	def connect( perc : Perceptron, conn : INeuronConnection ) : Unit =
	{
	    perc.addOutput(conn)
	    conn.setInput(perc)
	}
	
	// connection -> perceptron
	def connect( conn : INeuronConnection, perc : Perceptron ) : Unit =
	{
		conn.setOutput(perc)
	    perc.addInput(conn)
	}
}