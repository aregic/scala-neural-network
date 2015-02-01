package neuralnetwork

import neuralnetwork.activationfunc.ActivationFunction
import neuralnetwork.activationfunc.SigmoidActivationFunc
import neuralnetwork.learningfunctions.NoLearningFunc
import neuralnetwork.learningfunctions.IBackPropLearning
import neuralnetwork.NNOperators.sum
import scala.collection.mutable.Map
import neuralnetworkconnections.INeuronConnection
import neuralnetwork.sumstrategies.SumStrategy

object Perceptron
{
    var id = 0
}

class Perceptron
(
    var activationFunc		: ActivationFunction	= new SigmoidActivationFunc(), 
	var learningFunc 		: IBackPropLearning		= new NoLearningFunc(), 
	var weightSet			: WeightSet				= new WeightSet(),
	var errorSumStrategy	: IErrorSumStrategy		= SumStrategy,
    val outputConnections	: Map[String, INeuronConnection] = Map(),
    val inputConnections	: Map[String, INeuronConnection] = Map(),
    var name				: String				= ""
)
{
    var result 			: Double	= 0.0d
    var isValueReady	: Boolean	= false
    //var errorVector		: Map[INeuronConnection,Double]
    
    if ( name == "" )
        name = genId()
        
	def apply( x : Map[String,Double] ) : Double =
	    return activationFunc( sum(weightSet(x)) )
	
	def addInput( conn : INeuronConnection, weight : Double ) : Unit =
	{
	    weightSet( conn.getName ) = weight
	    inputConnections( conn.getName ) = conn
	}
	    
	def addInput( conn : INeuronConnection ) : Unit =
	    addInput( conn, 1.0d )
	    
    def addOutput( connection : INeuronConnection ) : Unit =
        outputConnections(connection.getName) = connection
	    
	def learn( propagatedGradient : Double ) : Unit =
	    learningFunc.learn( propagatedGradient, weightSet )
	    
    def isOutput( neuronConn : INeuronConnection ) : Boolean =
    {
	    outputConnections.foreach( p => 
	        if ( p._2 == neuronConn ) 
	            return true
	    )
	    return false
    }

	def isInputReady() : Boolean = 
	{
	    inputConnections.foreach( p => 
	        if ( ! p._2.isValueReady )
	            return false
	    )
	    return true
	}
	
	def inputEvent() : Unit = 
	{
		if ( isInputReady )
		{
		    outputConnections.foreach( p => 
		        p._2.setInput( this( collectInput() ) )
		    )
		}
	}
	
	def setError( error : Double ) : Unit =
	{
	    learningFunc.learn( error, weightSet )
	    
	    inputConnections.foreach( p => 
	        p._2.setError( error )
	    )
	    
	}
	
	def getError() : Double =
		errorSumStrategy.getSum(outputConnections)
	    
	
	private def collectInput() : Map[String,Double] =
	{
	    val inputMap = Map[String,Double]()
	    
	    inputConnections.foreach( p => 
	        inputMap(p._1) = p._2.getInput()
	    )
	    
	    return inputMap
	}
	
	private def genId() : String =
	{
	    Perceptron.id += 1
	    return "Perceptron" + Perceptron.id
	}
}