
STD_PROTOCOL_IF_SCRIPTS_DIR := $(dir $(lastword $(MAKEFILE_LIST)))
STD_PROTOCOL_IF_DIR := $(abspath $(STD_PROTOCOL_IF_SCRIPTS_DIR)/..)
PACKAGES_DIR ?= $(STD_PROTOCOL_IF_DIR)/packages
LIB_DIR = $(STD_PROTOCOL_IF_DIR)/lib

# Must support dual modes: 
# - build dependencies if this project is the active one
# - rely on the upper-level makefile to resolve dependencies if we're not
-include $(PACKAGES_DIR)/packages.mk
include $(STD_PROTOCOL_IF_DIR)/etc/ivpm.info

include $(PACKAGES_DIR)/chiselscripts/mkfiles/chiselscripts.mk
include $(PACKAGES_DIR)/chisellib/mkfiles/chisellib.mk
include $(STD_PROTOCOL_IF_DIR)/mkfiles/std_protocol_if.mk

STD_PROTOCOL_IF_SRC := \
	$(wildcard $(STD_PROTOCOL_IF_DIR)/src/std_protocol_if/*.scala)

RULES := 1

ifeq (true,$(PHASE2))
build : $(STD_PROTOCOL_IF_JAR)

clean :
	$(Q)rm -rf $(STD_PROTOCOL_IF_DIR)/build $(LIB_DIR)
else
build : $(std_protocol_if_deps)
	$(MAKE) -f $(STD_PROTOCOL_IF_SCRIPTS_DIR)/ivpm.mk PHASE2=true build

clean : $(std_protocol_if_clean_deps)
	$(MAKE) -f $(STD_PROTOCOL_IF_SCRIPTS_DIR)/ivpm.mk PHASE2=true clean

endif

$(STD_PROTOCOL_IF_JAR) : $(STD_PROTOCOL_IF_SRC) $(STD_PROTOCOL_IF_DEPS)
	$(Q)if test ! -d `dirname $@`; then mkdir -p `dirname $@`; fi
	$(Q)$(DO_CHISELC)

release : build
	$(Q)rm -rf $(CHISELLIB_DIR)/build
	$(Q)mkdir -p $(CHISELLIB_DIR)/build/chisellib
	$(Q)cp -r \
          $(CHISELLIB_DIR)/lib \
          $(CHISELLIB_DIR)/etc \
          $(CHISELLIB_DIR)/build/chisellib
	$(Q)cd $(CHISELLIB_DIR)/build ; \
		tar czf chisellib-$(version).tar.gz chisellib
	$(Q)rm -rf $(CHISELLIB_DIR)/build/chisellib

include $(STD_PROTOCOL_IF_DIR)/mkfiles/std_protocol_if.mk
include $(PACKAGES_DIR)/chiselscripts/mkfiles/chiselscripts.mk
include $(PACKAGES_DIR)/chisellib/mkfiles/chisellib.mk
-include $(PACKAGES_DIR)/packages.mk

