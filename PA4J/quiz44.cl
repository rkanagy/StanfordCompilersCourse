class Main {
	main() : Object {
		(new Bar).bar()
	};
};

class Foo inherits IO {
	foo() : SELF_TYPE {{
		out_string("Foo.foo()\n");
		foo();
		self;
	}};
};

class Bar inherits Foo {
	foo() : SELF_TYPE {{
		out_string("Bar.foo()\n");
		new SELF_TYPE;
	}};
	bar() : Foo {
		case foo() of
			f: Foo => f@Foo.foo();
			b: Bar => (new Bazz).foo();
			o: Object => foo();
		esac
	};
};
class Bazz inherits Bar {
	foo() : SELF_TYPE {{
		out_string("Bazz.foo()\n");
		(new Bar)@Foo.foo();
		self;
	}};
};
