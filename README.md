## Automower

LEXAILEC Inc. has decided to develop an automatic lawnmower for rectangular surfaces.

The mower can be programmed to cover the entire surface.\
The position of the mower is represented by a combination of coordinates (x,y) and a letter indicating orientation in
cardinal notation (N,E,W,S). \
The lawn is divided into grids to simplify navigation.

For example, the mower's position could be "0, 0, N", meaning it's in the lower left-hand corner of the lawn, facing
north.

To control the mower, we send it a simple sequence of letters.\
Possible letters are "R", "L" and "F". "R" and "L" rotate the mower 90° to the right or left respectively, without
moving it. "F" moves the mower forward one square in the direction it faces, without changing its orientation.\
If the position after movement is outside the lawn, the mower does not move, retains its orientation and processes the
next command.

We assume that the square to the north of position (x, y) has coordinates (x, y+1).

To program the mower, we provide it with an input payload constructed as follows:

- The first line corresponds to the coordinates of the top right-hand corner of the lawn, while those of the bottom
  left-hand corner are assumed to be (0,0).
- The rest of the payload is used to control all the mowers that have been deployed. Each mower has two lines:
    - the first line gives the initial position of the mower, as well as its orientation. Position and orientation are
      given in the form of 2 numbers and a letter, separated by a space.
    - the second line is a series of instructions instructing the mower to explore the lawn. The instructions are a
      sequence of characters without spaces.

Each mower moves sequentially, meaning that the second mower moves only when the first has completed its series of
instructions.

When a mower completes a series of instructions, it communicates its position and orientation.

OBJECTIVE

Design and write a program running on a JVM, implementing the above specification as an HTTP endpoint and passing the
following test

TEST

The following payload is provided as input :

55\
12N\
LFLFLFLFF\
33E\
FFRFFRFRRF

The following payload is expected as response:

13N\
51E

## Requirements

- Adoptium Temurin 21: https://adoptium.net/
- Gradle 8.6+: https://gradle.org/install/

## Usage

Start the service using the command:

    $ java -jar automower-1.0.jar