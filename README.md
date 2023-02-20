# Shopping List Application
The goal of this application is to *<mark style="background-color: #e4d5eb">generate a grocery list to aid in the 
process of meal prepping and planning </mark>* based on the recipes that were added by the users. 

## User Stories
- As a user, I want to *add and remove a meal* (includes the ingredients and the cook time) to the
current meal plan
- As a user, I want to *view the list of groceries with quantities* for the specific meal plan
- As a user, I want to *estimate the cooking time* of the whole meal plan
- As a user, I want to *see all the meals* that were added
- As a user, I want to *save the current meal plan*
- As a user, I want to *load the previously saved meal plan*

## Reference
- Implementation of JSONReader and JSONWriter is modelled after [JSONSerializationDemo by UBC]
(https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo)
- Implementation of GUI is modelled after examples provided in:
  - GUI Components: https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
  - Resizing icon in JLabel: https://stackoverflow.com/questions/16343098/resize-a-picture-to-fit-a-jlabel/32885963#32885963
- Implementation of Event and EventLog classes are modelled from Alarm System application:
  https://github.students.cs.ubc.ca/CPSC210/AlarmSystem
