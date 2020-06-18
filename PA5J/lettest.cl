class Main {
    main(): Object {
        let x: Int, y: Bool, z: String, a: Int <- 1, b: Bool <- true, c: String <- "string", io: IO in
	    {
	        io <- new IO;
	        (new IO).out_int(x);
		io.out_string("\n");
		if y then io.out_string("true") else io.out_string("false") fi;
		io.out_string("\n");
		io.out_string(z);
		io.out_string("\n");
	        io.out_int(a);
		io.out_string("\n");
		if b then io.out_string("true") else io.out_string("false") fi;
		io.out_string("\n");
		io.out_string(c);
		io.out_string("\n");
	    }
    };
};