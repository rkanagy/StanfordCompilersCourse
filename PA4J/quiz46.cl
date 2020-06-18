class Main inherits IO {
	x : Int;
	
	sing() : Int { 1 };
	valcomp(x : Int, y : Int) : Bool { x <= y };
	fib(x : Int) : Int {
		if valcomp(x, 2) then sing() else fib(x - 1) + fib (x - 2) fi
	};
	main() : Int {{
		x <- let y : Int <- 4 in fib(y);
		out_int(x);
	}};
};
