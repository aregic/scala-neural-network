package unittests

import org.scalatest.FunSuite
import neuralnetwork.Perceptron
import neuralnetwork.activationfunc.LinearActivationFunc
import neuralnetwork.learningfunctions.NoLearningFunc
import neuralnetworkconnections.InnerNeuronConnection
import neuralnetworkconnections.CircleInNeuralNetwork

class TestsForNeuralNetworkConnection
extends FunSuite
{
    test("Test add input for InnerNeuronConnection") {
        val perceptron = new Perceptron( 
                new LinearActivationFunc(),
                new NoLearningFunc() )
        
        val neuronConnection = new InnerNeuronConnection("1", perceptron)
        
        expect(perceptron) {
            neuronConnection.inputPerceptron
        }
    }
    
    test("Add output for InnerNeuronConnection") {
        val perceptron = new Perceptron( 
                new LinearActivationFunc(),
                new NoLearningFunc() )
        
        val perceptron2 = new Perceptron( 
                new LinearActivationFunc(),
                new NoLearningFunc() )
        
        val neuronConnection = new InnerNeuronConnection("1", perceptron)
        
        neuronConnection.addOutput( perceptron2 )
        
        expect(1) {
            neuronConnection.outputPerceptrons.size
        }
    }
    
    test("Create a cicle in InnerNeuronConnection") {
        val perceptron = new Perceptron( 
                new LinearActivationFunc(),
                new NoLearningFunc() )
        
        val neuronConnection = new InnerNeuronConnection("1", perceptron)
        
        intercept[CircleInNeuralNetwork] {
        	neuronConnection.addOutput( perceptron )
        }
    }
    
    test("InputNeuron should have this connection as it's input") {
        val perceptron = new Perceptron( 
                new LinearActivationFunc(),
                new NoLearningFunc() )
        
        val neuronConnection = new InnerNeuronConnection("1", perceptron)
        
        expect(true) {
            perceptron == neuronConnection.inputPerceptron 
        }
    }
    
    test("Add connection to perceptron") {
        val perceptron = new Perceptron( 
                new LinearActivationFunc(),
                new NoLearningFunc() )
        
        val neuronConnection = new InnerNeuronConnection("1", perceptron)
        perceptron.addOutput( neuronConnection )
        
        expect(true) {
            perceptron.isOutput( neuronConnection ) 
        }
    }
    
    test("Don't add connection to perceptron") {
        val perceptron = new Perceptron( 
                new LinearActivationFunc(),
                new NoLearningFunc() )
        
        val neuronConnection = new InnerNeuronConnection("1", perceptron)
        //perceptron.addOutput( neuronConnection )
        
        expect(false) {
            perceptron.isOutput( neuronConnection ) 
        }
    }
    
    test("Get name of InnerNeuronConnection") {
        val perceptron = new Perceptron( 
                new LinearActivationFunc(),
                new NoLearningFunc() )
        
        val neuronConnection = new InnerNeuronConnection("1", perceptron)
        perceptron.addOutput( neuronConnection )
        
        expect("1") {
            neuronConnection.getName() 
        }
    }

    /*
    test("Test the connection between 2 (linear) perceptrons") {
    	val neuralNetwork = new Perceptron( 
                new LinearActivationFunc(),
                new NoLearningFunc() )
        val neuralNetwork2 = new Perceptron( 
                new LinearActivationFunc(),
                new NoLearningFunc() )
    	
    	val neuronConnection = new InnerNeuronConnection()
    }
    */
}
