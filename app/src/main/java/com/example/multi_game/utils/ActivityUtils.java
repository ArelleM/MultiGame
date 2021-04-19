package com.example.multi_game.utils;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.multi_game.R;
import com.example.multi_game.activity.CreatePlayerActivity;
import com.example.multi_game.activity.SplashActivity;

public class ActivityUtils {

    public static void launchActivity(AppCompatActivity activity, Class className){
        Intent intent = new Intent (activity, className);
        launchActivity(activity, intent,true, true);
    }

    public static void launchActivity(AppCompatActivity activity, Class className, boolean isFinish,boolean isSlide){
        Intent intent = new Intent (activity, className);
        launchActivity(activity, intent,isFinish, isSlide);
    }

    public static void launchActivity(AppCompatActivity activity, Intent intent, boolean isFinish,boolean isSlide){
        activity.startActivity(intent);
        activity.overridePendingTransition( isSlide ? R.anim.slide_in_from_right : R.anim.fade_in,R.anim.fade_out);
        if (isFinish) {
            activity.finish();
        }
    }

    public static void addFragmentToFragment(Fragment parentFragment, Fragment fragment, int frameId) {
        FragmentTransaction transaction = parentFragment.getChildFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        transaction.replace(frameId, fragment, "tag");
        transaction.commit();
    }
    public static void addFragmentToFragment(Fragment parentFragment, Fragment fragment, int frameId, Bundle bundle) {
        //Bundle bundle = new Bundle();
        //bundle.putInt("score",12);

        FragmentTransaction transaction = parentFragment.getChildFragmentManager().beginTransaction();
        fragment.setArguments(bundle);
        transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        transaction.replace(frameId, fragment, "tag");
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public static void addFragmentToActivity(AppCompatActivity activity, Fragment fragment, int frameId) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        transaction.replace(frameId, fragment, "tag");
        transaction.commit();
    }
}
