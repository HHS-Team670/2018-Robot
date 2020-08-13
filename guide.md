# Power Up!
# Arjun Was Here!1!1!1!
Code for Team 670's PowerUp Robot
--------------------------
**Things Left to Do:**

- [ ] Creation of all Autonomous paths
	- [ ] Left
	- [ ] Right
	- [ ] Center
- [ ] Driving Camera Software
	- [ ] Using Raspberry Pi for vision processing
		- [ ] Communication with NetworkTables
		- [ ] Pivot and drive to cube
- [ ] Arduino - RoboRIO communication (USB Serial connection)
 	- [ ] LiDar connection with Arduino and communication of values
	- [ ] LiDar DriveDistance until certain distance from cube
- [ ] Testing Procedures:
	- [ ] Combined Joystick Drive
	- [ ] Testing all Operator Stick controls + Buttons
	- [ ] All autonomous commands
- [ ] Calibration Procedures:
 	- [ ] PID Values for encoder distance
	- [ ] NavX Calibration + PID Control loop

--------------------------
Guides

**Steps to flash a new RoboRIO**

[HERE](http://wpilib.screenstepslive.com/s/4485/m/24193/l/273817-updating-your-roborio-firmware)

**Steps to flash a new Router**

[HERE](https://wpilib.screenstepslive.com/s/4485/m/13503/l/144986-programming-your-radio-for-home-use)

**Steps to install the Motor Controller, CTRE interface**

[HERE](https://github.com/CrossTheRoadElec/Phoenix-Documentation#installing-phoenix-framework-onto-your-frc-robot)

--------------------------
Updates

**(2/12/17):** 
Libraries needed for building/compiling the robot code: (install this software prior to opening the robot software, or you risk breaking everything):
- http://www.ctr-electronics.com/control-system/hro.html#product_tabs_technical_resources
- https://www.pdocs.kauailabs.com/navx-mxp/software/roborio-libraries/java/
