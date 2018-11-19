package com.hotels.tajawal.dubaihotels.util;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.Transformation;

import static android.R.attr.animation;

/**
 * Utility Class for animation
 */

public class ViewAnimationUtilities {

    /**
     * Expand the given view in this duration.
     *
     * @param view     the view to be expanded.
     */
    public static void expandView(final View view) {
        view.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final int targetHeight = view.getMeasuredHeight();

        view.getLayoutParams().height = 0;
        view.setVisibility(View.VISIBLE);
        Animation transformation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                view.getLayoutParams().height = interpolatedTime == 1
                        ? ViewGroup.LayoutParams.WRAP_CONTENT
                        : (int) (targetHeight * interpolatedTime);
                view.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        transformation.setDuration((int)(targetHeight / view.getContext().getResources().getDisplayMetrics().density));

        Animation opacity = new AlphaAnimation(0.0f, 1.0f);
        opacity.setDuration(800);

        AnimationSet animSet = new AnimationSet(true);
        animSet.addAnimation(transformation);
        animSet.addAnimation(opacity);

        view.startAnimation(animSet);
    }

    /**
     * Collapse the given view in this duration.
     *
     * @param view     the view to be collapsed.
     */
    public static void collapseView(final View view) {
        final int initialHeight = view.getMeasuredHeight();

        Animation transformation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    view.setVisibility(View.GONE);
                } else {
                    view.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                    view.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        transformation.setDuration((int)(initialHeight /view.getContext().getResources().getDisplayMetrics().density));

        Animation opacity = new AlphaAnimation(1.0f, 0.0f);
        opacity.setDuration(800);

        AnimationSet animSet = new AnimationSet(true);
        animSet.addAnimation(opacity);
        animSet.addAnimation(transformation);

        view.startAnimation(animSet);
    }

    /**
     * Rotate the given view.
     *
     * @param view       view toDegree be rotated
     * @param fromDegree float: Rotation offset to apply at the start of the animation.
     * @param toDegree   float: Rotation offset to apply at the end of the animation.
     * @param duration   long: Duration in milliseconds
     */
    public static void rotateView(View view, int fromDegree, int toDegree, int duration) {
        RotateAnimation rotate = new RotateAnimation(fromDegree, toDegree, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(duration);
        rotate.setInterpolator(new LinearInterpolator());
        view.startAnimation(rotate);
    }
}
