package noname.hw5vs6;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.facebook.stetho.Stetho;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnadd;
    Button btnsave;
    EditText edt_name;
    EditText edt_phone;
    Switch schChange;
    boolean checked = false;
    ArrayList<User> listUser = new ArrayList<User>();
    DataHelper helper = new DataHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnadd = (Button) findViewById(R.id.btnAdd);
        schChange = (Switch) findViewById(R.id.stchChange);
        Stetho.initializeWithDefaults(this);
        setRecyclerView();
        ControlButton();
    }

    private void setRecyclerView() {
        listUser=helper.getAll();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rc_users);
        AdapterRecyler adapter = new AdapterRecyler(listUser);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void ControlButton() {

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.fillinfor);
                dialog.show();
                btnsave = (Button) dialog.findViewById(R.id.btnSave);
                Button btnCancel = (Button) dialog.findViewById(R.id.btnCancel);
                btnsave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        edt_name = (EditText) dialog.findViewById(R.id.edtName);
                        edt_phone = (EditText) dialog.findViewById(R.id.edtPhone);
                        String name = edt_name.getText().toString();
                        String phone = edt_phone.getText().toString();
                        helper.InsertUser(new User(name, phone));
                        setRecyclerView();
                    }
                });
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });
        schChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (checked == false) {
                    setGridview();
                    checked = true;
                } else {
                    setRecyclerView();
                    checked=false;
                }
            }
        });

    }

    private void setGridview() {
        listUser=helper.getAll();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rc_users);
        AdapterRecyler adapter = new AdapterRecyler(listUser);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }
}
