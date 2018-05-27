package com.example.trong_tai.demo_patternlockview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PatternLockView mPatternLockView;
    private Button mBtnAddress;
    private List<String> mList = new ArrayList<>();
    private List<Integer> mListPointInteger = new ArrayList<>();
    private static int mOriginalDirection = 2;
    private static int mSizeOfMatrix = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Init();

        mPatternLockView.addPatternLockListener(new PatternLockViewListener() {
            @Override
            public void onStarted() {

            }

            @Override
            public void onProgress(List<PatternLockView.Dot> progressPattern) {
                mList.add(PatternLockUtils.patternToString(mPatternLockView, progressPattern));

            }

            @Override
            public void onComplete(List<PatternLockView.Dot> pattern) {
                if(mList.size() != 0){
                    for (int i = 0; i < mList.size(); i++){
                        if (i == 0){
                            mListPointInteger.add(Integer.parseInt(mList.get(i)));
                        }
                        else{
                            mListPointInteger.add(Integer.parseInt(mList.get(i).substring((mList.get(i-1)).length())));
                        }
                    }
                }
//                else{
//                    Log.e("abc","wrong");
//                }

//                if(mListPointInteger.size() != 0){
//                    for (int i = 0; i < mListPointInteger.size(); i++){
//                        if (i == 0){
//                            Log.e("abc", ""+ mListPointInteger.get(i));
//                        }
//                        else{
//                              Log.e("abc", "" + mListPointInteger.get(i));
//                        }
//                    }
//                }else{
//                    Log.e("abc","wrong");
//                }
                //TODO everything here
            }

            @Override
            public void onCleared() {

            }
        });

        mBtnAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListPointInteger.size() == 0){
                    Toast.makeText(getApplicationContext(), "wrong drawing", Toast.LENGTH_LONG).show();
                    return;
                }
                if (mListPointInteger.get(0) != 0){
                    Toast.makeText(getApplicationContext(), "wrong drawing", Toast.LENGTH_LONG).show();
                    mListPointInteger.removeAll(mListPointInteger);
                    mList.removeAll(mList);
                    return;
                }
//                if (mListPointInteger.size() == 0){
//                    Toast.makeText(getApplicationContext(), "Please! Draw anything!", Toast.LENGTH_LONG).show();
//                }else{
//                    for (int i = 0; i <= mListPointInteger.size() - 2; i++){
//                        Log.e("way: ", GetTurn(mListPointInteger.get(i + 1), mListPointInteger.get(i)) + ", " +
//                                GetConnet(mListPointInteger.get(i + 1), mListPointInteger.get(i)) + ", " +
//                                Getdirection(mListPointInteger.get(i + 1), mListPointInteger.get(i)));
//                    }
//                    mListPointInteger.removeAll(mListPointInteger);
//                    mList.removeAll(mList);
//                }
                Address(mListPointInteger);
            }
        });
    }

    public void Init(){
        mBtnAddress = (Button) findViewById(R.id.btn_address);
        mPatternLockView = (PatternLockView) findViewById(R.id.pattern_lock_view);
    }

    public int GetConnet(int nextDirection, int currentDirection){
        switch (Math.abs(nextDirection - currentDirection)){
            case 1:
                return 45;
            case 2:
                return 90;
            case 3:
                return 45;
            case 5:
                return 45;
            case 6:
                return 90;
            case 7:
                return 45;
                default:
                    return 0;
        }
    }

    public String GetTurn(int nextDirection, int currentDirection){
        if ((nextDirection >= currentDirection)){
            if (((Math.abs(nextDirection - currentDirection) >= 4) &&
                    (Math.abs(nextDirection - currentDirection) <= 6)) ||
                    (Math.abs(nextDirection - currentDirection) >= 0) &&
                            (Math.abs(nextDirection - currentDirection) <= 2)){
                return "right";
            }
            return "left";
        }else{
            if (((Math.abs(nextDirection - currentDirection) >= 4) &&
                    (Math.abs(nextDirection - currentDirection) <= 6)) ||
                    (Math.abs(nextDirection - currentDirection) >= 0) &&
                            (Math.abs(nextDirection - currentDirection) <= 2)){
                return "left";
            }
            return "right";
        }
    }

    public String Getdirection(int nextDirection, int currentDirection){
        if((currentDirection <= 1) || (currentDirection >= 5)){
            if((Math.abs(nextDirection - currentDirection) <= 2) ||
                    (Math.abs(nextDirection - currentDirection) >= 6))
                return "ahead";
        }else{
            if((Math.abs(nextDirection - currentDirection) <= 2))
                return "ahead";
        }
        return "back";
    }

    public void Address(List<Integer> listPoint){
        int mCurrentPoint = 0;
        int mNextPoint = 0;
        int mCurrentDirection = mOriginalDirection;
        int mNextDirection = mOriginalDirection;
        for (int i = 0; i < listPoint.size(); i++){
            if(!checkNumber(mCurrentPoint, mNextPoint)) return;
        }
        for (int i = 0; i < listPoint.size() - 1; i++){
            mCurrentPoint = listPoint.get(i);
            mNextPoint = listPoint.get(i+1);
            if ((mNextPoint - mCurrentPoint) == 1){ // direction 2
                mNextDirection = 2;
                //TODO address
//                Log.e("way: ", GetTurn(mNextDirection, mCurrentDirection) + ", " +
//                        GetConnet(mNextPoint, mCurrentPoint) + ", " +
//                        Getdirection(mNextPoint, mCurrentPoint));
                GetWay(mCurrentDirection, mNextDirection);
                mCurrentDirection = mNextDirection;
            }
            if ((mNextPoint - mCurrentPoint) == 5){ // direction 4
                mNextDirection = 4;
                //TODO address
                GetWay(mCurrentDirection, mNextDirection);
                mCurrentDirection = mNextDirection;
            }
            if ((mCurrentPoint - mNextPoint) == 1){ // direction 6
                mNextDirection = 6;
                //TODO address
                GetWay(mCurrentDirection, mNextDirection);
                mCurrentDirection = mNextDirection;
            }
            if ((mCurrentPoint - mNextPoint) == 5){ // direction 0
                mNextDirection = 8;
                //TODO address
                GetWay(mCurrentDirection, mNextDirection);
                mCurrentDirection = mNextDirection;
            }
            if (((mCurrentPoint + 5) + (mCurrentPoint + 1) - mCurrentPoint) == mNextPoint){ // direction 3
                mNextDirection = 3;
                //TODO address
                GetWay(mCurrentDirection, mNextDirection);
                mCurrentDirection = mNextDirection;
            }
            if (((mCurrentPoint - 5) + (mCurrentPoint + 1) - mCurrentPoint) == mNextPoint){ // direction 1
                mNextDirection = 1;
                //TODO address
                GetWay(mCurrentDirection, mNextDirection);
                mCurrentDirection = mNextDirection;
            }
            if (((mCurrentPoint - 5) + (mCurrentPoint - 1) - mCurrentPoint) == mNextPoint){ // direction 7
                mNextDirection = 7;
                //TODO address
                GetWay(mCurrentDirection, mNextDirection);
                mCurrentDirection = mNextDirection;
            }
            if (((mCurrentPoint + 5) + (mCurrentPoint - 1) - mCurrentPoint) == mNextPoint){ // direction 5
                mNextDirection = 5;
                //TODO address
                GetWay(mCurrentDirection, mNextDirection);
                mCurrentDirection = mNextDirection;
            }
        }
        mList.removeAll(mList);
        mListPointInteger.removeAll(mListPointInteger);
    }

    public void GetWay(int currentDirection, int nextDirection){
        Log.e("way: ", GetTurn(nextDirection, currentDirection) + ", " +
            GetConnet(nextDirection, currentDirection) + ", " +
            Getdirection(nextDirection, currentDirection));
    }

    public boolean checkNumber(int n, int m){
        if(m == n+1){
            if((m/5) == 0){ // chia cho kich thuoc ma tran
                return false;
            }
        }
        if(Math.abs(m - n) > 6) // kich thuoc ma tran  + 1
            return false;
        return true;
    }
}
