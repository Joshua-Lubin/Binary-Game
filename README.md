# Binary-Game
This is a JavaFx game where the objective is to translate base 10 numbers into binary before the timer runs out. I completed this project as an honors contract for CSE 205.

## Functionality
- Re-playable without restarting the application
- Converts base 10 numbers to binary
- Gives the user a score at the end of the game
- Ends the game when the user runs out of time
- Includes a timer bar so the user can visualize the amount of time left
- Adds extra time for each number the user accurately converts to binary

## UML Diagram
<img src="https://user-images.githubusercontent.com/90717831/151893338-1e9514be-ae70-454e-a048-64694d2bc60f.jpg" width=750/>

## Menu
### (Fake) Loading Screen
<img src="https://user-images.githubusercontent.com/90717831/151893388-51013a9b-d471-452f-8a74-d65319d4a033.jpg" width=400/ align="left">
The logo fades in and text that says, "Press any button to begin," fades in and out. When the user clicks their mouse or keyboard, the screen fades away and the menu appears.
<br clear="left"/>

### Menu
<img src="https://user-images.githubusercontent.com/90717831/151893397-02b16707-2dcb-496c-9d65-61467f9ac0ef.jpg" width=400/ align="left">
In the menu, the user can choose the number of bits in each number, how much time is added to the clock if they get a number correct, and whether the time added decreases as the game progresses.
<br clear="left"/>

## Gameplay
### UI Before Binary Is Entered
<img src="https://user-images.githubusercontent.com/90717831/151893413-57205836-da36-409a-a517-74739ccda461.jpg" width=400/ align="left">
First, the program displays the base-10 number that the user must convert to binary.
<br clear="left"/>

### UI While Binary Is Being Entered
<img src="https://user-images.githubusercontent.com/90717831/151893423-750fda9f-22eb-4523-af6b-5e6084ca73a6.jpg" width=400/ align="left">
Second, as the user types in the binary equivalent of the base-10 number, their input appears below the base 10 number.
<br clear="left"/>

### UI If Binary Is Correct
<img src="https://user-images.githubusercontent.com/90717831/151893435-6feb810f-b1d7-4347-9891-37ae8ef55adf.jpg" width=400/ align="left">
Finally, if the user's conversion is correct, the base-10 number flashes green, a new base 10 number appears, and extra time is added to the timer.
<br clear="left"/>

### UI If Binary Is Incorrect
<img src="https://user-images.githubusercontent.com/90717831/151893453-72983b9c-d03d-4bfc-83ab-3e740551579b.jpg" width=400/ align="left">
If the user's conversion is incorrect, the base-10 number stays the same, the base-10 number flashes red, and the user's input is cleared.
<br clear="left"/>

### End Screen
<img src="https://user-images.githubusercontent.com/90717831/151893459-2897ae6a-1d44-45da-89fe-1286f8837c74.jpg" width=400/ align="left">
Once the timer runs out, the user's score is displayed, and they have the option to play again.
