# My Card Manager

Phone App for managing cards

# Vibe Coding Commands

Here are the commands I used for setting up the app

## Setup

* Create a multipage-page Android app using Kotlin, Jetpack Compose, and the MVVM design pattern. Use stateless composable. Create a master page for hosting all other pages. The bottom of this page will include a navigation bar. All pages will be hosted here.
* Include @Preview methods for all app pages
* Add a button to the CardsScreen page. When clicked, it will use the camera to scan a QR code or bar code. A popup window will show the scanned QR code or bar code to the user.
* Manual change: Scan result is moved to success (ScanResultDialog) and fail (ScanFailDialog) methods
* Add the image of the bar code or QR code to the dialog in ScanResultDialog
* When a QR code is scanned, identify the type of code scanned. Add this information to the dialog in ScanResultDialog
* Add a text box called Name to the dialog in ScanResultDialog. Include the hint text 'What is your Card Name?'
* Rename the OK button the Scanned Code page to Save. When the button is pressed, save the name, code number, and code type to a JSON file for later use.
* Call the generated JSON file to scanned\_codes.json
* Create a responsive grid for displaying information in CardsScreen.kt. For each entry in scanned\_codes.json, add an entry to the grid. The entry will contain name, a placeholder icon, and the scanned number
* The previous caused an exception: please check for index out of bound exception
* 
* 
* Add menu 
* 
* 
* When a person clicks on an item in the CardsScreen page, open a popup showing the Name, QR code or bar code, and number
* 
* 
* 
* 
* 
* 
* 
* The name in the Scanned Code popup isn't being displayed in the grid in the CardsScreen page
* Remember state when the user rotates the phone
* Refactor the code for showing the popup for showing the Name, QR code or bar code, and number. Place it into a method named CardDetailsPopup
* When a person does a long click on an item in the CardsScreen page, open a popup menu showing menu options. Refactor this into a method called CardOpotionsPopup. Add an edit button to this menu, and a delete button
* Make sure data is saved when user clicks Save button
* Refactor the alert dialog into a separate method
* 
* 
* 
* f
* 

