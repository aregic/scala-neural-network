package neuralnetwork.learningfunctions

import neuralnetwork.WeightSet
import neuralnetwork.activationfunc.ActivationFunction
import neuralnetwork.activationfunc.LinearActivationFunc

class NoLearningFunc
(
    val activationFunc	: ActivationFunction	= new LinearActivationFunc()
)
extends IBackPropLearning with Extracted
{
	def learn( propagatedError	 	: Double,
			   weightSet			: WeightSet,
			   inputVector			: Map[String,Double]
         ) : Double =
	    return 1.0d
}