package neuralnetwork.activationfunc

import java.math._

class SigmoidActivationFunc
( 
	var middle 		: Double 	= 0.0d, 
	var angle		: Double	= 1.0d 
)	  
extends ActivationFunction
{
	def apply( x : Double ) : Double = 
	{
		return 				1 /
			   ( 1 + math.exp( middle - angle*x ) )
	}
	
	def getDerivative() : ActivationFunction =
	{
	    return new DerivativeOfSigmoid(this)
	}
}


class DerivativeOfSigmoid
(
	var originalFunction : SigmoidActivationFunc
)
extends ActivationFunction
{
    def apply( x : Double ) : Double =
    {
        val f = originalFunction
        return f(x) * (1-f(x))
    }
    
    def getDerivative() : ActivationFunction =
    {
        throw new RuntimeException("Sorry, the derivative of this function is not yet craeted.")
    }
}