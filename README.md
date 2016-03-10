# Robot Tele-Op Controller

An android app to Teleoperate robots.

The app was originally written to control Fetch and Freight robots.

But can also be used to control other robots aswell.

The app communicates over wifi using UDP.

A receiver node can be found, which ouuld be used to receive the button presses.

# Robot Controller Receiver node

This is a ROS node which detects button presses and Joystick movement.

The robot controller receiver node publishes Joy messages and this has been tested to work well with [fetch_teleop](https://github.com/fetchrobotics/fetch_ros/tree/indigo-devel/fetch_teleop) node, to control the fetch and freight robots.

The receiver node receives button press events and publishes Joy messages across the Joy Topic.

This receiver node can also be used to control other robots, by simple observing the joy messages received, the buttons can be mapped according to the user's needs.

## Setup & Installation Instructions

Install the android app in your mobile phone.

This app and receiver, by default uses port 9001, and so please make sure that the firewall allows this port.

This can be done by

>$ sudo ufw allow 9001

 The port number can be changed in the source code according to the user's needs.

Place the robot_controller_receiver node on your Robot/Computer workspace and perform a catkin make.

##### Instructions for setting up your workspace
>$ mkdir -p my_workspace/src

>$ cd my_workspace/src

>$ catkin_init_workspace

>$ cd ..

>$ catkin_make

>$ source devel/setup.bash

Now paste the robot_controller_receiver_node folder into the src folder of your workspace.

>$ catkin_make

### Running Instructions

Before running the receiver node, find the ip address of your Robot/Computer by,

>$ hostname -I

Note : This Ip address should be used in the app to connect with the Robot/Computer

To run the receiver node,

>$ rosrun robot_controller_receiver_node receiver_node

##### For Further Details

Contact arvinasokan@gmail.com 
