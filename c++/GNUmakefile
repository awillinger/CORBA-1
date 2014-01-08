LIB_DIR=$(HOME)/downloads/omniORB-4.1.7/build/lib
INCLUDE_DIR=$(HOME)/downloads/omniORB-4.1.7/build/include

all:: check_dir prebuild_idl prebuild_server build_idl build

clean::
	rm -f *.o *.d corba1_server
	rm -R stub/

check_dir:
	-mkdir stub/

prebuild_idl:
	omniidl -bcxx -Wba -C./stub  ../idl/echo.idl

build_idl:
	cd stub/; omkdepend -D__cplusplus -D__GNUG__ -D__GNUC__ -D__OMNIORB4__ -D_REENTRANT -I. -I. -I$(INCLUDE_DIR) -D__OSVERSION__=2 -D__linux__ -D__x86_64__ echoDynSK.cc echoSK.cc; cd -
	cd stub/; g++ -c -O2 -Wall -Wno-unused -fexceptions  -D__OMNIORB4__ -I../stub -D_REENTRANT -I. -I. -I$(INCLUDE_DIR) -D__OSVERSION__=2 -D__linux__ -D__x86_64__ -o echoSK.o echoSK.cc; cd -

prebuild_server: 
	omkdepend -D__cplusplus -D__GNUG__ -D__GNUC__ -D__OMNIORB4__ -D_REENTRANT -I. -I./ -I$(INCLUDE_DIR) -Istub/ -D__OSVERSION__=2 -D__linux__ -D__x86_64__ corba1_server.cc
	g++ -c -O2 -Wall -Wno-unused -fexceptions  -D__OMNIORB4__ -D_REENTRANT -I. -I./ -I$(INCLUDE_DIR) -Istub/ -D__OSVERSION__=2 -D__linux__ -D__x86_64__ -o corba1_server.o corba1_server.cc

build:
	rm -f corba1_server
	g++ -o corba1_server -O2 -Wall -Wno-unused -fexceptions -L$(LIB_DIR) corba1_server.o stub/echoSK.o -lomniORB4 -lomnithread -lpthread