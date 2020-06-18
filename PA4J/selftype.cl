class Silly {
      copy() : SELF_TYPE { self };
      test() : Silly { self };
      test2() : Silly { new Silly };
      create() : Silly { new SELF_TYPE };
};

class Sally inherits Silly {
      testcreate() : Silly { create() };
      testcopy() : Silly { copy() };
};

class Tally inherits Sally {
      testcreate2() : Silly { (new SELF_TYPE).testcreate() };
      testcreate() : Silly { {
      		   (new IO).out_string("testcreate() in Tally\n");
		   let x : SELF_TYPE <- new SELF_TYPE in
		       x@Sally.testcreate();
      } };
};

class Tilly inherits Tally {
      testcreate() : Silly { {
      		   (new IO).out_string("testcreate() in Tilly\n");
		   let x : SELF_TYPE <- new SELF_TYPE in
		       x@Sally.testcreate();
      } };
};

class Main {
      x : Sally <- (new Sally).copy();
      y : Silly <- (new Sally).copy();
      z : Silly <- (new Silly).copy();
      (*a : Sally <- (new Sally).test();*)
      b : Silly <- (new Sally).test();
      c : Silly <- (new Silly).test();
      (*d : Sally <- (new Sally).test2();*)
      e : Silly <- (new Sally).test2();
      f : Silly <- (new Silly).test2();
      (*g : Sally <- (new Sally).create();*)
      h : Silly <- (new Sally).create();
      i : Silly <- (new Silly).create();
      j : Silly <- (new Sally).testcreate();
      k : Silly <- (new Tally).testcreate2();
      l : Silly <- (new Tilly).testcreate2();
      m : Silly <- (new Tilly).testcopy();
      n : Silly;
      p : SELF_TYPE;
      
      main() : Object { {
      	     printType(x);
	     printType(y);
	     printType(z);
	     (*printType(a);*)
	     printType(b);
	     printType(c);
	     (*printType(d);*)
	     printType(e);
	     printType(f);
	     (*printType(g);*)
	     printType(h);
	     printType(i);
	     printType(j);
	     printType(k);
	     printType(l);
	     printType(m);
	     printType(n <- new Tilly);
  	     p <- self;
             printType(p);
	     
	     let o : SELF_TYPE <- self in
	     	 printType(o);
      } };

      printType(t : Object) : Object {
      	     case t of
                  e : Tilly => (new IO).out_string("object is type Tilly\n");
                  d : Tally => (new IO).out_string("object is type Tally\n");
	     	  a : Sally => (new IO).out_string("object is type Sally\n");
		  b : Silly => (new IO).out_string("object is type Silly\n");
		  e : Main => (new IO).out_string("object is type Main\n");
		  c : Object => (new IO).out_string("object is type Object\n");
	     esac
      };
};
class SubMain inherits Main {
};
