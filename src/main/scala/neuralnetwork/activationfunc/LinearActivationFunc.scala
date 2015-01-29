package neuralnetwork.activationfunc

class LinearActivationFunc 
(
    val tangent	: Double = 1.0d
)
extends ActivationFunction 
{
	def apply( x : Double ) : Double = tangent * x
	
	def getDerivative() : ActivationFunction = 
	{
	    return new ConstantFunction( tangent )
	}
}


class ConstantFunction
(
    val value : Double = 0
)
extends ActivationFunction
{
    def apply( x : Double ) : Double = value
    
    def getDerivative() : ActivationFunction =
        throw new RuntimeException("Sorry, the derivative of this function is not yet created.")
}