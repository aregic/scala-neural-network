package neuralnetwork.learningfunctions

import neuralnetwork.activationfunc.ActivationFunction
import neuralnetwork.WeightSet
import vectormath.NamedVector
//import scala.collection.mutable.Map
import neuralnetwork.NNOperators.sum

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
        val temp = propagatedError * derivativeOfActivationFunc( sum(inputVector) )
	    for ( weightTuple <- weightSet ) {
	        weightSet(weightTuple._1) -= weightTuple._2 * temp
	    }
	    
	    return 0.0d
	}
}