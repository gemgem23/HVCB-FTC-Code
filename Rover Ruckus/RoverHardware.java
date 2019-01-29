package org.firstinspires.ftc.teamcode;

import android.nfc.cardemulation.OffHostApduService;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.lang.Math;

public class RoverHardware
{
    /* Public OpMode members. */
    public DcMotor  fl   = null;
    public DcMotor  fr  = null;
    public DcMotor  bl   = null;
    public DcMotor  br  = null;
    public DcMotor  lift    = null;
    public DcMotor  collectorArmExtend   = null;
    public DcMotor  collectorHarvesterFlip    = null;
    public DcMotor  verticalDistribution   = null;
    
    
    public Servo    marker   = null;
    public Servo    sensorArm = null;
    public Servo    collector1 = null;
    public Servo    collector2 = null;
    public Servo    cupArm = null;
    public Servo    selector = null;

    ColorSensor sensorColor;

    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    // Variables for Drive Speed
    final double FORWARD = .5;
    final double BACK = -0.5;
    final int OFF = 0;

    /* Constructor */
    public RoverHardware(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        fl  = hwMap.get(DcMotor.class, "fl");
        fr = hwMap.get(DcMotor.class, "fr");
        bl  = hwMap.get(DcMotor.class, "bl");
        br = hwMap.get(DcMotor.class, "br");
        lift = hwMap.get(DcMotor.class, "lift");
        collectorArmExtend = hwMap.get(DcMotor.class, "collectorArmExtend");
        collectorHarvesterFlip = hwMap.get(DcMotor.class, "collectorHarvesterFlip");
        verticalDistribution = hwMap.get(DcMotor.class, "verticalDistribution");
       
        
        fl.setDirection(DcMotor.Direction.REVERSE);
        bl.setDirection(DcMotor.Direction.REVERSE);
        fr.setDirection(DcMotor.Direction.FORWARD);
        br.setDirection(DcMotor.Direction.FORWARD);
        lift.setDirection(DcMotor.Direction.FORWARD);
        collectorArmExtend.setDirection(DcMotor.Direction.FORWARD);
        collectorHarvesterFlip.setDirection(DcMotor.Direction.FORWARD);
        verticalDistribution.setDirection(DcMotor.Direction.FORWARD);
    
      

        // Set all motors to zero power
        fl.setPower(0);
        fr.setPower(0);
        bl.setPower(0);
        br.setPower(0);
        lift.setPower(0);
        collectorArmExtend.setPower(0);
        collectorHarvesterFlip.setPower(0);
        verticalDistribution.setPower(0);


      

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        collectorArmExtend.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        collectorHarvesterFlip.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        verticalDistribution.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
   


        // Define and initialize ALL installed servos.
        marker  = hwMap.get(Servo.class, "marker");
        sensorArm = hwMap.get(Servo.class, "sensorArm");
        collector1 = hwMap.get(Servo.class, "collector1");
        collector2 = hwMap.get(Servo.class, "collector2");
        cupArm = hwMap.get(Servo.class, "cupArm");
        selector = hwMap.get(Servo.class, "selector");


    
        marker.setPosition(1); //// 1 is up
        collector1.setPosition(0.5);
        collector2.setPosition(0.5);
        cupArm.setPosition(0);
        selector.setPosition(0.5);
        
        reset();
        // // 1 = outside the robot for sampling THIS IS A COMMENT
        // // 0.365 = inside robot for start THIS IS A COMMENT
        //  sensorArm.setPosition(0);
         
        // get a reference to the color sensor.

        sensorColor = hwMap.get(ColorSensor.class, "CD1");
    }
    double cut = 0;

    // Methods

    // Method for driving forwards -- default
    public void forward(){
        fl.setPower(FORWARD);
        fr.setPower(FORWARD);
        bl.setPower(FORWARD);
        br.setPower(FORWARD);
    }

    // Method for driving forwards -- select speed
    public void forward(double speed){
        fl.setPower(Math.abs(speed));
        fr.setPower(Math.abs(speed));
        bl.setPower(Math.abs(speed));
        br.setPower(Math.abs(speed));
    }

    // Method for driving backwards -- default
    public void backward(){
        fl.setPower(BACK);
        fr.setPower(BACK);
        bl.setPower(BACK);
        br.setPower(BACK);
    }

    // Method for driving backwards -- select speed
    public void backward(double speed){
        fl.setPower(-Math.abs(speed));
        fr.setPower(-Math.abs(speed));
        bl.setPower(-Math.abs(speed));
        br.setPower(-Math.abs(speed));
    }

    // Method for driving left -- default
    public void left(){
        fl.setPower(BACK);
        fr.setPower(FORWARD);
        bl.setPower(FORWARD);
        br.setPower(BACK);
    }

    // Method for driving left -- select speed
    public void left(double speed){
        fl.setPower(-Math.abs(speed));
        fr.setPower(Math.abs(speed));
        bl.setPower(Math.abs(speed));
        br.setPower(-Math.abs(speed));
    }

    // Method for driving right -- default
    public void right(){
        fl.setPower(FORWARD);
        fr.setPower(BACK);
        bl.setPower(BACK);
        br.setPower(FORWARD);
    }

    // Method for driving right -- select speed
    public void right(double speed){
        fl.setPower(Math.abs(speed));
        fr.setPower(-Math.abs(speed));
        bl.setPower(-Math.abs(speed));
        br.setPower(Math.abs(speed));
    }

    // Method for driving diagonal forward left -- default
    public void forwardLeft(){
        fl.setPower(FORWARD);
        fr.setPower(OFF);
        bl.setPower(OFF);
        br.setPower(FORWARD);
    }

    // Method for driving diagonal forward left -- select speed
    public void forwardLeft(double speed){
        fl.setPower(Math.abs(speed));
        fr.setPower(OFF);
        bl.setPower(OFF);
        br.setPower(Math.abs(speed));
    }

    // Method for driving diagonal back left -- default
    public void backLeft(){
        fl.setPower(OFF);
        fr.setPower(BACK);
        bl.setPower(BACK);
        br.setPower(OFF);
    }

    // Method for driving diagonal back left -- select speed
    public void backLeft(double speed){
        fl.setPower(OFF);
        fr.setPower(-Math.abs(speed));
        bl.setPower(-Math.abs(speed));
        br.setPower(OFF);
    }

    // Method for driving diagonal forward right
    // Default
    public void forwardRight(){
        fl.setPower(OFF);
        fr.setPower(FORWARD);
        bl.setPower(FORWARD);
        br.setPower(OFF);
    }

    // Method for driving diagonal forward right
    // Select Speed
    public void forwardRight(double speed){
        fl.setPower(OFF);
        fr.setPower(Math.abs(speed));
        bl.setPower(Math.abs(speed));
        br.setPower(OFF);
    }

    // Method for driving diagonal back right
    // Default
    public void backRight(){
        fl.setPower(BACK);
        fr.setPower(OFF);
        bl.setPower(OFF);
        br.setPower(BACK);
    }

    // Method for driving diagonal back right
    // Select Speed
    public void backRight(double speed){
        fl.setPower(-Math.abs(speed));
        fr.setPower(OFF);
        bl.setPower(OFF);
        br.setPower(-Math.abs(speed));
    }

    // Method for spinning left -- default
    public void spinLeft(){
        fl.setPower(BACK);
        fr.setPower(FORWARD);
        bl.setPower(BACK);
        br.setPower(FORWARD);
    }

    // Method for spinning left -- select speed
    public void spinLeft(double speed){
        fl.setPower(-Math.abs(speed));
        fr.setPower(Math.abs(speed));
        bl.setPower(-Math.abs(speed));
        br.setPower(Math.abs(speed));
    }

    // Method for spinning right -- default
    public void spinRight(){
        fl.setPower(FORWARD);
        fr.setPower(BACK);
        bl.setPower(FORWARD);
        br.setPower(BACK);
    }

    // Method for spinning right -- select speed
    public void spinRight(double speed) {
        fl.setPower(Math.abs(speed));
        fr.setPower(-Math.abs(speed));
        bl.setPower(Math.abs(speed));
        br.setPower(-Math.abs(speed));
    }

    // Method for not moving
    public void stop() {
        fl.setPower(OFF);
        fr.setPower(OFF);
        bl.setPower(OFF);
        br.setPower(OFF);
    }
    
    public void reset () {
    fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    verticalDistribution.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

   
    fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    verticalDistribution.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
    
    public int avgencoderval () {
    return ((Math.abs(fl.getCurrentPosition()) + Math.abs(fr.getCurrentPosition()) +
           Math.abs(bl.getCurrentPosition()) + Math.abs(br.getCurrentPosition())) / 4);
    }
    
    public int avgencoderright () {
    return ((fl.getCurrentPosition() + br.getCurrentPosition()) / 2);
    }
    
    
    public int avgencoderleft () {
    return ((fr.getCurrentPosition() + bl.getCurrentPosition()) / 2);
    }
    
 }

