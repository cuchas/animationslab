package br.com.cucha.animationslab;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.transition.Transition;
import android.support.transition.TransitionValues;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by eduardo on 9/28/17.
 */

public class TranslationYTransition extends Transition {
    private static final String PROPNAME = "translationY";

    @Override
    public void captureStartValues(@NonNull TransitionValues transitionValues) {
        transitionValues.values.put(PROPNAME, transitionValues.view.getTranslationY());
    }

    @Override
    public void captureEndValues(@NonNull TransitionValues transitionValues) {
        transitionValues.values.put(PROPNAME, transitionValues.view.getTranslationY());
    }

    @Nullable
    @Override
    public Animator createAnimator(@NonNull ViewGroup sceneRoot, @Nullable TransitionValues startValues, @Nullable TransitionValues endValues) {

        if(startValues == null || endValues == null) return null;

        float start = (float) startValues.values.get(PROPNAME);
        float end = (float) endValues.values.get(PROPNAME);

        if(start == end) return null;

        final View view = endValues.view;
        view.setTranslationY(start);

        ValueAnimator animator = ValueAnimator.ofFloat(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                view.setTranslationY((float) valueAnimator.getAnimatedValue());
            }
        });

        return animator;
    }
}
