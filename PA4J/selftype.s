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
str_const21:
	.word	4
	.word	5
	.word	String_dispTab
	.word	int_const0
	.byte	0	
	.align	2
	.word	-1
str_const20:
	.word	4
	.word	6
	.word	String_dispTab
	.word	int_const1
	.ascii	"SubMain"
	.byte	0	
	.align	2
	.word	-1
str_const19:
	.word	4
	.word	6
	.word	String_dispTab
	.word	int_const2
	.ascii	"Main"
	.byte	0	
	.align	2
	.word	-1
str_const18:
	.word	4
	.word	6
	.word	String_dispTab
	.word	int_const3
	.ascii	"Tilly"
	.byte	0	
	.align	2
	.word	-1
str_const17:
	.word	4
	.word	6
	.word	String_dispTab
	.word	int_const3
	.ascii	"Tally"
	.byte	0	
	.align	2
	.word	-1
str_const16:
	.word	4
	.word	6
	.word	String_dispTab
	.word	int_const3
	.ascii	"Sally"
	.byte	0	
	.align	2
	.word	-1
str_const15:
	.word	4
	.word	6
	.word	String_dispTab
	.word	int_const3
	.ascii	"Silly"
	.byte	0	
	.align	2
	.word	-1
str_const14:
	.word	4
	.word	6
	.word	String_dispTab
	.word	int_const4
	.ascii	"String"
	.byte	0	
	.align	2
	.word	-1
str_const13:
	.word	4
	.word	6
	.word	String_dispTab
	.word	int_const2
	.ascii	"Bool"
	.byte	0	
	.align	2
	.word	-1
str_const12:
	.word	4
	.word	5
	.word	String_dispTab
	.word	int_const5
	.ascii	"Int"
	.byte	0	
	.align	2
	.word	-1
str_const11:
	.word	4
	.word	5
	.word	String_dispTab
	.word	int_const6
	.ascii	"IO"
	.byte	0	
	.align	2
	.word	-1
str_const10:
	.word	4
	.word	6
	.word	String_dispTab
	.word	int_const4
	.ascii	"Object"
	.byte	0	
	.align	2
	.word	-1
str_const9:
	.word	4
	.word	8
	.word	String_dispTab
	.word	int_const7
	.ascii	"<basic class>"
	.byte	0	
	.align	2
	.word	-1
str_const8:
	.word	4
	.word	10
	.word	String_dispTab
	.word	int_const8
	.ascii	"object is type Object\n"
	.byte	0	
	.align	2
	.word	-1
str_const7:
	.word	4
	.word	10
	.word	String_dispTab
	.word	int_const9
	.ascii	"object is type Main\n"
	.byte	0	
	.align	2
	.word	-1
str_const6:
	.word	4
	.word	10
	.word	String_dispTab
	.word	int_const10
	.ascii	"object is type Silly\n"
	.byte	0	
	.align	2
	.word	-1
str_const5:
	.word	4
	.word	10
	.word	String_dispTab
	.word	int_const10
	.ascii	"object is type Sally\n"
	.byte	0	
	.align	2
	.word	-1
str_const4:
	.word	4
	.word	10
	.word	String_dispTab
	.word	int_const10
	.ascii	"object is type Tally\n"
	.byte	0	
	.align	2
	.word	-1
str_const3:
	.word	4
	.word	10
	.word	String_dispTab
	.word	int_const10
	.ascii	"object is type Tilly\n"
	.byte	0	
	.align	2
	.word	-1
str_const2:
	.word	4
	.word	10
	.word	String_dispTab
	.word	int_const8
	.ascii	"testcreate() in Tilly\n"
	.byte	0	
	.align	2
	.word	-1
str_const1:
	.word	4
	.word	10
	.word	String_dispTab
	.word	int_const8
	.ascii	"testcreate() in Tally\n"
	.byte	0	
	.align	2
	.word	-1
str_const0:
	.word	4
	.word	7
	.word	String_dispTab
	.word	int_const11
	.ascii	"selftype.cl"
	.byte	0	
	.align	2
	.word	-1
int_const11:
	.word	2
	.word	4
	.word	Int_dispTab
	.word	11
	.word	-1
int_const10:
	.word	2
	.word	4
	.word	Int_dispTab
	.word	21
	.word	-1
int_const9:
	.word	2
	.word	4
	.word	Int_dispTab
	.word	20
	.word	-1
int_const8:
	.word	2
	.word	4
	.word	Int_dispTab
	.word	22
	.word	-1
int_const7:
	.word	2
	.word	4
	.word	Int_dispTab
	.word	13
	.word	-1
int_const6:
	.word	2
	.word	4
	.word	Int_dispTab
	.word	2
	.word	-1
int_const5:
	.word	2
	.word	4
	.word	Int_dispTab
	.word	3
	.word	-1
int_const4:
	.word	2
	.word	4
	.word	Int_dispTab
	.word	6
	.word	-1
int_const3:
	.word	2
	.word	4
	.word	Int_dispTab
	.word	5
	.word	-1
int_const2:
	.word	2
	.word	4
	.word	Int_dispTab
	.word	4
	.word	-1
int_const1:
	.word	2
	.word	4
	.word	Int_dispTab
	.word	7
	.word	-1
int_const0:
	.word	2
	.word	4
	.word	Int_dispTab
	.word	0
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
	.word	str_const10
	.word	str_const11
	.word	str_const12
	.word	str_const13
	.word	str_const14
	.word	str_const15
	.word	str_const16
	.word	str_const17
	.word	str_const18
	.word	str_const19
	.word	str_const20
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
	.word	Silly_protObj
	.word	Silly_init
	.word	Sally_protObj
	.word	Sally_init
	.word	Tally_protObj
	.word	Tally_init
	.word	Tilly_protObj
	.word	Tilly_init
	.word	Main_protObj
	.word	Main_init
	.word	SubMain_protObj
	.word	SubMain_init
Object_dispTab:
	.word	Object.abort
	.word	Object.type_name
	.word	Object.copy
Main_dispTab:
	.word	Object.abort
	.word	Object.type_name
	.word	Object.copy
	.word	Main.main
	.word	Main.printType
SubMain_dispTab:
	.word	Object.abort
	.word	Object.type_name
	.word	Object.copy
	.word	Main.main
	.word	Main.printType
Silly_dispTab:
	.word	Object.abort
	.word	Object.type_name
	.word	Silly.copy
	.word	Silly.test
	.word	Silly.test2
	.word	Silly.create
Sally_dispTab:
	.word	Object.abort
	.word	Object.type_name
	.word	Silly.copy
	.word	Silly.test
	.word	Silly.test2
	.word	Silly.create
	.word	Sally.testcreate
	.word	Sally.testcopy
Tally_dispTab:
	.word	Object.abort
	.word	Object.type_name
	.word	Silly.copy
	.word	Silly.test
	.word	Silly.test2
	.word	Silly.create
	.word	Tally.testcreate
	.word	Sally.testcopy
	.word	Tally.testcreate2
Tilly_dispTab:
	.word	Object.abort
	.word	Object.type_name
	.word	Silly.copy
	.word	Silly.test
	.word	Silly.test2
	.word	Silly.create
	.word	Tilly.testcreate
	.word	Sally.testcopy
	.word	Tally.testcreate2
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
	.word	9
	.word	18
	.word	Main_dispTab
	.word	0
	.word	0
	.word	0
	.word	0
	.word	0
	.word	0
	.word	0
	.word	0
	.word	0
	.word	0
	.word	0
	.word	0
	.word	0
	.word	0
	.word	0
	.word	-1
SubMain_protObj:
	.word	10
	.word	18
	.word	SubMain_dispTab
	.word	0
	.word	0
	.word	0
	.word	0
	.word	0
	.word	0
	.word	0
	.word	0
	.word	0
	.word	0
	.word	0
	.word	0
	.word	0
	.word	0
	.word	0
	.word	-1
Silly_protObj:
	.word	5
	.word	3
	.word	Silly_dispTab
	.word	-1
Sally_protObj:
	.word	6
	.word	3
	.word	Sally_dispTab
	.word	-1
Tally_protObj:
	.word	7
	.word	3
	.word	Tally_dispTab
	.word	-1
Tilly_protObj:
	.word	8
	.word	3
	.word	Tilly_dispTab
	.word	-1
String_protObj:
	.word	4
	.word	5
	.word	String_dispTab
	.word	int_const0
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
	la	$a0 Sally_protObj
	jal	Object.copy
	jal	Sally_init
	bne	$a0 $zero label0
	la	$a0 str_const0
	li	$t1 31
	jal	_dispatch_abort
label0:
	lw	$t1 8($a0)
	lw	$t1 8($t1)
	jalr		$t1
	sw	$a0 12($s0)
	la	$a0 Sally_protObj
	jal	Object.copy
	jal	Sally_init
	bne	$a0 $zero label1
	la	$a0 str_const0
	li	$t1 32
	jal	_dispatch_abort
label1:
	lw	$t1 8($a0)
	lw	$t1 8($t1)
	jalr		$t1
	sw	$a0 16($s0)
	la	$a0 Silly_protObj
	jal	Object.copy
	jal	Silly_init
	bne	$a0 $zero label2
	la	$a0 str_const0
	li	$t1 33
	jal	_dispatch_abort
label2:
	lw	$t1 8($a0)
	lw	$t1 8($t1)
	jalr		$t1
	sw	$a0 20($s0)
	la	$a0 Sally_protObj
	jal	Object.copy
	jal	Sally_init
	bne	$a0 $zero label3
	la	$a0 str_const0
	li	$t1 35
	jal	_dispatch_abort
label3:
	lw	$t1 8($a0)
	lw	$t1 12($t1)
	jalr		$t1
	sw	$a0 24($s0)
	la	$a0 Silly_protObj
	jal	Object.copy
	jal	Silly_init
	bne	$a0 $zero label4
	la	$a0 str_const0
	li	$t1 36
	jal	_dispatch_abort
label4:
	lw	$t1 8($a0)
	lw	$t1 12($t1)
	jalr		$t1
	sw	$a0 28($s0)
	la	$a0 Sally_protObj
	jal	Object.copy
	jal	Sally_init
	bne	$a0 $zero label5
	la	$a0 str_const0
	li	$t1 38
	jal	_dispatch_abort
label5:
	lw	$t1 8($a0)
	lw	$t1 16($t1)
	jalr		$t1
	sw	$a0 32($s0)
	la	$a0 Silly_protObj
	jal	Object.copy
	jal	Silly_init
	bne	$a0 $zero label6
	la	$a0 str_const0
	li	$t1 39
	jal	_dispatch_abort
label6:
	lw	$t1 8($a0)
	lw	$t1 16($t1)
	jalr		$t1
	sw	$a0 36($s0)
	la	$a0 Sally_protObj
	jal	Object.copy
	jal	Sally_init
	bne	$a0 $zero label7
	la	$a0 str_const0
	li	$t1 41
	jal	_dispatch_abort
label7:
	lw	$t1 8($a0)
	lw	$t1 20($t1)
	jalr		$t1
	sw	$a0 40($s0)
	la	$a0 Silly_protObj
	jal	Object.copy
	jal	Silly_init
	bne	$a0 $zero label8
	la	$a0 str_const0
	li	$t1 42
	jal	_dispatch_abort
label8:
	lw	$t1 8($a0)
	lw	$t1 20($t1)
	jalr		$t1
	sw	$a0 44($s0)
	la	$a0 Sally_protObj
	jal	Object.copy
	jal	Sally_init
	bne	$a0 $zero label9
	la	$a0 str_const0
	li	$t1 43
	jal	_dispatch_abort
label9:
	lw	$t1 8($a0)
	lw	$t1 24($t1)
	jalr		$t1
	sw	$a0 48($s0)
	la	$a0 Tally_protObj
	jal	Object.copy
	jal	Tally_init
	bne	$a0 $zero label10
	la	$a0 str_const0
	li	$t1 44
	jal	_dispatch_abort
label10:
	lw	$t1 8($a0)
	lw	$t1 32($t1)
	jalr		$t1
	sw	$a0 52($s0)
	la	$a0 Tilly_protObj
	jal	Object.copy
	jal	Tilly_init
	bne	$a0 $zero label11
	la	$a0 str_const0
	li	$t1 45
	jal	_dispatch_abort
label11:
	lw	$t1 8($a0)
	lw	$t1 32($t1)
	jalr		$t1
	sw	$a0 56($s0)
	la	$a0 Tilly_protObj
	jal	Object.copy
	jal	Tilly_init
	bne	$a0 $zero label12
	la	$a0 str_const0
	li	$t1 46
	jal	_dispatch_abort
label12:
	lw	$t1 8($a0)
	lw	$t1 28($t1)
	jalr		$t1
	sw	$a0 60($s0)
	move	$a0 $s0
	lw	$fp 12($sp)
	lw	$s0 8($sp)
	lw	$ra 4($sp)
	addiu	$sp $sp 12
	jr	$ra	
SubMain_init:
	addiu	$sp $sp -12
	sw	$fp 12($sp)
	sw	$s0 8($sp)
	sw	$ra 4($sp)
	addiu	$fp $sp 4
	move	$s0 $a0
	jal	Main_init
	move	$a0 $s0
	lw	$fp 12($sp)
	lw	$s0 8($sp)
	lw	$ra 4($sp)
	addiu	$sp $sp 12
	jr	$ra	
Silly_init:
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
Sally_init:
	addiu	$sp $sp -12
	sw	$fp 12($sp)
	sw	$s0 8($sp)
	sw	$ra 4($sp)
	addiu	$fp $sp 4
	move	$s0 $a0
	jal	Silly_init
	move	$a0 $s0
	lw	$fp 12($sp)
	lw	$s0 8($sp)
	lw	$ra 4($sp)
	addiu	$sp $sp 12
	jr	$ra	
Tally_init:
	addiu	$sp $sp -12
	sw	$fp 12($sp)
	sw	$s0 8($sp)
	sw	$ra 4($sp)
	addiu	$fp $sp 4
	move	$s0 $a0
	jal	Sally_init
	move	$a0 $s0
	lw	$fp 12($sp)
	lw	$s0 8($sp)
	lw	$ra 4($sp)
	addiu	$sp $sp 12
	jr	$ra	
Tilly_init:
	addiu	$sp $sp -12
	sw	$fp 12($sp)
	sw	$s0 8($sp)
	sw	$ra 4($sp)
	addiu	$fp $sp 4
	move	$s0 $a0
	jal	Tally_init
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
	addiu	$sp $sp -16
	sw	$fp 16($sp)
	sw	$s0 12($sp)
	sw	$ra 8($sp)
	addiu	$fp $sp 4
	move	$s0 $a0
	sw	$s1 0($fp)
	lw	$a0 12($s0)
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	move	$a0 $s0
	bne	$a0 $zero label13
	la	$a0 str_const0
	li	$t1 51
	jal	_dispatch_abort
label13:
	lw	$t1 8($a0)
	lw	$t1 16($t1)
	jalr		$t1
	lw	$a0 16($s0)
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	move	$a0 $s0
	bne	$a0 $zero label14
	la	$a0 str_const0
	li	$t1 52
	jal	_dispatch_abort
label14:
	lw	$t1 8($a0)
	lw	$t1 16($t1)
	jalr		$t1
	lw	$a0 20($s0)
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	move	$a0 $s0
	bne	$a0 $zero label15
	la	$a0 str_const0
	li	$t1 53
	jal	_dispatch_abort
label15:
	lw	$t1 8($a0)
	lw	$t1 16($t1)
	jalr		$t1
	lw	$a0 24($s0)
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	move	$a0 $s0
	bne	$a0 $zero label16
	la	$a0 str_const0
	li	$t1 55
	jal	_dispatch_abort
label16:
	lw	$t1 8($a0)
	lw	$t1 16($t1)
	jalr		$t1
	lw	$a0 28($s0)
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	move	$a0 $s0
	bne	$a0 $zero label17
	la	$a0 str_const0
	li	$t1 56
	jal	_dispatch_abort
label17:
	lw	$t1 8($a0)
	lw	$t1 16($t1)
	jalr		$t1
	lw	$a0 32($s0)
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	move	$a0 $s0
	bne	$a0 $zero label18
	la	$a0 str_const0
	li	$t1 58
	jal	_dispatch_abort
label18:
	lw	$t1 8($a0)
	lw	$t1 16($t1)
	jalr		$t1
	lw	$a0 36($s0)
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	move	$a0 $s0
	bne	$a0 $zero label19
	la	$a0 str_const0
	li	$t1 59
	jal	_dispatch_abort
label19:
	lw	$t1 8($a0)
	lw	$t1 16($t1)
	jalr		$t1
	lw	$a0 40($s0)
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	move	$a0 $s0
	bne	$a0 $zero label20
	la	$a0 str_const0
	li	$t1 61
	jal	_dispatch_abort
label20:
	lw	$t1 8($a0)
	lw	$t1 16($t1)
	jalr		$t1
	lw	$a0 44($s0)
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	move	$a0 $s0
	bne	$a0 $zero label21
	la	$a0 str_const0
	li	$t1 62
	jal	_dispatch_abort
label21:
	lw	$t1 8($a0)
	lw	$t1 16($t1)
	jalr		$t1
	lw	$a0 48($s0)
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	move	$a0 $s0
	bne	$a0 $zero label22
	la	$a0 str_const0
	li	$t1 63
	jal	_dispatch_abort
label22:
	lw	$t1 8($a0)
	lw	$t1 16($t1)
	jalr		$t1
	lw	$a0 52($s0)
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	move	$a0 $s0
	bne	$a0 $zero label23
	la	$a0 str_const0
	li	$t1 64
	jal	_dispatch_abort
label23:
	lw	$t1 8($a0)
	lw	$t1 16($t1)
	jalr		$t1
	lw	$a0 56($s0)
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	move	$a0 $s0
	bne	$a0 $zero label24
	la	$a0 str_const0
	li	$t1 65
	jal	_dispatch_abort
label24:
	lw	$t1 8($a0)
	lw	$t1 16($t1)
	jalr		$t1
	lw	$a0 60($s0)
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	move	$a0 $s0
	bne	$a0 $zero label25
	la	$a0 str_const0
	li	$t1 66
	jal	_dispatch_abort
label25:
	lw	$t1 8($a0)
	lw	$t1 16($t1)
	jalr		$t1
	la	$a0 Tilly_protObj
	jal	Object.copy
	jal	Tilly_init
	sw	$a0 64($s0)
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	move	$a0 $s0
	bne	$a0 $zero label26
	la	$a0 str_const0
	li	$t1 67
	jal	_dispatch_abort
label26:
	lw	$t1 8($a0)
	lw	$t1 16($t1)
	jalr		$t1
	move	$a0 $s0
	sw	$a0 68($s0)
	lw	$a0 68($s0)
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	move	$a0 $s0
	bne	$a0 $zero label27
	la	$a0 str_const0
	li	$t1 69
	jal	_dispatch_abort
label27:
	lw	$t1 8($a0)
	lw	$t1 16($t1)
	jalr		$t1
	move	$s1 $s0
	sw	$s1 0($sp)
	addiu	$sp $sp -4
	move	$a0 $s0
	bne	$a0 $zero label28
	la	$a0 str_const0
	li	$t1 72
	jal	_dispatch_abort
label28:
	lw	$t1 8($a0)
	lw	$t1 16($t1)
	jalr		$t1
	lw	$s1 0($fp)
	lw	$fp 16($sp)
	lw	$s0 12($sp)
	lw	$ra 8($sp)
	addiu	$sp $sp 16
	jr	$ra	
Main.printType:
	addiu	$sp $sp -16
	sw	$fp 16($sp)
	sw	$s0 12($sp)
	sw	$ra 8($sp)
	addiu	$fp $sp 4
	move	$s0 $a0
	sw	$s1 0($fp)
	lw	$a0 16($fp)
	bne	$a0 $zero label30
	la	$a0 str_const0
	li	$t1 76
	jal	_case_abort2
label30:
	lw	$t2 0($a0)
	blt	$t2 9 label31
	bgt	$t2 10 label31
	move	$s1 $a0
	la	$a0 str_const7
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	la	$a0 IO_protObj
	jal	Object.copy
	jal	IO_init
	bne	$a0 $zero label32
	la	$a0 str_const0
	li	$t1 81
	jal	_dispatch_abort
label32:
	lw	$t1 8($a0)
	lw	$t1 12($t1)
	jalr		$t1
	b	label29
label31:
	blt	$t2 8 label33
	bgt	$t2 8 label33
	move	$s1 $a0
	la	$a0 str_const3
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	la	$a0 IO_protObj
	jal	Object.copy
	jal	IO_init
	bne	$a0 $zero label34
	la	$a0 str_const0
	li	$t1 77
	jal	_dispatch_abort
label34:
	lw	$t1 8($a0)
	lw	$t1 12($t1)
	jalr		$t1
	b	label29
label33:
	blt	$t2 7 label35
	bgt	$t2 8 label35
	move	$s1 $a0
	la	$a0 str_const4
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	la	$a0 IO_protObj
	jal	Object.copy
	jal	IO_init
	bne	$a0 $zero label36
	la	$a0 str_const0
	li	$t1 78
	jal	_dispatch_abort
label36:
	lw	$t1 8($a0)
	lw	$t1 12($t1)
	jalr		$t1
	b	label29
label35:
	blt	$t2 6 label37
	bgt	$t2 8 label37
	move	$s1 $a0
	la	$a0 str_const5
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	la	$a0 IO_protObj
	jal	Object.copy
	jal	IO_init
	bne	$a0 $zero label38
	la	$a0 str_const0
	li	$t1 79
	jal	_dispatch_abort
label38:
	lw	$t1 8($a0)
	lw	$t1 12($t1)
	jalr		$t1
	b	label29
label37:
	blt	$t2 5 label39
	bgt	$t2 8 label39
	move	$s1 $a0
	la	$a0 str_const6
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	la	$a0 IO_protObj
	jal	Object.copy
	jal	IO_init
	bne	$a0 $zero label40
	la	$a0 str_const0
	li	$t1 80
	jal	_dispatch_abort
label40:
	lw	$t1 8($a0)
	lw	$t1 12($t1)
	jalr		$t1
	b	label29
label39:
	blt	$t2 0 label41
	bgt	$t2 10 label41
	move	$s1 $a0
	la	$a0 str_const8
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	la	$a0 IO_protObj
	jal	Object.copy
	jal	IO_init
	bne	$a0 $zero label42
	la	$a0 str_const0
	li	$t1 82
	jal	_dispatch_abort
label42:
	lw	$t1 8($a0)
	lw	$t1 12($t1)
	jalr		$t1
	b	label29
label41:
	jal	_case_abort
label29:
	lw	$s1 0($fp)
	lw	$fp 16($sp)
	lw	$s0 12($sp)
	lw	$ra 8($sp)
	addiu	$sp $sp 20
	jr	$ra	
Silly.copy:
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
Silly.test:
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
Silly.test2:
	addiu	$sp $sp -12
	sw	$fp 12($sp)
	sw	$s0 8($sp)
	sw	$ra 4($sp)
	addiu	$fp $sp 4
	move	$s0 $a0
	la	$a0 Silly_protObj
	jal	Object.copy
	jal	Silly_init
	lw	$fp 12($sp)
	lw	$s0 8($sp)
	lw	$ra 4($sp)
	addiu	$sp $sp 12
	jr	$ra	
Silly.create:
	addiu	$sp $sp -16
	sw	$fp 16($sp)
	sw	$s0 12($sp)
	sw	$ra 8($sp)
	addiu	$fp $sp 4
	move	$s0 $a0
	sw	$s1 0($fp)
	la	$t1 class_objTab
	lw	$t2 0($s0)
	sll	$t2 $t2 3
	addu	$t1 $t1 $t2
	move	$s1 $t1
	lw	$a0 0($t1)
	jal	Object.copy
	lw	$t1 4($s1)
	jalr		$t1
	lw	$s1 0($fp)
	lw	$fp 16($sp)
	lw	$s0 12($sp)
	lw	$ra 8($sp)
	addiu	$sp $sp 16
	jr	$ra	
Sally.testcreate:
	addiu	$sp $sp -12
	sw	$fp 12($sp)
	sw	$s0 8($sp)
	sw	$ra 4($sp)
	addiu	$fp $sp 4
	move	$s0 $a0
	move	$a0 $s0
	bne	$a0 $zero label43
	la	$a0 str_const0
	li	$t1 9
	jal	_dispatch_abort
label43:
	lw	$t1 8($a0)
	lw	$t1 20($t1)
	jalr		$t1
	lw	$fp 12($sp)
	lw	$s0 8($sp)
	lw	$ra 4($sp)
	addiu	$sp $sp 12
	jr	$ra	
Sally.testcopy:
	addiu	$sp $sp -12
	sw	$fp 12($sp)
	sw	$s0 8($sp)
	sw	$ra 4($sp)
	addiu	$fp $sp 4
	move	$s0 $a0
	move	$a0 $s0
	bne	$a0 $zero label44
	la	$a0 str_const0
	li	$t1 10
	jal	_dispatch_abort
label44:
	lw	$t1 8($a0)
	lw	$t1 8($t1)
	jalr		$t1
	lw	$fp 12($sp)
	lw	$s0 8($sp)
	lw	$ra 4($sp)
	addiu	$sp $sp 12
	jr	$ra	
Tally.testcreate2:
	addiu	$sp $sp -16
	sw	$fp 16($sp)
	sw	$s0 12($sp)
	sw	$ra 8($sp)
	addiu	$fp $sp 4
	move	$s0 $a0
	sw	$s1 0($fp)
	la	$t1 class_objTab
	lw	$t2 0($s0)
	sll	$t2 $t2 3
	addu	$t1 $t1 $t2
	move	$s1 $t1
	lw	$a0 0($t1)
	jal	Object.copy
	lw	$t1 4($s1)
	jalr		$t1
	bne	$a0 $zero label45
	la	$a0 str_const0
	li	$t1 14
	jal	_dispatch_abort
label45:
	lw	$t1 8($a0)
	lw	$t1 24($t1)
	jalr		$t1
	lw	$s1 0($fp)
	lw	$fp 16($sp)
	lw	$s0 12($sp)
	lw	$ra 8($sp)
	addiu	$sp $sp 16
	jr	$ra	
Tally.testcreate:
	addiu	$sp $sp -16
	sw	$fp 16($sp)
	sw	$s0 12($sp)
	sw	$ra 8($sp)
	addiu	$fp $sp 4
	move	$s0 $a0
	sw	$s1 0($fp)
	la	$a0 str_const1
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	la	$a0 IO_protObj
	jal	Object.copy
	jal	IO_init
	bne	$a0 $zero label46
	la	$a0 str_const0
	li	$t1 16
	jal	_dispatch_abort
label46:
	lw	$t1 8($a0)
	lw	$t1 12($t1)
	jalr		$t1
	la	$t1 class_objTab
	lw	$t2 0($s0)
	sll	$t2 $t2 3
	addu	$t1 $t1 $t2
	move	$s1 $t1
	lw	$a0 0($t1)
	jal	Object.copy
	lw	$t1 4($s1)
	jalr		$t1
	move	$s1 $a0
	move	$a0 $s1
	bne	$a0 $zero label47
	la	$a0 str_const0
	li	$t1 18
	jal	_dispatch_abort
label47:
	la	$t1 Sally_dispTab
	lw	$t1 24($t1)
	jalr		$t1
	lw	$s1 0($fp)
	lw	$fp 16($sp)
	lw	$s0 12($sp)
	lw	$ra 8($sp)
	addiu	$sp $sp 16
	jr	$ra	
Tilly.testcreate:
	addiu	$sp $sp -16
	sw	$fp 16($sp)
	sw	$s0 12($sp)
	sw	$ra 8($sp)
	addiu	$fp $sp 4
	move	$s0 $a0
	sw	$s1 0($fp)
	la	$a0 str_const2
	sw	$a0 0($sp)
	addiu	$sp $sp -4
	la	$a0 IO_protObj
	jal	Object.copy
	jal	IO_init
	bne	$a0 $zero label48
	la	$a0 str_const0
	li	$t1 24
	jal	_dispatch_abort
label48:
	lw	$t1 8($a0)
	lw	$t1 12($t1)
	jalr		$t1
	la	$t1 class_objTab
	lw	$t2 0($s0)
	sll	$t2 $t2 3
	addu	$t1 $t1 $t2
	move	$s1 $t1
	lw	$a0 0($t1)
	jal	Object.copy
	lw	$t1 4($s1)
	jalr		$t1
	move	$s1 $a0
	move	$a0 $s1
	bne	$a0 $zero label49
	la	$a0 str_const0
	li	$t1 26
	jal	_dispatch_abort
label49:
	la	$t1 Sally_dispTab
	lw	$t1 24($t1)
	jalr		$t1
	lw	$s1 0($fp)
	lw	$fp 16($sp)
	lw	$s0 12($sp)
	lw	$ra 8($sp)
	addiu	$sp $sp 16
	jr	$ra	
