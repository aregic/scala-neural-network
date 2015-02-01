package unittests

import org.scalatest.FunSuite
import neuralnetworkconnections.INeuronConnection
import neuralnetwork.sumstrategies.SumStrategy
import neuralnetworkconnections.InnerNeuronConnection
import scala.collection.mutable.Map

class TestsForErrorSumStrategies
extends FunSuite
{
	test("Simple sum strategy") {
	    val sumStrategy = SumStrategy
	    val innerConn = new InnerNeuronConnection()
	    val connections = Map( ("innerConn" -> innerConn) )
	    
	    expect(0.0d) {
	        sumStrategy.getSum( connections )
	    }
	}
}