# Smart Street Lamp Management System
An IOT project to reduce power consummation by making street lamps smarter.
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

![Screenshot 2023-01-21 184356](https://user-images.githubusercontent.com/42944621/213867374-6d71b6f3-c691-4f4e-8843-ce4bbf4f1ec7.png)

Create an Object, name it 'Room1' and create child key-value pair (value must be boolen type).

![Screenshot 2023-01-21 184225](https://user-images.githubusercontent.com/42944621/213867420-924e6dda-900d-4a1f-b254-24f9631f3c4e.png)


And that's all......... :)

As you add key-values to your Room1, keys will appear on your android application Home view.


