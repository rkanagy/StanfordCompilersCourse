class Main {
      attr: Object <- new IO;
      
      main(): Object {
      	      case attr of
	      	   io: IO =>
		        let io_var: IO <- new IO in
		            io_var.out_string("IO");
		   int: Int =>
		   	let io_var: IO <- new IO in
		   	    io_var.out_string("Int");
	      esac
      };
};
