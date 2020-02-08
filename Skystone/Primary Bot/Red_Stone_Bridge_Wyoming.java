package FTC_2019_2020_Season;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import java.util.Locale;

@Autonomous(name="Red Stone Bridge Wyoming", group="Auto")
public class Red_Stone_Bridge_Wyoming extends LinearOpMode {
 
 

 
     /* Declare OpMode members. */
     RobotHardware robot = new RobotHardware();
     private ElapsedTime runtime = new ElapsedTime();
     
    ColorSensor sensorColor0;
    ColorSensor sensorColor1;

  
 
     @Override
     public void runOpMode() throws InterruptedException{
      
      try  {
      
      
     /*
     * Initialize the drive system variables.
     * The init() method of the hardware class does all the work here
     */
     robot.init(hardwareMap);
     
       // get a reference to the color sensor.
        sensorColor0 = hardwareMap.get(ColorSensor.class, "sensorColor0");
        sensorColor1 = hardwareMap.get(ColorSensor.class, "sensorColor1");
        // get a reference to the distance sensor that shares the same name.
       // sensorDistance = hardwareMap.get(DistanceSensor.class, "sensor_color_distance");

        // hsvValues is an array that will hold the hue, saturation, and value information.
        float hsvValues0[] = {0F, 0F, 0F};
        float hsvValues1[] = {0F, 0F, 0F};
        // values is a reference to the hsvValues array.
       

        // sometimes it helps to multiply the raw RGB values with a scale factor
        // to amplify/attentuate the measured values.
        final double SCALE_FACTOR = 255;

        // get a reference to the RelativeLayout so we can change the background
        // color of the Robot Controller app to match the hue detected by the RGB sensor.
        int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);


        char skystoneLocation = 'r';
        boolean position = false;


        telemetry.addData("Mode", "calibrating...");
        telemetry.update();

        telemetry.addData("Mode", "calibrating2...");
        telemetry.update();

        while (!isStopRequested() && !robot.imu.isGyroCalibrated())
        {
            Thread.sleep(50);
            idle();
        }


     
     // Wait for the game to start (driver presses PLAY)
        /** Wait for the game to begin */
        telemetry.addData(">", "Press Play to start tracking");
        telemetry.addData("imu calib status", robot.imu.getCalibrationStatus().toString());
        telemetry.update();
        waitForStart();
        
 
            if (opModeIsActive()) {
             
             
             ///////////////////////////////////////////////////////////////////////////////////////////////////////
             /////////////////////////// Get to Block here/////////////////////////////////////////////////////////
             /////////////////////////////////////////////////////////////////////////////////////////////////////
             
             robot.forwardByEncoder(0.3 , 700);
             Thread.sleep(50);
             
             robot.backwardByEncoder(0.3 , -700);
             Thread.sleep(50);
             
             robot.leftByEncoder(0.75 , -7700);
             robot.leftByEncoder(0.2 , -874);
             Thread.sleep(50);
             
              
             while (position != true){
            Color.RGBToHSV((int) (sensorColor0.red() * SCALE_FACTOR),
                    (int) (sensorColor0.green() * SCALE_FACTOR),
                    (int) (sensorColor0.blue() * SCALE_FACTOR),
                    hsvValues0);
            Color.RGBToHSV((int) (sensorColor1.red() * SCALE_FACTOR),
                    (int) (sensorColor1.green() * SCALE_FACTOR),
                    (int) (sensorColor1.blue() * SCALE_FACTOR),
                    hsvValues1);
                    
            telemetry.addData("Hue for color sensor 0", hsvValues0[0]);
            telemetry.addData("----First color Sensor ends here" , "----");
            telemetry.addData("Hue for sensor 1", hsvValues1[0]);
            telemetry.addData("----Second color Sensor ends here" , "----");
            telemetry.update();
            
            Thread.sleep(100);
            
            ////LEFT////////////////////
            ////////////////////////////
            ////////////////////////////
            if (hsvValues1[0] > 105 && hsvValues0[0] < 105){
                telemetry.addData("Skystone is Left " , "yeet");
                    
                    robot.backwardByEncoder(0.2, -200);
                    Thread.sleep(50);
                    
                     robot.leftByEncoder(0.25, -600);
                    Thread.sleep(50);
                    
                    robot.blockBlue.setPosition(0.1);
                    Thread.sleep(750);
                    
                    robot.rotate(-2, 0.2);//was-6 degrees
                     Thread.sleep(100);
                     
                    robot.rightByEncoder(0.6, 2100);
                    robot.rightByEncoder(0.2, 300);
                    Thread.sleep(50);
                    
                    robot.forwardByEncoder(0.6 , 14688);
                    Thread.sleep(50);
                    
                    robot.blockBlue.setPosition(0.45);
                    Thread.sleep(750);

                    robot.backwardByEncoder(0.6 , -20808);
                    Thread.sleep(50);
                    
                    robot.forward(0.2); // was backward
                    Thread.sleep(750);
                    
                    robot.leftByEncoder(0.5 , -4075);
                    Thread.sleep(50);
                    
                     robot.forward(); // was backward
                    Thread.sleep(100);
                    robot.stop();
                    
                    robot.leftByEncoder(0.2 , -600);
                    Thread.sleep(50);
                    
                    robot.forward(); // was backward
                    Thread.sleep(100);
                    robot.stop();
                    
                    robot.blockBlue.setPosition(0.1);
                    Thread.sleep(750);
                    
                    robot.rightByEncoder(0.3 , 3000);
                    Thread.sleep(50);
                    
                    robot.forward(0.25); // was backward
                    Thread.sleep(200);
                    robot.stop();
                    
                    robot.rightByEncoder(0.35, 1600);
                    
                    robot.forwardByEncoder(0.6 , 21808);
                    Thread.sleep(50);
                    
                    robot.blockBlue.setPosition(0.45);
                    Thread.sleep(750);
                    
                    robot.backwardByEncoder(0.35 , -1800);
                    Thread.sleep(50);
                        
                    robot.right(0.3); // was left
                    Thread.sleep(300);
                        robot.stop();
                        
                    skystoneLocation = 'l';
                    position = true;
            }   
            ////////////////////////////////////////////////////
            ////////////RIGHT///////////////////////////////////
            ////////////////////////////////////////////////////
                    else if (hsvValues0[0] > 105 && hsvValues1[0] < 105){ 
                    telemetry.addData("Skystone is Middle " , "yeet");
                    
                    robot.backwardByEncoder(0.25 , -400);
                    Thread.sleep(50);
                    
                    
                    robot.leftByEncoder(0.25, -600);
                    Thread.sleep(50);
                    
                    robot.blockRed.setPosition(0.9);
                    Thread.sleep(750);

                    robot.rightByEncoder(0.55, 2100);
                    robot.rightByEncoder(0.2, 300);
                    Thread.sleep(50);
                    
                     robot.rotate(-2, 0.2); // was -6
                     Thread.sleep(100);
                    
                    robot.forwardByEncoder(0.6 , 14688);
                    Thread.sleep(50);
                    
                    robot.blockRed.setPosition(0.55);
                    Thread.sleep(750);

                    robot.backwardByEncoder(0.6 , -20808);
                    Thread.sleep(50);
                    
                    robot.forward(0.2); // was backward
                    Thread.sleep(750);
                    
                    robot.leftByEncoder(0.35 , -4575);
                    Thread.sleep(50);
                    
                    robot.forward(); // was backward
                    Thread.sleep(100);
                    robot.stop();
                    
                    robot.blockRed.setPosition(0.9);
                    Thread.sleep(750);
                    
                    robot.rightByEncoder(0.3 , 3000);
                    Thread.sleep(50);
                   
                    robot.forwardByEncoder(0.5 , 11000); // was 14000
                    Thread.sleep(50);

                    robot.rightByEncoder(0.3 , 1600); // was 800 // was 11000
                    Thread.sleep(50);

                    
                    robot.forwardByEncoder(0.5 , 11000); // was 8000
                    Thread.sleep(50);
                    
                   robot.blockRed.setPosition(0.55);
                    Thread.sleep(750);

                    robot.backwardByEncoder(0.45 , -5400); // was -2400
                    Thread.sleep(50);
                    
                    robot.right(0.3); // was left
                    Thread.sleep(300);
                        
                        robot.stop();
                    
                    skystoneLocation = 'm';
                    position = true;
            }   else {
             ///////////////////MID/////////////////
                    telemetry.addData("Skystone is Right " , "yeet");
                                       
                    robot.backwardByEncoder(0.3, -2400);
                    Thread.sleep(50);
                                       
                    robot.leftByEncoder(0.3, -1200);//was -600
                    Thread.sleep(50);
                    
                    robot.blockBlue.setPosition(0.1);
                    Thread.sleep(750);
                    
                    robot.rotate(-2, 0.2); // was -6
                    Thread.sleep(100);
                     
                    robot.rightByEncoder(0.55, 2700); // was 2100
                    robot.rightByEncoder(0.2, 300);
                    Thread.sleep(50);
                    
                    robot.forwardByEncoder(0.6 , 17000);
                    Thread.sleep(50);
                    
                    //Release stone
                    robot.blockBlue.setPosition(0.45);
                    Thread.sleep(750);

                    robot.backwardByEncoder(0.55 , -9100);
                    Thread.sleep(50);
                    
                    robot.leftByEncoder(0.3 , -2568);
                    Thread.sleep(50);
                    
                    robot.right(0.2); // was left
                    Thread.sleep(400);
                    robot.stop();
                    
                    //Last grab
                    robot.blockBlue.setPosition(0.1);
                    Thread.sleep(750);
                    
                    //Move away 
                    robot.rightByEncoder(0.35 , 4000); // was 3100
                    Thread.sleep(50);
                    
                    robot.forwardByEncoder(0.5 , 12000);
                    Thread.sleep(50);
                    
                    robot.blockBlue.setPosition(0.45);
                    Thread.sleep(750);
                    
                    robot.backwardByEncoder(0.4 , -4600); // was -3400
                    Thread.sleep(50);
                    
                    robot.right(0.3); // was left
                    Thread.sleep(300);
                        robot.stop();
                        
                        
                    skystoneLocation = 'r';
                    position = true;
            }
            
            Thread.sleep(500);
            
/////////////////////////////////////////////////////////////////
///////////////////////  LEFT /////////////////////////////////
//////////////////////////////////////////////////////////
            if (skystoneLocation == 'l'){
                
                telemetry.addData("Skystone is Left" , "!");
                telemetry.update();
                
                
                
            }
////////////////////////////////////////////////////////////////          
////////////////////////// MIDDLE //////////////////////////////            
////////////////////////////////////////////////////////////////           
            if (skystoneLocation == 'm'){
                
                telemetry.addData("Skystone is Middle" , "!");
                telemetry.update();
                
           
           
           
            
            }
//////////////////////////////////////////////////////////////////////////////
///////////////////// RIGHT /////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////
             if (skystoneLocation == 'r'){
                 
              telemetry.addData("Skystone is Right" , "!");
                telemetry.update();
                
                
                
           
           }
            telemetry.update();
            

            }
         }
         
       
      } catch(InterruptedException e){
 telemetry.addLine("Hey Caught Interruption");
 telemetry.update();
 robot.stop();
//  robot.ssExtend.setPower(0);
//  robot.ssScrew.setPower(0);
 Thread.sleep(500);
}
       
     }
  }
