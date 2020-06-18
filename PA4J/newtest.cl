class Main {
	main() : Object {
		(new A).copy()
	};
};
class A {
	copy() : SELF_TYPE { {
		(new IO).out_string("A.copy was called");
		self; 
	} };
};