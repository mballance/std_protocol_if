
STD_PROTOCOL_IF_MKFILES_DIR := $(dir $(lastword $(MAKEFILE_LIST)))
STD_PROTOCOL_IF_DIR := $(abspath $(STD_PROTOCOL_IF_MKFILES_DIR)/..)

ifneq (1,$(RULES))

STD_PROTOCOL_IF_JAR := $(STD_PROTOCOL_IF_DIR)/lib/std_protocol_if.jar

else # Rules

endif

