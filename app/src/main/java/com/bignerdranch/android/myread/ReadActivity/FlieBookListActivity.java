package com.bignerdranch.android.myread.ReadAtovity;
import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.android.myread.Permission.PermissionUtils;
import com.bignerdranch.android.myread.R;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlieBookListActivity extends AppCompatActivity {

    ListView listView;
    TextView textView;
    //记录当前父文件夹
    File currentParent;
    //记录当前路经下的所有文件
    File[] currentFiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flie_book_list);
        listView = (ListView) findViewById(R.id.list_flie_txt);
        textView = (TextView) findViewById(R.id.tv_file_textview);
        //获取系统SD卡目录
        File root = new File("/mnt");
        if (root.exists()) {
            currentParent = root;
            currentFiles = root.listFiles();
            //使用当前目录下的全部文件，来填充ListView
            inflateListView(currentFiles);
        }
        //为ListView的列表项单击事件绑定监听器
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //用户单击了文件，直接返回
                if (currentFiles[position].isFile()) return;
                //获取单击文件夹下的所有文件
                File[] tmp = currentFiles[position].listFiles();
                if (tmp == null || tmp.length == 0) {
                    Toast.makeText(FlieBookListActivity.this, "Not File", Toast.LENGTH_LONG).show();
                } else {
                    //获取用户单击的列表项对应的文件夹，设为当前父文件夹
                    currentParent = currentFiles[position];
                    //保存当前父文件夹内的所有文件和文件夹
                    currentFiles = tmp;
                    //再次更新ListView
                    inflateListView(currentFiles);
                }
            }
        });
        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        String[] permissionsNeedCheck = PermissionUtils.checkPermission(this, permissions);
        if (permissionsNeedCheck != null) {
            PermissionUtils.grantPermission(this, permissionsNeedCheck, PermissionUtils.REQUEST_GRANT_READ_AND_WRITE_EXTERNAL_STORAGE_PERMISSIONS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PermissionUtils.REQUEST_GRANT_READ_AND_WRITE_EXTERNAL_STORAGE_PERMISSIONS:
                if (PermissionUtils.isGrantedAllPermissions(permissions, grantResults)) {
                    Toast.makeText(this, "你允许了全部授权", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "你拒绝了部分权限，可能造成程序运行不正常", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }
    public boolean openfile(String s){
        File file = new File(s);
        if(!file.exists()) return true;
        /* 取得扩展名 */
        String end=file.getName().substring(file.getName().lastIndexOf(".") + 1,file.getName().length()).toLowerCase();
        if (end.equals())
    }

    private void inflateListView(File[] files) {
        //创建List集合，元素是Map
        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < files.length; i++) {
            Map<String, Object> listItem = new HashMap<String, Object>();
            //如果当前File是文件夹，使用文件夹图标，其它使用文件图标
            if (files[i].isDirectory()) {
                listItem.put("icon", R.mipmap.ic_launcher_wenjian);
            } else {
                listItem.put("icon", R.mipmap.ic_launcher_txt);
            }
            listItem.put("file", files[i].getName());
            //添加List项
            listItems.add(listItem);
        }
        //创建
        SimpleAdapter simpleAdapter = new SimpleAdapter(this
                , listItems, R.layout.flie_list_item
                , new String[]{"icon", "file"}
                , new int[]{R.id.icon, R.id.filename});
        //为ListView设置Adapter
        listView.setAdapter(simpleAdapter);
        try {
            textView.setText("CurrentPath:" + currentParent.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onBackPressed() {
        try {
            if (!"/mnt".equals(currentParent.getCanonicalPath())) {
                //获取上一层目录
                currentParent = currentParent.getParentFile();
                //列出当前目录下的所有文件
                currentFiles = currentParent.listFiles();
                //再次更新ListView
                inflateListView(currentFiles);
            }else {
                finish();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}