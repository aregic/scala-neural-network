package neuralnetwork.learningfunctions

import neuralnetwork.WeightSet
import neuralnetwork.activationfunc.ActivationFunction
import vectormath.NamedVector

trait IBackPropLearning
{
    // Returns with the gradient to propagate
	def learn( propagatedError	 	: Double,
	           //propagatedGradient	: Double,
			   weightSet			: WeightSet,
			   inputVector			: Map[String,Double]
         ) : Double
}