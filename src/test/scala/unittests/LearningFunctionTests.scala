package unittests

import org.scalatest.FunSuite
import neuralnetwork.learningfunctions.NoLearningFunc
import neuralnetwork.WeightSet
import neuralnetwork.activationfunc.LinearActivationFunc
import vectormath.vectornorms.EuclideanNorm
import neuralnetwork.activationfunc.SigmoidActivationFunc
import neuralnetwork.learningfunctions.GradientLearning
import org.scalamock.scalatest.MockFactory
import neuralnetwork.learningfunctions.NoLearningFunc
import neuralnetwork.Perceptron
import neuralnetwork.learningfunctions.IBackPropLearning
import neuralnetwork.WeightSet
import neuralnetworkconnections.InnerNeuronConnection
import neuralnetwork.NeuralNetworkBuilder
import neuralnetworkconnections.InnerNeuronConnection
import neuralnetworkconnections.InputConnection
import scala.collection.mutable.Map
import neuralnetwork.learningfunctions.GradientLearning
import neuralnetwork.activationfunc.SigmoidActivationFunc
import neuralnetworkconnections.InputConnection

class LearningFunctionTests 
extends FunSuite
with MockFactory
{
	ignore("BackPropagation learning - error decrease test") {
		val sigmoidActivationFunc = new SigmoidActivationFunc()
	    val gradBackPropLearning = new GradientLearning(sigmoidActivationFunc)
		val inputMap = Map( ("1"->1.0d), ("2"->2.0d) )
	    val weightSet = new WeightSet(inputMap) 
		
		val perceptron = new Perceptron( 
	        learningFunc = gradBackPropLearning,
	        activationFunc = sigmoidActivationFunc,
	        weightSet = weightSet )
		
		val inputConnection = new InnerNeuronConnection()
		val outputConnection = new InnerNeuronConnection()
		
		NeuralNetworkBuilder.connect(inputConnection, perceptron)
		NeuralNetworkBuilder.connect(perceptron, outputConnection)
	}
	
	test("Error beckpropagation - calling the learn function") {
	    val mLearningFunc = mock[IBackPropLearning]
	    val weightSet = new WeightSet()
	    val perceptron = new Perceptron( 
	            learningFunc = mLearningFunc, 
	            weightSet = weightSet,
	            name = "1"
	    )
	    val outputConn = new InnerNeuronConnection()
	    
	    perceptron.addOutput(outputConn)
	    outputConn.setInput(perceptron)
	    
	    (mLearningFunc.learn _).expects( 0.25, weightSet, scala.collection.immutable.Map[String,Double]() )
	    
	    outputConn.setError(1.0d)
	}
	
	test("Error backpropagation to InnerConnection") {
	    val outerPerceptron = new Perceptron()
	    val innerConnection = mock[InnerNeuronConnection]
	    val outputConnection = new InnerNeuronConnection()
	    
	    (innerConnection.getName _).expects().returning("some name") anyNumberOfTimes()
	    outerPerceptron.addInput( innerConnection )
	    
	    NeuralNetworkBuilder.connect(outerPerceptron, outputConnection)
	    
        (innerConnection.setError _).expects(0.25d)
        (innerConnection.getInput _).expects().returning(1.0d)
        
        outputConnection.setError(1.0d)
	}
	
	test("Error backpropagation through 1 level") {
	    val outerPerceptron = new Perceptron
	    val innerConnection = new InnerNeuronConnection
	    val outputConnection = new InnerNeuronConnection
	    val mHiddenNeuron = mock[Perceptron]
	    
	    (mHiddenNeuron.addOutput _).expects( innerConnection )
	    NeuralNetworkBuilder.connect(mHiddenNeuron, outerPerceptron, innerConnection)
	    NeuralNetworkBuilder.connect(outerPerceptron, outputConnection)
	    
	    (mHiddenNeuron.errorEvent _).expects()
	    outputConnection.setError(1.0d)	    
	}
	
	test("Error backpropagation through 1 level - test error addition in hidden layer") {
	    val outerPerceptron1 = new Perceptron
	    val outerPerceptron2 = new Perceptron
	    val outputConnection1= new InnerNeuronConnection
	    val outputConnection2 = new InnerNeuronConnection
	    val innerConnection1= new InnerNeuronConnection
	    val innerConnection2 = new InnerNeuronConnection
	    val hiddenNeuron = new Perceptron
	    
	    NeuralNetworkBuilder.connect(hiddenNeuron, outerPerceptron1, innerConnection1)
	    NeuralNetworkBuilder.connect(hiddenNeuron, outerPerceptron2, innerConnection2)
	    NeuralNetworkBuilder.connect(outerPerceptron1, outputConnection1)
	    NeuralNetworkBuilder.connect(outerPerceptron2, outputConnection2)
	
	    outputConnection1.setError(1.0d)
	    outputConnection2.setError(1.0d)
	    
	    expect(0.125d) {
	        hiddenNeuron.getError()
	    }
	}
	
	test("Test one backpropagation iteration") {
	    val sigmoidFunc = new SigmoidActivationFunc( middle = 1.0d )
	    val perceptron = new Perceptron( 
            learningFunc = new GradientLearning(sigmoidFunc,1.0d) 
        )
	    val outputConnection= new InnerNeuronConnection
	    val inputConnection = new InputConnection( name = "1" )
	    
	    NeuralNetworkBuilder.connect(perceptron, outputConnection)
	    NeuralNetworkBuilder.connect(inputConnection, perceptron)
	
	    inputConnection.setInput(1.0d)
	    outputConnection.setError(1.0d)
	    
	    expect(2.0d) {
	        perceptron(Map("1"->1))
	    }
	}
	
	
	
}
