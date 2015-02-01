package unittests

import org.scalatest.FunSuite
import neuralnetworkconnections.InputConnection
import neuralnetwork.Perceptron
import neuralnetwork.activationfunc.LinearActivationFunc
import neuralnetwork.learningfunctions.NoLearningFunc

class TestsForInputConnection
extends FunSuite
{
    test("getName of InputConnection") {
        val inputConnection = new InputConnection("Input1")
        
        expect("Input1") {
            inputConnection.getName()
        }
    }
    
    test("Add output neuron to InputConnection 1") {
        val inputConnection = new InputConnection("Input1")
        val perceptron = new Perceptron( 
                new LinearActivationFunc(),
                new NoLearningFunc() )
        
        inputConnection.setOutput( perceptron )
        
        assert( inputConnection.outputPerceptron == perceptron )
    }
    
    test("Add output neuron to InputConnection 2") {
        val inputConnection = new InputConnection("Input1")
        val perceptron = new Perceptron( 
                new LinearActivationFunc(),
                new NoLearningFunc() )
        
        assert( inputConnection.outputPerceptron != perceptron )
    }
    
    test("Add input neuron for inputconnection") {
        val inputConnection = new InputConnection("Input1")
        val perceptron = new Perceptron( 
                new LinearActivationFunc(),
                new NoLearningFunc() )
        
        intercept[RuntimeException] {
            inputConnection.setInput(perceptron)
        }
    }
}