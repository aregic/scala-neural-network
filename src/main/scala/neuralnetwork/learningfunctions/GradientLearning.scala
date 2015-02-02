package neuralnetwork.learningfunctions

import neuralnetwork.activationfunc.ActivationFunction
import neuralnetwork.WeightSet
import vectormath.NamedVector

class GradientLearning 
(
	val activationFunction	: ActivationFunction,
	val learningRate		: Double				= 1.0d
)
extends IBackPropLearning
{
    val derivativeOfActivationFunc = activationFunction.getDerivative()
    
	def learn( propagatedError	 	: Double,
			   weightSet			: WeightSet,
			   inputVector			: Map[String,Double]
         ) : Double =
	{
	    return propagatedError * derivativeOfActivationFunc( weightSet*inputVector )
	}
}