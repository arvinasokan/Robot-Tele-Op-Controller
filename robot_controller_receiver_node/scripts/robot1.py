 #!/usr/bin/env
import socket 
import sys
from numpy import interp




###############################################################################



##################################################################################
class receiver:
  def __init__(self,app_data):
    self.app_axis_data=[0,0,0,0]
    self.app_button_data=[0,0,0,0,0,0,0,0]
    self.test=[]
    self.test1=[]
    self.translated=[]
    self.axis_joy=[0,0,0,0,0]
    self.buttons_joy=[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0] 
    self.app_data=app_data
    self.joystick_dat =[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
  def sort_data(self):
    for i in range(0,2): 
      self.test.append(interp(self.app_data[i],[-250,250],[-1,1]))
      self.app_axis_data=map(float,self.test)
    for j in range(2,10):
      self.test1.append(self.app_data[j])
      self.app_button_data=map(int,self.test1)
    return self.test,self.test1

  def joystick_data(self,app_axis_data,app_button_data):
    self.axis_joy[3]=-(self.app_axis_data[1])
    self.axis_joy[0]=-(self.app_axis_data[0])
    self.buttons_joy[10]=1
    self.buttons_joy[12]=self.app_button_data[0] 
    self.buttons_joy[14]=self.app_button_data[1]
    self.buttons_joy[0]=self.app_button_data[2]
    self.buttons_joy[3]=self.app_button_data[3] 
    self.buttons_joy[6]=self.app_button_data[4]
    self.buttons_joy[8]=self.app_button_data[5]
    return self.axis_joy,self.buttons_joy     
  


   

#app_data=["3","0","10","12","14","0","3","6","8","1"]

#recv = receiver(app_data)
#app_axis_data,app_button_data=recv.sort_data()
#axis,buttons=recv.joystick_data(app_axis_data,app_button_data)
#print axis,buttons

  

