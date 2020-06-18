class Main {
      x: Int;
      io: IO <- new IO;
      
      main() : Object {
          let z : Int in
          {
              if not isvoid io then
	          io.out_string("io variable already initialized...\n")
              else
	      {
	          io <- new IO;
		  io.out_string("io variable now initiatlized...\n");
	      }
	      fi;

	      x <- 5;
	      io.out_int(x);
	      io.out_string("\n");
	      x <- ~x;
	      io.out_int(x);
	      io.out_string("\n");
	      
              (*io.out_string("Calling fib...\n");
	      z <- self@Main.fib(7);
	      io.out_int(z);
	      io.out_string("\n");*)
	      io.out_string("Calling looptest...\n");
	      looptest(7);
          }
      };
      fib(x : Int) : Int {
      	    if x = 1 then 0 else
	       if x = 2 then 1 else
	       	  fib(x - 1) + fib(x - 2)
	       fi
            fi
      };
      looptest(max: Int) : Object {
          let i: Int <- 1 in
	  {
	      io.out_string("Calculating fibonacci sequence...\n");
	      
	      while i < max + 1 loop
	      {
                  let f: Int in
		  {
		      f <- fib(i);	
	              io.out_int(f);
		      i <- i + 1;
		      io.out_string("\n");
		  };
	      }
	      pool;
	  }
      };
};
class A {
	a: Int <- 0;
	d: Int <- 1;
	f(): Int { a <- a + d };
	i(x: Int, y: Int, z: Int) : Int { a <- x - y + z };
};

class B inherits A {
	b: Int <- 2;
	f(): Int { a };
	g(): Int { a <- a - b };
};

class C inherits A {
	c: Int <- a + c - d + 3;
	s: String;
	classB: B <- new B;
	flag: Bool;
	obj: Object;
	h(): Int { a <- a * c };
	j(): Int { i(a + 1, d  + 2, c + 3) };
	k(m: Int): Object {
	     let u: Int <- m + 1 in
	     	 case obj of
	     	      x: Int => u + c;
		      y: String => s;
		      z: Bool => flag;
		      v: A => classB;
		      w: Object => obj;
	     	 esac
	};
};