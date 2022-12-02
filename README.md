# TERM PROJECT: Shopping List Application

## Project Proposal

The goal of this application is to *<mark style="background-color: #e4d5eb">generate a grocery list to aid in the 
process of meal prepping and planning </mark>* based on the recipes that were added by the users. 
Based on my experience with meal planning and prepping, having a meal plan and a grocery list would help the process of
making healthy meal choices easier and more time-efficient. Hence, this application aims toward students 
and working professional who would like to eat healthier.

---
## User Stories
- As a user, I want to *add and remove a meal* (includes the ingredients and the cook time) to the
current meal plan
- As a user, I want to *view the list of groceries with quantities* for the specific meal plan
- As a user, I want to *estimate the cooking time* of the whole meal plan
- As a user, I want to *see all the meals* that were added
- As a user, I want to *save the current meal plan*
- As a user, I want to *load the previously saved meal plan*

---
## Reference
- Implementation of JSONReader and JSONWriter is modelled after [JSONSerializationDemo by UBC]
(https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo)
- Implementation of GUI is modelled after examples provided in:
  - GUI Components: https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
  - Resizing icon in JLabel: https://stackoverflow.com/questions/16343098/resize-a-picture-to-fit-a-jlabel/32885963#32885963
- Implementation of Event and EventLog classes are modelled from Alarm System application:
  https://github.students.cs.ubc.ca/CPSC210/AlarmSystem

---
# Instructions for Grader

- You can generate the first required event related to adding Xs to a Y by clicking [View] in the main window then [Add]
to add a new meal into the exiting meal plan, which also include adding various ingredients into the ingredient list. 
After pressing [Add], window would pop up, please fill in the fields and the new meal panel that has a green border
would appear in the bottom of the Meal Plan window.
- You can generate the second required event related to adding Xs to a Y by [Grocery List] in the main window then [Add] 
at the bottom of the checklist. Once you finish typing the new Ingredient, press Enter so that it would
appear in the grocery list. The newly added items would be in a different color and do not have a quantity because it 
does not belong to any meal plan.
- You can locate my visual component by icons that were added in the starting window and different icons in various buttons.
- You can save the state of my application by clicking [View] in the main window 
then [Save] OR close the window and a prompt asking you to save would pop up.
- You can reload the state of my application by clicking [Yes] in the pop-up message that appear when the application
was launched OR [Load] in the main window

---
# Phase 4: Task 2

Below is a representative sample of the events that occur when the program runs 
and a previously saved meal plan was loaded and user adds and removes a meal.

~~~
Thu Dec 01 11:45:33 PST 2022
Added [bagel and cream cheese] to meal plan


Thu Dec 01 11:45:33 PST 2022
Added [fruit salad] to meal plan


Thu Dec 01 11:45:33 PST 2022
Added [oatmeal pancake] to meal plan


Thu Dec 01 11:45:33 PST 2022
Added [pasta] to meal plan


Thu Dec 01 11:45:33 PST 2022
Added [green salad] to meal plan


Thu Dec 01 11:45:33 PST 2022
Added [kimchi fried rice] to meal plan


Thu Dec 01 11:45:33 PST 2022
Added [overnight oats] to meal plan


Thu Dec 01 11:45:33 PST 2022
Added [poke bowl] to meal plan


Thu Dec 01 11:45:40 PST 2022
Removed [bagel and cream cheese] from meal plan.
~~~

There is no Event Log when user adds a new item to the grocery list because the newly added item does
not belong to any meal plan, hence, it is not tracked by the EventLog.

---
# Phase 4: Task 3  

If I had more time to work on the project, I would:
- create an abstract class for setting up the JFrame for GroceryListAppUI, GroceryListWindow, and MealPlanWindow 
to reduce coupling since these classes shared some similar methods such as frameSetUp() and buttonSetUp().
- create a new class that contains all styling (colors, icon, text font) for easy adjustment of the visual elements in the UI.
- create a method setJLabelAesthetics(List<JLabel> l, Font f, Color c, Color c) that set the font, the background, the foreground of JLabels 
to improve coupling.
- redesign MealPlan with a singleton design pattern so that there could only be 1 MealPlan that exists in the application and
other classes could have access to that MealPlan.
- create a new method persistenceFunction() that perform the saving and loading functions depends on the parameter to reduce duplicate code.
