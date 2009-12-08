ifndef ANT_TASK
	die "Please use ant to build the project!"
endif
	
SOURCES = $(wildcard $(SRC_DIR)/*.c)
OBJECTS = $(subst $(SRC_DIR),$(OBJ_DIR),$(SOURCES:.c=.o))

INCLUDES += $(NATIVE_INCLUDE)
INCLUDES += $(JAVA_INCLUDE)
INCLUDES += -I$(SRC_DIR)/..

all: $(SOURCES) $(EXECUTABLE)

$(EXECUTABLE): $(OBJECTS)
	$(LD) -O3 -fno-strict-aliasing -shared $(LDFLAGS) $(OBJECTS) -o $@

$(OBJ_DIR)/%.o: $(SRC_DIR)/%.c
	$(CC) -Wall -O3 -fno-strict-aliasing -shared $(CFLAGS) $(INCLUDES) $< -o $@
