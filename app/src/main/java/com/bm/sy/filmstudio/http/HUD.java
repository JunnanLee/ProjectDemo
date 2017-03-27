package com.bm.sy.filmstudio.http;

import android.content.Context;
import android.widget.ImageView;

import com.kaopiz.kprogresshud.KProgressHUD;

/**
 * Created by zanjr
 * on: 2016/8/27
 * time: 23:10
 * description:
 * open source:
 */
public class HUD {

    private volatile static HUD instance;

    private boolean isShown;

    private KProgressHUD hud;

    private HUD() {
    }

    public static HUD getInstance() {
        if (instance == null) {
            synchronized (HUD.class) {
                if (instance == null) {
                    instance = new HUD();
                }
            }
        }
        return instance;
    }

    public void show(Context ctx) {
        if (!isShown) {
            hud = KProgressHUD.create(ctx)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setLabel("让我静静的刷一会")
                    .setCancellable(false)
                    .setAnimationSpeed(2)
                    .setDimAmount(0.5f)
                    .show();
            isShown = true;
        }
    }

    public void show(Context ctx, int res, String msg) {
        if (!isShown) {
            ImageView imageView = new ImageView(ctx);

            imageView.setImageResource(res);
            hud = KProgressHUD.create(ctx)
                    .setCustomView(imageView)
                    .setLabel(msg)
                    .setAnimationSpeed(2)
                    .setDimAmount(0.5f)
                    .show();
            isShown = true;
        }
    }

    public void dismiss() {
        if (hud != null) {
            hud.dismiss();
            isShown = false;
        }
    }

}
