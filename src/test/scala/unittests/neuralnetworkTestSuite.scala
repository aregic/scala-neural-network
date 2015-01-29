package unittests

import org.scalatest.FunSuite
import org.scalamock.scalatest.MockFactory
import neuralnetwork.activationfunc.SigmoidActivationFunc
import neuralnetwork.WeightSet
import neuralnetwork.WeightSet
import scala.collection.mutable.Map
import neuralnetwork.WeightSet
import neuralnetwork.learningfunctions.NoLearningFunc
import neuralnetwork.Perceptron
import neuralnetwork.activationfunc.LinearActivationFunc
import neuralnetwork.exceptions.WrongIndexException
import vectormath.NamedVector
import vectormath.NamedVector
import vectormath.NamedVector
import neuralnetwork.NNOperators.sum
import neuralnetwork.activationfunc.LinearActivationFunc
import neuralnetwork.activationfunc.LinearActivationFunc
import neuralnetwork.activationfunc.SigmoidActivationFunc
import neuralnetwork.activationfunc.SigmoidActivationFunc
import neuralnetwork.learningfunctions.GradientLearning
import neuralnetwork.activationfunc.ActivationFunction
import neuralnetwork.activationfunc.LinearActivationFunc
import vectormath.vectornorms.EuclideanNorm
import neuralnetworkconnections.InnerNeuronConnection
import neuralnetworkconnections.CircleInNeuralNetwork
import neuralnetworkconnections.InputConnection
import neuralnetworkconnections.InnerNeuronConnection
import neuralnetwork.NeuralNetworkBuilder
import neuralnetworkconnections.InputConnection


class neuralnetworkTestSuite extends FunSuite 
{
	test("Sum of NamedVector") {
	    val testNV = new NamedVector( Map("1"->1,"2"->2,"3"->3) )
	    expect(6.0d) {
	        sum(testNV)
	    }
	}
}


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


class LearningFunctionTests 
extends FunSuite
{
    test("NoLearningFunc constructor") {
        val noLearningFunc = new NoLearningFunc()
    }
    
    test("NoLearningFunc learn function") {
        val noLearningFunc = new NoLearningFunc()
        val weightSet = new WeightSet()
        val actFunc = new LinearActivationFunc()
        noLearningFunc.learn( 0.0d, weightSet )
    }
    
	test("BackPropagation learning - error decrease test") {
		val sigmoidActivationFunc = new SigmoidActivationFunc()
	    val gradBackPropLearning = new GradientLearning(sigmoidActivationFunc)
	    val weightSet = new WeightSet() 
		EuclideanNorm.normalize( weightSet )
		
		
	}
}


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
	    
	    assert ( math.abs(sigmoidActFunc.apply( 2 ) - 0.88079707797) < 0.0001 )
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


class NeuralNetworkTests
extends FunSuite
with	MockFactory
{
    test("Test name generation for perceptron") {
        val perceptron = new Perceptron()
        
        assert( perceptron.name.startsWith("Perceptron") )
    }
    
    test("Test outpot of linear neural network with non-existing input index") {
        val perceptron = new Perceptron( 
                new LinearActivationFunc(),
                new NoLearningFunc() )
        
        expect(0.0d) {
            perceptron( Map("1" -> 1.0) )
        }
    }
    
    test("Test outpot of linear neural network with existing input index") {
        val perceptron = new Perceptron( 
                new LinearActivationFunc(),
                new NoLearningFunc() )
        val inputConn = new InputConnection("1")
        
        perceptron.addInput( inputConn )
        
        expect(1.0d) {
            perceptron( Map("1" -> 1.0) )
        }
    }
    
    test("Test outpot of linear neural network with input that has 2.0d weight") {
        val perceptron = new Perceptron( 
                new LinearActivationFunc(),
                new NoLearningFunc() )
        val inputConn = new InputConnection("1")
        
        perceptron.addInput( inputConn, 2.0d )
        
        expect(2.0d) {
            perceptron( Map("1" -> 1.0) )
        }
    }    
    
    test("Test outpot of linear neural network with multiple inputs") {
        val perceptron = new Perceptron( 
                new LinearActivationFunc(),
                new NoLearningFunc() )
        val inputConn1 = new InputConnection("1")
        val inputConn2 = new InputConnection("5")
        val inputConn3 = new InputConnection("6")
        
        perceptron.addInput(inputConn1, 2.0d)
        perceptron.addInput(inputConn2, 1.5d)
        perceptron.addInput(inputConn3, 1.0d)
        
        expect(8.0d) {
            perceptron( Map( "1" -> 1.0, "5" -> 2.0, "6" -> 3.0 ) )
        }
    }
    
    test("Test outpot of linear neural network with multiple inputs including an invalid one") {
        val perceptron = new Perceptron( 
                new LinearActivationFunc(),
                new NoLearningFunc() )
        
        val inputConn1 = new InputConnection("1")
        val inputConn2 = new InputConnection("5")
        val inputConn3 = new InputConnection("6")
        
        perceptron.addInput(inputConn1, 2.0d)
        perceptron.addInput(inputConn2, 1.5d)
        perceptron.addInput(inputConn3, 1.0d)
        
        expect(8.0d) {
            // Note that it should ignore the "7" input
            perceptron( Map( "1" -> 1.0, "5" -> 2.0, "6" -> 3.0, "7" -> 2.0 ) )
        }
    }
    
    test("Learning on linear neural network (we don't care about the output right now)") {
    	val perceptron = new Perceptron( 
                new LinearActivationFunc(),
                new NoLearningFunc() )
        
        perceptron.learn(1.0d)
    }
    
    
    test("isInputReady") {
        val neuralNetwork = new Perceptron( 
                new LinearActivationFunc(),
                new NoLearningFunc() )
        var inputConn = new InputConnection("1")
        
        inputConn.setInput(1)
        
        expect(true) {
            neuralNetwork.isInputReady
        }        
    }
    
    test("propagate value") {
        val mPerc = mock[Perceptron]
        var inputConn = new InputConnection("1")        
        inputConn.addOutput( mPerc )
        
        (mPerc.inputEvent _).expects()
        
        inputConn.setInput(5.0d)
    }
    
    test("propagate value throught 2 levels - test 1") {
        val lvl1Perc = new Perceptron()
        val mPerc = mock[Perceptron]
        var inputConn = new InputConnection("1")  
        val innerConn = new InnerNeuronConnection("2", lvl1Perc)

        inputConn.addOutput( lvl1Perc )
        lvl1Perc.addOutput(innerConn)
        innerConn.addOutput(mPerc)
        
        
        (mPerc.inputEvent _).expects()
        
        inputConn.setInput(5.0d)
    }
    
    test("Connect two neurons") {
        val nnBuilder = NeuralNetworkBuilder
        
        val lvl1Perc = new Perceptron()
        val mPerc = new Perceptron()
        val innerConn = new InnerNeuronConnection("2", lvl1Perc)
        
        nnBuilder.connect(lvl1Perc, mPerc, innerConn)
        
        assert( lvl1Perc.isOutput(innerConn) )
        
    }
    
    test("Connect two neurons 2") {
        val nnBuilder = NeuralNetworkBuilder
        
        val lvl1Perc = new Perceptron()
        val mPerc = new Perceptron()
        val innerConn = new InnerNeuronConnection("2", lvl1Perc)
        
        nnBuilder.connect(lvl1Perc, mPerc, innerConn)
        
        assert( lvl1Perc.isOutput(innerConn) )
        
        var found = false;
        mPerc.inputConnections.foreach( p =>
            if ( p._2 == innerConn )
                found = true
        )
        
        assert( found )
    }
    
    test("Connect two neurons 3") {
        val nnBuilder = NeuralNetworkBuilder
        
        val lvl1Perc = new Perceptron()
        val mPerc = new Perceptron()
        val innerConn = new InnerNeuronConnection("2", lvl1Perc)
        
        nnBuilder.connect(lvl1Perc, mPerc, innerConn)
        
        assert( lvl1Perc.isOutput(innerConn) )
        
        expect( lvl1Perc ) {
            innerConn.inputPerceptron
        }
    }
    
    test("Connect two neurons 4") {
        val nnBuilder = NeuralNetworkBuilder
        
        val lvl1Perc = new Perceptron()
        val mPerc = new Perceptron()
        var inputConn = new InputConnection("1")  
        val innerConn = new InnerNeuronConnection("2", lvl1Perc)
        
        nnBuilder.connect(lvl1Perc, mPerc, innerConn)
        
        assert( lvl1Perc.isOutput(innerConn) )
        
        var found = false;
        innerConn.outputPerceptrons.foreach( p =>
            if ( p._2 == mPerc )
                found = true
        )
        
        assert( found )
    }
    
    /*
    test("propagate value throught 2 levels - test 1") {
        val lvl1Perc = new Perceptron()
        val innerConn = mock[InnerNeuronConnection(lvl1Perc)]
        var inputConn = new InputConnection("1")  

        inputConn.addOutput("conn1", lvl1Perc)
        
        
        (inputConn.setInput(_:Double)).expects(1.0d)
        
        inputConn.setInput(5.0d)
    }
    */
    
    /*
    test("Test neural network forward propagation") {
        val neuralNetwork = new Perceptron( 
                new LinearActivationFunc(),
                new NoLearningFunc() )
        val neuralNetwork2 = new Perceptron( 
                new LinearActivationFunc(),
                new NoLearningFunc() )
        
    }
    */
}


class TestsForNeuralNetworkConnection
extends FunSuite
{
    test("Test add input for InnerNeuronConnection") {
        val perceptron = new Perceptron( 
                new LinearActivationFunc(),
                new NoLearningFunc() )
        
        val neuronConnection = new InnerNeuronConnection("1", perceptron)
        
        expect(perceptron) {
            neuronConnection.inputPerceptron
        }
    }
    
    test("Add output for InnerNeuronConnection") {
        val perceptron = new Perceptron( 
                new LinearActivationFunc(),
                new NoLearningFunc() )
        
        val perceptron2 = new Perceptron( 
                new LinearActivationFunc(),
                new NoLearningFunc() )
        
        val neuronConnection = new InnerNeuronConnection("1", perceptron)
        
        neuronConnection.addOutput( perceptron2 )
        
        expect(1) {
            neuronConnection.outputPerceptrons.size
        }
    }
    
    test("Create a cicle in InnerNeuronConnection") {
        val perceptron = new Perceptron( 
                new LinearActivationFunc(),
                new NoLearningFunc() )
        
        val neuronConnection = new InnerNeuronConnection("1", perceptron)
        
        intercept[CircleInNeuralNetwork] {
        	neuronConnection.addOutput( perceptron )
        }
    }
    
    test("InputNeuron should have this connection as it's input") {
        val perceptron = new Perceptron( 
                new LinearActivationFunc(),
                new NoLearningFunc() )
        
        val neuronConnection = new InnerNeuronConnection("1", perceptron)
        
        expect(true) {
            perceptron == neuronConnection.inputPerceptron 
        }
    }
    
    test("Add connection to perceptron") {
        val perceptron = new Perceptron( 
                new LinearActivationFunc(),
                new NoLearningFunc() )
        
        val neuronConnection = new InnerNeuronConnection("1", perceptron)
        perceptron.addOutput( neuronConnection )
        
        expect(true) {
            perceptron.isOutput( neuronConnection ) 
        }
    }
    
    test("Don't add connection to perceptron") {
        val perceptron = new Perceptron( 
                new LinearActivationFunc(),
                new NoLearningFunc() )
        
        val neuronConnection = new InnerNeuronConnection("1", perceptron)
        //perceptron.addOutput( neuronConnection )
        
        expect(false) {
            perceptron.isOutput( neuronConnection ) 
        }
    }
    
    test("Get name of InnerNeuronConnection") {
        val perceptron = new Perceptron( 
                new LinearActivationFunc(),
                new NoLearningFunc() )
        
        val neuronConnection = new InnerNeuronConnection("1", perceptron)
        perceptron.addOutput( neuronConnection )
        
        expect("1") {
            neuronConnection.getName() 
        }
    }

    /*
    test("Test the connection between 2 (linear) perceptrons") {
    	val neuralNetwork = new Perceptron( 
                new LinearActivationFunc(),
                new NoLearningFunc() )
        val neuralNetwork2 = new Perceptron( 
                new LinearActivationFunc(),
                new NoLearningFunc() )
    	
    	val neuronConnection = new InnerNeuronConnection()
    }
    */
}


class TestsForInputConnection
extends FunSuite
{
    test("getName of InputConnection") {
        val inputConnection = new InputConnection("Input1")
        
        expect("Input1") {
            inputConnection.getName()
        }
    }
    
    test("Add output neuron to InputConnection 1") {
        val inputConnection = new InputConnection("Input1")
        val perceptron = new Perceptron( 
                new LinearActivationFunc(),
                new NoLearningFunc() )
        
        inputConnection.addOutput( perceptron )
        
        var found = false
        inputConnection.outputPerceptrons.foreach( p => 
            if( p._2 == perceptron ) found = true
        )
        assert(found)
    }
    
    test("Add output neuron to InputConnection 2") {
        val inputConnection = new InputConnection("Input1")
        val perceptron = new Perceptron( 
                new LinearActivationFunc(),
                new NoLearningFunc() )
        
        var found = false
        inputConnection.outputPerceptrons.foreach( p => 
            if( p._2 == perceptron ) found = true
        )
        assert( !found )
    }
    
    test("Add input neuron for inputconnection") {
        val inputConnection = new InputConnection("Input1")
        val perceptron = new Perceptron( 
                new LinearActivationFunc(),
                new NoLearningFunc() )
        
        intercept[RuntimeException] {
            inputConnection.setInput(perceptron)
        }
    }
}


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