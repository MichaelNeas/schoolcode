OFILES= fib.o
OFILES1= fact1.o
OFILES2= fact2.o
OFILES3= max.o
CPATH=
LLVMASY=$(CPATH)llvm-as-3.6
LLVMOPT=$(CPATH)opt-3.6
LLVMLC=$(CPATH)llc-3.6
AS=clang-3.6 -c
LD=clang-3.6

all: fib fact1 fact2 max

fib: $(OFILES)
	$(LD) $(OFILES) $(LIBS) -o $@

fact1: $(OFILES1)
	$(LD) $(OFILES1) $(LIBS) -o $@

fact2: $(OFILES2)
	$(LD) $(OFILES2) $(LIBS) -o $@

max : $(OFILES3)
	$(LD) $(OFILES3) $(LIBS) -o $@

%.o : %.s
	$(AS) $< -o $@

%.s : %.bc
	$(LLVMLC) $< -O=3 -tailcallopt -o $@

%.bc : %.llvm
	$(LLVMASY) $< -o $@

clean:
	rm *.o *.s *.bc fib fact1 fact2 max *~