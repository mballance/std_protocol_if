
STD_PROTOCOL_IF_SRC_DIR := $(dir $(lastword $(MAKEFILE_LIST)))

ifneq (1,$(RULES))

STD_PROTOCOL_IF_LIB := std_protocol_if.jar
STD_PROTOCOL_IF_SRC := $(wildcard $(STD_PROTOCOL_IF_SRC_DIR)/std_protocol_if/*.scala)

else # Rules

$(STD_PROTOCOL_IF_LIB) : $(STD_PROTOCOL_IF_SRC) $(CHISELLIB_JAR)
	$(Q)$(CHISELC) -o $@ $(STD_PROTOCOL_IF_SRC) -L$(CHISELLIB_JAR)
	
endif

