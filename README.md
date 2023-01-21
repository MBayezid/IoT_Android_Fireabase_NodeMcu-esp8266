# IoT_Android_Fireabase_NodeMcu-esp8266
This project was developed as a part of CSE:426(IoT) Lab work.

Follow this steps to setup this project.

STEP 1:
Make sure you are using the latest version of Android studio.
Clone this project on your Android studio(follow this tutorial):
https://www.geeksforgeeks.org/how-to-clone-android-project-from-github-in-android-studio/

and use this HTTP Link to Clone:
https://github.com/MBayezid/IoT_Android_Fireabase_NodeMcu-esp8266.git

STEP 2:
Add firebase integration to your project(Follow this tutorial):
https://www.geeksforgeeks.org/adding-firebase-to-android-app/

Make sure your project is connected to a firebase project.

STEP 3:
Now add firebase realtime-database service to your firebase project.
Set Realtime database "Rules" as test or as following:
{
  "rules": {
    ".read": true,
    ".write": true
  }
}

Create an Object, name it 'Room1' and create child key-value pair (value must be boolen type).
![Screenshot 2023-01-21 183516](https://user-images.githubusercontent.com/42944621/213867185-4e502d7c-bb4b-4f53-89be-bf7b4564288f.png)

And that's all......... :)

As you add key-values to your Room1, keys will appear on your android application Home view.


