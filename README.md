workspace_calc
==============


* Folder structure <br>
** CalcTest --- It contains test cases for Android Junit. <br>
** Calculator --- It contains calculator android application. The required minimum android API version is 14.<br>



* Each module role <br>
** UI <br>
MainActivity.java : It is main calculator UI. It handles calculator relatd buttons event and show the each operation result. <br>
History.java : It is showing calculation history. It is launched from MainActivity.<br>
** Input <br>
InutData.java : It is a support class for the MainActivity. It is caracterized to store and return user input data. <br>
** Adapter <br>
Adapter.java : It is a mediator between UI and calculation logic (calc.java). It passes the input data <br>
from a user into the logic, and forward the result to UI.
** Logic <br>
calc.java : operating plus, multiplication, subtraction, division. <br>


* Architectural main point
Main point is testability since this project is short and should keep high quality at the same time. Almost
code must be tested alwasys. So to realize this point, test driven development style can be stronogly recomended
as a development style. so, this time, the main architectural point is focrusing on the testability. <br>
** Testability in this application : Each module should have high testability for unit test. To realize this, almost code
is located to "Input", "Adapter", "Logic". "UI" is just showing data which is passed by "Adapter". And
all input data from user is passed to "Input" and it is managing the data. "Logic" gets the inputted data
which can be calculatable well formated data which is passed by "Adapter". 
So, main module which can be easily managed by test driven development is separeted from UI part, and the 
structure can improve the test driven development style.
