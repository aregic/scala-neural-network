package neuralnetwork.learningfunctions

import neuralnetwork.activationfunc.ActivationFunction
import neuralnetwork.WeightSet

class GradientLearning 
(
	val activationFunction	: ActivationFunction,
	val learningRate		: Double				= 1.0d
)
extends IBackPropLearning
{
	def learn( propagatedGradient 	: Double,
			   weightSet			: WeightSet 
         ) : Double =
	{
	    return 1.0d
	}
}