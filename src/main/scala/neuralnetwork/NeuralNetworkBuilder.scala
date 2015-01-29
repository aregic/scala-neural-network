package neuralnetwork

import neuralnetworkconnections.INeuronConnection

object NeuralNetworkBuilder 
{
	def connect( perc1 : Perceptron, perc2 : Perceptron, conn : INeuronConnection ) : Unit =
	{
	    perc1.addOutput(conn)
	    perc2.addInput(conn)
	    conn.setInput(perc1)
	    conn.addOutput(perc2)
	}
}