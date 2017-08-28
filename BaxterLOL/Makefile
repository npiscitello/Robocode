# (C) 2012 by Eugeniy Mikhailov, <evgmik@gmail.com>
# see https://github.com/evgmik/robocode_bots.EvBot/blob/master/Makefile

ECHO					:=	@echo -e

SUPERPACKAGE 	:= 	nbp
ROBOTNAME 		:= 	BaxterLOL

ROBOCODE			:= 	~/windows/robocode
ROBOTS_DIR   	:= 	$(ROBOCODE)/robots
ROBOCODEJAR  	:= 	$(ROBOCODE)/libs/robocode.jar

ROBOTJAR    	:= 	$(SUPERPACKAGE).BaxterLOL.jar 
OUTDIR 				:= 	out

JAVAC 				:= 	javac
JFLAGS 				:= 	-d $(OUTDIR) -cp $(ROBOCODEJAR)

JAR						:=	jar cvf

SRC 					:= 	BaxterLOL.java	\
									EnemyBots.java

CLASSES 			:=	$(SRC:%.java=$(OUTDIR)/%.class)

.SUFFIXES: .java .class 

all: $(ROBOTS_DIR)/$(ROBOTJAR)

$(ROBOTS_DIR)/$(ROBOTJAR): $(ROBOTJAR)
	$(ECHO) "\n\x1B[32mCopying $< to $(ROBOTS_DIR)\x1B[0m"
	cp $(ROBOTJAR) $(ROBOTS_DIR)/$(TESTJAR)

$(ROBOTJAR): $(CLASSES)
	$(ECHO) "\n\x1B[32mPacking $@...\x1B[0m"
	$(JAR) $@  `find $(OUTDIR)/$(SUPERPACKAGE) -type f`

$(CLASSES): $(SRC)
	$(ECHO) "\n\x1B[32mCompiling $@...\x1B[0m"
	$(JAVAC) $(JFLAGS) $^

$(SRC): $(OUTDIR)

$(OUTDIR):
	$(ECHO) "\n\x1B[32mCreating output directory...\x1B[0m"
	mkdir -p $(OUTDIR)

clean:
	$(ECHO) "\n\x1B[32mTidying up...\x1B[0m"
	rm -f $(ROBOTJAR)
	rm -rf $(OUTDIR)
