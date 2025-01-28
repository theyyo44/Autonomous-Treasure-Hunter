# Autonomous Treasure Hunter


The "Autonomous Treasure Hunter" project involves designing a game where an autonomous character collects various treasures while avoiding obstacles on a map. The main goal of the project is to develop an algorithm that allows the character to collect all the treasures in the shortest possible time.

Key components of the game include:

Map Design: The map contains treasures and obstacles randomly placed across the grid. Obstacles restrict the character’s movement, requiring strategic decision-making to navigate efficiently.

Autonomous Character: The character starts from a designated point on the map. Using the developed algorithm, the character detects the locations of treasures and follows the shortest path to collect them while avoiding obstacles.

A Algorithm*: The A* algorithm was implemented to enable the character to find the shortest path to the treasures. This algorithm evaluates both the current cost (g-cost) of the path and the estimated distance to the target (h-cost), ensuring the most efficient route. It accounts for obstacles, selects safe paths, and minimizes the time taken to collect treasures.

Difficulty Levels: The difficulty of the game can be dynamically adjusted by increasing the size of the map or the number of treasures and obstacles, providing a scalable challenge.

The primary objective of this project is to simulate decision-making capabilities in an autonomous system and create an optimized game mechanism. The use of the A* algorithm enhanced the solution's efficiency and ensured smooth gameplay. This project not only serves as an engaging game but also demonstrates the applicability of algorithms in solving real-world problems effectively.
