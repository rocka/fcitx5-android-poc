set(LUA_VERSION_STRING "5.4")

include(FindPackageHandleStandardArgs)
FIND_PACKAGE_HANDLE_STANDARD_ARGS(Lua
        REQUIRED_VARS LUA_LIBRARIES LUA_INCLUDE_DIR
        VERSION_VAR LUA_VERSION_STRING)
