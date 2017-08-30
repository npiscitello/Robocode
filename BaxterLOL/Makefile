# (C) 2012 by Eugeniy Mikhailov, <evgmik@gmail.com>
# see https://github.com/evgmik/robocode_bots.EvBot/blob/master/Makefile

ECHO					:=	@echo -e

SUPERPACKAGE 	:= 	nbp
ROBOTNAME 		:= 	BaxterLOL
ROBOTVER			:=	0.1
ROBOTDESC			:=	I just wanted an excuse to write code...
ROBOTAUTH			:=	thatvolvonut
ROBOTCN				:=	nbp.BaxterLOL

ROBOCODE			:= 	~/windows/robocode
ROBOTS_DIR   	:= 	$(ROBOCODE)/robots
ROBOCODEJAR  	:= 	$(ROBOCODE)/libs/robocode.jar
ROBOCODEVER		:=	1.9.2.6

ROBOTJAR    	:= 	$(SUPERPACKAGE).BaxterLOL_$(ROBOTVER).jar 
OUTDIR 				:= 	out

JAVAC 				:= 	javac
JFLAGS 				:= 	-d $(OUTDIR) -cp $(ROBOCODEJAR)

JAR						:=	jar cvf

SRC 					:= 	BaxterLOL.java	\
									EnemyBot.java

CLASSES 			:=	$(SRC:%.java=$(OUTDIR)/%.class)
PROPS					:=	$(ROBOTNAME).properties

.SUFFIXES: .java .class 

all: $(ROBOTS_DIR)/$(ROBOTJAR)

$(ROBOTS_DIR)/$(ROBOTJAR): $(ROBOTJAR)
	$(ECHO) "\n\x1B[32mCopying $< to $(ROBOTS_DIR)\x1B[0m"
	mkdir -p $(ROBOTS_DIR)/$(SUPERPACKAGE)
	cp $(ROBOTJAR) $(ROBOTS_DIR)

$(ROBOTJAR): $(CLASSES) $(PROPS)
	$(ECHO) "\n\x1B[32mPacking $@...\x1B[0m"
	cd $(OUTDIR); $(JAR) ../$@  `find $(SUPERPACKAGE) -type f`

$(CLASSES): $(SRC)
	$(ECHO) "\n\x1B[32mCompiling $@...\x1B[0m"
	$(JAVAC) $(JFLAGS) $^

$(PROPS):
	$(ECHO) "\n\x1B[32mCreating $@...\x1B[0m"
	@$(ECHO) "robot.description=$(ROBOTDESC)" 	 > $(OUTDIR)/$(SUPERPACKAGE)/$@
	@$(ECHO) "robot.include.source=true" 				>> $(OUTDIR)/$(SUPERPACKAGE)/$@
	@$(ECHO) "robocode.version=$(ROBOCODEVER)" 	>> $(OUTDIR)/$(SUPERPACKAGE)/$@
	@$(ECHO) "robot.version=$(ROBOTVER)" 				>> $(OUTDIR)/$(SUPERPACKAGE)/$@
	@$(ECHO) "robot.author.name=$(ROBOTAUTH)" 	>> $(OUTDIR)/$(SUPERPACKAGE)/$@
	@$(ECHO) "robot.classname=$(ROBOTCN)" 			>> $(OUTDIR)/$(SUPERPACKAGE)/$@

$(SRC): $(OUTDIR)

$(OUTDIR):
	$(ECHO) "\n\x1B[32mCreating output directory...\x1B[0m"
	mkdir -p $(OUTDIR)

clean:
	$(ECHO) "\n\x1B[32mTidying up...\x1B[0m"
	rm -f $(ROBOTJAR)
	rm -rf $(OUTDIR)
