package com.example.rodneytressler.week3assessmentkey;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements AccountFragment.CallBack{

    @BindView(R.id.welcome_text)
    protected TextView welcomeText;
    private AccountFragment accountFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        accountFragment = AccountFragment.newInstance();
        accountFragment.setCallBack(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, accountFragment).commit();

    }

    @Override
    public void createAccount(Account account) {
        welcomeText.setText(getString(R.string.welcome, account.getName(), account.getAccountClass()));
        getSupportFragmentManager().beginTransaction().remove(accountFragment).commit();
    }

    @Override
    public void onBackPressed() {
        if(accountFragment.isAdded()){
            super.onBackPressed();
        }else{
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, accountFragment).commit();
        }
    }
}
