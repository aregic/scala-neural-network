package unittests

import org.scalatest.FunSuite
import neuralnetwork.learningfunctions.NoLearningFunc
import neuralnetwork.WeightSet
import neuralnetwork.activationfunc.LinearActivationFunc
import vectormath.vectornorms.EuclideanNorm
import neuralnetwork.activationfunc.SigmoidActivationFunc
import neuralnetwork.learningfunctions.GradientLearning

class LearningFunctionTests 
extends FunSuite
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
}
