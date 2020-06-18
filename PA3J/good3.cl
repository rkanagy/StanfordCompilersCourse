class Main {
	x  : Int <- 25;
	y  : Int <- 30;
    a  : Int;
    io : IO <- new IO;
    
    main() : Object {
    	{
    		a <- x + (y - 10) * (3 / 2);
    		io.out_int(a);
    		io.out_string("\n");
    	}
    };
};
