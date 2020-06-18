	.data
	.align	2
	.globl	class_nameTab
	.globl	Main_protObj
	.globl	Int_protObj
	.globl	String_protObj
	.globl	bool_const0
	.globl	bool_const1
	.globl	_int_tag
	.globl	_bool_tag
	.globl	_string_tag
_int_tag:
	.word	2
_bool_tag:
	.word	3
_string_tag:
	.word	4
	.globl	_MemMgr_INITIALIZER
_MemMgr_INITIALIZER:
	.word	_NoGC_Init
	.globl	_MemMgr_COLLECTOR
_MemMgr_COLLECTOR:
	.word	_NoGC_Collect
	.globl	_MemMgr_TEST
_MemMgr_TEST:
	.word	0
	.word	-1
str_const29:
	.word	4
	.word	5
	.word	String_dispTab
	.word	int_const8
	.byte	0	
	.align	2
	.word	-1
str_const28:
	.word	4
	.word	6
	.word	String_dispTab
	.word	int_const9
	.ascii	"Main"
	.byte	0	
	.align	2
	.word	-1
str_const27:
	.word	4
	.word	6
	.word	String_dispTab
	.word	int_const9
	.ascii	"BB__"
	.byte	0	
	.align	2
	.word	-1
str_const26:
	.word	4
	.word	5
	.word	String_dispTab
	.word	int_const0
	.ascii	"A"
	.byte	0	
	.align	2
	.word	-1
str_const25:
	.word	4
	.word	6
	.word	String_dispTab
	.word	int_const10
	.ascii	"String"
	.byte	0	
	.align	2
	.word	-1
str_const24:
	.word	4
	.word	6
	.word	String_dispTab
	.word	int_const9
	.ascii	"Bool"
	.byte	0	
	.align	2
	.word	-1
str_const23:
	.word	4
	.word	5
	.word	String_dispTab
	.word	int_const6
	.ascii	"Int"
	.byte	0	
	.align	2
	.word	-1
str_const22:
	.word	4
	.word	5
	.word	String_dispTab
	.word	int_const7
	.ascii	"IO"
	.byte	0	
	.align	2
	.word	-1
str_const21:
	.word	4
	.word	6
	.word	String_dispTab
	.word	int_const10
	.ascii	"Object"
	.byte	0	
	.align	2
	.word	-1
str_const20:
	.word	4
	.word	8
	.word	String_dispTab
	.word	int_const11
	.ascii	"<basic class>"
	.byte	0	
	.align	2
	.word	-1
str_const19:
	.word	4
	.word	9
	.word	String_dispTab
	.word	int_const12
	.ascii	"called by io@IO\n"
	.byte	0	
	.align	2
	.word	-1
str_const18:
	.word	4
	.word	8
	.word	String_dispTab
	.word	int_const11
	.ascii	"called by io\n"
	.byte	0	
	.align	2
	.word	-1
str_const17:
	.word	4
	.word	9
	.word	String_dispTab
	.word	int_const13
	.ascii	"y not equal to z\n"
	.byte	0	
	.align	2
	.word	-1
str_const16:
	.word	4
	.word	7
	.word	String_dispTab
	.word	int_const14
	.ascii	"y equals z\n"
	.byte	0	
	.align	2
	.word	-1
str_const15:
	.word	4
	.word	6
	.word	String_dispTab
	.word	int_const15
	.ascii	"x >= y\n"
	.byte	0	
	.align	2
	.word	-1
str_const14:
	.word	4
	.word	6
	.word	String_dispTab
	.word	int_const10
	.ascii	"x < y\n"
	.byte	0	
	.align	2
	.word	-1
str_const13:
	.word	4
	.word	12
	.word	String_dispTab
	.word	int_const4
	.ascii	"true else if-then-else branch\n"
	.byte	0	
	.align	2
	.word	-1
str_const12:
	.word	4
	.word	11
	.word	String_dispTab
	.word	int_const2
	.ascii	"true else if-then branch\n"
	.byte	0	
	.align	2
	.word	-1
str_const11:
	.word	4
	.word	9
	.word	String_dispTab
	.word	int_const13
	.ascii	"true then branch\n"
	.byte	0	
	.align	2
	.word	-1
str_const10:
	.word	4
	.word	9
	.word	String_dispTab
	.word	int_const16
	.ascii	"testio is not void\n"
	.byte	0	
	.align	2
	.word	-1
str_const9:
	.word	4
	.word	8
	.word	String_dispTab
	.word	int_const17
	.ascii	"testio is void\n"
	.byte	0	
	.align	2
	.word	-1
str_const8:
	.word	4
	.word	16
	.word	String_dispTab
	.word	int_const18
	.ascii	"case expression type could not be determined\n"
	.byte	0	
	.align	2
	.word	-1
str_const7:
	.word	4
	.word	6
	.word	String_dispTab
	.word	int_const1
	.ascii	"false"
	.byte	0	
	.align	2
	.word	-1
str_const6:
	.word	4
	.word	6
	.word	String_dispTab
	.word	int_const9
	.ascii	"true"
	.byte	0	
	.align	2
	.word	-1
str_const5:
	.word	4
	.word	14
	.word	String_dispTab
	.word	int_const19
	.ascii	"case expression is a Bool with value "
	.byte	0	
	.align	2
	.word	-1
str_const4:
	.word	4
	.word	14
	.word	String_dispTab
	.word	int_const20
	.ascii	"case expression is a String with value "
	.byte	0	
	.align	2
	.word	-1
str_const3:
	.word	4
	.word	5
	.word	String_dispTab
	.word	int_const0
	.ascii	"\n"
	.byte	0	
	.align	2
	.word	-1
str_const2:
	.word	4
	.word	14
	.word	String_dispTab
	.word	int_const19
	.ascii	"case expression is an Int with value "
	.byte	0	
	.align	2
	.word	-1
str_const1:
	.word	4
	.word	9
	.word	String_dispTab
	.word	int_const12
	.ascii	"This is a string"
	.byte	0	
	.align	2
	.word	-1
str_const0:
	.word	4
	.word	6
	.word	String_dispTab
	.word	int_const15
	.ascii	"good.cl"
	.byte	0	
	.align	2
	.word	-1
int_const20:
	.word	2
	.word	4
	.word	Int_dispTab
	.word	39
	.word	-1
int_const19:
	.word	2
	.word	4
	.word	Int_dispTab
	.word	37
	.word	-1
int_const18:
	.word	2
	.word	4
	.word	Int_dispTab
	.word	45
	.word	-1
int_const17:
	.word	2
	.word	4
	.word	Int_dispTab
	.word	15
	.word	-1
int_const16:
	.word	2
	.word	4
	.word	Int_dispTab
	.word	19
	.word	-1
int_const15:
	.word	2
	.word	4
	.word	Int_dispTab
	.word	7
	.word	-1
int_const14:
	.word	2
	.word	4
	.word	Int_dispTab
	.word	11
	.word	-1
int_const13:
	.word	2
	.word	4
	.word	Int_dispTab
	.word	17
	.word	-1
int_const12:
	.word	2
	.word	4
	.word	Int_dispTab
	.word	16
	.word	-1
int_const11:
	.word	2
	.word	4
	.word	Int_dispTab
	.word	13
	.word	-1
int_const10:
	.word	2
	.word	4
	.word	Int_dispTab
	.word	6
	.word	-1
int_const9:
	.word	2
	.word	4
	.word	Int_dispTab
	.word	4
	.word	-1
int_const8:
	.word	2
	.word	4
	.word	Int_dispTab
	.word	0
	.word	-1
int_const7:
	.word	2
	.word	4
	.word	Int_dispTab
	.word	2
	.word	-1
int_const6:
	.word	2
	.word	4
	.word	Int_dispTab
	.word	3
	.word	-1
int_const5:
	.word	2
	.word	4
	.word	Int_dispTab
	.word	10
	.word	-1
int_const4:
	.word	2
	.word	4
	.word	Int_dispTab
	.word	30
	.word	-1
int_const3:
	.word	2
	.word	4
	.word	Int_dispTab
	.word	26
	.word	-1
int_const2:
	.word	2
	.word	4
	.word	Int_dispTab
	.word	25
	.word	-1
int_const1:
	.word	2
	.word	4
	.word	Int_dispTab
	.word	5
	.word	-1
int_const0:
	.word	2
	.word	4
	.word	Int_dispTab
	.word	1
	.word	-1
bool_const0:
	.word	3
	.word	4
	.word	Bool_dispTab
	.word	0
	.word	-1
bool_const1:
	.word	3
	.word	4
	.word	Bool_dispTab
	.word	1
class_nameTab:
	.word	str_const21
	.word	str_const22
	.word	str_const23
	.word	str_const24
	.word	str_const25
	.word	str_const26
	.word	str_const27
	.word	str_const28
class_objTab:
	.word	Object_protObj
	.word	Object_init
	.word	IO_protObj
	.word	IO_init
	.word	Int_protObj
	.word	Int_init
	.word	Bool_protObj
	.word	Bool_init
	.word	String_protObj
	.word	String_init
	.word	A_protObj
	.word	A_init
	.word	BB___protObj
	.word	BB___init
	.word	Main_protObj
	.word	Main_init
Object_dispTab:
	.word	Object.abort
	.word	Object.type_name
	.word	Object.copy
Main_dispTab:
	.word	Object.abort
	.word	Object.type_name
	.word	Object.copy
	.word	Main.main
A_dispTab:
	.word	Object.abort
	.word	Object.type_name
	.word	Object.copy
BB___dispTab:
	.word	Object.abort
	.word	Object.type_name
	.word	Object.copy
String_dispTab:
	.word	Object.abort
	.word	Object.type_name
	.word	Object.copy
	.word	String.length
	.word	String.concat
	.word	String.substr
Bool_dispTab:
	.word	Object.abort
	.word	Object.type_name
	.word	Object.copy
Int_dispTab:
	.word	Object.abort
	.word	Object.type_name
	.word	Object.copy
IO_dispTab:
	.word	Object.abort
	.word	Object.type_name
	.word	Object.copy
	.word	IO.out_string
	.word	IO.out_int
	.word	IO.in_string
	.word	IO.in_int
	.word	-1
Object_protObj:
	.word	0
	.word	3
	.word	Object_dispTab
	.word	-1
Main_protObj:
	.word	7
	.word	7
	.word	Main_dispTab
	.word	int_const8
	.word	int_const8
	.word	0
	.word	0
	.word	-1
A_protObj:
	.word	5
	.word	3
	.word	A_dispTab
	.word	-1
BB___protObj:
	.word	6
	.word	3
	.word	BB___dispTab
	.word	-1
String_protObj:
	.word	4
	.word	5
	.word	String_dispTab
	.word	int_const8
	.word	0
	.word	-1
Bool_protObj:
	.word	3
	.word	4
	.word	Bool_dispTab
	.word	0
	.word	-1
Int_protObj:
	.word	2
	.word	4
	.word	Int_dispTab
	.word	0
	.word	-1
IO_protObj:
	.word	1
	.word	3
	.word	IO_dispTab
	.globl	heap_start
heap_start:
	.word	0
	.text
	.globl	Main_init
	.globl	Int_init
	.globl	String_init
	.globl	Bool_init
	.globl	Main.main
Object_init:
	addiu	$sp $sp -12
	sw	$fp 12($sp)
	sw	$s0 8($sp)
	sw	$ra 4($sp)
	addiu	$fp $sp 4
	move	$s0 $a0
	move	$a0 $s0
	lw	$fp 12($sp)
	lw	$s0 8($sp)
	lw	$ra 4($sp)
	addiu	$sp $sp 12
	jr	$ra	
Main_init:
	addiu	$sp $sp -12
	sw	$fp 12($sp)
	sw	$s0 8($sp)
	sw	$ra 4($sp)
	addiu	$fp $sp 4
	move	$s0 $a0
	jal	Object_init
	la	$a0 int_const0
	sw	$a0 12($s0)
	move	$a0 $s0
	lw	$fp 12($sp)
	lw	$s0 8($sp)
	lw	$ra 4($sp)
	addiu	$sp $sp 12
	jr	$ra	
A_init:
	addiu	$sp $sp -12
	sw	$fp 12($sp)
	sw	$s0 8($sp)
	sw	$ra 4($sp)
	addiu	$fp $sp 4
	move	$s0 $a0
	jal	Object_init
	move	$a0 $s0
	lw	$fp 12($sp)
	lw	$s0 8($sp)
	lw	$ra 4($sp)
	addiu	$sp $sp 12
	jr	$ra	
BB___init:
	addiu	$sp $sp -12
	sw	$fp 12($sp)
	sw	$s0 8($sp)
	sw	$ra 4($sp)
	addiu	$fp $sp 4
	move	$s0 $a0
	jal	A_init
	move	$a0 $s0
	lw	$fp 12($sp)
	lw	$s0 8($sp)
	lw	$ra 4($sp)
	addiu	$sp $sp 12
	jr	$ra	
String_init:
	addiu	$sp $sp -12
	sw	$fp 12($sp)
	sw	$s0 8($sp)
	sw	$ra 4($sp)
	addiu	$fp $sp 4
	move	$s0 $a0
	jal	Object_init
	move	$a0 $s0
	lw	$fp 12($sp)
	lw	$s0 8($sp)
	lw	$ra 4($sp)
	addiu	$sp $sp 12
	jr	$ra	
Bool_init:
	addiu	$sp $sp -12
	sw	$fp 12($sp)
	sw	$s0 8($sp)
	sw	$ra 4($sp)
	addiu	$fp $sp 4
	move	$s0 $a0
	jal	Object_init
	move	$a0 $s0
	lw	$fp 12($sp)
	lw	$s0 8($sp)
	lw	$ra 4($sp)
	addiu	$sp $sp 12
	jr	$ra	
Int_init:
	addiu	$sp $sp -12
	sw	$fp 12($sp)
	sw	$s0 8($sp)
	sw	$ra 4($sp)
	addiu	$fp $sp 4
	move	$s0 $a0
	jal	Object_init
	move	$a0 $s0
	lw	$fp 12($sp)
	lw	$s0 8($sp)
	lw	$ra 4($sp)
	addiu	$sp $sp 12
	jr	$ra	
IO_init:
	addiu	$sp $sp -12
	sw	$fp 12($sp)
	sw	$s0 8($sp)
	sw	$ra 4($sp)
	addiu	$fp $sp 4
	move	$s0 $a0
	jal	Object_init
	move	$a0 $s0
	lw	$fp 12($sp)
	lw	$s0 8($sp)
	lw	$ra 4($sp)
	addiu	$sp $sp 12
	jr	$ra	
Main.main:
	addiu	$sp $sp -36
	sw	$fp 36($sp)
	sw	$s0 32($sp)
	sw	$ra 28($sp)
	addiu	$fp $sp 4
	move	$s0 $a0
	sw	$s1 20($fp)
	sw	$s2 16($fp)
	sw	$s3 12($fp)
	sw	$s4 8($fp)
	sw	$s5 4($fp)
	sw	$s6 0($fp)
	la	$a0 IO_protObj
	jal	Object.copy
	jal	IO_init
	sw	$a0 20($s0)
	la	$s6 int_const1
	la	$s5 str_const1
	la	$s4 bool_const1
	la	$a0 IO_protObj
	jal	Object.copy
	jal	IO_init
	move	$s3 $a0
	bne	$s3 $zero label1
	la	$a0 str_const0
	li	$t1 19
	jal	_case_abort2
label1:
	lw	$t2 0($s3)
	blt	$t2 4 label2
	bgt	$t2 4 label2
	move	$s2 $s3
	la	$a0 str_const4
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	lw	$a0 20($s0)
	bne	$a0 $zero label3
	la	$a0 str_const0
	li	$t1 28
	jal	_dispatch_abort
label3:
	lw	$t1 8($a0)
	lw	$t1 12($t1)
	jalr		$t1
	sw	$s2 0($sp)
	addiu	$sp $sp -4
	lw	$a0 20($s0)
	bne	$a0 $zero label4
	la	$a0 str_const0
	li	$t1 29
	jal	_dispatch_abort
label4:
	lw	$t1 8($a0)
	lw	$t1 12($t1)
	jalr		$t1
	la	$a0 str_const3
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	lw	$a0 20($s0)
	bne	$a0 $zero label5
	la	$a0 str_const0
	li	$t1 30
	jal	_dispatch_abort
label5:
	lw	$t1 8($a0)
	lw	$t1 12($t1)
	jalr		$t1
	b	label0
label2:
	blt	$t2 3 label6
	bgt	$t2 3 label6
	move	$s2 $s3
	la	$a0 str_const5
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	lw	$a0 20($s0)
	bne	$a0 $zero label7
	la	$a0 str_const0
	li	$t1 34
	jal	_dispatch_abort
label7:
	lw	$t1 8($a0)
	lw	$t1 12($t1)
	jalr		$t1
	lw	$t1 12($s2)
	beqz	$t1 label8
	la	$a0 str_const6
	b	label9
label8:
	la	$a0 str_const7
label9:
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	lw	$a0 20($s0)
	bne	$a0 $zero label10
	la	$a0 str_const0
	li	$t1 35
	jal	_dispatch_abort
label10:
	lw	$t1 8($a0)
	lw	$t1 12($t1)
	jalr		$t1
	la	$a0 str_const3
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	lw	$a0 20($s0)
	bne	$a0 $zero label11
	la	$a0 str_const0
	li	$t1 36
	jal	_dispatch_abort
label11:
	lw	$t1 8($a0)
	lw	$t1 12($t1)
	jalr		$t1
	b	label0
label6:
	blt	$t2 2 label12
	bgt	$t2 2 label12
	move	$s2 $s3
	la	$a0 str_const2
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	lw	$a0 20($s0)
	bne	$a0 $zero label13
	la	$a0 str_const0
	li	$t1 22
	jal	_dispatch_abort
label13:
	lw	$t1 8($a0)
	lw	$t1 12($t1)
	jalr		$t1
	sw	$s2 0($sp)
	addiu	$sp $sp -4
	lw	$a0 20($s0)
	bne	$a0 $zero label14
	la	$a0 str_const0
	li	$t1 23
	jal	_dispatch_abort
label14:
	lw	$t1 8($a0)
	lw	$t1 16($t1)
	jalr		$t1
	la	$a0 str_const3
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	lw	$a0 20($s0)
	bne	$a0 $zero label15
	la	$a0 str_const0
	li	$t1 24
	jal	_dispatch_abort
label15:
	lw	$t1 8($a0)
	lw	$t1 12($t1)
	jalr		$t1
	b	label0
label12:
	blt	$t2 0 label16
	bgt	$t2 7 label16
	move	$s2 $s3
	la	$a0 str_const8
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	lw	$a0 20($s0)
	bne	$a0 $zero label17
	la	$a0 str_const0
	li	$t1 40
	jal	_dispatch_abort
label17:
	lw	$t1 8($a0)
	lw	$t1 12($t1)
	jalr		$t1
	b	label0
label16:
	jal	_case_abort
label0:
	lw	$a0 24($s0)
	move	$t1 $a0
	la	$a0 bool_const1
	beqz	$t1 label20
	la	$a0 bool_const0
label20:
	lw	$t1 12($a0)
	beqz	$t1 label18
	la	$a0 str_const9
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	lw	$a0 20($s0)
	bne	$a0 $zero label21
	la	$a0 str_const0
	li	$t1 45
	jal	_dispatch_abort
label21:
	lw	$t1 8($a0)
	lw	$t1 12($t1)
	jalr		$t1
	b	label19
label18:
	la	$a0 str_const10
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	lw	$a0 20($s0)
	bne	$a0 $zero label22
	la	$a0 str_const0
	li	$t1 47
	jal	_dispatch_abort
label22:
	lw	$t1 8($a0)
	lw	$t1 12($t1)
	jalr		$t1
label19:
	la	$a0 bool_const0
	lw	$t1 12($a0)
	beqz	$t1 label23
	la	$a0 str_const11
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	lw	$a0 20($s0)
	bne	$a0 $zero label25
	la	$a0 str_const0
	li	$t1 50
	jal	_dispatch_abort
label25:
	lw	$t1 8($a0)
	lw	$t1 12($t1)
	jalr		$t1
	b	label24
label23:
	la	$a0 bool_const0
	lw	$t1 12($a0)
	beqz	$t1 label26
	la	$a0 str_const12
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	lw	$a0 20($s0)
	bne	$a0 $zero label28
	la	$a0 str_const0
	li	$t1 53
	jal	_dispatch_abort
label28:
	lw	$t1 8($a0)
	lw	$t1 12($t1)
	jalr		$t1
	b	label27
label26:
	la	$a0 str_const13
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	lw	$a0 20($s0)
	bne	$a0 $zero label29
	la	$a0 str_const0
	li	$t1 55
	jal	_dispatch_abort
label29:
	lw	$t1 8($a0)
	lw	$t1 12($t1)
	jalr		$t1
label27:
label24:
	la	$s6 int_const2
	la	$s5 int_const3
	la	$s4 int_const3
	move	$s3 $s6
	lw	$t1 12($s3)
	lw	$t2 12($s5)
	la	$a0 bool_const1
	blt	$t1 $t2 label32
	la	$a0 bool_const0
label32:
	lw	$t1 12($a0)
	beqz	$t1 label30
	la	$a0 str_const14
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	lw	$a0 20($s0)
	bne	$a0 $zero label33
	la	$a0 str_const0
	li	$t1 61
	jal	_dispatch_abort
label33:
	lw	$t1 8($a0)
	lw	$t1 12($t1)
	jalr		$t1
	b	label31
label30:
	la	$a0 str_const15
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	lw	$a0 20($s0)
	bne	$a0 $zero label34
	la	$a0 str_const0
	li	$t1 63
	jal	_dispatch_abort
label34:
	lw	$t1 8($a0)
	lw	$t1 12($t1)
	jalr		$t1
label31:
	move	$s3 $s5
	move	$t1 $s3
	move	$t2 $s4
	la	$a0 bool_const1
	beq	$t1 $t2 label37
	la	$a1 bool_const0
	jal	equality_test
label37:
	lw	$t1 12($a0)
	beqz	$t1 label35
	la	$a0 str_const16
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	lw	$a0 20($s0)
	bne	$a0 $zero label38
	la	$a0 str_const0
	li	$t1 66
	jal	_dispatch_abort
label38:
	lw	$t1 8($a0)
	lw	$t1 12($t1)
	jalr		$t1
	b	label36
label35:
	la	$a0 str_const17
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	lw	$a0 20($s0)
	bne	$a0 $zero label39
	la	$a0 str_const0
	li	$t1 68
	jal	_dispatch_abort
label39:
	lw	$t1 8($a0)
	lw	$t1 12($t1)
	jalr		$t1
label36:
	la	$s6 int_const2
	la	$s5 int_const4
	la	$s4 int_const8
	la	$s3 int_const8
	sw	$s6 0($sp)
	addiu	$sp $sp -4
	lw	$a0 20($s0)
	bne	$a0 $zero label40
	la	$a0 str_const0
	li	$t1 73
	jal	_dispatch_abort
label40:
	lw	$t1 8($a0)
	lw	$t1 16($t1)
	jalr		$t1
	la	$a0 str_const3
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	lw	$a0 20($s0)
	bne	$a0 $zero label41
	la	$a0 str_const0
	li	$t1 74
	jal	_dispatch_abort
label41:
	lw	$t1 8($a0)
	lw	$t1 12($t1)
	jalr		$t1
	sw	$s5 0($sp)
	addiu	$sp $sp -4
	lw	$a0 20($s0)
	bne	$a0 $zero label42
	la	$a0 str_const0
	li	$t1 75
	jal	_dispatch_abort
label42:
	lw	$t1 8($a0)
	lw	$t1 16($t1)
	jalr		$t1
	la	$a0 str_const3
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	lw	$a0 20($s0)
	bne	$a0 $zero label43
	la	$a0 str_const0
	li	$t1 76
	jal	_dispatch_abort
label43:
	lw	$t1 8($a0)
	lw	$t1 12($t1)
	jalr		$t1
	move	$s2 $s6
	move	$a0 $s5
	jal	Object.copy
	lw	$t2 12($a0)
	lw	$t1 12($s2)
	add	$t1 $t1 $t2
	sw	$t1 12($a0)
	move	$s2 $a0
	la	$s1 int_const5
	la	$a0 int_const6
	jal	Object.copy
	lw	$t2 12($a0)
	lw	$t1 12($s1)
	mul	$t1 $t1 $t2
	sw	$t1 12($a0)
	move	$s1 $a0
	la	$a0 int_const7
	jal	Object.copy
	lw	$t2 12($a0)
	lw	$t1 12($s1)
	div	$t1 $t1 $t2
	sw	$t1 12($a0)
	jal	Object.copy
	lw	$t2 12($a0)
	lw	$t1 12($s2)
	sub	$t1 $t1 $t2
	sw	$t1 12($a0)
	move	$s4 $a0
	sw	$s4 0($sp)
	addiu	$sp $sp -4
	lw	$a0 20($s0)
	bne	$a0 $zero label44
	la	$a0 str_const0
	li	$t1 78
	jal	_dispatch_abort
label44:
	lw	$t1 8($a0)
	lw	$t1 16($t1)
	jalr		$t1
	la	$a0 str_const3
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	lw	$a0 20($s0)
	bne	$a0 $zero label45
	la	$a0 str_const0
	li	$t1 79
	jal	_dispatch_abort
label45:
	lw	$t1 8($a0)
	lw	$t1 12($t1)
	jalr		$t1
	move	$s2 $s3
	move	$a0 $s4
	jal	Object.copy
	lw	$t1 12($a0)
	neg	$t1 $t1
	sw	$t1 12($a0)
	jal	Object.copy
	lw	$t1 12($a0)
	neg	$t1 $t1
	sw	$t1 12($a0)
	lw	$t1 12($s2)
	lw	$t2 12($a0)
	la	$a0 bool_const1
	blt	$t1 $t2 label46
	la	$a0 bool_const0
label46:
	sw	$s3 0($sp)
	addiu	$sp $sp -4
	lw	$a0 20($s0)
	bne	$a0 $zero label47
	la	$a0 str_const0
	li	$t1 81
	jal	_dispatch_abort
label47:
	lw	$t1 8($a0)
	lw	$t1 16($t1)
	jalr		$t1
	la	$a0 str_const3
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	lw	$a0 20($s0)
	bne	$a0 $zero label48
	la	$a0 str_const0
	li	$t1 82
	jal	_dispatch_abort
label48:
	lw	$t1 8($a0)
	lw	$t1 12($t1)
	jalr		$t1
	la	$s6 int_const8
label49:
	move	$s5 $s6
	la	$a0 int_const1
	lw	$t1 12($s5)
	lw	$t2 12($a0)
	la	$a0 bool_const1
	ble	$t1 $t2 label51
	la	$a0 bool_const0
label51:
	lw	$t1 12($a0)
	beq	$t1 $zero label50
	sw	$s6 0($sp)
	addiu	$sp $sp -4
	lw	$a0 20($s0)
	bne	$a0 $zero label52
	la	$a0 str_const0
	li	$t1 88
	jal	_dispatch_abort
label52:
	lw	$t1 8($a0)
	lw	$t1 16($t1)
	jalr		$t1
	la	$a0 str_const3
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	lw	$a0 20($s0)
	bne	$a0 $zero label53
	la	$a0 str_const0
	li	$t1 89
	jal	_dispatch_abort
label53:
	lw	$t1 8($a0)
	lw	$t1 12($t1)
	jalr		$t1
	move	$s5 $s6
	la	$a0 int_const0
	jal	Object.copy
	lw	$t2 12($a0)
	lw	$t1 12($s5)
	add	$t1 $t1 $t2
	sw	$t1 12($a0)
	move	$s6 $a0
	b	label49
label50:
	move	$a0 $zero
	la	$a0 str_const18
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	lw	$a0 20($s0)
	bne	$a0 $zero label54
	la	$a0 str_const0
	li	$t1 94
	jal	_dispatch_abort
label54:
	lw	$t1 8($a0)
	lw	$t1 12($t1)
	jalr		$t1
	la	$a0 str_const19
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	lw	$a0 20($s0)
	bne	$a0 $zero label55
	la	$a0 str_const0
	li	$t1 95
	jal	_dispatch_abort
label55:
	la	$t1 IO_dispTab
	lw	$t1 12($t1)
	jalr		$t1
	la	$a0 int_const1
	sw	$a0 16($s0)
	lw	$a0 12($s0)
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	lw	$a0 20($s0)
	bne	$a0 $zero label56
	la	$a0 str_const0
	li	$t1 97
	jal	_dispatch_abort
label56:
	lw	$t1 8($a0)
	lw	$t1 16($t1)
	jalr		$t1
	la	$a0 str_const3
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	lw	$a0 20($s0)
	bne	$a0 $zero label57
	la	$a0 str_const0
	li	$t1 98
	jal	_dispatch_abort
label57:
	lw	$t1 8($a0)
	lw	$t1 12($t1)
	jalr		$t1
	lw	$a0 16($s0)
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	lw	$a0 20($s0)
	bne	$a0 $zero label58
	la	$a0 str_const0
	li	$t1 99
	jal	_dispatch_abort
label58:
	lw	$t1 8($a0)
	lw	$t1 16($t1)
	jalr		$t1
	la	$a0 str_const3
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	lw	$a0 20($s0)
	bne	$a0 $zero label59
	la	$a0 str_const0
	li	$t1 100
	jal	_dispatch_abort
label59:
	lw	$t1 8($a0)
	lw	$t1 12($t1)
	jalr		$t1
	lw	$s1 20($fp)
	lw	$s2 16($fp)
	lw	$s3 12($fp)
	lw	$s4 8($fp)
	lw	$s5 4($fp)
	lw	$s6 0($fp)
	lw	$fp 36($sp)
	lw	$s0 32($sp)
	lw	$ra 28($sp)
	addiu	$sp $sp 36
	jr	$ra	
