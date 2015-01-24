package neuralnetwork.activationfunc


trait ActivationFunction 
{
	def apply( x : Double ) : Double
	def getDerivative() : ActivationFunction
}