# mars-rover-controller

# Introduction
A squad of robotic rovers are to be landed by NASA on a plateau on Mars. This plateau, 
which is curiously rectangular, must be navigated by the rovers so that their on-board 
cameras can get a complete view of the surrounding terrain to send back to Earth. [...]

# Instructions
Main class is "Controller" located under "gov.nasa.controller". It includes the method 
"executeInstructions" which accepts a String including the instructions to move the 
rovers and returns another String with the final positions and errors (if any)

# Notes
Some assumptions were made in order to solve the problem:
* All rovers are placed on the plateau before the instructions are executed.
* Rovers are not allowed to move or be placed outside the limits of the plateau.
* Two rovers cannot be on the same grid position at the same time.
* Any rover trying to move into a position either outside the plateau or occupied by another 
rover will interrupt its movement in order to avoid collisions.
* If the movement of a rover is interrupted, any remaining instructions are ignored. It is 
supossed that the instructions were prepared to avoid errors, so if they occur it is better 
to abort in order to avoid unwanted consequences.
* Once an error occurs, thought the instructions are interrupted there must be a response 
with the  positions occupied by the rovers just before the problem. A message indicating 
the cause of the error should be also returned.
