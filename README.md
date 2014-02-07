workspace_calc
==============


*Folder structure

CalcTest --- It contains test cases for Android Junit. 

Calculator --- It contains calculator android application. The required minimum android API version is 14.


*Desing of Calculator

** UI
MainActivity.java : It is main calculator UI. It handles calculator relatd buttons event and show the each operation result.
History.java : It is showing calculation history. It is launched from MainActivity.

** Input
InutData.java : It is a support class for the MainActivity. It is caracterized to store and return user input data.

** Adapter
Adapter.java : It is a mediator between UI and calculation logic (calc.java). It passes the input data
from a user into the logic, and forward the result to UI.


** Logic
calc.java : operating plus, multiplication, subtraction, division.

