# Problem 

The scenario: It's the Wild West and a group of cowboys has gathered for a shootout. All start with the same number of health points (hp=10). The combatants stand in a circle, a random cowboy starts and shoots at either the direct left or direct right neighbor. The direction is determined by the current number of healthpoints of the active cowboy, if this number is even he shoots to the right, if it is odd, he shoots the left neighbor. The target loses 1-5 (random) healthpoints. If the target is killed (hp<=0), the circle is closed and the active cowboy shoots again, otherwise it's the target cowboy's turn and he continues following the same rules.

The shootout ends if only one cowboy is left.

Naturally, the sheriff of the town is very interested in this so he demands a protocol that includes for every shot:
 - who fired it
 - who was hit
 - how many healthpoints were lost
 - how many healthpoints the target has left

This document is archived (written to a file) and, to prevent forgery, a checksum of this file is calculated.



## Your task:
 - Please write a runnable program that takes the number of cowboys as input parameter and simulates the shootout process, writes a protocol file and calculates and prints the checksum
 - Please use Java as programming language and Eclipse as IDE
 - The choice of structures, types, variable names etc is yours, it just should make sense and you should be able to explain what you did and why you did it that way
 - The program does not need a GUI, just write the status messages to the console
 - The protocol file (the detailed structure is up to you) should be in a valid JSON format and it should pass validation (e.g. with jsonlint). Use UTF-8 for the file encoding.
 - Use MD5 as method for the checksum
 - You don't have to re-invent the wheel, use existing Java convenience functions and/or libraries where appropriate
