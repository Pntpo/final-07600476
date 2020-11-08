package th.ac.su.cp.speedrecords;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import th.ac.su.cp.speedrecords.Adapter.Useradapter;
import th.ac.su.cp.speedrecords.model.User;
import th.ac.su.cp.speedrecords.DB.Databases;
import th.ac.su.cp.speedrecords.util.AppExecutors;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();

    private RecyclerView mRecycleView;

    protected  void  onResume(){
        super.onResume();

        final AppExecutors executors = new AppExecutors();
        executors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                Databases DB = Databases.getInstance(MainActivity.this);
                final User[] users = DB.UserDao().getAllUsers();

                executors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        Useradapter adapter = new Useradapter(MainActivity.this,users);
                        mRecycleView.setAdapter(adapter);
                    }
                });
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecycleView = findViewById(R.id.user_recycler_view);

    }
}