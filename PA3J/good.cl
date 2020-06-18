class A {
};

Class BB__ inherits A {
};

class Main {
	exit_code:Int <- 1;
	anInt:Int;
	io:IO;
	testio:IO;
	
	main():IO { 
		{
			io <- new IO;
			
			let x : Int <- 5, y : String <- "This is a string", z : Bool <- true, i : IO <- new IO in
			{
				case i of
					h1 : Int    => 
						{
							io.out_string("case expression is an Int with value ");
							io.out_int(h1);
							io.out_string("\n");
						};
					h2 : String =>
						{
							io.out_string("case expression is a String with value ");
							io.out_string(h2);
							io.out_string("\n");
						};
					h3 : Bool   =>
						{
							io.out_string("case expression is a Bool with value ");
							io.out_string(if h3 then "true" else "false" fi);
							io.out_string("\n");
						};
					h4 : Object =>
						{
							io.out_string("case expression type could not be determined\n");
						};
				esac;
			};
			if (isvoid testio) then
				io.out_string("testio is void\n")
			else
				io.out_string("testio is not void\n")
			fi;
			if false then 
				io.out_string("true then branch\n") 
			else
				if false then 
					io.out_string("true else if-then branch\n")
				else
					io.out_string("true else if-then-else branch\n")
				fi
			fi;
			let x : Int <- 25, y : Int <- 26, z : Int <- 26 in
			{
				if x < y then
					io.out_string("x < y\n")
				else
					io.out_string("x >= y\n")
				fi;
				if y = z then
					io.out_string("y equals z\n")
				else
					io.out_string("y not equal to z\n")
				fi;
			};
			let x : Int <- 25, y : Int <- let z : Int <- 30 in z, a : Int, b : Int in
		    {
			    io.out_int(x);
			    io.out_string("\n");
			    io.out_int(y);
			    io.out_string("\n");
			    a <- x + y - 10 * 3 / 2;
			    io.out_int(a);
			    io.out_string("\n");
			    b <~ ~a;
			    io.out_int(b);
			    io.out_string("\n");
		    };
		    let i : Int <- 0 in
		    {
		    	while i <= 5 loop
		    	{
		    		io.out_int(i);
		    		io.out_string("\n");
		    		i <- (i + 1);
		    	}
		    	pool;
		    };
			io.out_string("called by io\n");
			io@IO.out_string("called by io@IO\n");
			anInt <- 5;
			io.out_int(exit_code);
			io.out_string("\n");
			io.out_int(anInt);
			io.out_string("\n");
		}
	};
};
