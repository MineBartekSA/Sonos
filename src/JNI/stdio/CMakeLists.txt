cmake_minimum_required(VERSION 2.8)
project(stdioport)
set(CMAKE_BUILD_TYPE Relese)
find_package(JNI REQUIRED)
include_directories(${JNI_INCLUDE_DIRS})
set(CMAKE_CXX_FLAGS "${CMAKE_CSS_FLAGS} -std=c++11")
set(SOURCE_FILES stdioport.cpp)
add_Library(stdioport SHARED ${SOURCE_FILES})
add_custom_command(
	TARGET stdioport POST_BUILD
	COMMAND ${CMAKE_COMMAND} -E copy
		${CMAKE_CURRENT_BINARY_DIR}/*.so
		${CMAKE_SOURCE_DIR})
