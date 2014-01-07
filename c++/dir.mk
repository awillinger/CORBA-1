
CXXSRCS = corba1_server.cc

DIR_CPPFLAGS = $(CORBA_CPPFLAGS)

CORBA_INTERFACES = echo

corba1_server        = $(patsubst %,$(BinPattern),corba1_server)

all:: $(corba1_server)

clean::
	$(RM) $(corba1_server)

$(corba1_server): corba1_server.o $(CORBA_STATIC_STUB_OBJS) $(CORBA_LIB_DEPEND)
	@(libs="$(CORBA_LIB_NODYN)"; $(CXXExecutable))