package vectormath

import scala.collection.mutable.Map

class NamedVector
( var map : Map[String,Double] = Map() ) 
extends Map[String,Double]
{
	override def size : Int	= map.size
	
    override def apply( s : String ) : Double = 
	    map(s)
	    
	override def get( s : String ) : Option[Double] =
	    map.get(s)
	    
	override def foreach[U]( p : ((String,Double)) => U ) {
	    map.foreach(p)
	}
	    
	override def update( s : String, value : Double ) {
	    map(s) = value
	}
	
	override def iterator = map.iterator
	
	override def +=( kv : (String,Double) ) : this.type = {
		map+= kv
		return this
	}
	
	override def -=( key : String ) : this.type = {
		map-= key
		return this
	}
}