package unittests

import org.scalatest.FunSuite
import neuralnetwork.Perceptron
import neuralnetwork.activationfunc.LinearActivationFunc
import neuralnetwork.learningfunctions.NoLearningFunc
import neuralnetworkconnections.InnerNeuronConnection
import neuralnetworkconnections.CircleInNeuralNetwork
import org.scalamock.scalatest.MockFactory

class TestsForNeuralNetworkConnection
extends FunSuite
with MockFactory
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
        
        neuronConnection.setOutput( perceptron2 )
        
        expect(perceptron2) {
            neuronConnection.outputPerceptron
        }
    }
    
    test("Create a cicle in InnerNeuronConnection") {
        val perceptron = new Perceptron( 
                new LinearActivationFunc(),
                new NoLearningFunc() )
        
        val neuronConnection = new InnerNeuronConnection("1", perceptron)
        
        intercept[CircleInNeuralNetwork] {
        	neuronConnection.setOutput( perceptron )
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
    
    test("Test getError for InnerNeuronConnection") {
        val conn = new InnerNeuronConnection()
        val mPerceptron = mock[Perceptron]
        conn.setInput(mPerceptron)
        (mPerceptron.setError _).expects(1.0d)
        conn.setError(1.0d)
        expect(1.0d) {
            conn.getError()
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
