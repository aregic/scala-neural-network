package neuralnetwork

import neuralnetwork.exceptions.WrongIndexException
import scala.collection.mutable.Map
import vectormath.NamedVector

class WeightSet 
	( map : Map[String,Double]  = Map() )
extends NamedVector ( map )
{
/*
    def apply( i : String ) : Option[Double] 		=
        return weightSet.get(i)
*/
    
    def apply( x : Map[String,Double]) : Map[String,Double] = 
    {
	    val ret = Map[String,Double]()
	    map.foreach( p => {
	    	ret(p._1) = ( p._2 * x(p._1) )
	    })
	    return ret
	}
	                

    // Sum_i(a_i*x_i)
    def getSum( x : Map[String,Double] ) : Double = 
    {
	    x.foreach( p => {
	        val tmp	= get( p._1 )
	        tmp match {
	            case Some(_) =>
	            case None => throw new NoSuchElementException("Index not found: " + p._1)
	        }
	    })
	    var sum = 0.0d
		map.foreach( p => sum+= ( p._2 * x(p._1) ) )
		return sum
	}
    
    // Normalize by newtonian norm ( = quadratic norm )
    def normByEucledian() : Unit =
    {
        val norm = getEucledianNorm()
        map.foreach( p => map(p._1) = p._2/norm )
    }
    
    def getEucledianNorm() : Double =
    {
        var sum = 0.0d
        map.foreach( p => sum+= (p._2*p._2) )
        return math.sqrt(sum) / map.size
    }
}