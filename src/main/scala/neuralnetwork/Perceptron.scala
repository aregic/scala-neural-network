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
    
	private def genId() : String =
	{
	    id += 1
	    return "Perceptron" + id
	}
}

class Perceptron
(
    var activationFunc		: ActivationFunction	= new SigmoidActivationFunc(), 
	var learningFunc 		: IBackPropLearning		= new NoLearningFunc(), 
	var weightSet			: WeightSet				= new WeightSet(),
	var errorSumStrategy	: IErrorSumStrategy		= SumStrategy,
    val outputConnections	: Map[String, INeuronConnection] = Map(),
    val inputConnections	: Map[String, INeuronConnection] = Map(),
    var name				: String				= Perceptron.genId()
)
{
    var result 			: Double	= 0.0d
    var isValueReady	: Boolean	= false
    var lastValue		: scala.collection.immutable.Map[String,Double] = 
        scala.collection.immutable.Map()
    //var errorVector		: Map[INeuronConnection,Double]
    
	def apply( x : Map[String,Double] ) : Double =
	{
	    return activationFunc( sum(weightSet(x)) )
	}
	
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
	    learningFunc.learn( propagatedGradient, weightSet, lastValue )
	    
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
	    if ( inputConnections.isEmpty )
	        return false
	        
	    inputConnections.foreach( p => 
	        if ( ! p._2.isValueReady )
	            return false
	    )
	    return true
	}
	
	def isErrorReady() : Boolean = 
	{
	    outputConnections.foreach( p => 
	        if ( ! p._2.isErrorReady )
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
	
	def errorEvent() : Unit =
	{
	    if ( isErrorReady ) 
	    {
		    learningFunc.learn( getError(), weightSet, collectInput().toMap )
		    
		    inputConnections.foreach( p => 
		        p._2.setError( getError() )
		    )
	    }
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
}