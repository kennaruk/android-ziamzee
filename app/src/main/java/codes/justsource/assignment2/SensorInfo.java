package codes.justsource.assignment2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.util.Log;

public class SensorInfo {
    private float accX, accY, accZ;
    private final int SHAKE_THRESHOLD = 15;

    private boolean isShowingDialog;
    /* Dialog */

    public SensorInfo() {
        accX = 0;
        accY = 0;
        accZ = 0;
        isShowingDialog = false;
    }

    public void setAcc(float accX, float accY, float accZ) {
        this.accX = accX;
        this.accY = accY;
        this.accZ = accZ;
    }

    public boolean isShake() {
        return false;
    }

    public void checkZiamZee(final AnimationDrawable mAnimation, Context context) {

        //isShaking right now
        if(Math.abs(accX) > SHAKE_THRESHOLD || Math.abs(accY) > SHAKE_THRESHOLD || Math.abs(accZ) > SHAKE_THRESHOLD ) {
            if(!isShowingDialog) {
                isShowingDialog = true;

                mAnimation.start();

                /* Random Ziamzee */
                final int ziamzeeIndex = (int) Math.round( Math.random()* (Ziamzee.getZiamzeeLength()-1) );

                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setTitle("Your ziamzee is number: "+ (ziamzeeIndex+1));
                dialog.setIcon(android.R.drawable.btn_star_big_on);
                dialog.setMessage(Ziamzee.getZiamzee(ziamzeeIndex));
                dialog.setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        isShowingDialog = false;
                        mAnimation.stop();
                    }
                });
                dialog.show();
            }
        }
    }

}
