package neuralnetwork.learningfunctions

import neuralnetwork.WeightSet
import neuralnetwork.activationfunc.ActivationFunction

trait IBackPropLearning
{
    // Returns with the gradient to propagate
	def learn( propagatedGradient 	: Double,
			   weightSet			: WeightSet 
         ) : Double
}