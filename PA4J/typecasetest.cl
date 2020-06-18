class Main {
	main() : Object {
		case new B of
			x : B => x.mul2(1, 2);
			y : A => y.plus2(1, 2);
			z : C => z.sub2(1, 2);
			w : D => w.divide2(2, 1);
			v : A => v.plus2(2, 3);
		esac
	};
};
class B inherits A {
	mul(x : Int, y : Int) : Int { x * y };
};
class A {
	plus(x : Int, y : Int) : Int { x + y };
};

class C inherits A {
	sub(x : Int, y : Int) : Int { x - y };
};

class D inherits A {
	divide(x : Int, y : Int) : Int { x / y };
};