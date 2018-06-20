cmake_minimum_required(VERSION 2.8)
project(yourPort)
set(CMAKE_BUILD_TYPE Relese)
find_package(JNI REQUIRED)
include_directories(${JNI_INCLUDE_DIRS})
set(CMAKE_CXX_FLAGS "${CMAKE_CSS_FLAGS} -std=c++11")
set(SOURCE_FILES yourPort.cpp)
add_Library(yourPort SHARED ${SOURCE_FILES})
add_custom_command(
	        TARGET yourPort POST_BUILD
		COMMAND ${CMAKE_COMMAND} -E copy
			${CMAKE_CURRENT_BINARY_DIR}/*.so
			${CMAKE_SOURCE_DIR})
