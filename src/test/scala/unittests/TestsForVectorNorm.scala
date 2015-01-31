package unittests

import org.scalatest.FunSuite
import neuralnetwork.WeightSet
import vectormath.vectornorms.EuclideanNorm
import scala.collection.mutable.Map

class TestsForVectorNorm
extends FunSuite
{
    test("Euclidean norm") {
        val weightSet = new WeightSet( Map( "1" -> 3, "2" -> 4 ) )
        
        expect( 2.5d ) {
            EuclideanNorm( weightSet )
        }
    }
    
    test("Normalization by euclidean norm") {
        val weightSet = new WeightSet( Map( "1" -> 3, "2" -> 4 ) )
        
        expect( 3d/2.5d ) {
            EuclideanNorm.normalize( weightSet )
            weightSet("1")
        }
    }
}