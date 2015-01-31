package unittests

import org.scalatest.FunSuite
import vectormath.NamedVector
import neuralnetwork.NNOperators.sum
import scala.collection.mutable.Map

class NamedVectorTests
extends FunSuite
{
    test("NamedVector costructor") {
        val namedVector = new NamedVector()
    }
    
    test("Named vector constructor with Map( \"1\" -> 1 ) ") {
        val namedVector = new NamedVector( Map("1" -> 1) )
        expect(1.0d) {
            namedVector("1")
        }
    }
    
	test("Sum of NamedVector") {
	    val testNV = new NamedVector( Map("1"->1,"2"->2,"3"->3) )
	    expect(6.0d) {
	        sum(testNV)
	    }
	}
    
    test("Named vector add and get element") {
        val namedVector = new NamedVector()
        namedVector("1") = 5
        expect(5.0d) {
            namedVector("1")
        }
    }
    
    test("Named vector get invalid element") {
        val namedVector = new NamedVector()
        intercept[NoSuchElementException] {
            namedVector("1")
        }
    }
    
	test("Operator -= for NamedVector") {
	    val testNV = new NamedVector( Map("1"->1,"2"->2,"3"->3) )
	    testNV -= "1"
	    intercept[NoSuchElementException] {
	        testNV("1")
	    }
	}
	
	test("Operator += for NamedVector") {
	    val testNV = new NamedVector( Map("1"->1,"2"->2,"3"->3) )
	    testNV += (("9",5.0d))
	    expect(5.0d) {
	        testNV("9")
	    }
	}   
}