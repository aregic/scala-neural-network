package unittests

import org.scalatest.FunSuite
import neuralnetwork.activationfunc.SigmoidActivationFunc
import neuralnetwork.activationfunc.LinearActivationFunc

class ActivationFuncTests
extends FunSuite
{
	test("Test SigmoidActivationFunc") {
		var sigmoidActFunc = new SigmoidActivationFunc();
		
		expect (0.5d) {
		    sigmoidActFunc.apply( 0 )
		}
	}
	
	test("Test SigmoidActivationFunc with non-default middle point") {
	    var sigmoidActFunc = new SigmoidActivationFunc(2);
	    
	    expect(0.5d) {
	        sigmoidActFunc.apply( 2 )
	    }
	}
	
	test("SigmoidActivationFunc with different angle") {
	    var sigmoidActFunc = new SigmoidActivationFunc(2, 2);
	    
	    assert ( math.abs(sigmoidActFunc.apply( 2 ) - 0.5) < 0.0001 )
	}
	
	test("SigmoidActivationFunc with different angle - 2") {
	    var sigmoidActFunc = new SigmoidActivationFunc(2, 2);
	    
	    assert ( sigmoidActFunc.apply( 3 ) > 0.5 )
	}
	
	test("Derivative of linear function") {
	    var linearFunction = new LinearActivationFunc(5)
	    
	    expect(5) {
	        linearFunction.getDerivative()(0)
	    }
	}
	
	test("Linear activation function") {
	    var linearActFunc = new LinearActivationFunc()
	    
	    expect(5) {
	        linearActFunc(5)
	    }
	}
	
	test("Derivative of sigmoid function") {
	    var sigmoidActFunc = new SigmoidActivationFunc()
	    
	    expect(1d/4) {
	        sigmoidActFunc.getDerivative()(0)
	    }
	}
}
