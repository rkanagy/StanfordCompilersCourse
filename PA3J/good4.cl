class Main {
    s  : String <- "This is a string.";
    io : IO     <- new IO;
      
    main() : Object {
      	{
		    io.out_int(s.length());
		    io.out_string("\n");
	    }
    };
};