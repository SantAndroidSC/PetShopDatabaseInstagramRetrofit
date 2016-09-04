package com.example.administrador.petshopdatabase;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.administrador.petshopdatabase.adapter.PageAdapter;
import com.example.administrador.petshopdatabase.fragments.RVMascotasInstagramFragment;
import com.example.administrador.petshopdatabase.fragments.RecyclerViewFragment;
import com.example.administrador.petshopdatabase.fragments.RecyvlerViewProfileFragment;
import com.example.administrador.petshopdatabase.restAPI.ConstantesRestAPI;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public Intent intent;
    public Toolbar toolbar;
    public TabLayout tabLayout;
    public ViewPager viewPager;

    private String usuarioID;
    private String nomUsuario;
    private String urlUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar)findViewById(R.id.myToolbar);
        tabLayout = (TabLayout) findViewById(R.id.myTab);
        viewPager = (ViewPager)findViewById(R.id.myViewPager);
        setUpViewPager();

        if(toolbar != null){
            setSupportActionBar(toolbar);
        }

        if(getIntent().getExtras() != null){
            Bundle parametros = getIntent().getExtras();
            usuarioID = parametros.getString("usuarioID");
            nomUsuario= parametros.getString("usuario");
            urlUsuario = parametros.getString("usuarioURL");
        }

        if(usuarioID == null){
            usuarioID= ConstantesRestAPI.SELF_USER;
            nomUsuario= ConstantesRestAPI.DEFAULT_USERNAME;
        }
    }

    //para devolver el ID de usuario
    public String getUsuarioID(){
        return usuarioID;
    }
    public String getNomUsuario(){
        return nomUsuario;
    }
    public String getUrlUsuario(){
        return urlUsuario;
    }

    public ArrayList<Fragment> agregarFragments(){
        ArrayList<Fragment>fragments = new ArrayList<>();
        fragments.add(new RecyclerViewFragment());
        fragments.add(new RecyvlerViewProfileFragment());
        fragments.add(new RVMascotasInstagramFragment());
        return fragments;
    }

    public void setUpViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(),agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.home_48);
        tabLayout.getTabAt(1).setIcon(R.drawable.dog_48);
        tabLayout.getTabAt(2).setIcon(R.drawable.dog_48);
    }
    /*
    public void callTopFive(){
        intent = new Intent(MainActivity.this, TopFiveActivity.class);
        startActivity(intent);
    }
    */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.mAbout:
                Intent intent = new Intent(this, BiographyActivity.class);
                startActivity(intent);
                break;
            case R.id.mContact:
                Intent i = new Intent(this, MailActivity.class);
                startActivity(i);
                break;
            case R.id.mSettings:
                Intent intent3 = new Intent(this, SettingActivity.class);
                startActivity(intent3);
                break;
            case R.id.action_favorite:
                Intent intent1 = new Intent(this, TopFiveActivity.class);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
