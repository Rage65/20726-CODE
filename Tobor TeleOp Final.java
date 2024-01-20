package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "ToborTeleopFinalUSEME (Blocks to Java)")
public class ToborTeleopFinalUSEME extends LinearOpMode {

  private DcMotor fl;
  private DcMotor fr;
  private DcMotor bl;
  private DcMotor br;
  private DcMotor viper;
  private CRServo planefly;
  private DcMotor armtilt;
  private CRServo intake;

  double arm_tilt_speed;
  double CurrMode_Speed_;
  double viper_speed;

  /**
   *runOpMode...
   */
  @Override
  public void runOpMode() {
    fl = hardwareMap.get(DcMotor.class, "fl");
    fr = hardwareMap.get(DcMotor.class, "fr");
    bl = hardwareMap.get(DcMotor.class, "bl");
    br = hardwareMap.get(DcMotor.class, "br");
    viper = hardwareMap.get(DcMotor.class, "viper");
    planefly = hardwareMap.get(CRServo.class, "plane fly");
    armtilt = hardwareMap.get(DcMotor.class, "arm tilt");
    intake = hardwareMap.get(CRServo.class, "intake");
// Mcinly i
    config();
    waitForStart();
    if (opModeIsActive()) {.
      while (opModeIsActive()) {
        viper_speed_togle();
        launch_drone();
        intake2();
        arm_slide();
        Motor_speed_function();
        OldDrive();
        arm__tilt_controler();
        ARM_TILT_SPEED_CHANGER();
        telemetry.addData("Speed", CurrMode_Speed_);
        telemetry.update();
      }
    }
  }

  /**
   *arm tilt speed change based on gamepad
   */
  private void ARM_TILT_SPEED_CHANGER() {
    int arm_tilt_speed_clicks;

    if (gamepad1.right_bumper) {
      arm_tilt_speed_clicks += 1;
    } else if (arm_tilt_speed_clicks == 0) {
    } else if (arm_tilt_speed_clicks == 1) {
      arm_tilt_speed = 0.3;
    } else if (arm_tilt_speed_clicks == 2) {
      arm_tilt_speed = 0.7;
    } else if (arm_tilt_speed_clicks == 3) {
      arm_tilt_speed_clicks = 1;
    }
  }

  /**
   * Describe this function...
   */
  private void OldDrive() {
    float vertical;
    float horizontal;
    float Pivot;

    vertical = gamepad1.left_stick_y;
    horizontal = gamepad1.left_stick_x;
    Pivot = -gamepad1.right_stick_x;
    fl.setPower(CurrMode_Speed_ * (Pivot + vertical + horizontal));
    fr.setPower(CurrMode_Speed_ * (-Pivot + (vertical - horizontal)));
    bl.setPower(CurrMode_Speed_ * (Pivot + (vertical - horizontal)));
    br.setPower(CurrMode_Speed_ * (-Pivot + vertical + horizontal));
  }

  /**
   * Describe this function...
   */
  private void arm_slide() {
    double viprt;

    viprt = viper_speed * gamepad2.right_stick_y;
    viper.setPower(viprt);
  }

  /**
   * Describe this function...
   */
  private void launch_drone() {
    if (gamepad2.a) {
      planefly.setPower(-1);
      sleep(600);
      planefly.setPower(0);
    }
  }

  /**
   * Describe this function...
   */
  private void arm_tilt() {
    armtilt.setPower(0.17);
    sleep(1000);
    armtilt.setPower(0.1);
  }

  /**
   * Describe this function...
   */
  private void arm__tilt_controler() {
    double idk;

    idk = 0.3 * gamepad2.left_stick_y;
    armtilt.setPower(idk);
  }

  /**
   *sets intake based on player input 
   */
  private void intake2() {
    if (gamepad2.right_bumper) {
      intake.setPower(1);
    } else if (gamepad2.left_bumper) {
      intake.setPower(-1);
    } else {
      intake.setPower(0);
    }
  }

  /**
   * changes drive chan speed by click if gamepad 1 right bumper
   */
  private void Motor_speed_function() {
    int speed_clicked;

    if (gamepad1.right_bumper) {
      if (2 == speed_clicked) {
        speed_clicked = 0;
      } else {
        speed_clicked += 1;
      }
    }
    if (0 == speed_clicked) {
      CurrMode_Speed_ = 0.5;
    } else {
      CurrMode_Speed_ = 1;
    }
  }

  /**
   * changes viper slide speed for hangign
   */
  private void viper_speed_togle() {
    int vip_speed_clicker;

    if (gamepad2.y) {
      if (2 <= vip_speed_clicker) {
        vip_speed_clicker = 0;
      } else {
        vip_speed_clicker += 1;
      }
    }
    if (0 == vip_speed_clicker) {
      viper_speed = 0.3;
    } else {
      viper_speed = 1;
    }
  }

  /**
   *This is just the config.
   */
  private void config() {
    arm_tilt_speed = 0.3;
    CurrMode_Speed_ = 0.5;
    armtilt.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    fr.setDirection(DcMotor.Direction.REVERSE);
    br.setDirection(DcMotor.Direction.REVERSE);
    viper.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
  }
}