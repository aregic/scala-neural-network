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

class LearningFunctionTests 
extends FunSuite
with MockFactory
{
    test("NoLearningFunc constructor") {
        val noLearningFunc = new NoLearningFunc()
    }
    
    test("NoLearningFunc learn function") {
        val noLearningFunc = new NoLearningFunc()
        val weightSet = new WeightSet()
        val actFunc = new LinearActivationFunc()
        noLearningFunc.learn( 0.0d, weightSet )
    }
    
	test("BackPropagation learning - error decrease test") {
		val sigmoidActivationFunc = new SigmoidActivationFunc()
	    val gradBackPropLearning = new GradientLearning(sigmoidActivationFunc)
	    val weightSet = new WeightSet() 
		EuclideanNorm.normalize( weightSet )
		
		
	}
	
	test("Error beckpropagation - calling the learn function") {
	    val mLearningFunc = mock[IBackPropLearning]
	    val weightSet = new WeightSet()
	    val perceptron = new Perceptron( 
	            learningFunc = mLearningFunc, 
	            weightSet = weightSet
	    )
	    
	    (mLearningFunc.learn _).expects(1.0, weightSet)
	    
	    perceptron.setError(1.0d)
	}
	
	test("Error backpropagation to InnerConnection") {
	    val outerPerceptron = new Perceptron()
	    val innerConnection = mock[InnerNeuronConnection]
	    
	    (innerConnection.getName _).expects().returning("some name") anyNumberOfTimes()
	    outerPerceptron.addInput( innerConnection )
	    
        (innerConnection.setError _).expects(1.0d)
        
        outerPerceptron.setError(1.0d)
	}
	
	test("Error backpropagation through 1 level") {
	    val outerPerceptron = new Perceptron
	    val innerConnection = new InnerNeuronConnection
	    val mHiddenNeuron = mock[Perceptron]
	    
	    (mHiddenNeuron.addOutput _).expects( innerConnection )
	    NeuralNetworkBuilder.connect(mHiddenNeuron, outerPerceptron, innerConnection)
	    
	    (mHiddenNeuron.setError _).expects( 1.0d )
	    outerPerceptron.setError(1.0d)	    
	}
	
	test("Error backpropagation through 1 level") {
	    val outerPerceptron = new Perceptron
	    val innerConnection = new InnerNeuronConnection
	    val mHiddenNeuron = mock[Perceptron]
	    
	    (mHiddenNeuron.addOutput _).expects( innerConnection )
	    NeuralNetworkBuilder.connect(mHiddenNeuron, outerPerceptron, innerConnection)
	
	    // Just a reminder: test spliting the error between
	    // input connections!!!
	    //(mHiddenNeuron.setError _).expects( 1.0d )
	    outerPerceptron.setError(1.0d)	    
	}
	
}
