class Count {
      i : Int <- 0;
      inc() : SELF_TYPE { {
      	    i <- i + 1;
	    self;
      } };
};

class Stock inherits Count {
      name : String;
      setName(n : String) : Object {
      	      name = n
      };
};

class Main {
      a : Stock <- (new Stock).inc();
      
      main() : Stock { {
      	     (*let y : Int <- x + 2 in y + 3;*)
      	     a.setName("Test");
	     a;
      } };
};