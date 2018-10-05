/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.robotcontroller.internal;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Basic Crater", group="Auto")
public class Crater_Basic extends LinearOpMode {

    /* Declare OpMode members. */
    RoverHardware robot   = new RoverHardware();   // Use a rover hardware
    private ElapsedTime     runtime = new ElapsedTime();

    @Override
    public void runOpMode() {

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status: ", "Ready to Deploy!");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        ////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////// LANDING //////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////

        // Step through each leg of the path, ensuring that the Auto mode has not been stopped along the way

        // Release lock on lander
        robot.lock.setPosition(1); // Assuming position 1 is open -- THIS NEEDS TESTED

        // Lower robot to level with playing field
        robot.lift.setPower(0.25); //If having to run against gravity
        //robot.lift.setPower(0); //If gravity is optimal speed
        //robot.lift.setPower(-0.25); //If aiding gravity
        runtime.reset();
        while(opModeIsActive() && (runtime.seconds() <= 1)){ //1 second -- NEEDS TESTED
            telemetry.addData("Unfolding:", "Time: %2.5f S Elapsed", runtime.seconds());
        }
        robot.lift.setPower(0);

        // Detach from lander
        robot.left();
        //robot.right();
        runtime.reset();
        while(opModeIsActive() && (runtime.seconds() <= 0.5)){
            telemetry.addData("Detach", "Time: %2.5f S Elapsed", runtime.seconds());
        }
        robot.stop();

        ////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////// SAMPLING //////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////

        // Drive towards to sample
        robot.forward();
        runtime.reset();
        while(opModeIsActive() && (runtime.seconds() <= 0.8)) {
            telemetry.addData("Arriving", "Time: %2.5f S Elapsed", runtime.seconds());
        }
        robot.stop();

        robot.sample.setPosition(0.5);

//// Add color logic here at a later time

        ///////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////// Move Towards Depot ///////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        robot.left();
        runtime.reset();
        while (opModeIsActive ()  &&  (runtime.seconds() <= 1.3))    {
            telemetry.addData ("Positioning1", "Time: %2.5f S Elapsed", runtime.seconds());
            telemetry.update ();
        }
        robot.stop();

        robot.spinLeft();
        while (opModeIsActive ()  &&  (runtime.seconds() <= 0.75)) {
            telemetry.addData("Turning", "Time: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        robot.stop();

        robot.forward();
        while (opModeIsActive ()   &&  (runtime.seconds()   <= 4)) {
            telemetry.addData("Positioning1", "Time: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        robot.stop();

        robot.marker.setPosition(0);

        robot.backRight();
        while (opModeIsActive ()  &&  (runtime.seconds() <= 0.5)) {
            telemetry.addData("Diagonal to Crater", "Time: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        robot.stop();

        robot.backward();
        while (opModeIsActive ()  &&  (runtime.seconds() <= 5)) {
            telemetry.addData("Back From Crater", "Time: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        robot.stop();

    }
}