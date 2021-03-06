package unittests

import scala.collection.mutable.Map
import org.scalamock.scalatest.MockFactory
import org.scalatest.FunSuite
import neuralnetwork.NeuralNetworkBuilder
import neuralnetwork.Perceptron
import neuralnetwork.activationfunc.LinearActivationFunc
import neuralnetwork.learningfunctions.NoLearningFunc
import neuralnetworkconnections.InnerNeuronConnection
import neuralnetworkconnections.InputConnection
import neuralnetworkconnections.INeuronConnection
import NeuralNetworkBuilder._
import scala.actors.InputChannel

class NeuralNetworkTests
extends FunSuite
with	MockFactory
{
    test("Test name generation for perceptron") {
        val perceptron = new Perceptron()
        
        assert( perceptron.name.startsWith("Perceptron") )
    }
    
    test("Test outpot of linear neural network with non-existing input index") {
        val perceptron = new Perceptron( 
                new LinearActivationFunc(),
                new NoLearningFunc() )
        
        expect(0.0d) {
            perceptron( Map("1" -> 1.0) )
        }
    }
    
    test("Test outpot of linear neural network with existing input index") {
        val perceptron = new Perceptron( 
                new LinearActivationFunc(),
                new NoLearningFunc() )
        val inputConn = new InputConnection("1")
        
        perceptron.addInput( inputConn )
        
        expect(1.0d) {
            perceptron( Map("1" -> 1.0) )
        }
    }
    
    test("Test outpot of linear neural network with input that has 2.0d weight") {
        val perceptron = new Perceptron( 
                new LinearActivationFunc(),
                new NoLearningFunc() )
        val inputConn = new InputConnection("1")
        
        perceptron.addInput( inputConn, 2.0d )
        
        expect(2.0d) {
            perceptron( Map("1" -> 1.0) )
        }
    }    
    
    test("Test outpot of linear neural network with multiple inputs") {
        val perceptron = new Perceptron( 
                new LinearActivationFunc(),
                new NoLearningFunc() )
        val inputConn1 = new InputConnection("1")
        val inputConn2 = new InputConnection("5")
        val inputConn3 = new InputConnection("6")
        
        perceptron.addInput(inputConn1, 2.0d)
        perceptron.addInput(inputConn2, 1.5d)
        perceptron.addInput(inputConn3, 1.0d)
        
        expect(8.0d) {
            perceptron( Map( "1" -> 1.0, "5" -> 2.0, "6" -> 3.0 ) )
        }
    }
    
    test("Test outpot of linear neural network with multiple inputs including an invalid one") {
        val perceptron = new Perceptron( 
                new LinearActivationFunc(),
                new NoLearningFunc() )
        
        val inputConn1 = new InputConnection("1")
        val inputConn2 = new InputConnection("5")
        val inputConn3 = new InputConnection("6")
        
        perceptron.addInput(inputConn1, 2.0d)
        perceptron.addInput(inputConn2, 1.5d)
        perceptron.addInput(inputConn3, 1.0d)
        
        expect(8.0d) {
            // Note that it should ignore the "7" input
            perceptron( Map( "1" -> 1.0, "5" -> 2.0, "6" -> 3.0, "7" -> 2.0 ) )
        }
    }
    
    test("Learning on linear neural network (we don't care about the output right now)") {
    	val perceptron = new Perceptron( 
                new LinearActivationFunc(),
                new NoLearningFunc() )
        
        perceptron.learn(1.0d)
    }
    
    
    test("isInputReady") {
        val neuralNetwork = new Perceptron( 
                new LinearActivationFunc(),
                new NoLearningFunc() )
        var inputConn = new InputConnection("1")
        
        neuralNetwork.addInput(inputConn)
        inputConn.setInput(1)
        
        expect(true) {
            neuralNetwork.isInputReady
        }        
    }
    
    test("propagate value") {
        val mPerc = mock[Perceptron]
        var inputConn = new InputConnection("1")        
        inputConn.setOutput( mPerc )
        
        (mPerc.inputEvent _).expects()
        
        inputConn.setInput(5.0d)
    }
    
    test("propagate value throught 2 levels - test 1") {
        val lvl1Perc = new Perceptron()
        val mPerc = mock[Perceptron]
        var inputConn = new InputConnection("1")  
        val innerConn = new InnerNeuronConnection("2", lvl1Perc)

        inputConn.setOutput( lvl1Perc )
        lvl1Perc.addInput(inputConn)
        lvl1Perc.addOutput(innerConn)
        innerConn.setOutput(mPerc)
        
        
        (mPerc.inputEvent _).expects()
        
        inputConn.setInput(5.0d)
    }
    
    test("Connect two neurons") {
        val nnBuilder = NeuralNetworkBuilder
        
        val lvl1Perc = new Perceptron()
        val mPerc = new Perceptron()
        val innerConn = new InnerNeuronConnection("2", lvl1Perc)
        
        nnBuilder.connect(lvl1Perc, mPerc, innerConn)
        
        assert( lvl1Perc.isOutput(innerConn) )
        
    }
    
    test("Connect two neurons 2") {
        val nnBuilder = NeuralNetworkBuilder
        
        val lvl1Perc = new Perceptron()
        val mPerc = new Perceptron()
        val innerConn = new InnerNeuronConnection("2", lvl1Perc)
        
        nnBuilder.connect(lvl1Perc, mPerc, innerConn)
        
        assert( lvl1Perc.isOutput(innerConn) )
        
        var found = false;
        mPerc.inputConnections.foreach( p =>
            if ( p._2 == innerConn )
                found = true
        )
        
        assert( found )
    }
    
    test("Connect two neurons 3") {
        val nnBuilder = NeuralNetworkBuilder
        
        val lvl1Perc = new Perceptron()
        val mPerc = new Perceptron()
        val innerConn = new InnerNeuronConnection("2", lvl1Perc)
        
        nnBuilder.connect(lvl1Perc, mPerc, innerConn)
        
        assert( lvl1Perc.isOutput(innerConn) )
        
        expect( lvl1Perc ) {
            innerConn.inputPerceptron
        }
    }
    
    test("Connect two neurons 4") {
        val nnBuilder = NeuralNetworkBuilder
        
        val lvl1Perc = new Perceptron()
        val mPerc = new Perceptron()
        var inputConn = new InputConnection("1")  
        val innerConn = new InnerNeuronConnection("2", lvl1Perc)
        
        nnBuilder.connect(lvl1Perc, mPerc, innerConn)
        
        assert( lvl1Perc.isOutput(innerConn) )
        
        var found = false;
        if ( innerConn.outputPerceptron == mPerc )
                found = true
        
        assert( found )
    }
    
    test("Connect neuron to neuron connection") {
        val mPerceptron = mock[Perceptron]
        val outputConnection = new InnerNeuronConnection()
        
        (mPerceptron.addOutput _).expects(outputConnection)
        NeuralNetworkBuilder.connect(mPerceptron, outputConnection)
    }
    
    test("Connect neuron to neuron connection 2") {
        val perceptron = new Perceptron()
        val mOutputConnection = mock[InnerNeuronConnection]
        
        (mOutputConnection.setInput(_:Perceptron)).expects(perceptron)
        (mOutputConnection.getName _).expects().returning("1")
        NeuralNetworkBuilder.connect(perceptron, mOutputConnection)
    }
    
    test("Connect neuron connection to neuron") {
        val mInputConnection = mock[InnerNeuronConnection]
        val perceptron = new Perceptron()
        
        (mInputConnection.setOutput _).expects(perceptron)
        (mInputConnection.getName _).expects().returning("1") anyNumberOfTimes()
        NeuralNetworkBuilder.connect(mInputConnection, perceptron)
    }
    
    test("Connect neuron connection to neuron 2") {
        val inputConnection = new InnerNeuronConnection()
        val mPerceptron = mock[Perceptron]
        
        (mPerceptron.addInput(_:INeuronConnection)).expects(inputConnection)
        NeuralNetworkBuilder.connect(inputConnection, mPerceptron)
    }
    
    
    test("propagate value throught 2 levels - test 2") {
        val lvl1Perc = new Perceptron()
        val mOutputPerc = mock[Perceptron]
        val innerConn = new InnerNeuronConnection()
        val inputConn = new InputConnection("1")  
        val outputConn = new InputConnection("2")

        NeuralNetworkBuilder.connect(inputConn, lvl1Perc)
        
        (mOutputPerc.addInput(_:INeuronConnection)).expects(innerConn)
        NeuralNetworkBuilder.connect(lvl1Perc, mOutputPerc, innerConn)
        
        (mOutputPerc.inputEvent _).expects()
        
        inputConn.setInput(5.0d)
    }
    
    /*
    test("Test neural network forward propagation") {
        val neuralNetwork = new Perceptron( 
                new LinearActivationFunc(),
                new NoLearningFunc() )
        val neuralNetwork2 = new Perceptron( 
                new LinearActivationFunc(),
                new NoLearningFunc() )
        
    }
    */
}
