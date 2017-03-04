package org.usfirst.frc.team1523.robot.commands;

import org.usfirst.frc.team1523.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoTurn extends Command {

	private double angle=0;
	
	private boolean finished = false;
	
    public AutoTurn(double deltaAngle) {
        requires(Robot.drive);
        this.angle=deltaAngle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.gyro.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(angle>0){
    		if(Robot.gyro.getAngle()<angle){
    			Robot.drive.drive(0, 0, 0.3);
    		}else{
    			this.finished = true;
    		}
    	}else{
    		if(Robot.gyro.getAngle()>angle){
    			Robot.drive.drive(0, 0, -0.3);
    		}else{
    			this.finished=true;
    		}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drive.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
