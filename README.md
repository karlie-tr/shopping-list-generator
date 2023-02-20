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

## GUI
<img width="562" alt="image" src="https://user-images.githubusercontent.com/99240209/220048746-6b733ed8-9f05-4f2e-8b2c-723898009f73.png">
<img width="562" alt="image" src="https://user-images.githubusercontent.com/99240209/220049233-0a28f7be-fc4d-4d4b-b77a-055e48ebb149.png">
<img width="562" alt="image" src="https://user-images.githubusercontent.com/99240209/220049088-70bd6941-9a84-4514-9e63-05c1ca3372f5.png">



## Reference
- Implementation of JSONReader and JSONWriter is modelled after [JSONSerializationDemo by UBC]
(https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo)
- Implementation of GUI is modelled after examples provided in:
  - GUI Components: https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
  - Resizing icon in JLabel: https://stackoverflow.com/questions/16343098/resize-a-picture-to-fit-a-jlabel/32885963#32885963
- Implementation of Event and EventLog classes are modelled from Alarm System application:
  https://github.students.cs.ubc.ca/CPSC210/AlarmSystem
