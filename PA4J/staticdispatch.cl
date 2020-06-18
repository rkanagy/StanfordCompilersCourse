class A {
	test(msg : String) : IO { {
		(new IO).out_string("A.test was called:  ");
		(new IO).out_string(msg);
		(new IO).out_string("\n");
	} };
};

class B inherits A {
	test(msg : String) : IO { {
		(new IO).out_string("B.test was called:  ");
		(new IO).out_string(msg);
		(new IO).out_string("\n");
	} };
};

class C {
};

class Main {
	main() : IO { {
		(new B)@A.test("Called by static dispatch");
		(new B).test("Called by regular dispatch");
	} };
};
