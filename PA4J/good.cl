(*class D inherits C {
};*)
class F inherits IO {
};

class E inherits C {
};

class D inherits C {
	n : C;
	getAFromC(i : Int) : C { {
		n <- new E;
		init(i, false);
	} };
	init(x : Int, y : Bool) : C {
	       self
	};
};

Class Main {

	main():C { {
	  (new D).init(1, true);
	  (new D).initselftype(10);
	  (new SELF_TYPE).test();
	} };
	test() : C { {
		(new IO).out_string("Main.test called");
		new C;
	} };
};

class C {
	a : Int;
	b : Bool;
	init(x : Int, y : Bool) : C {
           {
		a <- x;
		b <- y;
		c <- x;
		d <- x;
		self;
           }
	};
	
	init2(x : Int) : C {
		self

	};
	
	initselftype(x : Int) : SELF_TYPE { {
		a <- x;
		self;
	} };
	
	d : Int <- c;
	c : Int <- 2 * a;
};
(*
Class D inherits E {
};

class E inherits D {
};

class D inherits C {
};

class F inherits G {
};
*)