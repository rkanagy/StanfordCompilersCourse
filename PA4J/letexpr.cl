class Main {
	main() : IO {
		let x : Int <- 123, y : String in
			printvals(x, y)
	};
	printvals(a : Int, b : String) : IO { {
		(new IO).out_int(a);
		(new IO).out_string("\n");
		(new IO).out_string(b);
		(new IO).out_string("\n");
	} }; 
};