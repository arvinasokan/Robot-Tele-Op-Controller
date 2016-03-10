#!/usr/bin/env python
#    / \   _ ____   _(_)_ __      / \   ___  ___ | | ____ _ _ __  
#   / _ \ | '__\ \ / / | '_ \    / _ \ / __|/ _ \| |/ / _` | '_ \ 
#  / ___ \| |   \ V /| | | | |  / ___ \\__ \ (_) |   < (_| | | | |
# /_/   \_\_|    \_/ |_|_| |_| /_/   \_\___/\___/|_|\_\__,_|_| |_|
#
# Code Written by Arvin Asokan
# For further details contact arvinasokan@gmail.com


#Standard Libraries
import socket 
import sys

#Third party Libraries
import rospy
from std_msgs.msg import String
from sensor_msgs.msg import Joy

import robot1

# A simple teleop receiver node for the android app
# This node was initially written to control the Fetch Robots
# However, this could be easily changed to control other robots as well.
# As of now the key mappings are based off of Fetch Teleop node
# This node sends Joy messages to the Joy topic.


host0=''
port0=9001

try:
  s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
except socket.error, msg0 :
  print 'Failed to create socket. Error Code : ' + str(msg0[0]) + ' Message ' + msg0[1]
  sys.exit()
try:
  s.bind((host0, port0))
except socket.error, msg0 :
  print 'Bind failed. Error Code : ' + str(msg0[0]) + ' Message ' + msg0[1]
  sys.exit()

def talker():
    pub = rospy.Publisher('joy', Joy, queue_size=20)
    rospy.init_node('test', anonymous=True)
    rate = rospy.Rate(100) # 10hz 
    bits=[]    
    #print axis,buttons
    while not rospy.is_shutdown():
        data = s.recvfrom(6144)
        app_data = data[0]
        app_data_addr = data[1]
        app_data=app_data.strip("[]")  
        app_data=app_data.split(",")
        app_data=map(int,app_data)  
        #print app_data  
        #app_data=[10,10,0,0,0,0,0,0,0,0] 
        recv = robot1.receiver(app_data)
        app_axis_data,app_button_data=recv.sort_data()
        axis,buttons=recv.joystick_data(app_axis_data,app_button_data)
        joy=Joy()
        joy.axes=axis
        joy.buttons=buttons
        #hello_str = "hello world %s" % rospy.get_time()
        rospy.loginfo(app_data)
        rospy.loginfo(buttons)
        pub.publish(joy)
        rate.sleep()

if __name__ == '__main__':
    try:
        talker()
    except rospy.ROSInterruptException:
        pass
