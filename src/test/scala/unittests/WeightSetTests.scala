package unittests

import org.scalatest.FunSuite
import neuralnetwork.WeightSet
import scala.collection.mutable.Map

class WeightSetTests 
extends FunSuite
{
	test("WeigthSet initialization test") {
	    val weightSet = new WeightSet()
	    
	    intercept[NoSuchElementException] {
	        weightSet("0")
	    }
	}
	
	test("WeightSet default weight value test") {
	    val weightSet = new WeightSet()
	    var i = 0
	    
	    for ( i <- 1 to 10 ) {	        
		    intercept[NoSuchElementException] {
		        weightSet(i.toString())
		    }
	    }
	}
	
	test("Test constructor") {
	    val weightSet = new WeightSet( Map( "0" -> 2, "1" -> 2, "2" -> 2 ) )
	    
	    for ( i <- 0 to 2 ) {
		    expect( 2.0d ) {
		        weightSet(i.toString())
		    }
		}
	}
	
	test("Set weight") {
	    val weightSet = new WeightSet()
	    
	    weightSet("5") = 2.0d
	    
	    expect( 2.0d ) {
	        weightSet("5")
	    }
	}
	
	test("Test sum with invalid index") {
		val weightSet = new WeightSet( Map( "1" -> 2, "2" -> 2, "3" -> 2 ) )
	    
	    intercept[NoSuchElementException] {
	        weightSet.getSum( Map( "1" -> 1, "2" -> 1, "4" -> 1) )
	    }	    
	}
	
	test("Create sum") {
	    val weightSet = new WeightSet( Map( "1" -> 2, "2" -> 2, "3" -> 2 ) )
	    
	    expect( 6.0 ) {
	        weightSet.getSum( Map( "1" -> 1, "2" -> 1, "3" -> 1) )
	    }
	}
	
	test("Create sum 2") {
	    val weightSet = new WeightSet( Map( "1" -> 2, "2" -> 2, "3" -> 2 ) )
	    
	    expect( 8.0 ) {
	        weightSet.getSum( Map( "1" -> 1, "2" -> 2, "3" -> 1) )
	    }
	}
	
	test("Newtonian norm") {
	    val weightSet = new WeightSet( Map( "1" -> 3, "2" -> 4 ) )
	    
	    expect( 2.5d ) {
	        weightSet.getEucledianNorm()
	    }
	}
	
	test("Normed weight set") {
	    val weightSet = new WeightSet( Map( "1" -> 3, "2" -> 4 ) )
	    
	    expect( 3d/2.5d ) {
	        weightSet.normByEucledian()
	        weightSet("1")
	    }
	}
}
