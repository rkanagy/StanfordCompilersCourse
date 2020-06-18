class Main inherits I2A {

        main() : Object {
                (new IO).out_string(fact(25))
        };
	
        fact(i: Int) : Int {
                if (i = 0) then 1 else i * fact(i - 1) fi
        };
};
