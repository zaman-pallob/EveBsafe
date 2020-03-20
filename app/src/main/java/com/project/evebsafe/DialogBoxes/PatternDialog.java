package com.project.evebsafe.DialogBoxes;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

import com.project.evebsafe.R;


public class PatternDialog extends Dialog {

    public PatternDialog( Context context) {
        super(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        getWindow().getAttributes().windowAnimations= R.style.DialogTheme;
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT<Build.VERSION_CODES.O){

            getWindow().setType(WindowManager.LayoutParams.TYPE_PHONE);

        }else {

            getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
        }
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        setContentView(R.layout.patternlockview);
        show();

    }


}
